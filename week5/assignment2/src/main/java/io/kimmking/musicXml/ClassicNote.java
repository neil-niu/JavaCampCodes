package io.kimmking.musicXml;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ClassicNote implements Note{
    private String title = "Piano Concerto No.21 in C Major, K.467";
    private String artist = "Mozart";
    private int id;
    private String name;

    public void play() {
        System.out.println("under xml wiring---->Playing " + title + " by " + artist);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
