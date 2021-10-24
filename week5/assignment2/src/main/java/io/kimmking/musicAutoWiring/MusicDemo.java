package io.kimmking.musicAutoWiring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MusicDemo {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("musicContext.xml");
//        Student student123 = context.getBean(Student.class);

        ClassicNote myNote = (ClassicNote) context.getBean(ClassicNote.class);
        myNote.play();

        Piano piano = (Piano) context.getBean(Piano.class);
        piano.play();
    }
}
