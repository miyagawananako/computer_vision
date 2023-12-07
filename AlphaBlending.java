
import java.awt.*;


public class AlphaBlending {

	static MyImage execute(MyImage input1, MyImage input2) {

		final double ALPHA = 0.2;
		int width1 = input1.width;
		int width2 = input2.width;
		int height1 = input1.height;
		int height2 = input2.height;
	
		int width  = (width1  > width2)  ? width1  : width2;
		int height = (height1 > height2) ? height1 : height2;
	
		MyImage output = new MyImage(width, height);

		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				boolean isProcessed = false;
		
				if(i < height1 && j < width1) {

					Color color1 = input1.getColor(j, i);

					if (!(i < height2 && j < width2)) {
						output.setColor(j, i, color1);  //itot
						isProcessed = true;
					}

					else {
						Color color2 = input2.getColor(j, i);
						Color color3 = new Color(
							(int)(ALPHA * color1.getRed() + (1.0 - ALPHA) * color2.getRed()),
							(int)(ALPHA * color1.getGreen() + (1.0 - ALPHA) * color2.getGreen()),
							(int)(ALPHA * color1.getBlue() + (1.0 - ALPHA) * color2.getBlue())
						);
						output.setColor(j, i, color3);  // blending(itot & ochatop)
						isProcessed = true;
					}
				}

				if(i < height2 && j < width2) {
					if(isProcessed == false) {
						Color color2 = input2.getColor(j, i);
						output.setColor(j, i, color2);  // ochatop
					}
				}
			}
		}

		return output;

	}

}
