package spring.boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.data.ImageRespository;
import spring.boot.model.ImageModel;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService{

    private final ImageRespository imageRespository;

    public ImageServiceImpl(ImageRespository imageRespository){
        this.imageRespository = imageRespository;
    }

    @Override
    public ImageModel create(ImageModel image) {
        return imageRespository.save(image);
    }

    @Override
    public List<ImageModel> viewAll() {
        return (List<ImageModel>) imageRespository.findAll();
    }

    @Override
    public ImageModel viewById(long id) {
        return imageRespository.findById(id).get();
    }
}
