package hanzipractice.ui;

import java.util.Scanner;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import hanzipractice.domain.HPService;
import hanzipractice.dao.FileUserDao;

public class TextUI {

    private HPService hpService;

    private Scanner reader;
    private String username;

    public void launchUI() {

        try {
            init();
        } catch (Exception e) {
            System.exit(1);
        }

        reader = new Scanner(System.in);

        System.out.println("Welcome to Hanzi Practice! \n");
        startMenu();

    }

    public void init() throws Exception {
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));

        String userFile = properties.getProperty("userFile");
        String dictionaryFile = properties.getProperty("dictionaryFile");

        FileUserDao userDao = new FileUserDao(userFile);
        hpService = new HPService(userDao);

    }

    public void startMenu() {

        while (true) {
            System.out.println("\n-- Start -- \n");
            System.out.println("1: Login");
            System.out.println("2: Create New User\n");
            System.out.print("Select action by inputting corresponding number: ");
            int i = Integer.valueOf(reader.nextLine());

            if (i == 1) {
                login();
            }
            if (i == 2) {
                newUser();
            }
        }
    }

    public void login() {

        System.out.println("\n-- Login -- \n");

        while (true) {

            System.out.println("input username (or x to exit)");
            String aUsername = reader.nextLine();

            if (aUsername.equals("x")) {
                startMenu();
            } else if (hpService.login(aUsername)) {
                this.username = aUsername;
                mainMenu();
            } else {
                System.out.println("User does not exist!");
            }
        }

    }

    public void newUser() {

        System.out.println("\n-- Create New User -- \n");

        while (true) {
            System.out.print("Input username: ");
            String name = reader.nextLine();
            System.out.print("\nInput name: ");
            String newUsername = reader.nextLine();

            if (newUsername.length() < 2 || name.length() < 2) {

                System.out.println("Username or name too short!");

            } else if (hpService.createUser(newUsername, name)) {
                System.out.println("\nNew user created!");
                login();
            } else {
                System.out.println("\nUsername has to be unique!");
            }
        }

    }

    public void mainMenu() {

        System.out.println("\n-- Main Menu -- user:" + username + "\n");

        while (true) {
            System.out.println("1: Practice!");
            System.out.println("2: My Word List");
            System.out.println("3: Dictionary");
            System.out.println("4: Logout");
            System.out.print("Select action by inputting corresponding number: ");
            int i = Integer.valueOf(reader.nextLine());

            if (i == 1) {
                practice();
            }
            if (i == 2) {
                myWordList();
            }
            if (i == 3) {
                dictionary();
            }
            if (i == 4) {
                hpService.logout();
                this.username = null;
                startMenu();
            }
        }
    }

    public void practice() {

        System.out.println("\n-- Practice! -- \n");

        while (true) {

            System.out.println("1: Hanzi to Pinyin");
            System.out.println("2: Hanzi to English");
            System.out.println("3. Exit");

            int i = Integer.valueOf(reader.nextLine());

            if (i == 1) {
                System.out.println("Under construction!");
            }
            if (i == 2) {
                System.out.println("Under construction!");
            }
            if (i == 3) {
                mainMenu();
            }
        }
    }

    public void myWordList() {

        System.out.println("WIP");
    }

    public void dictionary() {
        System.out.println("WIP");
    }

}
