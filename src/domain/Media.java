package domain;

import java.io.IOException;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public abstract class Media {

    private String name;
    private List<String> categories;
    private double rating;
    private String year;
    private BufferedImage poster;
    protected Map<String, List<Video>> infoMap;
    protected Type type;
    
    enum Type {
        MOVIE,
        SERIES
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
            System.out.println(path+name+".jpg");
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

    public Map<String, List<Video>> getInfoMap(){
        return this.infoMap;
    }

    public String toString(){
        return this.name + "; " + this.year + "; " + String.join(", ", this.categories) + "; " + this.rating;
    }

    public Type getType(){
        return this.type;
    }
    public void loadData(List<List<String>> mediaFile, List<Media> targetList) {
        for (List<String> media : mediaFile) {
            int listLength = mediaFile.size();
            String name = media.get(0);
            String year = media.get(1);
            List<String> category = new ArrayList<>(Arrays.asList(media.get(2).split("\\s*,\\s*")));
            double rating = Double.valueOf(media.get(3).replace(",", "."));
            if (listLength == 4) {
                String seasonToEpisode = media.get(4);
                String posterPath = "Data/serieforsider/";
                targetList.add(new Series(name, category, rating, year, posterPath, seasonToEpisode));
            } else {
                String posterPath = "Data/filmplakater/";
                targetList.add(new Movie(name, category, rating, year, posterPath));
            }
            for (String string : category) {
                categories.add(string);
            }
        }
    }

}
