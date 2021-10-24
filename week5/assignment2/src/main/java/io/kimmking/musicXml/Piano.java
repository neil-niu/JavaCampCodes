package io.kimmking.musicXml;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class Piano implements Instrument{
    private Note note;
    private int id;
    private String name;

    @Autowired
    public Piano(Note note) {
        this.note = note;
    }

    public void play() {
        System.out.println("Now Piano playing: ");
        note.play();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
