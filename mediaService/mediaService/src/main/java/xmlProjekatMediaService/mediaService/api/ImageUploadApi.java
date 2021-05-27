package xmlProjekatMediaService.mediaService.api;


import lombok.extern.slf4j.Slf4j;
import xmlProjekatMediaService.mediaService.load.UploadFileResponse;
import xmlProjekatMediaService.mediaService.model.ImageMetadata;
import xmlProjekatMediaService.mediaService.service.ImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Slf4j
@RestController
public class ImageUploadApi {

    @Autowired
    private ImageService imageService;

    @PostMapping("/images")
    @PreAuthorize("hasRole('USER')")
    public UploadFileResponse uploadFile(@RequestParam("image") MultipartFile file,
                                         @AuthenticationPrincipal Principal principal) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        log.info("Primljen zahtev za upload datoteke {} korisnika {}", filename, principal.getName());

        ImageMetadata metadata = imageService.upload(file, principal.getName());

        return new UploadFileResponse(metadata.getFilename(), metadata.getUri(),
                metadata.getFileType());
    }

}
