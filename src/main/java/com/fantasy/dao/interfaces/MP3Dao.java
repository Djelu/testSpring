package com.fantasy.dao.interfaces;

import com.fantasy.dao.objects.Author;
import com.fantasy.dao.objects.MP3;

import java.util.List;
import java.util.Map;

/**
 * Created by Djelu on 03.09.2017.
 */
public interface MP3Dao {

    int insertAuthor(Author author);
    int insertMP3AndGetCount(MP3 mp3);
    int insertMP3AndGetId(MP3 mp3);
    void insertMP3(List<MP3> mp3List);
    void insertMP3(MP3 mp3);
    int insertList(List<MP3> mp3List);
    int updateList(List<MP3> mp3List);

    void delete(int id);
    void delete(MP3 mp3);

    int getMP3Count();
    int getIdAuthor(String name);
    Map<String, Integer> getStat();

    MP3 getMP3ByID(int id);
    List<MP3> getMP3ListByName(String name);
    List<MP3> getMP3ListByAuthor(String author);

}
