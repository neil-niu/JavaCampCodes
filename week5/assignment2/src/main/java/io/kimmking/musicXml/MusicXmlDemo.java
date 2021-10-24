package io.kimmking.musicXml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MusicXmlDemo {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("musicXmlContext.xml");
//        Student student123 = context.getBean(Student.class);

        ClassicNote myNote = (ClassicNote) context.getBean(ClassicNote.class);
        myNote.play();

        Piano piano = (Piano) context.getBean(Piano.class);
        piano.play();
    }
}
