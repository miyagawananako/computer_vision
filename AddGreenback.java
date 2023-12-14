import java.awt.Color;

public class AddGreenback {
	public static void main(String args[]) {
    String filename_desk = "desk.png";
    MyImage image_desk = JpegFileReader.read(filename_desk);

    String filename_output = "desk.jpg";
    MyImage output = new MyImage(image_desk.width, image_desk.height);

		for(int i = 0; i < image_desk.height; i++) {
			for(int j = 0; j < image_desk.width; j++) {
				
				Color color1 = image_desk.getColor(j, i);

                int original_r = color1.getRed();
                int original_g = color1.getGreen();
                int original_b = color1.getBlue();

                int r, g, b;
                int S = 0;
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

                Color color2 = new Color(r, g, b);

				output.setColor(j, i, color2);
			}
		}
    JpegFileWriter.write(filename_output, output);
}
}