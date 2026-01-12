package part02;

import console.Console;

/**
 * This class represents a menu interface for user interaction.
 */
public class ConsoleMenu {
	private String items[]; // Array of menu options for user selection
	private String title; // Title of menu

	/**
	 * Constructor for Menu class.
	 * 
	 * @param title - The title of the menu.
	 * @param data  - The menu options for user selection.
	 */
	public ConsoleMenu(String title, String data[]) {
		this.title = title;
		this.items = data;

	}

	/**
	 * Displays the title followed by a line of plus symbols ('+') matching the
	 * length of the title. Then, it displays a numbered list of items contained in
	 * the 'items' array.
	 * 
	 */
	private void display(Console con) {
		// Display the title
		con.println(title);
		// Display a line of plus symbols ('+') matching the length of the title
		for (int count = 0; count < title.length(); count++) {
			con.print("+");
		}
		con.println();
		// Display a numbered list of items contained in the 'items' array
		for (int option = 1; option <= items.length; option++) {
			con.println(option + ". " + items[option - 1]);
		}
		con.println();
	}
	
	/**
	 * Displays a menu of options using the 'display()' method and prompts the user
	 * to enter their selection.
	 * 
	 * @return The integer value entered by the user as their selection. If an
	 *         invalid input is provided (such as a non-integer value), returns 0.
	 */
	public int getUserChoice(Console con) {
		display(con);
		int value = 0;
		try {
			// Prompt the user to enter their selection
			con.print("Enter Selection: ");
			// Read the user's input as an integer
			value = Integer.parseInt(con.readLn());

		} catch (Exception e) {
		}
		return value;
	}
}
