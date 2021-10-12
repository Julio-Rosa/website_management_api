package website.dashboard.api.util;

import website.dashboard.api.model.Address;
import website.dashboard.api.model.Contact;

public class ContactCreator {
    private static long ID = 1;
    private static final String EMAIL = "someemail@mail.com";
    private static final String PHONE = "(00)91644889";

    private static final String EMAIL_UPDATE = "updated.someemail@mail.com";
    private static final String PHONE_UPDATE = "(00)11111111";

    public static Contact createContactToBeSave(){
        return Contact.builder()
                .email(EMAIL)
                .phone(PHONE)
                .build();
    }
    public static Contact createValidContact(){
        return Contact.builder()
                .id(ID)
                .email(EMAIL)
                .phone(PHONE)
                .build();
    }
    public static Contact createValidContactToUpdate(){
        return Contact.builder()
                .id(ID)
                .email(EMAIL_UPDATE)
                .phone(PHONE_UPDATE)
                .build();
    }


}
