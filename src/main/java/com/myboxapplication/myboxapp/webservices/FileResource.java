package com.myboxapplication.myboxapp.webservices;

import com.myboxapplication.myboxapp.models.UploadFileResponse;
import com.myboxapplication.myboxapp.services.FileStorageService;
import com.myboxapplication.myboxapp.services.ResponseGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class FileResource {

    private static final Logger logger = LoggerFactory.getLogger(FileResource.class);

    @Autowired
    ResponseGenerator responseGenerator;

    @Autowired
    private FileStorageService fileStorageService;
    
    @PostMapping(value = "/uploadFile",produces = "application/json")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

//        return ResponseEntity.ok(new UploadFileResponse(fileName, fileDownloadUri,
//                file.getContentType(), file.getSize()));

        return ResponseEntity
                .ok(responseGenerator
                        .success(
                                new UploadFileResponse(fileName,
                                        fileDownloadUri,file.getContentType(),
                                        file.getSize()),"file.uploaded"));
    }

//    @PostMapping("/uploadMultipleFiles")
//    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file))
//                .collect(Collectors.toList());
//    }

    /*@GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }*/
}