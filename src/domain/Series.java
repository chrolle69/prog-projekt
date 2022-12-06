package domain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.*;

public class Series extends Media{
    private Map<Integer,List<Episode>> seasonToEpisode;
    public Series(String name, List<String> categories, double rating, String year, Poster poster, String seasonEpisodes){
        super(name, categories, rating, year, poster);
        this.seasonToEpisode = new HashMap<>();
        constructSeasonEpisodeMap(seasonEpisodes);
    }

    private void constructSeasonEpisodeMap(String seasonEpisodes){
        //Use regex to find all patterns of [number]-[number] to get season-episodeNumber
        Pattern pattern = Pattern.compile("(([0-9]+)-([0-9]+))+");
        Matcher matcher = pattern.matcher(seasonEpisodes);
        //Iterate through all matches
        while (matcher.find()){
            //Tempt arraylist to make Episode objects and later add that arraylist to the seasonToEpisode hashmap together with the season number
            List<Episode> temptEpisodeList = new ArrayList<>();
            //matcher.group(2) is the season number and matcher.group(3) is the amount of episodes
            for (int i = 1; i <= Integer.valueOf(matcher.group(3)); i++){
                temptEpisodeList.add(new Episode("Episode " + String.valueOf(i)));
            }
            seasonToEpisode.put(Integer.valueOf(matcher.group(2)),temptEpisodeList);
            }
        }




    public Map<Integer,List<Episode>> getSeasonToEpisode(){
        return this.seasonToEpisode;
    }
}
