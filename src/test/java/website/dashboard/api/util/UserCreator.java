package website.dashboard.api.util;

import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import website.dashboard.api.model.User;

@DataJpaTest
@DisplayName("Tests for User repository")
public class UserCreator {
    private static final long ID =1;
    private static  final String NAME = "Name";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "@Password5858";
    private static final String AUTHORITIES = "ROLE_ADMIN";

    private static  final String NAME_UPDATE = "Name Updated ";
    private static final String USERNAME_UPDATE = "Username Updated";
    private static final String PASSWORD_UPDATE = "@Password5858Updated";
    private static final String AUTHORITIES_UPDATED = "ROLE_USER";
    public static User createUserToBeSave(){
        return User.builder()
                .name(NAME)
                .username(USERNAME)
                .password(PASSWORD)
                .authorities(AUTHORITIES)
                .build();
    }
    public static User createValidUser(){
        return User.builder()
                .id(ID)
                .name(NAME)
                .username(USERNAME)
                .password(PASSWORD)
                .authorities(AUTHORITIES)
                .build();
    }
    public static User createValidUserToUpdate(){
        return User.builder()
                .id(ID)
                .name(NAME_UPDATE)
                .username(USERNAME_UPDATE)
                .password(PASSWORD_UPDATE)
                .authorities(AUTHORITIES_UPDATED)
                .build();
    }
}
