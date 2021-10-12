package website.dashboard.api.util;

import website.dashboard.api.dto.request.AddressDTO;
import website.dashboard.api.model.Address;

public class AddressCreator {
    private static long ID = 1;
    private static final String CITY_NAME = "Some city";
    private static final String STREET_NAME = "Some street";
    private static final String DISTRICT_NAME = "Some district";
    private static final String NUMBER = "126";
    private static final String COMPLEMENT = "Some complement";

    private static final String CITY_NAME_UPDATE = "Some city Updated";
    private static final String STREET_NAME_UPDATE = "Some street Updated";
    private static final String DISTRICT_NAME_UPDATE = "Some district Updated";
    private static final String NUMBER_UPDATED = "000";
    private static final String COMPLEMENT_UPDATE = "Some complement Updated";
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
                .city(CITY_NAME_UPDATE)
                .street(STREET_NAME_UPDATE)
                .district(DISTRICT_NAME_UPDATE)
                .number(NUMBER_UPDATED)
                .complement(COMPLEMENT_UPDATE )
                .build();
    }
    public static AddressDTO createAddressDTOToBeSave(){
        return AddressDTO.builder()
                .city(CITY_NAME)
                .street(STREET_NAME)
                .district(DISTRICT_NAME)
                .number(NUMBER)
                .complement(COMPLEMENT)
                .build();
    }
    public static AddressDTO createValidAddressDTO(){
        return AddressDTO.builder()
                .id(ID)
                .city(CITY_NAME)
                .street(STREET_NAME)
                .district(DISTRICT_NAME)
                .number(NUMBER)
                .complement(COMPLEMENT)
                .build();
    }
    public static AddressDTO createValidUpdatedAddressDTO(){
        return AddressDTO.builder()
                .id(ID)
                .city(CITY_NAME_UPDATE)
                .street(STREET_NAME_UPDATE)
                .district(DISTRICT_NAME_UPDATE)
                .number(NUMBER_UPDATED)
                .complement(COMPLEMENT_UPDATE)
                .build();
    }
}
