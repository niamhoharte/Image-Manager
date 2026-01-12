package part02;

/**
 * An enum representing different genres for images.
 */
public enum ImageType {
	ASTRONOMY("Astronomy"), ARCHITECTURE("Architecture"), SPORT("Sport"), LANDSCAPE("Landscape"), PORTRAIT("Portrait"),
	NATURE("Nature"), AERIAL("Aerial"), FOOD("Food"), OTHER("Other");

	private String desc; // A string representing the genre for the images

	/**
	 * Constructs a new ImageType enum with the specified description.
	 * 
	 * @param str - The description of the ImageType.
	 */
	private ImageType(String str) {
		desc = str;
	}

	/**
	 * Returns the description of the ImageType.
	 * 
	 * @return The description of the ImageType.
	 */
	public String toString() {
		return desc;
	}
}
