package website.dashboard.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import website.dashboard.api.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
