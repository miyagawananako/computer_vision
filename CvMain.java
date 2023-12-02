// javac *.java -Xdiags:verbose && java CvMain && open copy.jpg

import java.awt.Color;

public class CvMain {


	static void imageProcessing1() {

		String filename_desk = "desk.png";
		String filename_mac = "macbook.jpg";
		String filename_folder = "folder.png";
		String filename_window = "window.jpg";
		String filename_food = "food.JPG";

		String filename_output = "copy.jpg";

		MyImage image_desk, image_desk_filter, image_desk_binalization, image_mac, image_mac_filter, image_mac_binalization, image_folder, image_folder_binalization, image_folder_mosaic, image_window, image_window_filter, image_food, image_food_window, image_ClubUnreality, image_filter, image_output;

		Color background_color = new Color(0, 255, 0);  // green

		double filter_sharp [] = {0,-1,0,-1,5,-1,-0,-1,0}; // シャープ化フィルタ
		double filter_smooth [] = {0,0,0.33333,0,0.33333,0,0.33333,0,0};  //方向に対して重みのある平滑化
	
		image_desk = JpegFileReader.read(filename_desk);
		//image_desk_filter = SpaceFiltering.execute(image_desk);
		image_desk_binalization = Binalization.execute(image_desk, 0.0);

		image_mac = JpegFileReader.read(filename_mac);
		//image_mac_filter = SpaceFiltering.execute(image_mac);
		image_mac_binalization = Binalization.execute(image_mac, 255 * 3 - 10);

		image_folder = JpegFileReader.read(filename_folder);
		image_folder_mosaic = Mosaic.execute(image_folder, background_color);

		image_food = SpaceFiltering.execute(GammaCorrection.execute(Scale.execute(JpegFileReader.read(filename_food))), filter_sharp);
		image_window = JpegFileReader.read(filename_window);
		//image_food_window = image_window;

		image_window_filter = SpaceFiltering.execute(image_window, filter_sharp);

		{
			//image_output = Negative.execute(image1);
			//image_output = Binalization.execute(image1);
			//image_output = GammaCorrection.execute(image1);
			//image_output = SpaceFiltering.execute(image1);	
			//image_output = Scale.execute(image1);
			//image_output = Rotation.execute(image1);
			//image_desk_dark = Dark.execute(image_desk);
			image_ClubUnreality = ClubUnreality.execute(background_color, image_desk, image_desk_binalization, image_mac, image_mac_binalization, image_folder_mosaic);
			//image_filter = SpaceFiltering.execute(image_ClubUnreality, filter_smooth);
			image_output = AddWindow.execute(image_ClubUnreality, image_window_filter, image_food); //filterをかけたくないものはここでadd
			//image_output = image_food_window; //一旦ね。
			//image_output = image_filter;
		}

		JpegFileWriter.write(filename_output, image_output);
		//JpegFileWriter.write(filename_output, image_folder_mosaic);

	}


	static void imageProcessing2() {

		String filename1 = "itot.jpg";
		String filename2 = "ochatop.jpg";
		String filename3 = "copy.jpg";

		MyImage image1, image2, image3, image0;
	
		image1 = JpegFileReader.read(filename1);
		image2 = JpegFileReader.read(filename2);

		KMeans kmeans = new KMeans();
		kmeans.clustering(image1, 6);
		image0 = Chromakey.execute(image1, kmeans, 3);

		//image3 = VirtualStudio.execute(image1, image2, image0); 
		//image3 = AlphaBlending.execute(image1, image2, image0); 	
		//image3 = AlphaBlending2.execute(image1, image2, image0); 	
		image3 = Tiling.execute(image1, image2); 	

		JpegFileWriter.write(filename3, image3);

	}


	public static void main(String args[]) {

		imageProcessing1();
		//imageProcessing2();

	}
}
