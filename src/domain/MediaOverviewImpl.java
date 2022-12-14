package domain;

import data.DataAccessImpl;

import java.util.*;


public class MediaOverviewImpl implements IMediaOverview {

    private List<Media> mediaList;
    private Set<String> categories;
    private DataAccessImpl data;

    private List<Media> favoriteList;

    public MediaOverviewImpl(){
        this.mediaList = new ArrayList<>();
        this.categories = new HashSet<>();
        this.data = new DataAccessImpl();
        this.favoriteList = new ArrayList<>();
        initialize();
    }


    public List<Media> getMediaList(){
        return this.mediaList;
    }

    public Set<String> getCategories(){
        return this.categories;
    }

  

    public void loadData(List<List<String>> mediaFile, List<Media> targetList) {
        for (List<String> media : mediaFile) {
            int listLength = media.size();
            String name = media.get(0);
            String year = media.get(1);
            List<String> category = new ArrayList<>(Arrays.asList(media.get(2).split("\\s*,\\s*")));
            double rating = Double.valueOf(media.get(3).replace(",", "."));
            if (listLength == 5) {
                String seasonToEpisode = media.get(4);
                String posterPath = "Data/serieforsider/";
                targetList.add(new Series(name, category, rating, year, posterPath, seasonToEpisode));
            } else {
                String posterPath = "Data/filmplakater/";
                targetList.add(new Movie(name, category, rating, year, posterPath));
            }
                for (String string : category) {
                    categories.add(string);
                }
            }
        }

    public void initialize() {
        List<List<String>> movieFile = data.load("Data/film.txt");
        List<List<String>> seriesFile = data.load("Data/serier.txt");
        List<List<String>> favFile = data.load("Data/favourites.txt");
        loadData(movieFile, mediaList);
        loadData(seriesFile, mediaList);
        loadData(favFile, favoriteList);
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

    public List<Media> searchMedia(String keyword){
        List<Media> temptList = new ArrayList<>();
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

    public List<Media> searchCategories(List<String> categories){
        List<Media> temptList = new ArrayList<>();
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

    public List<Media> getFavoriteMedia(){
        return this.favoriteList;
    }

    public boolean isFavorite(Media media){
        for(Media favoriteMedia : favoriteList){
            if(media.getName().equals(favoriteMedia.getName())){
                return true;
            }
        }
        return false;
    }

    public void saveFavorite(){
       List<String> temptList = new ArrayList<>();
       for (Media media : getFavoriteMedia()){
           temptList.add(media.toString());
       }
       data.saveFav(temptList);
    }
}
