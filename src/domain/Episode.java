package domain;

public class Episode {
    private String name;
    private String playMessage;

    public Episode(String name){
        this.name = name;
        this.playMessage = "You are now playing " + this.name;
    }
    public String getName(){
        return name;
    }
    public String getPlayMessage(){
        return this.playMessage;
    }
}
