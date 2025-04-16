package spring.boot.data;

import org.springframework.data.repository.CrudRepository;
import spring.boot.model.ImageModel;

public interface ImageRespository extends CrudRepository<ImageModel, Long> {

}
