
import java.awt.*;


public class Rotation {
	static double SCALEX = 2.0, SCALEY = 2.0;  // 2倍のとき
	
	static MyImage execute(MyImage input) {
		int width1, height1, width2, height2, i, j;
		
		width1 = input.width;
		height1 = input.height;

		// 180度 & 2倍
		//width2 = (int)(input.width * SCALEX);
		//height2 = (int)(input.height * SCALEY);

		// 180度
		//width2 = (int)(input.width);
		//height2 = (int)(input.height);

		// 90度
		width2 = (int)(input.height);
		height2 = (int)(input.width);

		MyImage output = new MyImage(width2, height2);
	
		
		for(i = 0; i < height2; i++) {
			for(j = 0; j < width2; j++) {
				double x1, y1, r, g, b;
				
				x1 = calcX(j, i, width1, height1);
				y1 = calcY(j, i, width1, height1);

				calcRGB(input, output, x1, y1, j, i);

			}
		}

		return output;

	}

	
	static double calcX(int x2, int y2, int width1, int height1) {
		double x = 0.0;
		double xx = 0.0;

		// 180度 & 2倍
		//x = x2 / SCALEX;
		//xx = width1 - 1 - x;

		// 180度
		//x = x2;
		//xx = width1 - 1 - x;

		// 90度
		xx = width1 - 1 - y2;

		if(x < 0.0 || x > (double)width1) {
			System.out.println(x2);
			System.out.println(y2);
			System.out.println("EXIT! x=" + x);
			System.exit(-1);
		}

		return xx;
	}

	
	static double calcY(int x2, int y2, int width1, int height1) {
		double y = 0.0;
		double yy = 0.0;

		// 180度 & 2倍
		//y = y2 / SCALEY;
		//yy = height1 - 1 - y;

		// 180度
		//y = y2;
		//yy = height1 - 1 - y;

		// 90度
		yy = x2;

		if(y < 0.0 || y > (double)height1) {
			System.out.println(x2);
			System.out.println(y2);
			System.out.println("EXIT! y=" + y);
			System.exit(-1);
		}

		return yy;
	}




	static void calcRGB(MyImage input, MyImage output, double x1, double y1, int x2, int y2) {

		int xx = (int)(x1 + 0.5);
		if(xx < 0) xx = 0;
		if(xx >= input.width) xx = input.width - 1;
		int yy = (int)(y1 + 0.5);
		if(yy < 0) yy = 0;
		if(yy >= input.height) yy = input.height - 1;

		Color color = input.getColor(xx, yy);
		int value = color.getRGB();
		output.setColor(x2, y2, color);
	
	}

}
