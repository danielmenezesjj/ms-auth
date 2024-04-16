package com.msauth.msauth.services.AwsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
public class AwsService {


    @Autowired
    private S3Client s3Client;

    @Value("${aws.bucketName}")
    private String bucketName;

    public String uploadImage(MultipartFile file) {
        try {
            // Carregar o arquivo para o Amazon S3
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(file.getOriginalFilename())
                    .build();
            s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));

            // URL do objeto carregado
            String objectUrl = s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(file.getOriginalFilename())).toExternalForm();
            return objectUrl;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao carregar imagem");
        }
    }



}
