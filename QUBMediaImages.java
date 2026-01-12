package part02;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;

import javax.swing.ImageIcon;

import console.Console;

public class QUBMediaImages {

	public static void main(String[] args) {
		initialise(); // Initialises some ImageRecord objects

		String[] options = { "Add Image", "Search", "Display All", "Exit" }; // Array of menu options for user selection

		// Initialise a ConsoleMenu object with the title "QUB Images" and the provided options
		ConsoleMenu myMenu = new ConsoleMenu("QUB Images", options);

		boolean finished = false; // Boolean flag to control the loop
		do {
			// Get the user's choice from the menu
			int option = myMenu.getUserChoice(con);
			con.clear();

			// Perform actions based on the user's choice
			switch (option) {
			case 1:
				// Add Image option selected
				addImage();
				break;
			case 2:
				// Search option selected
				searchImages();
				break;
			case 3:
				// Display All option selected
				con.println("Displaying all images: ");
				displayImages(manager.getAllImages());
				break;
			case 4:
				// Exit option selected
				finished = true;
				break;
			default:
	            // Invalid option selected
				con.println("Not a valid option.\n\nPress ENTER to continue.");
				con.readLn();
				con.clear();
			}

		} while (!finished); // Repeat until the user chooses to exit

		// Exit the program
		System.exit(1);

	}

	/**
	 * Retrieves an image file as an ImageIcon object.
	 * 
	 * This method constructs the path to the image file based on the provided
	 * thumbnail and the current user directory. It then creates an ImageIcon object
	 * representing the image located at the specified path.
	 * 
	 * @param thumbnail - The filename of the image thumbnail.
	 * @return An ImageIcon object representing the image.
	 */
	public static ImageIcon getImage(String thumbnail) {
		// Get the current user directory
		String userdir = System.getProperty("user.dir");
		// Construct the full path to the image file
		String path = userdir + "/Images/" + thumbnail;
		// Create an ImageIcon object representing the image
		ImageIcon img = new ImageIcon(path);
		return img;
	}

	/**
	 * Displays the images contained in the provided ImageAlbum.
	 * 
	 * If the provided ImageAlbum is not null, this method prints the details of the
	 * first image using 'getFirst()', followed by the details of subsequent images
	 * retrieved using 'getNext()' until there are no more images.
	 * 
	 * If the provided ImageAlbum is null, it prints a message indicating that there
	 * are no images to display.
	 * 
	 * @param images The ImageAlbum containing the images to be displayed.
	 */
	private static void displayImages(ImageAlbum images) {
		ImageIcon image;
		if (images != null) {
			// If the ImageAlbum is not null, display the details of the first image
			image = getImage(images.getFirst().getThumbnail());
			con.println();
			con.println(image);
			con.println(images.getFirst());
			con.println();

			// Display details of subsequent images until there are no more images
			ImageRecord nextImage = images.getNext();
			while (nextImage != null) {
				image = getImage(nextImage.getThumbnail());
				con.println(image);
				con.println(nextImage);
				con.println();
				nextImage = images.getNext();
			}
			// If the ImageAlbum is null print a message indicating no images to display
			con.println("All images displayed.\n\nPress ENTER to continue.");
			con.readLn();
			con.clear();
		} else {
			con.println("No images to display.\n\nPress ENTER to continue.");
			con.readLn();
			con.clear();
		}

	}

