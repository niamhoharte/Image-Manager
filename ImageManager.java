package part01;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This class is responsible for managing a collection of ImageRecord instances.
 */
public class ImageManager {

	private ArrayList<ImageRecord> collection = new ArrayList<ImageRecord>(); // a collection of ImageRecord objects.

	/**
	 * Constructor for ImageManager class.
	 */
	public ImageManager() {

	}

	/**
	 * Adds a new ImageRecord object to the collection.
	 * 
	 * @param image - new ImageRecord to be added to the collection.
	 */
	public void addImage(ImageRecord image) {
		collection.add(image);
	}

	/**
	 * Searches for and retrieves an ImageRecord in the collection with the
	 * specified ID.
	 * 
	 * @param id - The ID of the ImageRecord to search for.
	 * @return The ImageRecord with the specified ID, or null if no such
	 *         ImageRecords found.
	 */
	public ImageRecord searchId(int id) {
		ImageRecord target = null;
		for (int i = 0; i < collection.size(); i++) {
			// Get the current ImageRecord in the collection
			ImageRecord image = collection.get(i);
			// Check if the ID of the current ImageRecord matches the specified ID
			if (image.getId() == id) {
				target = image;
				// Exit loop since the target ImageRecord has been found
				break;
			}
		}
		return target;
	}

	/**
	 * Searches for ImageRecords in the collection with titles containing the
	 * specified string and creates an ImageAlbum from them.
	 * 
	 * @param str - The string to search for within image titles.
	 * @return An ImageAlbum containing ImageRecords with titles containing the
	 *         specified string, or null if no such ImageRecords are found.
	 */
	public ImageAlbum searchTitle(String str) {
		ArrayList<ImageRecord> album = new ArrayList<ImageRecord>(); // ArrayList used to store matching ImageRecords.
		for (int i = 0; i < collection.size(); i++) {
			// Get the current ImageRecord in the collection
			ImageRecord image = collection.get(i);
			// Check if the title of the current ImageRecord contains the specified string
			if (image.getTitle().contains(str)) {
				album.add(image);
			}
		}
		// Check if matching ImageRecords were found.
		if (album.size() > 0) {
			ImageAlbum titleAlbum = new ImageAlbum(album); // ImageAlbum containing the matching ImageRecords
			return titleAlbum;
		} else {
			return null;
		}
	}

	/**
	 * Searches for ImageRecords in the collection with descriptions containing the
	 * specified string and creates an ImageAlbum from them.
	 * 
	 * @param str - The string to search for within image descriptions.
	 * @return An ImageAlbum containing ImageRecords with descriptions containing
	 *         the specified string, or null if no such ImageRecords are found.
	 */
	public ImageAlbum searchDesc(String str) {
		ArrayList<ImageRecord> album = new ArrayList<ImageRecord>(); // ArrayList used to store matching ImageRecords
		for (int i = 0; i < collection.size(); i++) {
			// Get the current ImageRecord in the collection
			ImageRecord image = collection.get(i);
			// Check if the description of the current ImageRecord contains the specified string
			if (image.getDesc().contains(str)) {
				album.add(image);
			}
		}
		// Check if matching ImageRecords were found.
		if (album.size() > 0) {
			ImageAlbum descAlbum = new ImageAlbum(album); // ImageAlbum containing the matching ImageRecords
			return descAlbum;
		} else {
			return null;
		}
	}

	/**
	 * Searches for ImageRecords in the collection with the specified ImageType and
	 * creates an ImageAlbum from them.
	 * 
	 * @param type - The ImageType of the ImageRecords to search for
	 * @return An ImageAlbum containing ImageRecords with the specified ImageType,
	 *         or null if no such ImageRecords found.
	 */
	public ImageAlbum searchGenre(ImageType type) {
		ArrayList<ImageRecord> album = new ArrayList<ImageRecord>();// ArrayList used to store matching ImageRecords
		for (int i = 0; i < collection.size(); i++) {
			// Get the current ImageRecord in the collection
			ImageRecord image = collection.get(i);
			// Check if the ImageType of the current ImageRecord matches the specified ImageType
			if (image.getGenre().equals(type)) {
				album.add(image);
			}
		}
		// Check if matching ImageRecords were found
		if (album.size() > 0) {
			ImageAlbum genreAlbum = new ImageAlbum(album);// ImageAlbum containing the matching ImageRecords
			return genreAlbum;
		} else {
			return null;
		}

	}

	/**
	 * Searches for ImageRecords in the collection with dates between the specified
	 * date range and creates an ImageAlbum from them.
	 * 
	 * @param start - The start date of the date range to search within.
	 * @param end   - The end date of the date range to search within.
	 * @return An ImageAlbum containing ImageRecords within the specified date
	 *         range, or null if no such ImageRecords are found.
	 */
	public ImageAlbum searchDates(LocalDate start, LocalDate end) {
		ArrayList<ImageRecord> album = new ArrayList<ImageRecord>();// ArrayList used to store matching ImageRecords
		for (int i = 0; i < collection.size(); i++) {
			// Get the current ImageRecord in the collection
			ImageRecord image = collection.get(i);
			// Check if the date of the current ImageRecord falls within the specified date range
			if (image.getDateTaken().isAfter(start) && image.getDateTaken().isBefore(end)) {
				album.add(image);
			}
		}
		// Check if matching ImageRecords were found
		if (album.size() > 0) {
			ImageAlbum dateAlbum = new ImageAlbum(album);// ImageAlbum containing the matching ImageRecords
			return dateAlbum;
		} else {

			return null;
		}
	}

	/**
	 * Retrieves all ImageRecords from the collection and creates an ImageAlbum
	 * containing them.
	 * 
	 * @return An ImageAlbum containing all the ImageRecords from the collection, or
	 *         null if the collection is empty.
	 */
	public ImageAlbum getAllImages() {
		//Check if the collection is not empty
		if (collection.size() > 0) {
			ImageAlbum allImages = new ImageAlbum(collection);// ImageAlbum containing all ImageRecords
			return allImages;
		}
		return null;
	}

}
