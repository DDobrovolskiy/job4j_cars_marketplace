package ru.job4j.services;

import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.services.properties.PropertyFactory;

import java.io.File;

@Slf4j
@ThreadSafe
public class DownloadService {
    private DownloadService() {
    }

    public static File download(String photo) {
        return new DownloadService().downloadPhoto(photo);
    }

    private File downloadPhoto(String photo) {
        File downloadFile = null;
        String path = PropertyFactory.load("app.properties").getProperty("pathDownloadPhoto");
        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        } else {
            for (File file : filePath.listFiles()) {
                if (file.getName().startsWith(photo)) {
                    downloadFile = file;
                    log.debug("Search image result: {}", file.getName());
                    break;
                }
            }
        }

        if (downloadFile == null) {
            downloadFile = new File(path + "0.png");
        }

        return downloadFile;
    }
}
