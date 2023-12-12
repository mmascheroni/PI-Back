package com.backend.PIBack.controller;

import com.backend.PIBack.service.IS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/s3")
public class S3Controller {


    private IS3Service s3Service;

    @Autowired
    public S3Controller(IS3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return s3Service.uploadFile(file);
    }

//    @GetMapping("/download/{filename}")
//    public String downloadFile(@PathVariable("filename") String filename) {
//        return s3Service.downloadFile(filename);
//    }
//
//    @CrossOrigin(origins = "http://localhost:5173")
//    @GetMapping("/imageUrl/{filename}")
//    public String getImageUrl(@PathVariable("filename") String filename) {
//        return s3Service.getImageUrl(filename);
//    }
//
//    @GetMapping("/list")
//    public List<String> getAllObjects() throws IOException {
//        return s3Service.listFiles();
//    }
//
//
//
//    @PostMapping("/uploadMultiple")
//    public String uploadMultipleFiles(@RequestParam("files") List<MultipartFile> files)
//            throws ImageUploadException {
//        return s3Service.uploadFiles(files);
//    }
//
//    @PutMapping("/rename/{oldFilename}/{newFilename}")
//    public String updateFile(@PathVariable("oldFilename") String oldFilename,
//                             @PathVariable("newFilename") String newFilename) throws IOException {
//        return s3Service.renameFile(oldFilename, newFilename);
//    }
//
//    @PutMapping("/update/{oldFilename}")
//    public String updateFile(@RequestParam("file") MultipartFile file,
//                             @PathVariable("oldFilename") String oldFilename) throws IOException {
//        return s3Service.updateFile(file, oldFilename);
//    }
//
//    @DeleteMapping("/delete/{filename}")
//    public String deleteFile(@PathVariable("filename") String filename) throws IOException {
//        return s3Service.deleteFile(filename);
//    }


}