	/**
	 * Prompts the user to search for images based on various criteria such as ID,
	 * title, description, genre, or date.
	 * 
	 * This method presents a menu-like interface where the user can choose a search
	 * criterion. Depending on the user's choice, it prompts for additional
	 * information (such as ID, title, etc.) and performs the corresponding search
	 * operation using the ImageManager instance.
	 * 
	 * If the user provides invalid input or encounters an error during input, the
	 * method handles the exception and prompts the user to enter valid input.
	 * 
	 */
	private static void searchImages() {
		boolean validInput;
		do {
			validInput = true;
			try {
				// Prompt the user to choose a search criterion
				con.print("Would you like to search by id, title, description, genre, or date?: ");
				String choice = con.readLn();

				switch (choice) {
				case "id":
					// Search by image ID
					con.print("Please enter an id: ");
					int id = Integer.parseInt(con.readLn());
					// Perform ID search and display the result
					ImageRecord idImage = manager.searchId(id);
					if (idImage != null) {
						con.println("\nDisplaying an image with id " + id + ":");
						ImageIcon image = getImage(idImage.getThumbnail());
						con.println();
						con.println(image);
						con.println(idImage);
						con.println("\nImage displayed.\n\nPress ENTER to continue.");
						con.readLn();
						con.clear();
					} else {
						con.println("No image with id " + id + " to display.\n\nPress ENTER to continue");
						con.readLn();
						con.clear();
					}
					break;
				case "title":
					// Search by image title
					con.print("Please enter a title: ");
					String title = con.readLn();
					// Perform title search and display the result
					ImageAlbum titleAlbum = manager.searchTitle(title);
					con.println("\nDisplaying images with '" + title + "' in their title: ");
					displayImages(titleAlbum);
					break;
				case "description":
					// Search by image description
					con.print("Please enter a description: ");
					String desc = con.readLn();
					// Perform description search and display the result
					ImageAlbum descAlbum = manager.searchDesc(desc);
					con.println("\nDisplaying images with '" + desc + "' in their description: ");
					displayImages(descAlbum);
					break;
				case "genre":
					// Search by image genre
					con.print("Please enter a genre: ");
					String genreString = con.readLn();
					// Convert genre string to ImageType enum
					ImageType genre = changeStringToImageType(genreString);
					// Perform genre search and display the result
					ImageAlbum genreAlbum = manager.searchGenre(genre);
					con.println("\nDisplaying images in the '" + genreString + "' genre: ");
					displayImages(genreAlbum);
					break;
				case "date":
					// Search by image capture date
					con.print("Please enter a start year: ");
					int startYear = Integer.parseInt(con.readLn());
					con.print("Please enter a start month: ");
					int startMonth = Integer.parseInt(con.readLn());
					con.print("Please enter a start day: ");
					int startDay = Integer.parseInt(con.readLn());
					con.print("Please enter an end year: ");
					int endYear = Integer.parseInt(con.readLn());
					con.print("Please enter an end month: ");
					int endMonth = Integer.parseInt(con.readLn());
					con.print("Please enter an end day: ");
					int endDay = Integer.parseInt(con.readLn());
					ImageAlbum dateAlbum = manager.searchDates(LocalDate.of(startYear, startMonth, startDay),
							LocalDate.of(endYear, endMonth, endDay));
					con.println("\nDisplaying images taken between " + LocalDate.of(startYear, startMonth, startDay)
							+ " and " + LocalDate.of(endYear, endMonth, endDay) + ":");
					displayImages(dateAlbum);
					break;
				default:
					// Handle invalid input
					con.println("Please enter valid input.\n\nPress ENTER to continue.");
					con.readLn();
					con.clear();
					validInput = false;
				}

			} catch (Exception e) {
				// Handle exceptions and prompt for valid input
				con.println("Please enter valid input.\n\nPress ENTER to continue.");
				con.readLn();
				con.clear();
				validInput = false;
			}
		} while (!validInput); // Repeat the loop until valid input is provided
		con.println();
	}

	/**
	 * Adds a new ImageRecord object to an ImageManager instance.
	 * 
	 * This method prompts the user to input details for a new image, including its
	 * title, description, thumbnail path, date taken, and genre. It then creates a
	 * new ImageRecord object with the provided details and adds it to the
	 * ImageManager using the 'addImage' method. If any input provided by the user
	 * is invalid, the method catches the exception, prompts the user to enter valid
	 * input, and retries until valid input is provided.
	 */
	private static void addImage() {
		boolean validInput;
		do {
			validInput = true;
			try {
				// Prompt the user to input details for the new image
				con.print("Please enter the title of the image: ");
				String title = con.readLn();
				con.print("Please enter the description of the image: ");
				String desc = con.readLn();
				con.print("Please enter the thumbnail of the image: ");
				String thumbnail = con.readLn();
				con.print("Please enter the year the image was taken: ");
				int yearTaken = Integer.parseInt(con.readLn());
				con.print("Please enter the month the image was taken: ");
				int monthTaken = Integer.parseInt(con.readLn());
				con.print("Please enter the day the image was taken: ");
				int dayTaken = Integer.parseInt(con.readLn());
				con.print("Please enter a genre: ");
				String genreString = con.readLn();

				// Convert genre string to ImageType enum
				ImageType genre = changeStringToImageType(genreString);
				// Create a new ImageRecord object with the provided details
				ImageRecord image = new ImageRecord(title, desc, thumbnail, yearTaken, monthTaken, dayTaken, genre);

				// Add the new image to the ImageManager
				manager.addImage(image);
				con.println("A new image has been added.\n\nPress ENTER to continue.");
				con.readLn();
				con.clear();
			} catch (Exception ex) {
				// Handle invalid input and prompt for valid input
				con.println("Please enter valid input.\n\nPress ENTER to continue.");
				con.readLn();
				con.clear();
				validInput = false;
			}
		} while (!validInput);
		con.println();
	}

