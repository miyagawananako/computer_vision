
import java.awt.Color;


public class AddWindow {

	public static MyImage execute(MyImage input1, MyImage input2, MyImage input3) {

		MyImage output = new MyImage(input1.width, input1.height);
	
		for(int i = 0; i < input1.height; i++) {
			for(int j = 0; j < input1.width; j++) {
        boolean isProcessed = false;
				
				Color color1 = input1.getColor(j, i);

        // window
        double scalex = 1.0;
        double scaley = 0.6;
        
        if (i < input2.height * scaley && j > (input1.width - input2.width * scalex) / 2 && j < (input1.width + input2.width * scalex) / 2) {
          Color color2 = input2.getColor((int)((j - (input1.width - input2.width * scalex) / 2) / scalex), (int)(i / scaley));

          // windowの背景透過 && input1の背景が緑
          if (color2.getRed() + color2.getGreen() + color2.getBlue() < 255 * 3 - 100 && color1.getGreen() > 250){
            output.setColor(j, i, color2);  //windowの色
            isProcessed = true;
          }
        }

        // food
        int food_plus_height = 90;
        if (!isProcessed && i > food_plus_height && i < input3.height + food_plus_height && j > (input1.width - input3.width) / 2 && j < (input1.width + input3.width) / 2){
          Color color3 = input3.getColor((int)(j - (input1.width - input3.width) / 2), i - food_plus_height);
          if (color1.getGreen() > 250){
            output.setColor(j, i, color3);
            isProcessed = true;
          }
        }

        // unreality
        if (!isProcessed) {
          output.setColor(j, i, color1);
        }

        /*Color color2 = new Color(r, g, b);

				output.setColor(j, i, color2);*/
			}
		}
		
		return output;
	}

}

