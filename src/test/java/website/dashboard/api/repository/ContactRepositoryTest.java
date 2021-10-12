package website.dashboard.api.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import website.dashboard.api.model.Address;
import website.dashboard.api.model.Contact;
import website.dashboard.api.util.AddressCreator;
import website.dashboard.api.util.ContactCreator;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@DisplayName("Tests for Contact repository")
class ContactRepositoryTest {
    @Autowired
    private ContactRepository contactRepository;


    @Test
    @DisplayName("Save persists contact when successful")
    void save_PersistsContact_WhenSuccessful() {
        Contact contactToBeSave = ContactCreator.createContactToBeSave();
        Contact contactSaved = this.contactRepository.save(contactToBeSave);

        Assertions.assertThat(contactSaved).isNotNull();
        Assertions.assertThat(contactSaved.getId()).isNotNull();
        Assertions.assertThat(contactSaved.getEmail()).isEqualTo(contactToBeSave.getEmail());
        Assertions.assertThat(contactSaved.getPhone()).isEqualTo(contactToBeSave.getPhone());


    }
    @Test
    @DisplayName("Save updates contact when successful")
    void save_UpdatesContact_WhenSuccessful() {
        Contact contactToBeSave = ContactCreator.createContactToBeSave();
        Contact contactSaved = this.contactRepository.save(contactToBeSave);
        contactSaved = ContactCreator.createValidContactToUpdate();
        Contact contactUpdated = this.contactRepository.save(contactSaved);

        Assertions.assertThat(contactUpdated).isNotNull();
        Assertions.assertThat(contactUpdated.getId()).isNotNull();
        Assertions.assertThat(contactUpdated.getEmail()).isEqualTo(contactSaved.getEmail());
        Assertions.assertThat(contactUpdated.getPhone()).isEqualTo(contactSaved.getPhone());


    }
    @Test
    @DisplayName("findById returns  contact when successful")
    void findById_ReturnsContact_WhenSuccessful() {
        Contact contactToBeSave = ContactCreator.createContactToBeSave();
        Contact contactSaved = this.contactRepository.save(contactToBeSave);

        Optional<Contact> returnsOfFindById = this.contactRepository.findById(contactSaved.getId());

        Assertions.assertThat(returnsOfFindById).isNotNull();
        Assertions.assertThat(returnsOfFindById.get().getId()).isNotNull();
        Assertions.assertThat(returnsOfFindById.get().getId()).isEqualTo(contactSaved.getId());
        Assertions.assertThat(returnsOfFindById.get().getEmail()).isEqualTo(contactSaved.getEmail());
        Assertions.assertThat(returnsOfFindById.get().getPhone()).isEqualTo(contactSaved.getPhone());


    }
    @Test
    @DisplayName("findById returns empty list when contact not exists")
    void findById_ReturnsEmptyList_WhenContactNotExits() {
        Contact contactToBeSave = ContactCreator.createContactToBeSave();
         this.contactRepository.save(contactToBeSave);
         long idThatDoesNotExist = 22l;

        Optional<Contact> returnsOfFindById = this.contactRepository.findById(idThatDoesNotExist);

        Assertions.assertThat(returnsOfFindById).isEmpty();



    }
    @Test
    @DisplayName("delete removes contact when successful")
    void delete_RemovesContact_WhenSuccessful() {
        Contact contactToBeSave = ContactCreator.createContactToBeSave();
        Contact contactSaved = this.contactRepository.save(contactToBeSave);
        this.contactRepository.deleteById(contactSaved.getId());

        Optional<Contact> returnsOfFindById = this.contactRepository.findById(contactSaved.getId());

        Assertions.assertThat(returnsOfFindById).isEmpty();



    }

}