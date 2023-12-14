import java.awt.Color;

public class Mosaic {

  public static MyImage execute(MyImage input, Color color) {

		MyImage output = new MyImage(input.width, input.height);

    int step = input.width / 20; //モザイクの粒度、適当に決めた
	
		for(int i = 0; i < input.height; i = i + step) {
			for(int j = 0; j < input.width; j = j + step) {
				
        int r_sum = 0;
        int g_sum = 0;
        int b_sum = 0;

        int counter = 0;

        for (int k = 0; k < step; k++) {
          for (int l = 0; l < step; l++){
            try {
              Color color1 = input.getColor(j + k, i + l);
              if (color1.getRed() + color1.getGreen() + color1.getBlue() > 255 * 3 - 20){  //ほぼ白なら背景色を指定
                r_sum += color.getRed();
                g_sum += color.getGreen();
                b_sum += color.getBlue();
              }
              else {
                r_sum += color1.getRed();
                g_sum += color1.getGreen();
                b_sum += color1.getBlue();
              }
              counter += 1;
            } catch (java.lang.ArrayIndexOutOfBoundsException e) {
              break;
            }
          }
        }

        int r, g, b;
        r = r_sum / (counter);
        g = g_sum / (counter);
        b = b_sum / (counter);

        Color color2 = new Color(r, g, b);
        
       
          for (int k = 0; k < step; k++) {
            for (int l = 0; l < step; l++){
              try {
				        output.setColor(j + k, i + l, color2);
              } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                // 行き過ぎたところには画素を置かなくて良い
              }
            }
          }
			}
		}
		
		return output;
	}


}