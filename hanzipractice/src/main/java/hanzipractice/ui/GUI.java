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
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.input.KeyCode;

public class GUI extends Application {

    private HPService hpService;

    private Scene mainMenuScene;
    private Scene newUserScene;
    private Scene loginScene;
    private Scene myListScene;
    private Scene dictionaryScene;
    private Scene practiceMenuScene;
    private Scene practiceScene;
    private Scene gameOverScene;

    private Label userLabel = new Label();

    private int buttonAction;

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
                createMyListScene(primaryStage);
            } else {
                loginMessage.setText("user does not exist");
                loginMessage.setTextFill(Color.RED);
            }
        });

        newUserButton.setOnAction(e -> {
            usernameInput.setText("");
            loginMessage.setText("");
            primaryStage.setScene(newUserScene);
        });

        exitButton.setOnAction(e -> {
            stop();
        });

        loginInput.getChildren().addAll(usernameInput, loginButton);
        loginOptions.getChildren().addAll(newUserButton, exitButton, loginMessage);
        loginPane.getChildren().addAll(titleLabel, loginInput, loginOptions);

        Scene loginScene = new Scene(loginPane, 300, 300);

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
        createNewUserButton.setOnAction(e -> {
            if (newUsernameInput.getText().length() >= 2 && newNameInput.getText().length() >= 2) {
                if(hpService.createUser(newUsernameInput.getText(), newNameInput.getText())) {
                    newUsernameInput.setText("");
                    newNameInput.setText("");
                    loginMessage.setText("New User Created!");
                    primaryStage.setScene(loginScene);
                } else {
                    userCreationMessage.setText("Username already taken!");
                }
            } else {
                userCreationMessage.setText("Username or name too short!");
            }
            
        });

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

        practiceButton.setOnAction(e -> {
            primaryStage.setScene(practiceMenuScene);
        });

        myListButton.setOnAction(e -> {
            primaryStage.setScene(myListScene);
        });

        logoutButton.setOnAction(e -> {
            hpService.logout();
            primaryStage.setScene(loginScene);
        });

        mainMenuPane.getChildren().addAll(currentUser, mainMenuTitleLabel, practiceButton, myListButton, dictionaryButton, logoutButton);
        mainMenuScene = new Scene(mainMenuPane, 300, 400);

        //practice menu scene
        VBox practiceMenuPane = new VBox(10);
        practiceMenuPane.setPadding(new Insets(20));
        practiceMenuPane.setSpacing(20);
        practiceMenuPane.setAlignment(Pos.CENTER);

        BorderPane pCurrentUser = new BorderPane();
        pCurrentUser.setRight(userLabel);

        Label practiceMenuTitleLabel = new Label("Select Practice Type");
        practiceMenuTitleLabel.setFont(Font.font(20));
        practiceMenuTitleLabel.setPadding(new Insets(10));

        Button pinyinPracticeButton = new Button("Hanzi to Pinyin");
        Button englishPracticeButton = new Button("Hanzi to English");
        Button pinEngPracticeButton = new Button("Hanzi to Pinyin and English");
        Button exitButton2 = new Button("Exit");
        pinyinPracticeButton.setPrefSize(240, 50);
        englishPracticeButton.setPrefSize(240, 50);
        pinEngPracticeButton.setPrefSize(240, 50);
        exitButton2.setPrefSize(240, 50);

        pinyinPracticeButton.setOnAction(e -> {
            createPracticeScene(primaryStage, 1);
        });
        englishPracticeButton.setOnAction(e -> {
            createPracticeScene(primaryStage, 2);
        });

        pinEngPracticeButton.setOnAction(e -> {
            createPracticeScene(primaryStage, 3);
        });

        exitButton2.setOnAction(e -> {
            primaryStage.setScene(mainMenuScene);
        });

        practiceMenuPane.getChildren().addAll(pCurrentUser, practiceMenuTitleLabel, pinyinPracticeButton, englishPracticeButton, pinEngPracticeButton, exitButton2);
        practiceMenuScene = new Scene(practiceMenuPane, 300, 400);

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

    public void createPracticeScene(Stage primaryStage, int type) {

        hpService.createPractice();
        VBox practicePane = new VBox(10);
        VBox modifiedBox = new VBox(10);
        practicePane.setPadding(new Insets(20));

        Label askedWordLabel = new Label(hpService.askQuestion());
        askedWordLabel.setFont(Font.font(30));
        askedWordLabel.setPadding(new Insets(10));
        Label inputLabel = new Label("");
        Label feedbackLabel = new Label("");

        TextField answerField = new TextField();
        

        Button okButton = new Button("OK");
        Button nextButton = new Button("Next");
        Button doneButton = new Button("Done");

        doneButton.setOnAction(e -> {
            createGameOverScene(primaryStage, type);
        });

        if (type == 1) {
            inputLabel.setText("Input pinyin \n(format: lowercase letters + tone number, e.g. you3)");
        }
        if (type == 2) {
            inputLabel.setText("Input English translation \n(format: lowercase letters, e.g. apple)");
        }

        modifiedBox.getChildren().addAll(inputLabel, answerField);

        buttonAction = 0;

        answerField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                handlePracticeEvent(type, askedWordLabel, answerField, inputLabel, feedbackLabel, modifiedBox, primaryStage);
            }
        });

        okButton.setOnAction(e -> {
            handlePracticeEvent(type, askedWordLabel, answerField, inputLabel, feedbackLabel, modifiedBox, primaryStage);
        });

        practicePane.getChildren().addAll(askedWordLabel, modifiedBox, okButton, feedbackLabel);

        practiceScene = new Scene(practicePane, 500, 300);
        primaryStage.setScene(practiceScene);

    }

    public void handlePracticeEvent(int type, Label askedWordLabel, TextField answerField, Label inputLabel, Label feedbackLabel, VBox modifiedBox, Stage primaryStage) {

        if (buttonAction % 2 == 0) {

            String answer = answerField.getText();
            if (hpService.isCorrect(answer, type)) {
                feedbackLabel.setText("Correct!");
                feedbackLabel.setTextFill(Color.GREEN);

            } else {
                feedbackLabel.setText("Incorrect!");
                feedbackLabel.setTextFill(Color.RED);
            }

            modifiedBox.getChildren().clear();

        } else {

            modifiedBox.getChildren().addAll(inputLabel, answerField);
            answerField.requestFocus();

            if (hpService.isOver()) {
                createGameOverScene(primaryStage, type);

            }

            answerField.setText("");
            askedWordLabel.setText(hpService.askQuestion());

        }

        buttonAction++;
    }

    public void createGameOverScene(Stage primaryStage, int type) {
        VBox gameOverPane = new VBox(10);
        gameOverPane.setPadding(new Insets(20));
        gameOverPane.setAlignment(Pos.CENTER);

        Label gameOverLabel = new Label("Score");
        gameOverLabel.setFont(Font.font(20));
        Label scoreLabel = new Label("Your scored " + hpService.gameOver());
        scoreLabel.setFont(Font.font(16));

        Button tryAgainButton = new Button("Try again");
        Button exitButton = new Button("Exit");

        tryAgainButton.setOnAction(e -> {
            createPracticeScene(primaryStage, type);
        });

        exitButton.setOnAction(e -> {
            primaryStage.setScene(practiceMenuScene);
        });

        gameOverPane.getChildren().addAll(gameOverLabel, scoreLabel, tryAgainButton, exitButton);
        gameOverScene = new Scene(gameOverPane, 200, 200);
        primaryStage.setScene(gameOverScene);
    }

    public void createMyListScene(Stage primaryStage) {

        hpService.createMyList();

        BorderPane myListNodes = new BorderPane();
        VBox myListBox = new VBox();
        VBox dictionaryBox = new VBox();
        VBox buttonsBox = new VBox();

        Label myListLabel = new Label("My Word List");
        myListLabel.setFont(Font.font(16));
        ObservableList<String> mList = FXCollections.observableArrayList(hpService.myListAsStrings());
        ListView<String> mWords = new ListView<>(mList);
        mWords.setOrientation(Orientation.VERTICAL);
        mWords.setPrefSize(300, 600);
        myListBox.setSpacing(10);
        myListBox.getChildren().addAll(myListLabel, mWords);

        Label dictionaryLabel = new Label("Dictionary");
        dictionaryLabel.setFont(Font.font(16));
        ObservableList<String> dList = FXCollections.observableArrayList(hpService.dictionaryAsStrings());
        ListView<String> dWords = new ListView<>(dList);

        dWords.setOrientation(Orientation.VERTICAL);
        dWords.setPrefSize(300, 600);
        dictionaryBox.setSpacing(10);
        dictionaryBox.getChildren().addAll(dictionaryLabel, dWords);

        Button addWordButton = new Button("Add >>");
        addWordButton.setDisable(true);
        Button removeWordButton = new Button("Remove");
        removeWordButton.setDisable(true);
        Button exitButton = new Button("Exit");
        buttonsBox.getChildren().addAll(addWordButton, removeWordButton, exitButton);
        buttonsBox.setSpacing(20);
        buttonsBox.setPadding(new Insets(100, 0, 0, 5));

        dWords.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            String selectedItem = dWords.getSelectionModel().getSelectedItem();
            int index = dWords.getSelectionModel().getSelectedIndex();
            addWordButton.setDisable(false);
            removeWordButton.setDisable(true);
            addWordButton.setOnAction(e -> {
                hpService.addWordToMyList(selectedItem);
                mWords.getItems().add(selectedItem);
            });

        });

        mWords.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            String selectedItem = mWords.getSelectionModel().getSelectedItem();
            int index = mWords.getSelectionModel().getSelectedIndex();
            addWordButton.setDisable(true);
            removeWordButton.setDisable(false);
            removeWordButton.setOnAction(e -> {
                hpService.removeWordFromMyList(selectedItem);
                mWords.getItems().remove(index);
            });

        });

        exitButton.setOnAction(e -> {
            primaryStage.setScene(mainMenuScene);
        });

        myListNodes.setLeft(dictionaryBox);
        myListNodes.setCenter(buttonsBox);
        myListNodes.setRight(myListBox);
        myListNodes.setPadding(new Insets(20));

        myListScene = new Scene(myListNodes, 720, 600);

    }

    public void practice() {

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
