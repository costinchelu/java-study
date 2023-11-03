package sec8b_arraylist.challengepersonalcode;

// Create a program that implements a simple mobile phone with the following capabilities.
// Able to store, modify, remove and query contact names.
// You will want to create a separate class for Contacts (name and phone number).
// Create a master class (MobilePhone) that holds the ArrayList of Contacts
// The MobilePhone class has the functionality listed above.
// Add a menu of options that are available.
// Options:  Quit, print list of contacts, add new contact, update existing contact, remove contact
// and search/find contact.
// When adding or updating be sure to check if the contact already exists (use name)
// Be sure not to expose the inner workings of the Arraylist to MobilePhone
// e.g. no ints, no .get(i) etc
// MobilePhone should do everything with Contact objects only.

import java.util.Scanner;

public class Main {
    private static Scanner input = new Scanner(System.in);
    private static MobilePhone phoneBook = new MobilePhone(35);

    public static void main(String[] args) {
        boolean quit = false;
        int choice;
        printInstructions();
        while(!quit) {
            System.out.println("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 0:
                    quit = true;
                    break;
                case 1:
                    phoneBook.printContactList();
                    break;
                case 2:
                    addNewContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    removeContact();
                    break;
                case 5:
                    findContact();
                    break;
                case 6:
                    printInstructions();
                    break;
            }
        }
    }

    private static void printInstructions() {
        System.out.println("\nPress ");
        System.out.println("\t 0 - To quit the application.");
        System.out.println("\t 1 - To print the list of contacts.");
        System.out.println("\t 2 - To add a new contact.");
        System.out.println("\t 3 - To update a contact.");
        System.out.println("\t 4 - To remove a contact.");
        System.out.println("\t 5 - To search for a contact.");
        System.out.println("\t 6 - To print instructions.");
    }

    private static void addNewContact() {
        System.out.println("Enter contact's name: ");
        String name = input.nextLine();
        System.out.println("Enter contact's phone number:");
        String phoneNo = input.nextLine();
        phoneBook.addToList(name, phoneNo);
    }

    private static void updateContact() {
        System.out.println("Enter contact's name:\r");
        String oldName = input.nextLine();
        System.out.println("Enter new contact name: (0 to keep same name)\r");
        String newName = input.nextLine();
        System.out.println("Enter new contact phone number: (0 to keep same number)\r");
        String newNo = input.nextLine();
        phoneBook.modifyEntry(oldName, newName, newNo);
    }

    private static void removeContact() {
        System.out.println("Enter contact's name:\r");
        String name = input.nextLine();
        phoneBook.removeEntry(name);
    }

    private static void findContact() {
        System.out.println("Enter contact's name:\r");
        String name = input.nextLine();
        phoneBook.findContact(name);
    }
}
