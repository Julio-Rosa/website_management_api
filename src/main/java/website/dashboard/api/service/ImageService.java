package website.dashboard.api.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import website.dashboard.api.model.Image;
import website.dashboard.api.repository.ImageRepository;

@Service
@AllArgsConstructor
public class ImageService {

    private ImageRepository imageRepository;

    public Image uploadImage(Image image){
        return imageRepository.save(image);
    }
    public Page<Image> listAll(Pageable pageable){
        return imageRepository.findAll(pageable);
    }
    public Image findById(long id){
        return imageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image Not Found!"));
    }
    public void delete(long id){
        findById(id);
        imageRepository.deleteById(id);
    }

}
