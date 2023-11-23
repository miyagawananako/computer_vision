import java.awt.Color;

public class ClubUnreality {

  public static MyImage execute(MyImage input1, MyImage input1_bi, MyImage input2, MyImage input2_bi) {

    //int width_output = 1280;小さかった
    //int height_output = 720;

    int width_output = input1.width;
    int height_output = input1.height;

		MyImage output = new MyImage(width_output, height_output);
	
		for(int i = 0; i < height_output; i++) {
			for(int j = 0; j < width_output; j++) {
				boolean isProcessed = false;
        try {
          
          // desk
          double desk_scale = 0.7;
          int desk_plus_height = 500;

          if (i > desk_plus_height && i < input1.height * desk_scale + desk_plus_height && j > (width_output - input1.width * desk_scale) / 2 && j < (width_output + input1.width * desk_scale) / 2) {
            Color color0 = input1_bi.getColor((int)((j - (width_output - input1.width * desk_scale) / 2) / desk_scale), (int)((i - desk_plus_height) / desk_scale));
            Color color1 = input1.getColor((int)((j - (width_output - input1.width * desk_scale) / 2) / desk_scale), (int)((i - desk_plus_height) / desk_scale));

            // deskを暗く加工する。（関数にしたいね）
            if (color0.getRed() > 0){
              int r = (color1.getRed() - 50 >= 0) ? (int)(color1.getRed() - 50) : 0;
              int g = (color1.getGreen() - 50 >= 0) ? (int)(color1.getGreen() - 50) : 0;
              int b = (color1.getBlue() - 50 >= 0) ? (int)(color1.getBlue() - 50) : 0;

              Color color_desk = new Color(r, g, b);

              output.setColor(j, i, color_desk);
              isProcessed = true;
            }
          }

          // macbook
          double mac_scale = 0.8;
          int mac_plus_height = desk_plus_height - 120;
          if (i > mac_plus_height && i < input2.height * mac_scale + mac_plus_height && j > (width_output - input2.width * mac_scale) / 2 && j < (width_output + input2.width) / 2) {
            Color color2_bi = input2_bi.getColor((int)((j - (width_output - input2.width * mac_scale) / 2) / mac_scale), (int)((i - mac_plus_height) / mac_scale));
            Color color2 = input2.getColor((int)((j - (width_output - input2.width * mac_scale) / 2) / mac_scale), (int)((i - mac_plus_height) / mac_scale));

            if(color2_bi.getRed() == 0){
              output.setColor(j, i, color2);
              isProcessed = true;
            }
          }
          
          // 背景
          if (!isProcessed) {
            Color color_green = new Color(0, 255, 0);
            output.setColor(j, i, color_green);
          }
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
          Color color_green = new Color(0, 255, 0);
          output.setColor(j, i, color_green);
        }

			}
		}
		
		return output;
	}


}