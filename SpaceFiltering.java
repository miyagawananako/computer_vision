
import java.awt.*;


public class SpaceFiltering {

	static double filter[] = {
		// 0.33333,0,0,0,0.33333,0,0,0,0.33333  // original value
		// 0.11111,0.11111,0.11111,0.11111,0.11111,0.11111,0.11111,0.11111,0.11111  // 方向に対して重みのない平滑化
		0,0,0.33333,0,0.33333,0,0.33333,0,0  // 方向に対して重みのある平滑化
		// -1,0,1,-2,0,2,-1,0,1  // ソーベルフィルタ
		// 0,-1,0,-1,4,-1,0,-1,0  // ラプラシアンフィルタ
		// 0,-1,0,-1,5,-1,-0,-1,0  // シャープ化フィルタ

		// 試行錯誤して編み出したフィルタ ->
		// -1,0,1,-1,0,1,-1,0,1 // ブリューウィットフィルタ、横方向に差分を取り縦方向に平滑化（講義資料より）
		// 0,0,0,-1,0,1,0,0,0  // 横微分フィルタ（参照：https://www.visco-tech.com/newspaper/column/detail19/#:~:text=%E7%A9%BA%E9%96%93%E3%83%95%E3%82%A3%E3%83%AB%E3%82%BF%E3%83%AA%E3%83%B3%E3%82%B0%EF%BC%88Spatial%20Filtering%EF%BC%89%E3%81%A8,%EF%BC%89%E3%80%8D%E3%81%AB%E5%88%86%E3%81%91%E3%82%89%E3%82%8C%E3%81%BE%E3%81%99%E3%80%82)
		// 0,-1,0,0,0,0,0,1,0  // 縦微分フィルタ（参照：上記）
		// 1,1,1,1,-8,1,1,1,1  // ラプラシアンフィルタ（8近傍）（参照：https://www.clg.niigata-u.ac.jp/~medimg/practice_medical_imaging/imgproc_scion/4filter/index.html）
		// 0,1,0,1,-4,1,0,1,0  // ラプラシアンフィルタ（4近傍）（参照：上記）
		// 0.625,0.125,0.625,0.125,0.25,0.125,0.625,0.125,0.625  // ガウシアンフィルタ（中心付近に重みをつけている）（講義資料より）
		// -0.11111,-0.11111,-0.11111,-0.11111,1.88888,-0.11111,-0.11111,-0.11111,-0.11111  //アンシャープマスキング k = 1（参照：https://imagingsolution.net/imaging/unsharpmasking/）
		// -1,-1,-1,-1,9,-1,-1,-1,-1  // アンシャープマスキング k = 9
		// -2,-2,-2,-2,17,-2,-2,-2,-2  // アンシャープマスキング k = 18
	};

	static MyImage execute(MyImage input) {
		int width = input.width;
		int height = input.height;
		int n;
		MyImage output = new MyImage(width, height);
		

		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				double valueR = 0.0, valueG = 0.0, valueB = 0.0;

				if(i > 0 && j > 0) {
					Color color = input.getColor(j-1, i-1);
					valueR += (double)color.getRed()   * filter[0];
					valueG += (double)color.getGreen() * filter[0];
					valueB += (double)color.getBlue()  * filter[0];
				}

				if(i > 0) {
					Color color = input.getColor(j, i-1);
					valueR += (double)color.getRed()   * filter[1];
					valueG += (double)color.getGreen() * filter[1];
					valueB += (double)color.getBlue()  * filter[1];
				}

				if(i > 0 && j < (width - 1)) {
					Color color = input.getColor(j+1, i-1);
					valueR += (double)color.getRed()   * filter[2];
					valueG += (double)color.getGreen() * filter[2];
					valueB += (double)color.getBlue()  * filter[2];
				}
			
				if(j > 0) {
					Color color = input.getColor(j-1, i);
					valueR += (double)color.getRed()   * filter[3];
					valueG += (double)color.getGreen() * filter[3];
					valueB += (double)color.getBlue()  * filter[3];
				}
				
				{
				Color color = input.getColor(j, i);
				valueR += (double)color.getRed()   * filter[4];
				valueG += (double)color.getGreen() * filter[4];
				valueB += (double)color.getBlue()  * filter[4];
				}
		
				if(j < (width - 1)) {
					Color color = input.getColor(j+1, i);
					valueR += (double)color.getRed()   * filter[5];
					valueG += (double)color.getGreen() * filter[5];
					valueB += (double)color.getBlue()  * filter[5];
				}

				if(i < (height - 1) && j > 0) {
					Color color = input.getColor(j-1, i+1);
					valueR += (double)color.getRed()   * filter[6];
					valueG += (double)color.getGreen() * filter[6];
					valueB += (double)color.getBlue()  * filter[6];
				}

				if(i < (height - 1)) {
					Color color = input.getColor(j, i+1);
					valueR += (double)color.getRed()   * filter[7];
					valueG += (double)color.getGreen() * filter[7];
					valueB += (double)color.getBlue()  * filter[7];
				}

				if(i < (height - 1) && j < (width - 1)) {
					Color color = input.getColor(j+1, i+1);
					valueR += (double)color.getRed()   * filter[8];
					valueG += (double)color.getGreen() * filter[8];
					valueB += (double)color.getBlue()  * filter[8];
				}

				if(valueR < 0.0) valueR = 0.0;
				if(valueR > 255.0) valueR =255.0;
				if(valueG < 0.0) valueG = 0.0;
				if(valueG > 255.0) valueG =255.0;
				if(valueB < 0.0) valueB = 0.0;
				if(valueB > 255.0) valueB =255.0;

				Color color2 = new Color((int)valueR, (int)valueG, (int)valueB);
				output.setColor(j, i, color2);
			}
		}
		
		return output;
	}


}
