package domain;

import java.util.List;
public class Movie extends Media{

    private String playMessage;


    public Movie(String name, List<String> categories, double rating, String year, Poster poster){
        super(name, categories, rating, year, poster);
        this.playMessage = "You are now playing " + super.getName();
    }

    public String getPlayMessage(){
        return this.playMessage;
    }

}
