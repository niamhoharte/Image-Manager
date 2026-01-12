package part02;

import java.time.LocalDate;

/**
 * This class describes the attributes and behaviour of ImageRecord objects.
 */
public class ImageRecord {
	private static int nextId = 1;
	private int imageId;// unique image id
	private String title; // title of image
	private String desc; // description of image
	private String thumbnail; // thumbnail for image
	private int yearTaken; // year image was taken
	private int monthTaken; // month image was taken
	private int dayTaken; // day image was taken
	private ImageType genre; // the type of the image
	private LocalDate dateTaken; // date image was taken

	/**
	 * Constructor for ImageRecord class.
	 * 
	 * @param title      - The title of he image.
	 * @param desc       - The description of he image.
	 * @param thumbnail  - The thumbnail for the image.
	 * @param yearTaken  - The year when the image was taken.
	 * @param monthTaken - The month when the image was taken.
	 * @param dayTaken   - The day when the image was taken.
	 * @param genre      - The genre of the image.
	 */
	public ImageRecord(String title, String desc, String thumbnail, int yearTaken, int monthTaken, int dayTaken,
			ImageType genre) {
		this.imageId = nextId++;
		setTitle(title);
		setDesc(desc);
		setThumbnail(thumbnail);
		setYearTaken(yearTaken);
		setMonthTaken(monthTaken);
		setDayTaken(dayTaken);
		setGenre(genre);

		dateTaken = LocalDate.of(this.yearTaken, this.monthTaken, this.dayTaken); // LocalDate object representing the
																					// date when the image was taken.

	}

	/**
	 * Sets the title of the image . If the provided title is not null, it sets the
	 * title of the image to the provided value. If the provided title is null, it
	 * sets the title to a default value "Title".
	 * 
	 * @param title - The title to be set for the image.
	 */
	public void setTitle(String title) {
		if (title != null) {
			this.title = title;
		} else {
			this.title = "Title";
		}
	}

	/**
	 * Sets the description of the image . If the provided description is not null,
	 * it sets the description of the image to the provided value. If the provided
	 * description is null, it sets the title to a default value "Description".
	 * 
	 * @param desc - The description to be set for the image.
	 */
	public void setDesc(String desc) {
		if (desc != null) {
			this.desc = desc;
		} else {
			this.desc = "Description";
		}
	}

	/**
	 * Sets the thumbnail for the image. If the provided thumbnail is not null and
	 * contains ".png" extension, it sets the thumbnail for the image to the
	 * provided value. If the provided thumbnail is not null but does not contain
	 * ".png" extension, it appends ".png" extension to the provided thumbnail. If
	 * the provided thumbnail is null, it sets the thumbnail to a default value
	 * "default.png".
	 * 
	 * @param thumbnail - The thumbnail to be set for the image.
	 */
	public void setThumbnail(String thumbnail) {
		if (thumbnail != null && thumbnail.contains(".png")) {
			this.thumbnail = thumbnail;
		} else if (thumbnail.length() > 0) {
			this.thumbnail = thumbnail + ".png";

		} else {
			this.thumbnail = "default.png";
		}
	}

	/**
	 * Sets the year when the image was taken. If the provided year is greater than
	 * 0, it sets the year taken of the image to the provided value. If the provided
	 * year is less than or equal to 0, it sets the year taken to a default value
	 * 2023.
	 * 
	 * @param yearTaken - The year when the image was taken.
	 */
	public void setYearTaken(int yearTaken) {
		if (yearTaken > 0) {
			this.yearTaken = yearTaken;
		} else {
			this.yearTaken = 2023;
		}
	}

	/**
	 * Sets the month the image was taken. If the provided month is greater than 0,
	 * it sets the month taken of the image to the provided value. If the provided
	 * month is less than or equal to 0, it sets the month taken to a default value
	 * 1.
	 * 
	 * @param monthTaken - The month when the image was taken.
	 */
	public void setMonthTaken(int monthTaken) {
		if (monthTaken > 0) {
			this.monthTaken = monthTaken;
		} else {
			this.monthTaken = 1;
		}

	}

	/**
	 * Sets the day the image was taken. If the provided day is greater than 0, it
	 * sets the day taken of the image to the provided value. If the provided day is
	 * less than or equal to 0, it sets the day taken to a default value 1.
	 * 
	 * @param dayTaken - The day the image was taken.
	 */
	public void setDayTaken(int dayTaken) {
		if (dayTaken > 0) {
			this.dayTaken = dayTaken;
		} else {
			this.dayTaken = 1;
		}
	}

	/**
	 * Sets the genre of the image. If the provided genre is not null, it sets the
	 * genre of the image to the provided value. If the provided genre is null, it
	 * sets the genre to a default value ImageType.OTHER.
	 * 
	 * @param genre - The genre to be set for the image.
	 */
	public void setGenre(ImageType genre) {
		if (genre != null) {
			this.genre = genre;
		} else {
			this.genre = ImageType.OTHER;
		}
	}

	/**
	 * Retrieves the unique ID of the image.
	 * 
	 * @return The unique ID of the image.
	 */
	public int getId() {
		return this.imageId;
	}

	/**
	 * Retrieves the title of the image.
	 * 
	 * @return The title of the image.
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Retrieves the thumbnail of the image.
	 * 
	 * @return - The thumbnail of the image.
	 */
	public String getThumbnail() {
		return this.thumbnail;
	}

	/**
	 * Retrieves the description of the image.
	 * 
	 * @return - The description of the image.
	 */
	public String getDesc() {
		return this.desc;
	}

	/**
	 * Retrieves the date the image was taken.
	 * 
	 * @return - The date the image was taken.
	 */
	public LocalDate getDateTaken() {
		return this.dateTaken;
	}

	/**
	 * Retrieves the genre of the image.
	 * 
	 * @return - The genre of the image.
	 */
	public ImageType getGenre() {
		return this.genre;
	}

	/**
	 * Returns a string representation of the ImageRecord object.
	 * 
	 * @return A string containing information about the image, including its ID,
	 *         title, description, thumbnail, genre, and date taken.
	 */
	public String toString() {
		String res = "";
		res += "\nID: " + this.imageId + "\nTitle: " + this.title + "\nDescription: " + this.desc + "\nThumbnail: "
				+ this.thumbnail + "\nGenre: " + this.genre + "\nDate Taken: " + this.dateTaken;
		return res;
	}
}
