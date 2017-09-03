package com.fantasy.main;

import com.fantasy.aop.objects.CustomFileFilter;
import com.fantasy.aop.objects.FileManager;
import com.fantasy.aop.objects.FileManagerWithoutManager;
import com.fantasy.aop.objects.SomeService;
import com.fantasy.dao.impls.SQLiteDAO;
import com.fantasy.dao.interfaces.MP3Dao;
import com.fantasy.dao.objects.MP3;
import com.fantasy.impls.robot.ModelT1000;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Djelu on 31.08.2017.
 */
public class Start {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("DAOContext.xml");

        MP3Dao mp3Dao = (MP3Dao) context.getBean("sqliteDAO");
//        sqLiteDAO.insertWithJDBC();
//        mp3Dao.insert(new MP3("Rhapsody of Fire", "Emerald sword"));
        System.out.println(mp3Dao.insertAndGetId(new MP3("Author", "SongName")));
    }
}
