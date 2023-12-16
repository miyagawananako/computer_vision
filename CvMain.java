// javac *.java -Xdiags:verbose && java CvMain && open copy.jpg

import java.awt.Color;

public class CvMain {

	static void imageProcessing1() {

		String filename_desk = "desk.jpg";
		String filename_mac = "macbook.jpeg";
		String filename_folder = "folder.jpeg";
		String filename_window = "window.jpeg";
		String filename_food = "food.jpeg";
		String filename_paper = "paper.jpeg";
		String filename_screen = "screen.jpeg";

		String filename_output = "copy.jpg";

		Color background_color = new Color(0, 255, 0);  // green

		double filter_sharp [] = {0,-1,0,-1,5,-1,-0,-1,0}; // シャープ化フィルタ
	
		// desk加工
		MyImage image_desk, image_desk_binalization;
		image_desk = JpegFileReader.read(filename_desk);
		KMeans kmeans = new KMeans();
		kmeans.clustering(image_desk, 6);
		image_desk_binalization = Chromakey.execute(image_desk, kmeans, 1);

		// mac加工
		MyImage image_mac, image_mac_binalization;
		image_mac = JpegFileReader.read(filename_mac);
		image_mac_binalization = Binalization.execute(image_mac, 255 * 3 - 10);

		// folder加工
		MyImage image_folder, image_folder_mosaic;
		image_folder = JpegFileReader.read(filename_folder);
		image_folder = AddGreenback.execute(image_folder, 255 * 3 - 150); // 白背景を緑背景にする。そのあとモザイクをかけるのでかなり幅を持たせて白部分をなくす。
		image_folder_mosaic = Mosaic.execute(image_folder, image_folder.width / 20);

		// paper読取
		MyImage image_paper;
		image_paper = JpegFileReader.read(filename_paper);

		// food加工
		MyImage image_food;
		image_food = JpegFileReader.read(filename_food);	// foodの画像を読み込む
		image_food = Scale.execute(image_food);	// 画像を縮小する
		image_food= GammaCorrection.execute(image_food);	// 画像をガンマ補正する
		image_food = SpaceFiltering.execute(image_food, filter_sharp);	// 画像にシャープ化フィルタを適用する
		image_food = AlphaBlending.execute(image_paper, image_food);	// 画像をアルファブレンディングする
		int[][] greenindex = GetGreenIndex.execute(image_food); // 緑のスタート座標とゴール座標を取得して返す
		// image_food = GetGreenIndexTest.execute(image_food, greenindex);  // greenIndexが取得できていたら内部を黒く塗りつぶす
		image_food = Mosaic.execute(image_food, image_food.width / 250, greenindex);  // 緑のスタート座標とゴール座標を受け取って、中にモザイクかけて返す
		
		// window加工
		MyImage image_window, image_window_filter;
		image_window = JpegFileReader.read(filename_window);
		image_window_filter = SpaceFiltering.execute(image_window, filter_sharp);

		// screen加工
		MyImage image_screen;
		image_screen = JpegFileReader.read(filename_screen);

		// 出力画像作成
		MyImage image_output;
		image_output = ClubUnreality.execute(background_color, image_desk, image_desk_binalization, image_mac, image_mac_binalization, image_folder_mosaic, image_screen);
		image_output = AddWindow.execute(image_output, image_window_filter, image_food);
		image_output = Posterize.execute(image_output);

		JpegFileWriter.write(filename_output, image_output);

	}

	public static void main(String args[]) {

		imageProcessing1();

	}
}
