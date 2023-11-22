
import java.awt.*;


public class Tiling {

	static MyImage execute(MyImage input1, MyImage input2) { 

		int width1 = input1.width;
		int width2 = input2.width;
		int height1 = input1.height;
		int height2 = input2.height;
	
		int width = width1 + width2;
		int height = (height1 > height2) ? height1 : height2;
	
		MyImage output = new MyImage(width, height);

		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {

				// input1を左に配置
				if(i < height1 && j < width1) {
					Color color1 = input1.getColor(j, i);
					output.setColor(j, i, color1);
				}

				// input2を右に配置
				else if(i < height2 && j >= width1) {
					Color color2 = input2.getColor(j - width1, i);
					output.setColor(j, i, color2);
				}

				// その他は黒で埋める
				else {
					Color colorBlack = new Color(0, 0, 0);
					output.setColor(j, i, colorBlack);
				}
			}
		}

		return output;

	}

}
