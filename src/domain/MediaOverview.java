package domain;

import java.util.List;
import java.util.Set;

public interface MediaOverview {

    enum SearchType{
        ALL,
        MOVIE,
        SERIES
    }

    List<Media> getMediaList();

    List<Media> searchMedia(String keyword);

    List<Media> searchCategories(List<String> categories, SearchType type);

    List<Media> getFavoriteMedia();

    Set<String> getCategories();

    void saveFavorite();

    void removeFavorite(Media media);

    void addFavorite(Media media);

    boolean isFavorite(Media media);

    void initialize();
}