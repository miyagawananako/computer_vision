import java.awt.Color;

public class GetGreenIndex {

  public static int[][] execute(MyImage input) {

    int[][] index = {{0, 0}, {0, 0}};
    boolean isStarted = false;

    for(int i = 0; i < input.height; i++) {
			for(int j = 0; j < input.width; j++) {
				
				Color color1 = input.getColor(j, i);

        int r = color1.getRed();
        int g = color1.getGreen();
        int b = color1.getBlue();

        if (r <= 50 && g >= 200 && b <= 50) {  //green(0, 255, 0)から約50幅を持たせた
          if (!isStarted) {  // 最初の緑indexが格納される
            index[0][0] = j;
            index[0][1] = i;
            isStarted = true;
          }
          else {  // 最後の緑indexが格納される
            index[1][0] = j;
            index[1][1] = i;
          }
        }

      }
    }
    System.out.println("greenindex[0][0]: " + index[0][0]);
		System.out.println("greenindex[0][1]: " + index[0][1]);
		System.out.println("greenindex[1][0]: " + index[1][0]);
		System.out.println("greenindex[1][1]: " + index[1][1]);
    return index;
  }

}