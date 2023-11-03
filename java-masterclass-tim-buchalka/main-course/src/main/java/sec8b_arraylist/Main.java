package sec8b_arraylist;

import java.util.Scanner;
import java.util.ArrayList;

// arraylists handles resizing automatically
// can only hold objects (reference types)

public class Main {
	private static Scanner scanner = new Scanner(System.in);
	private static GroceryList groceryList = new GroceryList();

	public static void main(String[] args) {
		boolean quit = false;
		int choice = 0;
		printInstructions();
		while(!quit) {
			System.out.println("Enter your choice: ");
			choice = scanner.nextInt();
			scanner.nextLine();			// to clear input buffer;

			switch (choice) {
				case 0:
					printInstructions();;
					break;
				case 1:
					groceryList.printGroceryList();
					break;
				case 2:
					addItem();
					break;
				case 3:
					modifyItem();
					break;
				case 4:
					removeItem();
					break;
				case 5:
					searchForItem();
					break;
				default: //case 6:
					quit = true;
					System.out.printf("Application exit!");
					break;
			}
		}
	}

	public static void printInstructions() {
		System.out.println("\nPress ");
		System.out.println("\t 0 - To print choice options.");
		System.out.println("\t 1 - To print the list of grocery items.");
		System.out.println("\t 2 - To add an item to the list.");
		System.out.println("\t 3 - To modify an item in the list.");
		System.out.println("\t 4 - To remove an item from the list.");
		System.out.println("\t 5 - To search for an item in the list.");
		System.out.println("\t 6 - To quit the application.");
	}

	public static void addItem() {
		System.out.print("Please enter the grocery item: ");
		groceryList.addGroceryItem(scanner.nextLine());
	}

	public static void modifyItem() {
		System.out.print("Enter item name: ");
		String itemNo = scanner.nextLine();
		System.out.print("Enter replacement item: ");
		String newItem = scanner.nextLine();
		groceryList.modifyGroceryItem(itemNo, newItem);		// to modify index = option - 1
	}

	public static void removeItem() {
		System.out.print("Enter item name: ");
		String itemName = scanner.nextLine();
		scanner.nextLine();
		groceryList.removeGroceryItem(itemName);

	}

	public static void searchForItem() {
		System.out.print("Item to search for: ");
		String searchItem = scanner.nextLine();
		if (groceryList.onFile(searchItem)) {
			System.out.println("Found " + searchItem);
		} else {
			System.out.println(searchItem + ", not on file.");
		}
	}

	public static void processArrayList() {
		// var I
		ArrayList<String> newArray = new ArrayList<String>();
		newArray.addAll(groceryList.getGroceryList());

		// var II
		ArrayList<String> nextArray = new ArrayList<String>(groceryList.getGroceryList());

		// var III
		String[] myArray = new String[groceryList.getGroceryList().size()];
		myArray = groceryList.getGroceryList().toArray(myArray);
	}
}
