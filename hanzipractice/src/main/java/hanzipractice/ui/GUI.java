package hanzipractice.ui;

import java.util.Scanner;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import hanzipractice.dao.FileUserDao;
import hanzipractice.dao.FileWordDao;
import hanzipractice.dao.MyListFileDao;
import hanzipractice.domain.HPService;
import javafx.geometry.Orientation;

public class GUI extends Application {

    private HPService hpService;

    private Scene mainMenuScene;
    private Scene newUserScene;
    private Scene loginScene;
    private Scene myListScene;
    private Scene dictionaryScene;
    private Scene practiceMenuScene;

    private Label userLabel = new Label();

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
        hpService.readMyLists();

    }

    @Override
    public void start(Stage primaryStage) {

        //login scene
        VBox loginPane = new VBox(10);
        loginPane.setPadding(new Insets(40, 20, 20, 20));
        loginPane.setSpacing(20);
        loginPane.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Hanzi Practice");
        titleLabel.setFont(Font.font(20));
        titleLabel.setPadding(new Insets(10));

        Label loginLabel = new Label("username");
        TextField usernameInput = new TextField();

        HBox loginInput = new HBox(10);
        loginInput.setSpacing(10);
        loginInput.setAlignment(Pos.CENTER);

        VBox loginOptions = new VBox(10);
        loginOptions.setSpacing(10);
        loginOptions.setAlignment(Pos.CENTER);

        Label loginMessage = new Label();

        Button loginButton = new Button("Login");
        Button newUserButton = new Button("Create New User");
        newUserButton.setPrefSize(240, 50);
        Button exitButton = new Button("Exit");
        exitButton.setPrefSize(240, 50);
        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            userLabel.setText("logged in user: " + username);
            if (hpService.login(username)) {
                loginMessage.setText("");
                primaryStage.setScene(mainMenuScene);
                usernameInput.setText("");
                createMyListScene();
            } else {
                loginMessage.setText("user does not exist");
                loginMessage.setTextFill(Color.RED);
            }
        });

        newUserButton.setOnAction(e -> {
            usernameInput.setText("");
            primaryStage.setScene(newUserScene);
        });

        loginInput.getChildren().addAll(usernameInput, loginButton);
        loginOptions.getChildren().addAll(newUserButton, exitButton, loginMessage);
        loginPane.getChildren().addAll(titleLabel, loginInput, loginOptions);

        Scene loginScene = new Scene(loginPane, 300, 210);

        // newUserScene
        VBox newUserPane = new VBox(10);
        newUserPane.setPadding(new Insets(40, 20, 20, 20));
        newUserPane.setAlignment(Pos.CENTER);
        HBox newUsernamePane = new HBox(10);
        newUsernamePane.setAlignment(Pos.CENTER);

        Label newUserTitle = new Label("Create New User");
        newUserTitle.setFont(Font.font(20));
        newUserTitle.setPadding(new Insets(10));

        TextField newUsernameInput = new TextField();
        Label newUsernameLabel = new Label("Username");
        newUsernameLabel.setPrefWidth(100);
        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameInput);

        HBox newNamePane = new HBox(10);
        newNamePane.setAlignment(Pos.CENTER);
        newNamePane.setPadding(new Insets(10));
        TextField newNameInput = new TextField();
        Label newNameLabel = new Label("Name");
        newNameLabel.setPrefWidth(100);
        newNamePane.getChildren().addAll(newNameLabel, newNameInput);

        Label userCreationMessage = new Label();

        Button createNewUserButton = new Button("Create");
        createNewUserButton.setPadding(new Insets(10));
        createNewUserButton.setPrefWidth(240);

        newUserPane.getChildren().addAll(newUserTitle, newUsernamePane, newNamePane, createNewUserButton, userCreationMessage);
        newUserScene = new Scene(newUserPane, 300, 210);

        //main menu scene
        VBox mainMenuPane = new VBox(10);
        mainMenuPane.setPadding(new Insets(20));
        mainMenuPane.setSpacing(20);
        mainMenuPane.setAlignment(Pos.CENTER);

        BorderPane currentUser = new BorderPane();
        currentUser.setRight(userLabel);

        Label mainMenuTitleLabel = new Label("Main Menu");
        mainMenuTitleLabel.setFont(Font.font(20));
        mainMenuTitleLabel.setPadding(new Insets(10));

        Button practiceButton = new Button("Practice!");
        Button myListButton = new Button("My List");
        Button dictionaryButton = new Button("Dictionary");
        Button logoutButton = new Button("Logout");
        practiceButton.setPrefSize(240, 50);
        myListButton.setPrefSize(240, 50);
        dictionaryButton.setPrefSize(240, 50);
        logoutButton.setPrefSize(240, 50);

        myListButton.setOnAction(e -> {
            primaryStage.setScene(myListScene);
        });

        logoutButton.setOnAction(e -> {
            hpService.logout();
            primaryStage.setScene(loginScene);
        });

        mainMenuPane.getChildren().addAll(currentUser, mainMenuTitleLabel, practiceButton, myListButton, dictionaryButton, logoutButton);
        mainMenuScene = new Scene(mainMenuPane, 300, 400);

        //my list scene
        primaryStage.setTitle("Hanzi Practice v0.3");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            System.out.println("closing");
            System.out.println(hpService.getLoggedUser());
            if (hpService.getLoggedUser() != null) {
                e.consume();
            }
        });

    }

    public void createMyListScene() {
        hpService.createMyList();
        ObservableList<String> list = FXCollections.observableArrayList(hpService.myListAsStrings());
        ListView<String> words = new ListView<>(list);
        words.setOrientation(Orientation.VERTICAL);
        words.setPrefSize(300, 600);

        VBox wordSelection = new VBox();
        wordSelection.setSpacing(10);
        wordSelection.getChildren().add(words);

        myListScene = new Scene(wordSelection, 400, 700);

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