	/**
	 * Converts a string representation of image genre to its corresponding
	 * ImageType enum value.
	 * 
	 * This method takes a string representing an image genre and returns the
	 * corresponding ImageType enum value. If the provided string matches one of the
	 * predefined genres, the method returns the corresponding ImageType enum value.
	 * If the provided string does not match any predefined genre, the method
	 * returns null.
	 * 
	 * @param genre A string representing the genre of the image.
	 * @return The corresponding ImageType enum value if the provided string matches
	 *         a predefined genre or null otherwise.
	 */
	public static ImageType changeStringToImageType(String genre) {
		switch (genre.toLowerCase()) {
		case "astronomy":
			return ImageType.ASTRONOMY;
		case "architecture":
			return ImageType.ARCHITECTURE;
		case "sport":
			return ImageType.SPORT;
		case "landscape":
			return ImageType.LANDSCAPE;
		case "portrait":
			return ImageType.PORTRAIT;
		case "nature":
			return ImageType.NATURE;
		case "aerial":
			return ImageType.AERIAL;
		case "food":
			return ImageType.FOOD;
		case "other":
			return ImageType.OTHER;
		default:
			return null;

		}
	}

	/**
	 * Initialises the ImageManager with a set of predefined ImageRecord objects.
	 * 
	 * This method creates a set of predefined ImageRecord objects representing
	 * different images. Each ImageRecord object includes details such as title,
	 * description, thumbnail, date taken, and genre. These ImageRecord objects are
	 * then added to the ImageManager using the 'addImage' method to populate the
	 * ImageManager with initial data.
	 * 
	 */
	public static void initialise() {
		// Create and initialise predefined ImageRecord objects
		ImageRecord im1 = new ImageRecord("Andromeda Galaxy", "Image of the Andromeda galaxy.", "Andromeda.png", 2023,
				1, 1, ImageType.ASTRONOMY);
		ImageRecord im2 = new ImageRecord("Lanyon QUB", "An image of the QUB Lanyon building.", "LanyonQUB.png", 2023,
				2, 1, ImageType.ARCHITECTURE);
		ImageRecord im3 = new ImageRecord("Kermit Plays Golf", "An image of Kermit the frog playing golf.",
				"KermitGolf.png", 2023, 3, 1, ImageType.SPORT);
		ImageRecord im4 = new ImageRecord("Mourne Mountains", "A panoramic view of the Mourne mountains.",
				"Mournes.png", 2023, 4, 1, ImageType.LANDSCAPE);
		ImageRecord im5 = new ImageRecord("Homer Simpson", "Homer Simpson- A portrait of the man.", "Homer.png", 2023,
				3, 1, ImageType.PORTRAIT);
		ImageRecord im6 = new ImageRecord("Red Kite", "A Red Kite bird of prey in flight.", "RedKite.png", 2023, 4, 1,
				ImageType.NATURE);
		ImageRecord im7 = new ImageRecord("Central Park", "An overhead view of Central Park New York USA.",
				"CentralPark.png", 2023, 5, 1, ImageType.AERIAL);
		ImageRecord im8 = new ImageRecord("Apples", "A bunch of apples.", "Apples.png", 2023, 6, 1, ImageType.FOOD);
		ImageRecord im9 = new ImageRecord("Programming Meme", "A Chat GPT programming meme.", "ChatGPT.png", 2023, 7, 1,
				ImageType.OTHER);

		// Add predefined ImageRecord objects to the ImageManager
		manager.addImage(im1);
		manager.addImage(im2);
		manager.addImage(im3);
		manager.addImage(im4);
		manager.addImage(im5);
		manager.addImage(im6);
		manager.addImage(im7);
		manager.addImage(im8);
		manager.addImage(im9);
	}

	/**
	 * Initialises a new console with specific settings and returns it.
	 * 
	 * @return The initialised Console object.
	 */
	public static Console initConsole() {
		Console con = new Console(true);
		con.setSize(900, 800);
		con.setVisible(true);

		con.setBgColour(Color.BLACK);
		Font ft = new Font("Consolas", Font.BOLD, 20);
		con.setFont(ft);

		con.setColour(Color.CYAN);
		return con;
	}

	private static Console con = initConsole(); // Initialise the console for input and output
	private static ImageManager manager = new ImageManager(); // ImageManager used to hold and manage collections of images
}
