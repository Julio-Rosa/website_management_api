package website.dashboard.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.dashboard.api.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
