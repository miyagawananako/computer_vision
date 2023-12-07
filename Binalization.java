
import java.awt.Color;


public class Binalization {

	public static MyImage execute(MyImage input, double S) {

		MyImage output = new MyImage(input.width, input.height);
	
		for(int i = 0; i < input.height; i++) {
			for(int j = 0; j < input.width; j++) {
				
				Color color1 = input.getColor(j, i);

                int original_r = color1.getRed();
                int original_g = color1.getGreen();
                int original_b = color1.getBlue();

                int r, g, b;
                if (original_r + original_g + original_b > S) {  // white
                    r = 255;
                    g = 255;
                    b = 255;
                }
                else {
                    r = 0;
                    g = 0;
                    b = 0;
                }

                Color color2 = new Color(r, g, b);

				output.setColor(j, i, color2);
			}
		}
		
		return output;
	}

}

