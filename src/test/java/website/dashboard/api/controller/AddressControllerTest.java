package website.dashboard.api.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import website.dashboard.api.dto.request.AddressDTO;
import website.dashboard.api.model.Address;
import website.dashboard.api.service.AddressService;
import website.dashboard.api.util.AddressCreator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class AddressControllerTest {
    @InjectMocks
    private AddressController addressController;

    @Mock
    private AddressService addressServiceMock;

    @BeforeEach
    void setup(){
        List<Address> addressList = new ArrayList<>(List.of(AddressCreator.createValidAddress()));
        BDDMockito.when(addressServiceMock.listAll())
                .thenReturn(addressList);

        BDDMockito.when(addressServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(AddressCreator.createValidAddress());

        BDDMockito.when(addressServiceMock.createAddress(ArgumentMatchers.any(AddressDTO.class)))
                .thenReturn(AddressCreator.createValidAddress());

        BDDMockito.when(addressServiceMock.update(ArgumentMatchers.any(AddressDTO.class)))
                .thenReturn(AddressCreator.createValidUpdatedAddress());

        BDDMockito.doNothing().when(addressServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("ListAll return list of addresses when successful")
    void listAll_ReturnsListOfAddresses_WhenSuccessful(){
        String expectedCity = AddressCreator.createValidAddress().getCity();
        List<Address> addresses = addressController.listAll().getBody();

        Assertions.assertThat(addresses).isNotNull();
        Assertions.assertThat(addresses.toArray()).isNotEmpty().hasSize(1);
        Assertions.assertThat(addresses.get(0).getCity()).isEqualTo(expectedCity);
    }
    @Test
    @DisplayName("findById returns address when successful")
    void findById_ReturnsAddress_WhenSuccessful(){
        Long expectedId = AddressCreator.createValidAddress().getId();
        Address address = addressController.findById(1).getBody();
        Assertions.assertThat(address).isNotNull();
        Assertions.assertThat(address.getId()).isEqualTo(expectedId);
    }
    @Test
    @DisplayName("createAddress returns address when successful")
    void createAddress_ReturnsAddress_WhenSuccessful(){
        AddressDTO addressDTOToBeSave = AddressCreator.createAddressDTOToBeSave();
        Address addressSaved  = addressController.createAddress(addressDTOToBeSave).getBody();

        Assertions.assertThat(addressSaved)
                .isNotNull()
                .isEqualTo(AddressCreator.createValidAddress());
    }
    @Test
    @DisplayName("update updates address when successful")
    void update_UpdatesAddress_WhenSuccessful(){
        ResponseEntity<Address> updatedAddress = addressController.update(AddressCreator.createValidUpdatedAddressDTO());
        Assertions.assertThat(updatedAddress).isNotNull();
        Assertions.assertThat(updatedAddress.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    @DisplayName("delete removes address when successful")
    void delete_RemovesAddress_WhenSuccessful(){

        Address addressToDelete = addressController.createAddress(AddressCreator.createValidAddressDTO()).getBody();
        ResponseEntity<Void> deleted = addressController.delete(addressToDelete.getId());

        Assertions.assertThat(deleted.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}