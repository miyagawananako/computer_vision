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
		MyImage image_folder, image_folder_mosaic, image_folder_green;
		image_folder = JpegFileReader.read(filename_folder);
		image_folder_green = AddGreenback.execute(image_folder, 255 * 3 - 150); // 白背景を緑背景にする。そのあとモザイクをかけるのでかなり幅を持たせて白部分をなくす。
		int[][] default_index = {{0, 0}, {image_folder.width, image_folder.height}};
		image_folder_mosaic = Mosaic.execute(image_folder_green, default_index);

		MyImage image_paper;
		image_paper = JpegFileReader.read(filename_paper);

		// food加工
		MyImage image_food_original, scaledImage, gammaCorrectedImage, sharpenedImage, image_food;
		image_food_original = JpegFileReader.read(filename_food);	// foodの画像を読み込む
		scaledImage = Scale.execute(image_food_original);	// 画像を縮小する
		gammaCorrectedImage = GammaCorrection.execute(scaledImage);	// 画像をガンマ補正する
		sharpenedImage = SpaceFiltering.execute(gammaCorrectedImage, filter_sharp);	// 画像にシャープ化フィルタを適用する
		image_food = AlphaBlending.execute(image_paper, sharpenedImage);	// 画像をアルファブレンディングする
		
		// window加工
		MyImage image_window, image_window_filter;
		image_window = JpegFileReader.read(filename_window);
		image_window_filter = SpaceFiltering.execute(image_window, filter_sharp);

		MyImage image_ClubUnreality, image_output;
		image_ClubUnreality = ClubUnreality.execute(background_color, image_desk, image_desk_binalization, image_mac, image_mac_binalization, image_folder_mosaic); // image_characterも追加で
		image_output = AddWindow.execute(image_ClubUnreality, image_window_filter, image_food);

		JpegFileWriter.write(filename_output, image_output);

	}

	static void imageProcessing2() {
		String filename_input = "face.jpeg";
		MyImage input =  JpegFileReader.read(filename_input);

		String filename_output = "copy2.jpg";
		MyImage image_output;

		//1.緑のスタート座標とゴール座標を取得して返す
		int[][] greenindex = GetGreenIndex.execute(input);
		System.out.println(greenindex[0][0]);
		System.out.println(greenindex[0][1]);
		System.out.println(greenindex[1][0]);
		System.out.println(greenindex[1][1]);

		//2.緑のスタート座標とゴール座標を受け取って、中にモザイクかけて返す。
		image_output = Mosaic.execute(input, greenindex);

		//image_output = GetGreenIndexTest.execute(input, greenindex); //モザイクかける部分が正しいか確認するテストコード

		JpegFileWriter.write(filename_output, image_output);
	}


	public static void main(String args[]) {

		imageProcessing1();
		imageProcessing2();

	}
}
