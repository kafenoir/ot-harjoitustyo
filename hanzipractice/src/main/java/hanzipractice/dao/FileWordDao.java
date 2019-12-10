package hanzipractice.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import hanzipractice.domain.Word;

/**
 * class handles the reading of the dictionary file
 */
public class FileWordDao implements WordDao {

    private List<Word> words;
    private String file;

    public FileWordDao(String file) throws Exception {
        words = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                Word w = new Word(Integer.valueOf(parts[0]), parts[1], parts[2], parts[3]);
                words.add(w);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }

    @Override
    public List<Word> getAll() {
        return words;
    }

    @Override
    public Word findByID(int id) {
        return words.stream()
                .filter(w -> w.getID() == id)
                .findFirst()
                .orElse(null);
    }

}
