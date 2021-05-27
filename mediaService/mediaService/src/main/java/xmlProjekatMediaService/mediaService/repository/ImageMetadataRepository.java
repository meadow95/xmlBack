package xmlProjekatMediaService.mediaService.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import xmlProjekatMediaService.mediaService.model.ImageMetadata;

public interface ImageMetadataRepository extends MongoRepository<ImageMetadata, String> {

}
