import java.awt.Color;

/* greenindexが動いてるか確認するためのコード */
public class GetGreenIndexTest {

  public static MyImage execute(MyImage input, int[][] index) {
    MyImage output = new MyImage(input.width, input.height);
	
		for(int i = 0; i < input.height; i++) {
			for(int j = 0; j < input.width; j++) {
				
				Color color1 = input.getColor(j, i);

        int r = color1.getRed();
        int g = color1.getGreen();
        int b = color1.getBlue();

        Color color2 = new Color(0, 0, 0);

        if (i >= index[0][1] && i < index[1][1] && j >= index[0][0] && j < index[1][0]) {
          output.setColor(j, i, color2);
        }
        else {
          output.setColor(j, i, color1);
        }

				
			}
		}
		
		return output;
  }
}