package domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.*;

public class Series extends Media{

    public Series(String name, List<String> categories, double rating, String year, String path, String seasonEpisodes){
        super(name, categories, rating, year, path);
        this.type = Type.SERIES;
        constructInfoMap(seasonEpisodes);
    }

    private void constructInfoMap(String seasonEpisodes){
        //Use regex to find all patterns of [number]-[number] to get season-episodeNumber
        infoMap.put("Trailer",new ArrayList<>());
        Pattern pattern = Pattern.compile("(([0-9]+)-([0-9]+))+");
        Matcher matcher = pattern.matcher(seasonEpisodes);
        //Iterate through all matches
        while (matcher.find()){
            Video trailer = new Video("Trailer for season " + matcher.group(2));
            infoMap.get("Trailer").add(trailer);
            //Tempt arraylist to make Episode objects and later add that arraylist to the seasonToEpisode hashmap together with the season number
            List<Video> temptEpisodeList = new ArrayList<>();
            //matcher.group(2) is the season number and matcher.group(3) is the amount of episodes
            for (int i = 1; i<=Integer.valueOf(matcher.group(3)); i++){
                temptEpisodeList.add(new Video("Episode " + String.valueOf(i)));
            }
            infoMap.put(matcher.group(2),temptEpisodeList);
            }

        }


    public String toString(){
        boolean isFirst = true;
        String seasonToEpisodeString = "";
        for (Map.Entry<String, List<Video>> entry : this.infoMap.entrySet()){
            if (!isFirst) {
                seasonToEpisodeString += entry.getKey() + "-" + entry.getValue().size() + ", ";
            } else{
                isFirst = false;
            }

        }
        seasonToEpisodeString = seasonToEpisodeString.substring(0, seasonToEpisodeString.length()-2);
        seasonToEpisodeString += ";";
        return super.toString() + "; " + seasonToEpisodeString;
    }
}
