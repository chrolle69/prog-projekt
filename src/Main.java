import java.util.Arrays;
import java.util.Map;

import data.DataAccessImpl;
import domain.Media;
import domain.Movie;
import domain.Series;
import domain.Video;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        List<Movie> movieList = new ArrayList<Movie>();
        List<Series> seriesList = new ArrayList<>();
        /*
        List<String> dieHardGenre = new ArrayList<>();
        dieHardGenre.add("Action");
        dieHardGenre.add("Christmas");
        dieHardGenre.add("Love");
        String dieHardSeasonEpisodes = "1-13, 2-13, 3-13, 4-13, 5-13, 6-21";
        Series dieHard = new Series("Die hard", dieHardGenre, 9.9, "2000", null, dieHardSeasonEpisodes);
        System.out.println(dieHard.getName());
        System.out.println(dieHard.getCategories());
        System.out.println(dieHard.getRating());
        System.out.println(dieHard.getSeasonToEpisode());
        Map<Integer, List<Episode>> myMap = dieHard.getSeasonToEpisode();
        for (List<Episode> episodes : myMap.values()){
            for (Episode episode : episodes){
                System.out.println(episode.getName());
                System.out.println(episode.getPlayMessage());
            }
        }
        */
        DataAccessImpl data = new DataAccessImpl();
        List<Media> mediaList = new ArrayList<>();
        List<List<String>> movieFile = data.load("Data/film.txt");
        List<List<String>> seriesFile = data.load("Data/serier.txt");
        for (List<String> movie : movieFile){
            String name = movie.get(0);
            String year = movie.get(1);
            List<String> category = new ArrayList<String>(Arrays.asList(movie.get(2).split("\\s*,\\s*")));
            double rating = Double.valueOf(movie.get(3).replace(",","."));
            String posterPath = "Data/filmplakater/";
            mediaList.add(new Movie(name,category,rating,year,posterPath));
        }

        for (List<String> series : seriesFile){
            String name = series.get(0);
            String year = series.get(1);
            List<String> category = new ArrayList<String>(Arrays.asList(series.get(2).split("\\s*,\\s*")));
            double rating = Double.valueOf(series.get(3).replace(",","."));
            String seasonToEpisode = series.get(4);
            String posterPath = "Data/serieforsider/";
            mediaList.add(new Series(name,category,rating,year,posterPath,seasonToEpisode));
        }

        Media tempt = mediaList.get(102);
        System.out.println(tempt.toString());
        System.out.println(tempt.getInfoMap().get("Trailer").get(2).getPlayMessage());
        }
    }