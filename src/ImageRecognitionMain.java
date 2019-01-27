import ComplexComponents.Image;

public class ImageRecognitionMain {

	public static void main(String[] args) {
		//Insert the file path of the input image without the file extension
		new Image("../images/car");
	}
}


//Read in image and gather info.

//Build color map and check for edges in same cycle
//Build map in zig zag motion to reduce number of full cluster gathers to one

//Build cluster based on read direction(left or right)
//Check all pixels?
//Check one pixel with surrounding colors?
//	-Disadvantages
//		Not all pixels are checked

//Build cluster with buffer of clusterWidth/2
//	-unless at edges


//Goal:
//Create an algorithm that can build 3D objects with several images
//Read in all color values
//Check for edges using clusters
