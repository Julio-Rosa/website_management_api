package website.dashboard.api.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import website.dashboard.api.model.User;
import website.dashboard.api.util.UserCreator;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests for User repository")
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("save persists user when successful")
    void save_PersistsUser_WhenSuccessful() {
        User userToBeSave = UserCreator.createUserToBeSave();
        User userSaved = this.userRepository.save(userToBeSave);

        Assertions.assertThat(userSaved).isNotNull();
        Assertions.assertThat(userSaved.getId()).isNotNull();
        Assertions.assertThat(userSaved.getName()).isEqualTo(userToBeSave.getName());
        Assertions.assertThat(userSaved.getUsername()).isEqualTo(userToBeSave.getUsername());
        Assertions.assertThat(userSaved.getPassword()).isEqualTo(userToBeSave.getPassword());
        Assertions.assertThat(userSaved.getAuthorities()).isEqualTo(userToBeSave.getAuthorities());
    }

    @Test
    @DisplayName("save updated user when successful")
    void save_UpdatedUser_WhenSuccessful() {
        User userToBeSave = UserCreator.createUserToBeSave();
        User userSaved = this.userRepository.save(userToBeSave);
        userSaved = UserCreator.createValidUserToUpdate();
        User userUpdated = this.userRepository.save(userSaved);

        Assertions.assertThat(userUpdated).isNotNull();
        Assertions.assertThat(userUpdated.getId()).isNotNull();
        Assertions.assertThat(userUpdated.getName()).isEqualTo(userSaved.getName());
        Assertions.assertThat(userUpdated.getUsername()).isEqualTo(userSaved.getUsername());
        Assertions.assertThat(userUpdated.getPassword()).isEqualTo(userSaved.getPassword());
        Assertions.assertThat(userUpdated.getAuthorities()).isEqualTo(userSaved.getAuthorities());
    }
    @Test
    @DisplayName("findById returns user when successful")
    void findById_ReturnsUser_WhenSuccessful(){
        User userToBeSave = UserCreator.createUserToBeSave();
        User userSaved = this.userRepository.save(userToBeSave);
        Optional<User> returnsOfFindById = this.userRepository.findById(userSaved.getId());

        Assertions.assertThat(returnsOfFindById).isNotEmpty();
        Assertions.assertThat(returnsOfFindById.get().getId()).isEqualTo(userSaved.getId());
        Assertions.assertThat(returnsOfFindById.get().getName()).isEqualTo(userSaved.getName());
        Assertions.assertThat(returnsOfFindById.get().getUsername()).isEqualTo(userSaved.getUsername());
        Assertions.assertThat(returnsOfFindById.get().getPassword()).isEqualTo(userSaved.getPassword());
        Assertions.assertThat(returnsOfFindById.get().getAuthorities()).isEqualTo(userSaved.getAuthorities());

    }
    @Test
    @DisplayName("findById returns empty list when user not exists")
    void findById_ReturnsEmptyList_WhenUserNotExists(){

        User userToBeSave = UserCreator.createUserToBeSave();
        User userSaved = this.userRepository.save(userToBeSave);
        long idThatDoesNotExist = 22;
        Optional<User> returnsOfFindById = this.userRepository.findById(idThatDoesNotExist);

        Assertions.assertThat(returnsOfFindById).isEmpty();

    }
    @Test
    @DisplayName("findByUsername returns user when successful")
    void findByUsername_ReturnsUser_WhenSuccessful(){
        User userToBeSave = UserCreator.createUserToBeSave();
        User userSaved = this.userRepository.save(userToBeSave);
        User returnsOfByUsername = this.userRepository.findByUsername(userSaved.getUsername());

        Assertions.assertThat(returnsOfByUsername).isNotNull();
        Assertions.assertThat(returnsOfByUsername.getUsername()).isEqualTo(userSaved.getUsername());

    }
    @Test
    @DisplayName("verifyIfExists  returns true when successful")
    void findByUsername_ReturnsTrue_WhenSuccessful(){
        User userToBeSave = UserCreator.createUserToBeSave();
        User userSaved = this.userRepository.save(userToBeSave);
        boolean returnOfExistsById = this.userRepository.existsById(userSaved.getId());

       Assertions.assertThat(returnOfExistsById).isTrue();

    }

    @Test
    @DisplayName("delete removes user when successful")
    void delete_RemovesUser_WhenSuccessful(){

        User userToBeSave = UserCreator.createUserToBeSave();
        User userSaved = this.userRepository.save(userToBeSave);
       this.userRepository.deleteById(userSaved.getId());
        Optional<User> returnsOfFindById = this.userRepository.findById(userSaved.getId());

        Assertions.assertThat(returnsOfFindById).isEmpty();

    }


}