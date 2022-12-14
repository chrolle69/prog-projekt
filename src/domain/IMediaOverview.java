package domain;

import java.util.List;
import java.util.Set;

public interface IMediaOverview {
    
    List<Media> getMediaList();

    List<Media> searchMedia(String keyword);

    List<Media> searchCategories(List<String> categories);

    List<Media> getFavoriteMedia();

    Set<String> getCategories();

    void saveFavorite();

    void removeFavorite(Media media);

    void addFavorite(Media media);

    boolean isFavorite(Media media);

    void initialize();
}