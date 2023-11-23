import java.awt.Color;

public class Mosaic {

  public static MyImage execute(MyImage input, MyImage input_bi, Color color) {

		MyImage output = new MyImage(input.width, input.height);

    int step = input.width / 30; //適当に決めた
	
		for(int i = 0; i < input.height - step; i = i + step) {
			for(int j = 0; j < input.width - step; j = j + step) {
				
        int r_sum = 0;
        int g_sum = 0;
        int b_sum = 0;

        for (int k = 0; k < step; k++) {
          for (int l = 0; l < step; l++){
            Color color1 = input.getColor(j + k, i + l);
            if (color1.getRed() + color1.getGreen() + color1.getBlue() > 255 * 3 - 20){ //白なら
              r_sum += color.getRed();
              g_sum += color.getGreen();
              b_sum += color.getBlue();
            }
            else {
              r_sum += color1.getRed();
              g_sum += color1.getGreen();
              b_sum += color1.getBlue();
            }
          }
        }

        int r, g, b;
        r = r_sum / (step * step);
        g = g_sum / (step * step);
        b = b_sum / (step * step);

        /*if (input_bi.getRed() == 0) {

        }*/
        Color color2 = new Color(r, g, b);
        
        try {
          for (int k = 0; k < step; k++) {
            for (int l = 0; l < step; l++){
				      output.setColor(j + k, i + l, color2);
            }
          }
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
          //
        }
			}
		}
		
		return output;
	}


}