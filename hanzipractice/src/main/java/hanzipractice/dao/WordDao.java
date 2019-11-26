package hanzipractice.dao;

import java.util.List;
import hanzipractice.domain.Word;

public interface WordDao {

    List<Word> getAll();

    Word findByID(int id);

}
