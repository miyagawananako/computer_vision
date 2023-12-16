import java.awt.Color;
import java.lang.Math;

public class Posterize {

  public static MyImage execute(MyImage input) {

    MyImage output = new MyImage(input.width, input.height);

    for(int i = 0; i < input.height; i++) {
			for(int j = 0; j < input.width; j++) {

				Color color1 = input.getColor(j, i);

        int r = color1.getRed();
        int g = color1.getGreen();
        int b = color1.getBlue();

        r = (int)(Math.round(r / 100))*100;
        g = (int)(Math.round(g / 100))*100;
        b = (int)(Math.round(b / 100))*100;

        Color color2 = new Color(r, g, b);

				output.setColor(j, i, color2);

      }
    }

    return output;

  }

}