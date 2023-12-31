import java.awt.Color;

public class Mosaic {

  public static MyImage execute(MyImage input, int step, int[][] index) {
    return mosaic(input, step, index);
	}

  public static MyImage execute(MyImage input, int step) {
    int[][] index = {{0, 0}, {input.width, input.height}};
    return mosaic(input, step, index);
	}

  public static MyImage mosaic(MyImage input, int step, int[][] index) {

		MyImage output = new MyImage(input.width, input.height);

    //int step = (index[1][0] - index[0][0]) / 20;
	
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
              r_sum += color1.getRed();
              g_sum += color1.getGreen();
              b_sum += color1.getBlue();
              counter += 1;
            } catch (java.lang.ArrayIndexOutOfBoundsException e) {
              break;
            }
          }
        }

        int r, g, b;
        if (counter != 0) { 
          r = r_sum / (counter);
          g = g_sum / (counter);
          b = b_sum / (counter);
        }
        else {
          r = r_sum;
          g = g_sum;
          b = b_sum;
        }

        Color color2 = new Color(r, g, b);
        
       
          for (int k = 0; k < step; k++) {
            for (int l = 0; l < step; l++){
              try {
                if (i + l >= index[0][1] && i + l <= index[1][1] && j + k >= index[0][0] && j + k <= index[1][0]) {
				          output.setColor(j + k, i + l, color2);
                }
                else {
                  output.setColor(j + k, i + l, input.getColor(j + k, i + l));
                }
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