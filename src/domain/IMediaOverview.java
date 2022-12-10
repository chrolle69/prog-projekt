package domain;
import java.util.ArrayList;
import data.DataAccessImpl;

import java.util.Arrays;
import java.util.Map;

import domain.Movie;
import domain.Series;
import domain.Video;

import java.util.List;
import java.util.HashSet;


public interface IMediaOverview {
    List<Media> getMedias();

    ArrayList<Media> searchMedia(String keyword);

    ArrayList<Media> searchCategories(ArrayList<String> categories);

    ArrayList<Media> getFavoriteMedia();

    HashSet<String> getCategories();

    void saveFavorite(Media media);

    void removeFavorite(Media media);

    void initialize();
}