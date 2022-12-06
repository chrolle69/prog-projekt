package domain;
import java.util.List;

public abstract class Media {
    private String name;

    private List<String> categories;

    private double rating;
    private String year;

    private Poster poster;

    public Media(String name, List<String> categories, double rating, String year, Poster poster){
        this.name = name;
        this.categories = categories;
        this.rating = rating;
        this.year = year;
        this.poster = poster;
    }

    public String getName(){
        return this.name;
    }

    public List<String> getCategories(){
        return this.categories;
    }

    public double getRating(){
        return this.rating;
    }
    public String getYear() {
        return this.year;
    }

    public Poster getPoster(){
        return this.poster;
    }

    public String toString(){
        return this.name + "; " + this.year + "; " + String.join(",", this.categories) + "; " + this.rating;
    }

}
