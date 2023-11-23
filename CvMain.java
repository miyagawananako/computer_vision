import java.awt.Color;
public class CvMain {


	static void imageProcessing1() {

		String filename_desk = "desk.png";
		String filename_mac = "macbook.jpg";
		String filename_folder = "folder.png";

		String filename_output = "copy.jpg";

		MyImage image_desk, image_desk_binalization, image_mac, image_mac_binalization, image_folder, image_folder_binalization, image_folder_mosaic, image_ClubUnreality, image_output;
	
		image_desk = JpegFileReader.read(filename_desk);
		image_desk_binalization = Binalization.execute(image_desk, 0.0);

		image_mac = JpegFileReader.read(filename_mac);
		image_mac_binalization = Binalization.execute(image_mac, 255 * 3 - 10);

		image_folder = JpegFileReader.read(filename_folder);
		image_folder_binalization = Binalization.execute(image_folder, 255 * 3 - 10);
		image_folder_mosaic = Mosaic.execute(image_folder, image_folder_binalization, new Color(0, 255, 0));//←絶対使う！！

		{
			//image_output = Negative.execute(image1);
			//image_output = Binalization.execute(image1);
			//image_output = GammaCorrection.execute(image1);
			//image_output = SpaceFiltering.execute(image1);	
			//image_output = Scale.execute(image1);
			//image_output = Rotation.execute(image1);
			//image_desk_dark = Dark.execute(image_desk);
			image_ClubUnreality = ClubUnreality.execute(image_desk, image_desk_binalization, image_mac, image_mac_binalization);
			image_output = SpaceFiltering.execute(image_ClubUnreality);
		}

		//JpegFileWriter.write(filename_output, image_output);
		JpegFileWriter.write(filename_output, image_folder_mosaic);

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
