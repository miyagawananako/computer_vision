
public class CvMain {


	static void imageProcessing1() {

		String filename1 = "itot.jpg";

		String filename2 = "copy.jpg";

		MyImage image1, image2;
	
		image1 = JpegFileReader.read(filename1);

		{
			//image2 = Negative.execute(image1);
			//image2 = Binalization.execute(image1);
			//image2 = GammaCorrection.execute(image1);
			//image2 = SpaceFiltering.execute(image1);	
			//image2 = Scale.execute(image1);
			image2 = Rotation.execute(image1);
		}

		JpegFileWriter.write(filename2, image2);

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
