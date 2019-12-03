package hanzipractice.ui;

import java.util.Scanner;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import hanzipractice.domain.HPService;
import hanzipractice.dao.FileUserDao;
import hanzipractice.dao.FileWordDao;
import hanzipractice.dao.MyListFileDao;
import javafx.application.Application;
import javafx.stage.Stage;

public class TextUI extends Application {

    private HPService hpService;

    private Scanner reader;
    private String username;

    @Override
    public void init() throws Exception {
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));

        String userFile = properties.getProperty("userFile");
        String dictionaryFile = properties.getProperty("dictionaryFile");
        String myListFile = properties.getProperty("myListFile");

        FileUserDao userDao = new FileUserDao(userFile);
        FileWordDao wordDao = new FileWordDao(dictionaryFile);
        MyListFileDao myListDao = new MyListFileDao(myListFile);
        hpService = new HPService(userDao, wordDao, myListDao);
        hpService.readDictionary();

    }

    @Override
    public void start(Stage primaryStage) {

        reader = new Scanner(System.in);

        System.out.println("Welcome to Hanzi Practice! \n");
        startMenu();

    }

    public void startMenu() {

        while (true) {
            System.out.println("\n-- Start -- \n");
            System.out.println("1: Login");
            System.out.println("2: Create New User\n");
            System.out.println("3: Exit");

            while (true) {
                System.out.print("Select action by inputting corresponding number: ");

                try {
                    int i = Integer.valueOf(reader.nextLine());
                    if (i == 1) {
                        login();
                    } else if (i == 2) {
                        newUser();
                    } else if (i == 3) {
                        stop();
                    } else {
                        System.out.println("Invalid input!");
                    }
                } catch (Exception ex) {
                    System.out.println("Invalid input!");
                }
            }

        }
    }

    public void login() {

        System.out.println("\n-- Login -- \n");

        while (true) {

            System.out.print("Input username (or x to exit): ");
            String aUsername = reader.nextLine();

            if (aUsername.equals("x")) {
                startMenu();
            } else if (hpService.login(aUsername)) {
                this.username = aUsername;
                hpService.readMyLists();
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
            String newUsername = reader.nextLine();
            System.out.print("\nInput name: ");
            String name = reader.nextLine();

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

        System.out.println("\n-- My Word List --\n");

        hpService.printMyList();
        while (true) {
            System.out.println("Commands:");
            System.out.println("a, add words from the dictionary");
            System.out.println("r, remove a word");
            System.out.println("q, go to Main Menu");
            System.out.print("Input command: ");
            String i = reader.nextLine();

            if (i.equals("a")) {
                dictionary();
            } else if (i.equals("r")) {
                System.out.print("Remove a word by inputting its number: ");
                try {
                    int n = Integer.valueOf(reader.nextLine());
                    System.out.println(hpService.removeWordFromMyList(n));
                    hpService.printMyList();
                } catch (Exception ex) {
                    System.out.println("Invalid command!");
                }
            } else if (i.equals("q")) {
                mainMenu();
            } else {
                System.out.println("Invalid command!");
            }
        }

    }

    public void dictionary() {
        System.out.println("\n-- Dictionary --\n");
        hpService.printDictionary();
        System.out.println("Commands:");
        System.out.println("<number>, to add the corresponding word to My List ");
        System.out.println("m, go to My List");
        System.out.println("q, go to Main Menu");
        while (true) {
            System.out.print("Input command: ");
            String i = reader.nextLine();
            if (i.equals("m")) {
                myWordList();
            } else if (i.equals("q")) {
                mainMenu();
            } else if (Integer.valueOf(i) > 0 && Integer.valueOf(i) < 50) {
                hpService.addWordToMyList(Integer.valueOf(i));
                System.out.println("Word added!");

            } else {
                System.out.println("Invalid command!");
            }
        }
    }

    @Override
    public void stop() {

        System.out.println("See ya!");
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
