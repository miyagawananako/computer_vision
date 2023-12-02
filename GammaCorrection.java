
import java.awt.Color;
import java.lang.Math;

public class GammaCorrection {

	public static MyImage execute(MyImage input) {

		MyImage output = new MyImage(input.width, input.height);
	
		for(int i = 0; i < input.height; i++) {
			for(int j = 0; j < input.width; j++) {
				
				Color color1 = input.getColor(j, i);

				// GAMMA > 0.0
				// Colors with higher gamma will be stronger, colors with lower gamma will be weaker.
        double GAMMA_R = 2.0;
        double GAMMA_G = 0.8;
				double GAMMA_B = 0.8;

			  int r = (int)(255.0 * Math.pow(color1.getRed() / 255.0, 1.0 / GAMMA_R));
				int g = (int)(255.0 * Math.pow(color1.getGreen() / 255.0, 1.0 / GAMMA_G));
				int b = (int)(255.0 * Math.pow(color1.getBlue() / 255.0, 1.0 / GAMMA_B));

				Color color2 = new Color(r, g, b);

				output.setColor(j, i, color2);
			}
		}
		
		return output;
	}

}

