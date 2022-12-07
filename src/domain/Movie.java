package domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import domain.Episode;
public class Movie extends Media{

    private String playMessage;



    public Movie(String name, List<String> categories, double rating, String year, Poster poster){
        super(name, categories, rating, year, poster);
        this.playMessage = "You are now playing " + super.getName();
        constructInfoMap();
    }

    public String getPlayMessage(){
        return this.playMessage;
    }

    private void constructInfoMap(){
        infoMap.put("Trailer",new ArrayList<>());
        Episode trailer = new Episode("This is the trailer for: " + this.getName());
        infoMap.get("Trailer").add(trailer);
    }
}
