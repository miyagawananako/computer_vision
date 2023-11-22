
import java.awt.Color;


public class Negative {

	public static MyImage execute(MyImage input) {

		MyImage output = new MyImage(input.width, input.height);
	
		for(int i = 0; i < input.height; i++) {
			for(int j = 0; j < input.width; j++) {
				
				Color color1 = input.getColor(j, i);

				int r = 255 - color1.getRed();
				int g = 255 - color1.getGreen();
				int b = 255 - color1.getBlue();
				Color color2 = new Color(r, g, b);

				output.setColor(j, i, color2);
			}
		}
		
		return output;
	}

}

