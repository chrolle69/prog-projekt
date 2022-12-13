package domain;

import data.DataAccessImpl;
import domain.Movie;
import domain.Series;
import domain.Media;

import java.util.*;
import java.sql.Array;


public class MediaOverviewImpl implements IMediaOverview {

    private ArrayList<Media> mediaList;
    private HashSet<String> categories;
    private DataAccessImpl data;

    private ArrayList<Media> favoriteList;


    public MediaOverviewImpl(){
        this.mediaList = new ArrayList<>();
        this.categories = new HashSet<>();
        this.data = new DataAccessImpl();
        this.favoriteList = new ArrayList<>();
        initialize();
    }


    public ArrayList<Media> getMediaList(){
        return this.mediaList;
    }

    public HashSet<String> getCategories(){
        return this.categories;
    }

  

    public void initialize() {
        List<List<String>> movieFile = data.load("Data/film.txt");
        List<List<String>> seriesFile = data.load("Data/serier.txt");
        List<List<String>> favFile = data.load("Data/favourites.txt");
        for (List<String> movie : movieFile){
            String name = movie.get(0);
            String year = movie.get(1);
            List<String> category = new ArrayList<>(Arrays.asList(movie.get(2).split("\\s*,\\s*")));
            double rating = Double.valueOf(movie.get(3).replace(",","."));
            String posterPath = "Data/filmplakater/";
            mediaList.add(new Movie(name,category,rating,year,posterPath));
            for (String string : category) {
                categories.add(string);
            }
        }

        for (List<String> series : seriesFile){
            String name = series.get(0);
            String year = series.get(1);
            List<String> category = new ArrayList<>(Arrays.asList(series.get(2).split("\\s*,\\s*")));
            double rating = Double.valueOf(series.get(3).replace(",","."));
            String seasonToEpisode = series.get(4);
            String posterPath = "Data/serieforsider/";
            mediaList.add(new Series(name,category,rating,year,posterPath,seasonToEpisode));
            for (String string : category) {
                categories.add(string);
            }
        }
    }
    public void removeFavorite(Media media){
        int indexer = 0;
        for (Media medias : getFavoriteMedia()){
            if (medias.getName() == media.getName()){
                this.favoriteList.remove(indexer);
                break;
            }
        indexer++;
        }

        saveFavorite();
    }

    public void addFavorite(Media media){
        favoriteList.add(media);
        saveFavorite();
    }

    public ArrayList<Media> searchMedia(String keyword){
        ArrayList<Media> temptList = new ArrayList<>();
        if (keyword.isEmpty()){
            return getMediaList();
        }
        for (Media media : getMediaList()){
            String currentName = media.getName().toLowerCase();
            if (currentName.contains(keyword.toLowerCase())){
                temptList.add(media);
            }
        }
        return temptList;
    }

    public ArrayList<Media> searchCategories(ArrayList<String> categories){
        ArrayList<Media> temptList = new ArrayList<>();
        if(categories.size() == 0){
            return getMediaList();
        }
        for (Media media : mediaList){
            if (!Collections.disjoint(media.getCategories(),categories)){
                temptList.add(media);
            }
        }
        return temptList;
    }

    public ArrayList<Media> getFavoriteMedia(){
        return this.favoriteList;
    }

    public void saveFavorite(){
       List<String> temptList = new ArrayList<>();
       for (Media media : getFavoriteMedia()){
           temptList.add(media.toString());
       }
       data.saveFav(temptList);
    }
}
