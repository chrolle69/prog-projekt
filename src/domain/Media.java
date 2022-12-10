package domain;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
public abstract class Media {
    private String name;

    private List<String> categories;

    private double rating;
    private String year;

    private BufferedImage poster;

    protected Map<String, List<Video>> infoMap;
    protected Type type;

    enum Type
    {
        MOVIE,
        SERIE
    }

    public Media(String name, List<String> categories, double rating, String year, String path){
        this.name = name;
        this.categories = categories;
        this.rating = rating;
        this.year = year;
        try{
            this.poster = ImageIO.read(new File(path+name+".jpg"));
        }catch (IOException e){
            System.out.println("Poster not found for " + name);
        }
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

    public BufferedImage getPoster(){
        return this.poster;
    }

    public Map<String,List<Video>> getInfoMap(){
        return this.infoMap;
    }

    public Type getType() {
        return type;
    }

    public String toString(){
        return this.name + "; " + this.year + "; " + String.join(",", this.categories) + "; " + this.rating;
    }

}
