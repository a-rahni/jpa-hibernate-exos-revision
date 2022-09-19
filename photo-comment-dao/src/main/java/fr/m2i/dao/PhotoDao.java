package fr.m2i.dao;

import fr.m2i.model.Photo;

import java.util.List;


public interface PhotoDao {

    void save(Photo photo);

    Photo findById(long id);

    List<Photo> findAll();

    void remove(Photo photo);

    void addComment(long photoId, String comment);
}
