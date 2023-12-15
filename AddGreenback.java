import java.awt.Color;

public class AddGreenback {
    public static MyImage execute (MyImage input, int S) {
        MyImage output = new MyImage(input.width, input.height);

        for(int i = 0; i < input.height; i++) {
			for(int j = 0; j < input.width; j++) {
				
				Color color1 = input.getColor(j, i);

                int original_r = color1.getRed();
                int original_g = color1.getGreen();
                int original_b = color1.getBlue();

                int r, g, b;

                if (S == 0) {  // 背景透過画像のとき
                    if (original_r + original_g + original_b > S) {  // white
                        r = original_r;
                        g = original_g;
                        b = original_b;
                    }
                    else {
                        r = 0;
                        g = 255;
                        b = 0;
                    }
                }

                else {
                    if (original_r + original_g + original_b > S) {
                        r = 0;
                        g = 255;
                        b = 0;
                    }
                    else {
                        r = original_r;
                        g = original_g;
                        b = original_b;
                    }
                }

                Color color2 = new Color(r, g, b);

				output.setColor(j, i, color2);
			}
		}
        return output;
    }

	public static void main(String args[]) {
    String filename_desk = "desk.png";
    MyImage image_desk = JpegFileReader.read(filename_desk);

    String filename_output = "desk.jpg";
    MyImage output = execute(image_desk, 0);
    JpegFileWriter.write(filename_output, output);
}
}