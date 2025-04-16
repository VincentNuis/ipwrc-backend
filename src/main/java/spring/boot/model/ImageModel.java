package spring.boot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.sql.Blob;
import java.util.Date;

@Entity
public class ImageModel {
    @Id
    private long id;
    @Lob
    private Blob image;
    private Date date = new Date();

    public long getId(){
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Blob getImage() {
        return image;
    }
    public void setImage(Blob image) {
        this.image = image;
    }
    public Date getDate() {
        return date;
    }
}
