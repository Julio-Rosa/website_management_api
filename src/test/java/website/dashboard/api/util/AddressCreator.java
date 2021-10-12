package website.dashboard.api.util;

import website.dashboard.api.model.Address;

public class AddressCreator {
    private static long ID = 1;
    private static final String CITY_NAME = "Some city";
    private static final String STREET_NAME = "Some street";
    private static final String DISTRICT_NAME = "Some district";
    private static final String NUMBER = "126";
    private static final String COMPLEMENT = "Some complement";
    public static Address createAddressToBeSave(){
        return Address.builder()
                .city(CITY_NAME)
                .street(STREET_NAME)
                .district(DISTRICT_NAME)
                .number(NUMBER)
                .complement(COMPLEMENT)
                .build();
    }
    public static Address createValidAddress(){
        return Address.builder()
                .id(ID)
                .city(CITY_NAME)
                .street(STREET_NAME)
                .district(DISTRICT_NAME)
                .number(NUMBER)
                .complement(COMPLEMENT)
                .build();
    }
    public static Address createValidUpdatedAddress(){
        return Address.builder()
                .id(ID)
                .city(CITY_NAME + " updated")
                .street(STREET_NAME +" updated")
                .district(DISTRICT_NAME +" updated")
                .number(NUMBER)
                .complement(COMPLEMENT + " updated" )
                .build();
    }
}
