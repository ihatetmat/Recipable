package capstone.recipable.global.s3;

import capstone.recipable.global.entity.Uuid;
import capstone.recipable.global.error.ApplicationException;
import capstone.recipable.global.error.ErrorCode;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AmazonS3Manager{

    private final AmazonS3 amazonS3;

    private final AmazonConfig amazonConfig;

    private final UuidRepository uuidRepository;
    public String uploadFile(String keyName, MultipartFile file){
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentDisposition("inline");
        metadata.setContentLength(file.getSize());
        try {
            amazonS3.putObject(new PutObjectRequest(amazonConfig.getBucket(), keyName, file.getInputStream(), metadata));
        } catch (IOException e) {
            throw new ApplicationException(ErrorCode.IMAGE_IS_NOT_UPLOADED);
        }
        return amazonS3.getUrl(amazonConfig.getBucket(), keyName).toString();
    }

    public String generateIngredientKeyName(Uuid uuid) {
        return uuid.getUuid();
    }
}
