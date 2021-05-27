package xmlProjekatMediaService.mediaService.service;


import lombok.extern.slf4j.Slf4j;
import xmlProjekatMediaService.mediaService.model.ImageMetadata;
import xmlProjekatMediaService.mediaService.repository.ImageMetadataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class ImageService {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ImageMetadataRepository imageMetadataRepository;


    public ImageMetadata upload(MultipartFile file, String username) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        log.info("Cuvanje datoteke {}", filename);

        ImageMetadata metadata = fileStorageService.store(file, username);
        return imageMetadataRepository.save(metadata);
    }
}
