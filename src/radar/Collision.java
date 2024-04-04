package radar;

import java.io.Serializable;

public class Collision implements Serializable {
    private String description;
    private String time;
    private String position;


    public Collision(String description, String time, String position) {
        this.description = description;
        this.time = time;
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public String getPosition() {
        return position;
    }
}
