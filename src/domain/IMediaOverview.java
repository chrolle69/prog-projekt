package domain;
import java.util.ArrayList;
import data.DataAccessImpl;
import domain.Video;
import domain.Movie;
import domain.Series;

import java.util.Arrays;
import java.util.Map;
import java.util.List;
import java.util.HashSet;


public interface IMediaOverview {
    ArrayList<Media> getMediaList();

    ArrayList<Media> searchMedia(String keyword);

    ArrayList<Media> searchCategories(ArrayList<String> categories);

    ArrayList<Media> getFavoriteMedia();

    HashSet<String> getCategories();

    void saveFavorite();

    void removeFavorite(Media media);

    void addFavorite(Media media);

    boolean isFavorite(Media media);

    void initialize();
}