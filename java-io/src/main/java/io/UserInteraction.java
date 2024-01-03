package io;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class UserInteraction {

    public static void main(String[] args) {
        usingConsole();
    }

    public static void usingStreamReader() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
        System.out.println(">>> " + input);
    }

    public static void usingConsole() {
        Console console = System.console();
        if (console == null) {
            throw new RuntimeException("Console not available");
        } else {
            console.writer().println("Welcome to Our Zoo!");
            console.format("It has %d animals and employs %d people", 391, 25);
            console.writer().println();
            console.printf("The zoo spans %5.1f acres", 128.91);
        }
    }

    public static void usingConsole2() {
        Console console = System.console();
        if (console == null) {
            throw new RuntimeException("Console not available");
        } else {
            String name = console.readLine("Please enter your name: ");
            console.writer().format("Hi %s", name);
            console.writer().println();
            console.format("What is your address? ");
            String address = console.readLine();
            char[] password = console.readPassword("Enter a password " + "between %d and %d characters: ", 5, 10);
            char[] verify = console.readPassword("Enter the password again: ");
            console.printf("Passwords " + (Arrays.equals(password, verify) ? "match" : "do not match"));
        }
    }
}
