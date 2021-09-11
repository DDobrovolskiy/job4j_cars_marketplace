package ru.job4j.services;

import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.services.dao.DataAccessObject;
import ru.job4j.services.properties.PropertyFactory;

import java.io.File;

@Slf4j
@ThreadSafe
public class UploadService {
    private UploadService() {
    }

    public static String getSaveDirectory() {
        String uploadFilePath = PropertyFactory
                .load("app.properties")
                .getProperty("pathDownloadPhoto");
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        return uploadFilePath;
    }

    public static void upload(int idCar, String photo) {
        new UploadService().uploadPhoto(idCar, photo);
    }

    private void uploadPhoto(int idCar, String photo) {
        DataAccessObject.instOf().insertPhotoInCar(idCar, photo);
    }
}
