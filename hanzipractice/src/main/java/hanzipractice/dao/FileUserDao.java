package hanzipractice.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import hanzipractice.domain.User;

/**
 *
 * class handles the reading and writing of the file that contains names
 * andusernames
 *
 */
public class FileUserDao implements UserDao {

    private List<User> users;
    private String file;

    /**
     * reads the users file, creates users from the lines and adds them to a
     * list
     *
     * if a users file is not found, creates a new users file
     *
     * @param file the name of the file with the usernames
     * @throws Exception
     */
    public FileUserDao(String file) throws Exception {
        users = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                User u = new User(parts[0], parts[1]);
                users.add(u);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }

    /**
     * saves a new user to the users file
     *
     * @throws Exception
     */
    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (User user : users) {
                writer.write(user.getUsername() + ";" + user.getName() + "\n");
            }
        }
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    /**
     * finds users from the list based on username
     *
     * @param username
     * @return user if matching user found on the list, null if matching user
     * not found
     */
    @Override
    public User findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername()
                .equals(username))
                .findFirst()
                .orElse(null);
    }

    /**
     * adds a created user to the list, and saves it to the file
     *
     * @param user Object representing the user
     * @return Object representing the user
     * @throws Exception
     */
    @Override
    public User create(User user) throws Exception {
        users.add(user);
        save();
        return user;
    }

}
