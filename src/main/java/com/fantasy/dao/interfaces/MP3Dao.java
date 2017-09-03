package com.fantasy.dao.interfaces;

import com.fantasy.dao.objects.MP3;

import java.util.List;
import java.util.Map;

/**
 * Created by Djelu on 03.09.2017.
 */
public interface MP3Dao {

    int insertAndGetId(MP3 mp3);
    void insert(List<MP3> mp3List);
    void insert(MP3 mp3);

    void delete(int id);
    void delete(MP3 mp3);

    int getMP3Count();
    Map<String, Integer> getStat();

    MP3 getMP3ByID(int id);
    List<MP3> getMP3ListByName(String name);
    List<MP3> getMP3ListByAuthor(String author);

}
