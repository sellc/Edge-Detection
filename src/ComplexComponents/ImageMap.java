package ComplexComponents;

import java.awt.image.BufferedImage;

import BasicComponents.Pixel;

public class ImageMap {

	private BufferedImage image;
	private Pixel[][] imageMap;

	private int imageWidth;
	private int imageHeight;
	private final int clusterWidth = 7;
	private final int clusterHeight = 7;

	private int xIndex;
	private int yIndex;
	private int clusterX;
	private int clusterY;

	private int rgb;
	private int red;
	private int green;
	private int blue;

	private int rThreshold;
	private int gThreshold;
	private int bThreshold;

	private double start;
	private double stop;

	public ImageMap(BufferedImage image) {
		this.image = image;
		imageMap = null;
		start = 0;
		stop = 0;
		yIndex = 0;
		xIndex = 0;
		rThreshold = 0;
		gThreshold = 0;
		bThreshold = 0;
		if (this.image != null) {
			imageWidth = image.getWidth();
			imageHeight = image.getHeight();
			imageMap = new Pixel[imageWidth][imageHeight];
			start = System.currentTimeMillis();
			buildImageMap();
			stop = System.currentTimeMillis();
			System.out.println(stop - start);
		}
	}

	public void buildImageMap() {
		for (yIndex = 0; yIndex < imageHeight; yIndex++) {
			for (xIndex = 0; xIndex < imageWidth; xIndex++) {
				getColor();
				imageMap[xIndex][yIndex] = new Pixel(red, green, blue);
				checkCluster();
			}
		}
	}

	public int getWidth() {
		return imageWidth;
	}

	public int getHeight() {
		return imageHeight;
	}

	public Pixel getImageMapPixel(int xIndex, int yIndex) {
		return imageMap[xIndex][yIndex];
	}

	// Get pixel color based on x and y position
	public void getColor() {
		rgb = image.getRGB(xIndex, yIndex);
		red = (rgb & 0x00ff0000) >> 16;
		green = (rgb & 0x0000ff00) >> 8;
		blue = rgb & 0x000000ff;
	}

	public void checkCluster() {
		// Get cluster once and add new values based on x or y move
		// No need to populate array for each increment
		if (xIndex > clusterWidth && yIndex > clusterHeight) {
			setLocalThresholds();
			for (clusterY = 0; clusterY > -clusterWidth; clusterY--) {
				for (clusterX = 0; clusterX > -clusterHeight; clusterX--) {
					if (Math.abs(imageMap[xIndex - clusterWidth / 2][yIndex - clusterHeight / 2].getRed()
							- imageMap[xIndex + clusterX][yIndex + clusterY].getRed()) > rThreshold
							&& Math.abs(imageMap[xIndex - clusterWidth / 2][yIndex - clusterHeight / 2].getGreen()
									- imageMap[xIndex + clusterX][yIndex + clusterY].getGreen()) > gThreshold
							&& Math.abs(imageMap[xIndex - clusterWidth / 2][yIndex - clusterHeight / 2].getBlue()
									- imageMap[xIndex + clusterX][yIndex + clusterY].getBlue()) > bThreshold) {
						imageMap[xIndex - clusterWidth / 2][yIndex - clusterHeight / 2].tagEdge();
					}
				}
			}
		}
	}

	public void setLocalThresholds() {
		rThreshold = 0;
		gThreshold = 0;
		bThreshold = 0;
		for (clusterY = 0; clusterY > -clusterWidth; clusterY--) {
			for (clusterX = 0; clusterX > -clusterHeight; clusterX--) {
				rThreshold += Math.abs(imageMap[xIndex - clusterWidth / 2][yIndex - clusterHeight / 2].getRed()
						- imageMap[xIndex + clusterX][yIndex + clusterY].getRed());
				gThreshold += Math.abs(imageMap[xIndex - clusterWidth / 2][yIndex - clusterHeight / 2].getGreen()
						- imageMap[xIndex + clusterX][yIndex + clusterY].getGreen());
				bThreshold += Math.abs(imageMap[xIndex - clusterWidth / 2][yIndex - clusterHeight / 2].getBlue()
						- imageMap[xIndex + clusterX][yIndex + clusterY].getBlue());
			}
		}
//		rThreshold /= clusterWidth;
//		gThreshold /= clusterWidth;
//		bThreshold /= clusterWidth;

		rThreshold /= clusterWidth + clusterHeight;
		gThreshold /= clusterWidth + clusterHeight;
		bThreshold /= clusterWidth + clusterHeight;
	}

	public boolean checkForNoise() {
		for (clusterY = 0; clusterY > -clusterWidth/2; clusterY--) {
			for (clusterX = 0; clusterX > -clusterHeight/2; clusterX--) {
				if (imageMap[xIndex + clusterX][yIndex + clusterY].isEdge()) {
					return false;
				}
			}
		}
		return true;
	}

}
