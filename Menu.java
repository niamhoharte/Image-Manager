package part01;

import java.util.Scanner;

/**
 * This class represents a menu interface for user interaction.
 */
public class Menu {
	private String items[]; // Array of menu options for user selection
	private String title; // Title of menu
	private Scanner input; // Scanner for user input

	/**
	 * Constructor for Menu class.
	 * 
	 * @param title - The title of the menu.
	 * @param data  - The menu options for user selection.
	 */
	public Menu(String title, String data[]) {
		this.title = title;
		this.items = data;
		this.input = new Scanner(System.in);
	}

	/**
	 * Displays the title followed by a line of plus symbols ('+') matching the
	 * length of the title. Then, it displays a numbered list of items contained in
	 * the 'items' array.
	 * 
	 */
	private void display() {
		// Display the title
		System.out.println(title);
		// Display a line of plus symbols ('+') matching the length of the title
		for (int count = 0; count < title.length(); count++) {
			System.out.print("+");
		}
		System.out.println();
		// Display a numbered list of items contained in the 'items' array
		for (int option = 1; option <= items.length; option++) {
			System.out.println(option + ". " + items[option - 1]);
		}
		System.out.println();
	}

	/**
	 * Displays a menu of options using the 'display()' method and prompts the user
	 * to enter their selection.
	 * 
	 * @return The integer value entered by the user as their selection. If an
	 *         invalid input is provided (such as a non-integer value), returns 0.
	 */
	public int getUserChoice() {
		display();
		int value = 0;
		try {
			// Prompt the user to enter their selection
			System.out.print("Enter Selection: ");
			// Read the user's input as an integer
			value = input.nextInt();
		} catch (Exception e) {
			// If an exception occurs consume the invalid input
			input.nextLine();
		}
		return value;
	}
}
