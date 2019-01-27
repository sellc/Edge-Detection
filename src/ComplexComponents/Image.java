package ComplexComponents;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

public class Image {

	private String imageFilePath;
	private ImageMap map;
	private PrintWriter pw;
	private File file;

	public Image(String imageFilePath) {
		this.imageFilePath = imageFilePath;
		map = new ImageMap(loadImage());
		file = new File(imageFilePath + "Map.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		writeOut();
	}

	public String getImageFilePath() {
		return imageFilePath;
	}

	// Loads image based on imageFilePath
	public BufferedImage loadImage() {
		try {
			return ImageIO.read(new File(imageFilePath + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void writeOut() {
		try {
			pw = new PrintWriter(file);
			System.out.println(file.getPath());
			for (int yIndex = 0; yIndex < map.getHeight(); yIndex++) {
				for (int xIndex = 0; xIndex < map.getWidth(); xIndex++) {
					if (map.getImageMapPixel(xIndex, yIndex).isEdge()) {
						pw.println(xIndex + " " + yIndex);
					}
				}
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
