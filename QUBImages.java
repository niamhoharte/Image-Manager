package part01;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * This class represents a program for managing images.
 */
public class QUBImages {

	public static void main(String[] args) {
		initialise(); // Initialises some ImageRecord objects

		String[] options = { "Add Image", "Search", "Display All", "Exit" }; // Array of menu options for user selection

		// Initialise a Menu object with the title "QUB Images" and the provided options
		Menu myMenu = new Menu("QUB Images", options);

		boolean finished = false; // Boolean flag to control the loop
		do {
			// Get the user's choice from the menu
			int option = myMenu.getUserChoice();
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
				System.out.println("Displaying all images: ");
				displayImages(manager.getAllImages());
				break;
			case 4:
				// Exit option selected
				finished = true;
				break;
			default:
				// Invalid option selected
				System.out.println("Not a valid option.");
			}

		} while (!finished); // Repeat until the user chooses to exit
		System.out.println();
		System.out.println("Goodbye!");
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
	 * @param images - The ImageAlbum containing the images to be displayed.
	 */
	private static void displayImages(ImageAlbum images) {

		if (images != null) {
			// If the ImageAlbum is not null, display the details of the first image
			System.out.println(images.getFirst());
			System.out.println();

			// Display details of subsequent images until there are no more images
			ImageRecord nextImage = images.getNext();
			while (nextImage != null) {
				System.out.println(nextImage);
				System.out.println();
				nextImage = images.getNext();
			}

		} else {
			// If the ImageAlbum is null print a message indicating no images to display
			System.out.println("No images to display.");
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
				System.out.print("Would you like to search by id, title, description, genre, or date?: ");
				String choice = input.nextLine();

				switch (choice) {
				case "id":
					// Search by image ID
					System.out.print("Please enter an id: ");
					int id = input.nextInt();
					input.nextLine();
					// Perform ID search and display the result
					ImageRecord idImage = manager.searchId(id);
					if (idImage != null) {
						System.out.println("Displaying an image with id " + id + ":");
						System.out.println(idImage);
					} else {
						System.out.println("No image with id " + id + " to display.");
					}
					break;
				case "title":
					// Search by image title
					System.out.print("Please enter a title: ");
					String title = input.nextLine();
					// Perform title search and display the result
					ImageAlbum titleAlbum = manager.searchTitle(title);
					System.out.println("Displaying images with '" + title + "' in their title: ");
					displayImages(titleAlbum);
					break;
				case "description":
					// Search by image description
					System.out.print("Please enter a description: ");
					String desc = input.nextLine();
					// Perform description search and display the result
					ImageAlbum descAlbum = manager.searchDesc(desc);
					System.out.println("Displaying images with '" + desc + "' in their description: ");
					displayImages(descAlbum);
					break;
				case "genre":
					// Search by image genre
					System.out.print("Please enter a genre: ");
					String genreString = input.nextLine();
					// Convert genre string to ImageType enum
					ImageType genre = changeStringToImageType(genreString);
					// Perform genre search and display the result
					ImageAlbum genreAlbum = manager.searchGenre(genre);
					System.out.println("Displaying images in the '" + genreString + "' genre: ");
					displayImages(genreAlbum);
					break;
				case "date":
					// Search by image capture date
					System.out.print("Please enter a start year: ");
					int startYear = input.nextInt();
					input.nextLine();
					System.out.print("Please enter a start month: ");
					int startMonth = input.nextInt();
					input.nextLine();
					System.out.print("Please enter a start day: ");
					int startDay = input.nextInt();
					input.nextLine();
					System.out.print("Please enter an end year: ");
					int endYear = input.nextInt();
					input.nextLine();
					System.out.print("Please enter an end month: ");
					int endMonth = input.nextInt();
					input.nextLine();
					System.out.print("Please enter an end day: ");
					int endDay = input.nextInt();
					ImageAlbum dateAlbum = manager.searchDates(LocalDate.of(startYear, startMonth, startDay),
							LocalDate.of(endYear, endMonth, endDay));
					System.out
							.println("Displaying images taken between " + LocalDate.of(startYear, startMonth, startDay)
									+ " and " + LocalDate.of(endYear, endMonth, endDay) + ":");
					displayImages(dateAlbum);
					input.nextLine();
					break;
				default:
					// Handle invalid input
					System.out.println("Please enter valid input.");
					validInput = false;
				}

			} catch (Exception e) {
				// Handle exceptions and prompt for valid input
				System.out.println("Please enter valid input.");
				validInput = false;
				input.nextLine(); // Clear the input buffer
			}
		} while (!validInput); // Repeat the loop until valid input is provided
		System.out.println();
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
				System.out.print("Please enter the title of the image: ");
				String title = input.nextLine();
				System.out.print("Please enter the description of the image: ");
				String desc = input.nextLine();
				System.out.print("Please enter the thumbnail of the image: ");
				String thumbnail = input.nextLine();
				System.out.print("Please enter the year the image was taken: ");
				int yearTaken = input.nextInt();
				input.nextLine();
				System.out.print("Please enter the month the image was taken: ");
				int monthTaken = input.nextInt();
				input.nextLine();
				System.out.print("Please enter the day the image was taken: ");
				int dayTaken = input.nextInt();
				input.nextLine();
				System.out.print("Please enter a genre: ");
				String genreString = input.nextLine();

				// Convert genre string to ImageType enum
				ImageType genre = changeStringToImageType(genreString);
				// Create a new ImageRecord object with the provided details
				ImageRecord image = new ImageRecord(title, desc, thumbnail, yearTaken, monthTaken, dayTaken, genre);

				// Add the new image to the ImageManager
				manager.addImage(image);
				System.out.println("A new image has been added.");
			} catch (Exception ex) {
				// Handle invalid input and prompt for valid input
				System.out.println("Please enter valid input.");
				input.nextLine(); // Clear the input buffer
				validInput = false;
			}
		} while (!validInput); // Repeat the loop until valid input is provided
		System.out.println();
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

	private static Scanner input = new Scanner(System.in); // Scanner used for user input
	private static ImageManager manager = new ImageManager(); // ImageManager used to hold and manage collections of images

}
