package website.dashboard.api.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import website.dashboard.api.model.Image;
import website.dashboard.api.util.ImageCreator;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests for Image repository")
class ImageRepositoryTest {

    @Autowired
    private ImageRepository imageRepository;

    @Test
    @DisplayName("save persists image when successful")
    void save_PersistsImage_WhenSuccessful(){
        Image imageToBeSave = ImageCreator.createImageToBeSave();
        Image imageSaved = this.imageRepository.save(imageToBeSave);


        Assertions.assertThat(imageSaved).isNotNull();
        Assertions.assertThat(imageSaved.getId()).isNotNull();
        Assertions.assertThat(imageSaved.getImageUrl()).isEqualTo(imageToBeSave.getImageUrl());
        Assertions.assertThat(imageSaved.getPublicId()).isEqualTo(imageToBeSave.getPublicId());
    }
    @Test
    @DisplayName("findById returns image when successful")
    void findById_ReturnsImage_WhenSuccessful(){
        Image imageToBeSave = ImageCreator.createImageToBeSave();
        Image imageSaved = this.imageRepository.save(imageToBeSave);
        Optional<Image> returnsOfFindById = this.imageRepository.findById(imageSaved.getId());

        Assertions.assertThat(returnsOfFindById).isNotEmpty();
        Assertions.assertThat(returnsOfFindById.get().getId()).isEqualTo(imageSaved.getId());
        Assertions.assertThat(returnsOfFindById.get().getImageUrl()).isEqualTo(imageSaved.getImageUrl());
        Assertions.assertThat(returnsOfFindById.get().getPublicId()).isEqualTo(imageSaved.getPublicId());

    }
    @Test
    @DisplayName("findById returns empty list when image not exists")
    void findById_ReturnsEmptyList_WhenImageNotExists(){
        Image imageToBeSave = ImageCreator.createImageToBeSave();
        this.imageRepository.save(imageToBeSave);
        long idThatDoesNotExist =  22l;
        Optional<Image> returnsOfFindById = this.imageRepository.findById(idThatDoesNotExist);

        Assertions.assertThat(returnsOfFindById).isEmpty();


    }
    @Test
    @DisplayName("delete removes image when successful")
    void delete_RemovesImage_WhenSuccessful(){
        Image imageToBeSave = ImageCreator.createImageToBeSave();
        Image imageSaved = this.imageRepository.save(imageToBeSave);
        imageRepository.deleteById(imageSaved.getId());
        Optional<Image> returnsOfFindById = this.imageRepository.findById(imageSaved.getId());

        Assertions.assertThat(returnsOfFindById).isEmpty();


    }

}