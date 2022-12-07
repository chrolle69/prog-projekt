package domain;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
public abstract class Media {
    private String name;

    private List<String> categories;

    private double rating;
    private String year;

    private Poster poster;

    protected Map<String, List<Episode>> infoMap;

    public Media(String name, List<String> categories, double rating, String year, Poster poster){
        this.name = name;
        this.categories = categories;
        this.rating = rating;
        this.year = year;
        this.poster = poster;
        this.infoMap = new LinkedHashMap<>();
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

    public Map<String,List<Episode>> getInfoMap(){
        return this.infoMap;
    }

    private void constructInfoMap(){}

    public String toString(){
        return this.name + "; " + this.year + "; " + String.join(",", this.categories) + "; " + this.rating;
    }

}
