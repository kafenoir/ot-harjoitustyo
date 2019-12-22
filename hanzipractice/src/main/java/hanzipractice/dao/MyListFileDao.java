package hanzipractice.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import hanzipractice.domain.MyList;
import java.util.HashMap;

public class MyListFileDao implements MyListDao {

    private String file;
    private HashMap<String, ArrayList<Integer>> myLists;

    public MyListFileDao(String file) throws Exception {

        myLists = new HashMap<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));

            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                String username = parts[0];
                ArrayList<Integer> words = new ArrayList<>();
                if (parts.length > 1) {
                    for (int i = 1; i < parts.length; i++) {
                        words.add(Integer.valueOf(parts[i]));
                    }
                }
                myLists.put(username, words);
            }

        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }

    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {

            for (HashMap.Entry<String, ArrayList<Integer>> entry : myLists.entrySet()) {
                String user = entry.getKey();
                ArrayList<Integer> wordlist = entry.getValue();
                writer.write(user + ";");
                if (!wordlist.isEmpty()) {
                    for (int i = 0; i < wordlist.size() - 1; i++) {
                        writer.write(wordlist.get(i) + ";");
                    }
                    writer.write(wordlist.get(wordlist.size() - 1) + "\n");
                }
            }
        }

    }

    @Override
    public boolean edit(HashMap<String, ArrayList<Integer>> ml) throws Exception {
        this.myLists = ml;
        save();
        return true;
    }

    @Override
    public HashMap<String, ArrayList<Integer>> getAll() {
        return myLists;
    }
}
