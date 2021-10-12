package website.dashboard.api.util;

import website.dashboard.api.model.Image;

public class ImageCreator {
    private static final long ID = 1;
    private static final String IMAGE_URL = "https://imageurl.com";
    private static final String IMAGE_PUBLIC_ID = "2A2BC";

    private static final String IMAGE_URL_UPDATE = "https://imageurl.com/updated";
    private static final String IMAGE_PUBLIC_ID_UPDATE = "2A2BCUPDATED";
    public static Image createImageToBeSave(){

        return Image.builder()
                .imageUrl(IMAGE_URL)
                .publicId(IMAGE_PUBLIC_ID)
                .build();
    }
    public static Image createValidImage(){

        return Image.builder()
                .id(ID)
                .imageUrl(IMAGE_URL)
                .publicId(IMAGE_PUBLIC_ID)
                .build();
    }
    public static Image createValidImageToUpdate(){

        return Image.builder()
                .id(ID)
                .imageUrl(IMAGE_URL_UPDATE)
                .publicId(IMAGE_PUBLIC_ID_UPDATE)
                .build();
    }
}
