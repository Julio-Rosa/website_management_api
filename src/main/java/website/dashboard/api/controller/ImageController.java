package website.dashboard.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Upload images")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The image was uploaded successfully"),

    })
    public ResponseEntity<Image> uploadImage(@RequestPart(value = "multipartFile", required = true)MultipartFile multipartFile) throws IOException {
        Map result = cloudinaryService.upload(multipartFile);
        Image image = new Image(
                (String)result.get("url"),
                (String) result.get("public_id"));

        return new ResponseEntity<>(imageService.uploadImage(image), HttpStatus.CREATED);
    }
    @GetMapping
    @Operation(summary = "List all images")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),

    })
    public ResponseEntity<Page<Image>> listAll(@Parameter(hidden = true) Pageable pageable){
        return new ResponseEntity<>(imageService.listAll(pageable), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a image by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "When the image is successfully deleted"),
            @ApiResponse(responseCode = "404", description = "When the image with this id not exists"),

    })
    public ResponseEntity<Void> delete(@PathVariable long id) throws IOException {
        Image image = imageService.findById(id);
        Map result = cloudinaryService.delete(image.getPublicId());
        imageService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
