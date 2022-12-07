package domain;

import java.util.ArrayList;

public interface IMediaOverview {
    ArrayList<Media> getMediaList();

    ArrayList<Media> searchMedia(String keyword);

    ArrayList<Media> searchCategories(ArrayList<String> categories);

    ArrayList<Media> getFavoriteMedia();

    ArrayList<String> getCategories();

    void saveFavorite(Media media);

    void removeFavorite(Media media);

    void initialize();
}