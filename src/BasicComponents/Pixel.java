package BasicComponents;

public class Pixel {

	private int red;
	private int green;
	private int blue;
	private boolean edge;
	private boolean object;

	public Pixel(int red, int green, int blue) {
		this(red, green, blue, false, false);
	}

	public Pixel(int red, int green, int blue, boolean edge, boolean object) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.edge = edge;
		this.object = object;
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}

	public boolean isEdge() {
		return edge;
	}

	public boolean isObject() {
		return object;
	}

	public void tagEdge() {
		edge = true;
	}

	public void tagObject() {
		object = true;
	}

}
