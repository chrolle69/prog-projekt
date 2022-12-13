package domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import domain.Video;
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
        Video trailer = new Video("trailer for: " + this.getName());
        infoMap.get("Trailer").add(trailer);
    }
}
