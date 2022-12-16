package data;

import java.util.List;

public interface DataAccess {
    List<List<String>> load(String path);

    void saveFav(List<String> data);

}
