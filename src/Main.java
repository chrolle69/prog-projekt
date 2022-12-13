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
        ArrayList<String> categories = new ArrayList<>();
        ArrayList<Media> mediaList = new ArrayList<>();
        DataAccessImpl data = new DataAccessImpl();
        List<List<String>> movieFile = data.load("Data/film.txt");
        List<List<String>> seriesFile = data.load("Data/serier.txt");
        for (List<String> movie : movieFile) {
            String name = movie.get(0);
            String year = movie.get(1);
            List<String> category = new ArrayList<>(Arrays.asList(movie.get(2).split("\\s*,\\s*")));
            double rating = Double.valueOf(movie.get(3).replace(",", "."));
            String posterPath = "Data/filmplakater/";
            mediaList.add(new Movie(name, category, rating, year, posterPath));
            for (String string : category) {
                categories.add(string);
            }
        }

        for (List<String> series : seriesFile) {
            String name = series.get(0);
            String year = series.get(1);
            List<String> category = new ArrayList<>(Arrays.asList(series.get(2).split("\\s*,\\s*")));
            double rating = Double.valueOf(series.get(3).replace(",", "."));
            String seasonToEpisode = series.get(4);
            String posterPath = "Data/serieforsider/";
            mediaList.add(new Series(name, category, rating, year, posterPath, seasonToEpisode));
            for (String string : category) {
                categories.add(string);
            }
        }
        MediaOverviewImpl OW = new MediaOverviewImpl();
        OW.addFavorite(OW.getMediaList().get(109));
        OW.addFavorite(OW.getMediaList().get(120));
        OW.addFavorite(OW.getMediaList().get(150));
        OW.addFavorite(OW.getMediaList().get(30));
        OW.addFavorite(OW.getMediaList().get(40));
        OW.removeFavorite(OW.getMediaList().get(109));
        ArrayList<Media> testSearch = OW.searchMedia("Hou");
        System.out.println(testSearch);
        ArrayList<String> temptCat = new ArrayList<String>();
        temptCat.add("Action");
        temptCat.add("Horror");
        ArrayList<Media> testCatSearch = OW.searchCategories(temptCat);
        System.out.println(testCatSearch);


    }
}