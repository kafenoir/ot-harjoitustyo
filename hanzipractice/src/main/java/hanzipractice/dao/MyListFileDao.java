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
    private HashMap<String, int[]> myLists;

    public MyListFileDao(String file) throws Exception {

        myLists = new HashMap<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));

            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                String username = parts[0];
                int[] words = new int[parts.length - 1];
                for (int i = 1; i < parts.length; i++) {
                    words[i - 1] = Integer.valueOf(parts[i]);
                }
                myLists.put(username, words);
            }

        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }

//    public void save() throws Exception {
//        
//        try (FileWriter writer = new FileWriter(new File(file))) {
//            for (MyList myList : myLists) {
//                writer.write(myList.getUser().getUsername() + ";" + myList.getWordsAsString() + "\n");
//                
//            }
//        }
//    }
    @Override
    public HashMap<String, int[]> getAll() {
        return myLists;
    }
}
