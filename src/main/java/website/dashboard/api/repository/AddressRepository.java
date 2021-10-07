package website.dashboard.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import website.dashboard.api.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
