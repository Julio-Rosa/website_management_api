package website.dashboard.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import website.dashboard.api.model.Image;
import website.dashboard.api.service.CloudinaryService;
import website.dashboard.api.service.ImageService;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("api/v1/image")
@AllArgsConstructor
public class ImageController {
    private CloudinaryService cloudinaryService;
    private ImageService imageService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Image> uploadImage(@RequestParam MultipartFile multipartFile) throws IOException {
        Map result = cloudinaryService.upload(multipartFile);
        Image image = new Image(
                (String)result.get("url"),
                (String) result.get("public_id"));

        return new ResponseEntity<>(imageService.uploadImage(image), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<Page<Image>> listAll(Pageable pageable){
        return new ResponseEntity<>(imageService.listAll(pageable), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable long id) throws IOException {
        Image image = imageService.findById(id);
        Map result = cloudinaryService.delete(image.getPublicId());
        imageService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
