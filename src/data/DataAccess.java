package Data;
import java.util.List;

import Domain.Media;
public interface DataAccess {
    List<List<String>> load(String path);
    /*
    void saveFav(String path, List<Media> mediaList);
    */
}
