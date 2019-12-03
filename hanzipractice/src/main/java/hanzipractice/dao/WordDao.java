package hanzipractice.dao;

import java.util.List;
import hanzipractice.domain.Word;

public interface WordDao {

    Word findByID(int id);

    List<Word> getAll();

}
