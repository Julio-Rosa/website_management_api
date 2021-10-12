package website.dashboard.api.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import website.dashboard.api.model.Address;
import website.dashboard.api.util.AddressCreator;

import java.util.Optional;


@DataJpaTest
@DisplayName("Tests for Address Repository")
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;


    @Test
    @DisplayName("Save persists address when successful")
    void save_PersistsAddress_WhenSuccessful() {
        Address addressToBeSave = AddressCreator.createAddressToBeSave();
        Address addressSaved = this.addressRepository.save(addressToBeSave);

        Assertions.assertThat(addressSaved).isNotNull();
        Assertions.assertThat(addressSaved.getId()).isNotNull();
        Assertions.assertThat(addressSaved.getCity()).isEqualTo(addressToBeSave.getCity());
        Assertions.assertThat(addressSaved.getStreet()).isEqualTo(addressToBeSave.getStreet());
        Assertions.assertThat(addressSaved.getDistrict()).isEqualTo(addressToBeSave.getDistrict());
        Assertions.assertThat(addressSaved.getNumber()).isEqualTo(addressToBeSave.getNumber());
        Assertions.assertThat(addressSaved.getComplement()).isEqualTo(addressToBeSave.getComplement());

    }

    @Test
    @DisplayName("Save updates address when successful")
    void save_UpdatesAddress_WhenSuccessful() {
        Address addressToBeSave = AddressCreator.createAddressToBeSave();
        Address addressSaved = this.addressRepository.save(addressToBeSave);

        addressSaved = AddressCreator.createValidUpdatedAddress();

        Address addressUpdated = addressRepository.save(addressSaved);

        Assertions.assertThat(addressUpdated).isNotNull();
        Assertions.assertThat(addressUpdated.getId()).isNotNull();
        Assertions.assertThat(addressUpdated.getCity()).isEqualTo(addressSaved.getCity());
        Assertions.assertThat(addressUpdated.getStreet()).isEqualTo(addressSaved.getStreet());
        Assertions.assertThat(addressUpdated.getDistrict()).isEqualTo(addressSaved.getDistrict());
        Assertions.assertThat(addressUpdated.getNumber()).isEqualTo(addressSaved.getNumber());
        Assertions.assertThat(addressUpdated.getComplement()).isEqualTo(addressSaved.getComplement());

    }

    @Test
    @DisplayName("findById returns address when successful")
    void findById_ReturnAddress_WhenSuccessful() {
        Address addressToBeSave = AddressCreator.createAddressToBeSave();
        Address addressSaved = this.addressRepository.save(addressToBeSave);

        Optional<Address> returnedAddressOfFindById = this.addressRepository.findById(addressSaved.getId());

        Assertions.assertThat(returnedAddressOfFindById.get().getId()).isEqualTo(addressSaved.getId());
        Assertions.assertThat(returnedAddressOfFindById.get().getStreet()).isEqualTo(addressSaved.getStreet());
        Assertions.assertThat(returnedAddressOfFindById.get().getDistrict()).isEqualTo(addressSaved.getDistrict());
        Assertions.assertThat(returnedAddressOfFindById.get().getCity()).isEqualTo(addressSaved.getCity());
        Assertions.assertThat(returnedAddressOfFindById.get().getNumber()).isEqualTo(addressSaved.getNumber());
        Assertions.assertThat(returnedAddressOfFindById.get().getComplement()).isEqualTo(addressSaved.getComplement());
    }

    @Test
    @DisplayName("FindById returns a empty list when address not exits ")
    void findById_ReturnsEmptyList_WhenAddressNotExists(){
        Address addressToBeSaved = AddressCreator.createAddressToBeSave();
        this.addressRepository.save(addressToBeSaved);
        long idThatDoesNotExist = 22l;
        Optional<Address> returnsOfFindById = this.addressRepository.findById(idThatDoesNotExist);

        Assertions.assertThat(returnsOfFindById).isEmpty();
    }

    @Test
    @DisplayName("delete removes address when successful ")
    void delete_RemovesAddress_WhenSuccessful(){
        Address addressToBeSaved = AddressCreator.createAddressToBeSave();
        Address addressSaved = this.addressRepository.save(addressToBeSaved);

        this.addressRepository.deleteById(addressSaved.getId());
        Optional<Address> returnsOfFindById = this.addressRepository.findById(addressSaved.getId());
        Assertions.assertThat(returnsOfFindById).isEmpty();
    }




}