package spring.boot.services;

import spring.boot.model.ImageModel;

import java.util.List;

public interface ImageService {
    public ImageModel create(ImageModel image);
    public List<ImageModel> viewAll();
    public ImageModel viewById(long id);
}
