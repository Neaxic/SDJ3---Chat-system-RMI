package External;

import java.io.Serializable;

public class Message implements Serializable {
    private String text;
    private String who;

    public Message(String text) {
        this.text = text;
    }
    public Message(String who, String text){
        this.text = text;
        this.who = who;
    }

    public void setWhoText(String who, String text){
        this.text = text;
        this.who = who;
    }

    public String getText() {
        return text;
    }

    public String getWho() {
        return who;
    }

    public String getWhoText(){
        return who+": "+text;
    }
}
