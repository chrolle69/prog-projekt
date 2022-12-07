package domain;

public class Video {
    private String name;
    private String playMessage;

    public Video(String name){
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
