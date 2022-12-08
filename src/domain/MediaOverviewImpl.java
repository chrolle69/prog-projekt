package domain;

import data.DataAccessImpl;
import domain.Movie;
import domain.Series;
import domain.Media;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;




public class MediaOverviewImpl implements IMediaOverview {

    private List<Media> medias;
    private HashSet<String> categories;
    private DataAccessImpl dataAccess;

    MediaOverviewImpl(){
        this.medias = new ArrayList<>();
        this.categories = new HashSet<>();
        initialize();
    }


    public List<Media> getMedias(){
        return this.medias;
    }

    public HashSet<String> getCategories(){
        return this.categories;
    }

  

    public void initialize() {
        DataAccessImpl data = new DataAccessImpl();
        List<Media> mediaList = new ArrayList<>();
        HashSet<String> categories = new HashSet<>();
        List<List<String>> movieFile = data.load("Data/film.txt");
        List<List<String>> seriesFile = data.load("Data/serier.txt");
        for (List<String> movie : movieFile){
            String name = movie.get(0);
            String year = movie.get(1);
            List<String> category = new ArrayList<String>(Arrays.asList(movie.get(2).split("\\s*,\\s*")));
            double rating = Double.valueOf(movie.get(3).replace(",","."));
            Poster poster = null;
            mediaList.add(new Movie(name,category,rating,year,poster));
            for (String string : category) {
                categories.add(string);
            }
        }

        for (List<String> series : seriesFile){
            String name = series.get(0);
            String year = series.get(1);
            List<String> category = new ArrayList<String>(Arrays.asList(series.get(2).split("\\s*,\\s*")));
            double rating = Double.valueOf(series.get(3).replace(",","."));
            String seasonToEpisode = series.get(4);
            Poster poster = null;
            mediaList.add(new Series(name,category,rating,year,poster,seasonToEpisode));
            for (String string : category) {
                categories.add(string);
            }
        }
        this.medias = mediaList;
        this.categories = categories;
    }



}
