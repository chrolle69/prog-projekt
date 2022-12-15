package domain;

import java.util.List;
import java.util.ArrayList;

public class Movie extends Media{

    private String playMessage;

    public Movie(String name, List<String> categories, double rating, String year, String path){
        super(name, categories, rating, year, path);
        this.playMessage = "You are now playing " + super.getName();
        this.type = Type.MOVIE;
        constructInfoMap();
    }

    public String getPlayMessage(){
        return this.playMessage;
    }

    private void constructInfoMap(){
        infoMap.put("Trailer",new ArrayList<>());
        infoMap.put("Movie", new ArrayList<>());
        Video trailer = new Video("Movie Trailer 1");
        Video movie = new Video(getName());
        infoMap.get("Trailer").add(trailer);
        infoMap.get("Movie").add(movie);
    }
}
