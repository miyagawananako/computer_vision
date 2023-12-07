// javac *.java -Xdiags:verbose && java CvMain && open copy.jpg

import java.awt.Color;

public class CvMain {

	static void imageProcessing1() {

		String filename_desk = "desk.png";
		String filename_mac = "macbook.jpg";
		String filename_folder = "folder.png";
		String filename_window = "window.jpg";
		String filename_food = "food.JPG";
		String filename_paper = "paper.jpg";
		// String filename_character = "character_program.png";

		String filename_output = "copy.jpg";

		Color background_color = new Color(0, 255, 0);  // green

		double filter_sharp [] = {0,-1,0,-1,5,-1,-0,-1,0}; // シャープ化フィルタ
		double filter_smooth [] = {0,0,0.33333,0,0.33333,0,0.33333,0,0};  //方向に対して重みのある平滑化
	
		// desk加工
		MyImage image_desk, image_desk_binalization;
		image_desk = JpegFileReader.read(filename_desk);
		image_desk_binalization = Binalization.execute(image_desk, 0.0);

		// mac加工
		MyImage image_mac, image_mac_binalization;
		image_mac = JpegFileReader.read(filename_mac);
		image_mac_binalization = Binalization.execute(image_mac, 255 * 3 - 10);

		// folder加工
		MyImage image_folder, image_folder_mosaic;
		image_folder = JpegFileReader.read(filename_folder);
		image_folder_mosaic = Mosaic.execute(image_folder, background_color);

		// food加工
		MyImage image_food;
		image_food = SpaceFiltering.execute(GammaCorrection.execute(Scale.execute(JpegFileReader.read(filename_food))), filter_sharp);
		
		// window加工
		MyImage image_window, image_window_filter;
		image_window = JpegFileReader.read(filename_window);
		image_window_filter = SpaceFiltering.execute(image_window, filter_sharp);

		MyImage image_paper;
		image_paper = JpegFileReader.read(filename_paper);

		// character加工
		// MyImage image_character;
		// image_character = JpegFileReader.read(filename_character);

		MyImage image_ClubUnreality, image_addwindow, image_output;
		image_ClubUnreality = ClubUnreality.execute(background_color, image_desk, image_desk_binalization, image_mac, image_mac_binalization, image_folder_mosaic); // image_characterも追加で
		image_addwindow = AddWindow.execute(image_ClubUnreality, image_window_filter, image_food);
		image_output = AlphaBlending.execute(image_paper, image_addwindow);

		JpegFileWriter.write(filename_output, image_output);

	}


	public static void main(String args[]) {

		imageProcessing1();

	}
}
