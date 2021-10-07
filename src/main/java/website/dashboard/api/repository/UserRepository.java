package website.dashboard.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import website.dashboard.api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
