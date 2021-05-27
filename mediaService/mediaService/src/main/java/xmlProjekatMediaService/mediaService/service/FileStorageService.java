package xmlProjekatMediaService.mediaService.service;


import lombok.extern.slf4j.Slf4j;
import xmlProjekatMediaService.mediaService.model.ImageMetadata;
import xmlProjekatMediaService.mediaService.exception.*;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;



import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@Slf4j
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDirectory;

    @Value("${file.path.prefix}")
    private String filePathPrefix;

    @Autowired
    private Environment environment;


    public ImageMetadata store(MultipartFile file, String username) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        log.info("Cuvanje datoteke {}", filename);

        try {
            if (file.isEmpty()) {
                log.warn("Neuspesno cuvanje prazne datoteke {}", filename);
                throw new InvalidFileException("Failed to store empty file " + filename);
            }

            if (filename.contains("..")) {
                // Ovo je bezbednosna provera
                log.warn("Ne moze se sacuvati datoteka sa relativnom putanjom {}", filename);
                throw new InvalidFileNameException(
                        "Ne moze se sacuvati datoteka sa relativnom putanjom izvan trenutnog foldera "
                                + filename);
            }

            String extension = FilenameUtils.getExtension(filename);
            String newFilename = UUID.randomUUID() + "." + extension;

            try (InputStream inputStream = file.getInputStream()) {
                Path userDir = Paths.get(uploadDirectory + username);

                if(Files.notExists(userDir)) {
                    Files.createDirectory(userDir);
                }

                Files.copy(inputStream, userDir.resolve(newFilename),
                        StandardCopyOption.REPLACE_EXISTING);
            }

            String port = environment.getProperty("local.server.port");
            String hostName = InetAddress.getLocalHost().getHostName();

            String fileUrl = String.format("http://%s:%s%s/%s/%s",
                    hostName, port, filePathPrefix, username, newFilename);

            log.info("Uspesno sacuvana datoteka {} lokacija {}", filename, fileUrl);

            return new ImageMetadata(newFilename, fileUrl, file.getContentType());
        }
        catch (IOException e) {
            log.error("Neuspesno cuvanje datotele {} error: {}", filename, e);
            throw new StorageException("Neuspesno " + filename, e);
        }
    }
}
