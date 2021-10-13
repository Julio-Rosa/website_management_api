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
import website.dashboard.api.dto.request.ContactDTO;
import website.dashboard.api.model.Address;
import website.dashboard.api.model.Contact;
import website.dashboard.api.service.ContactService;
import website.dashboard.api.util.AddressCreator;
import website.dashboard.api.util.ContactCreator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for Contact controller")
class ContactControllerTest {

    @InjectMocks
    ContactController contactController;

    @Mock
    ContactService contactServiceMock;

    @BeforeEach
    void setup(){
        ArrayList<Contact> contactList = new ArrayList<>(List.of(ContactCreator.createValidContact()));
        BDDMockito.when(contactServiceMock.listAll())
                .thenReturn(contactList);

        BDDMockito.when(contactServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(ContactCreator.createValidContact());

        BDDMockito.when(contactServiceMock.createContact(ArgumentMatchers.any(ContactDTO.class)))
                .thenReturn(ContactCreator.createValidContact());

        BDDMockito.doNothing().when(contactServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("listAll returns a list of contacts when successful")
    void listAll_ReturnsListOfContacts_WhenSuccessful(){
        String expectedEmail = ContactCreator.createValidContact().getEmail();
        List<Contact> contacts = contactController.listAll().getBody();

        Assertions.assertThat(contacts).isNotNull().isNotEmpty().hasSize(1);
        Assertions.assertThat(contacts.get(0).getEmail()).isEqualTo(expectedEmail);

        BDDMockito.when(contactServiceMock.updateById(ArgumentMatchers.any(ContactDTO .class)))
                .thenReturn(ContactCreator.createValidContactToUpdate());

    }

    @Test
    @DisplayName("findById returns contact when successful")
    void findById_ReturnsContact_WhenSuccessful(){
        long expectedId = ContactCreator.createValidContact().getId();
        Contact contact = contactController.findById(1).getBody();

        Assertions.assertThat(contact).isNotNull();
        Assertions.assertThat(contact.getId()).isEqualTo(expectedId);


    }
    @Test
    @DisplayName("createContact returns a contact when successful")
    void createContact_ReturnsContact_WhenSuccessful(){
        ContactDTO contactDTOToBeSave = ContactCreator.createContactDTOToBeSave();
        Contact contactSaved = contactController.createContact(contactDTOToBeSave).getBody();

        Assertions.assertThat(contactSaved).isNotNull();
        Assertions.assertThat(contactSaved.getEmail()).isEqualTo(contactDTOToBeSave.getEmail());
        Assertions.assertThat(contactSaved.getPhone()).isEqualTo(contactDTOToBeSave.getPhone());

    }
    @Test
    @DisplayName("update updates contact when successful")
    void update_UpdatesContact_WhenSuccessful(){
        ResponseEntity<Contact> updatedContact = contactController.update(ContactCreator.createValidContactDTOToUpdate());

        Assertions.assertThat(updatedContact).isNotNull();
        Assertions.assertThat(updatedContact.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    @DisplayName("delete removes contact when successful")
    void delete_RemovesContact_WhenSuccessful(){


        Contact contactToDelete = contactController.createContact(ContactCreator.createValidContactDTO()).getBody();

        ResponseEntity<Void> deleted = contactController.delete(contactToDelete.getId());

        Assertions.assertThat(deleted.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }




}