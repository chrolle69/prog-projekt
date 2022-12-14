import data.DataAccessImpl;
import domain.Video;
import domain.Movie;
import domain.MediaOverviewImpl;
import domain.Series;
import domain.Media;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MediaOverviewImpl OW = new MediaOverviewImpl();
        OW.addFavorite(OW.getMediaList().get(109));
        OW.addFavorite(OW.getMediaList().get(120));
        OW.addFavorite(OW.getMediaList().get(150));
        OW.addFavorite(OW.getMediaList().get(30));
        OW.addFavorite(OW.getMediaList().get(40));
        OW.removeFavorite(OW.getMediaList().get(109));
        List<Media> favList = OW.getFavoriteMedia();
        List<Media> testSearch = OW.searchMedia("Hou");
        System.out.println(testSearch);
        ArrayList<String> temptCat = new ArrayList<String>();
        temptCat.add("Action");
        temptCat.add("Horror");
        List<Media> testCatSearch = OW.searchCategories(temptCat);
        System.out.println(testCatSearch);


    }
}