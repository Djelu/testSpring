package com.fantasy.main;

import com.fantasy.dao.interfaces.MP3Dao;
import com.fantasy.dao.objects.Author;
import com.fantasy.dao.objects.MP3;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Djelu on 31.08.2017.
 */
public class Start {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("DAOContext.xml");

        MP3Dao mp3Dao = (MP3Dao) context.getBean("sqliteDAO");
//        sqLiteDAO.insertWithJDBC();
//        mp3Dao.insert(new MP3("Rhapsody of Fire", "Emerald sword"));
        List<MP3> mp3s = new ArrayList<>();
//        mp3s.add(new MP3(1,"Song №4", new Author(1,"Black Eye Peace")));
//        mp3s.add(new MP3(2,"Sunrise3", new Author(2,"Justin Timberlake")));
        mp3s.add(new MP3(3,"Imago", new Author(3,"Catharsis")));
        mp3s.add(new MP3(3,"Pururu", new Author(3,"Ururu")));

        System.out.println(mp3Dao.insertList(mp3s));
    }
}
