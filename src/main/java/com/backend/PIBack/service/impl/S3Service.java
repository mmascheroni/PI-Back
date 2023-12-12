package com.backend.PIBack.service.impl;

import com.backend.PIBack.service.IS3Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

@Service
public class S3Service implements IS3Service {
    private final S3Client s3Client;

    @Autowired
    public S3Service(S3Client s3Client){
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        try {
            // Verifica si el tipo de archivo es jpg, jpeg, png o svg
            if (!isValidImageType(file.getOriginalFilename())) {
                throw new IllegalArgumentException("Tipo de archivo no admitido. Solo se permiten archivos jpg, jpeg, png o svg.");
            }

            // Genera un nombre único para el archivo
            String uniqueFileName = generateUniqueFileName(file.getOriginalFilename());

            // Configura la carpeta en S3 y la clave del objeto
            String folderName = "img/";
            String key = folderName + uniqueFileName;

            // Configura la solicitud para subir el archivo a S3
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket("1023c09-grupo1-img")
                    .key(key)
                    .contentType(getContentType(uniqueFileName)) // Configura el tipo MIME
                    .build();

            // Sube el archivo a S3
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

            // Devuelve el nombre único como respuesta
            return "/img/" + uniqueFileName;
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    // Método para obtener el tipo MIME basado en la extensión del archivo
    private String getContentType(String fileName) {
        if (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (fileName.toLowerCase().endsWith(".png")) {
            return "image/png";
        } else if (fileName.toLowerCase().endsWith(".svg")) {
            return "image/svg+xml";
        }
        // Agrega más extensiones y tipos MIME según tus necesidades
        return "application/octet-stream"; // Tipo MIME predeterminado si no se encuentra una coincidencia
    }

    // Método para validar si el tipo de archivo es jpg, jpeg, png o svg
    private boolean isValidImageType(String fileName) {
        return fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg") ||
                fileName.toLowerCase().endsWith(".png") || fileName.toLowerCase().endsWith(".svg");
    }

    // Método para generar un nombre único basado en el nombre original del archivo
    private String generateUniqueFileName(String originalFileName) {
        // Obtén el timestamp actual en milisegundos y conviértelo a representación hexadecimal
        String timestampHex = Long.toHexString(Instant.now().toEpochMilli());
        // Genera un UUID y toma solo los primeros 8 caracteres para reducir la longitud
        String shortUUID = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        // Combina el timestamp y el identificador único con el nombre original del archivo
        return timestampHex + "" + shortUUID + "" + originalFileName;
    }
}
