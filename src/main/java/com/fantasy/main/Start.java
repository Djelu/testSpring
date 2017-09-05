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
        ApplicationContext context = new ClassPathXmlApplicationContext("MVCContext.xml");


    }
}
