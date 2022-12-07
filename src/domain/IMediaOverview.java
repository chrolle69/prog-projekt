package domain;
import java.util.ArrayList;
import data.DataAccessImpl;
import domain.Video;
import domain.Movie;
import domain.Poster;
import domain.Series;

import java.util.Arrays;
import java.util.Map;
import java.util.List;
import domain.Media;

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