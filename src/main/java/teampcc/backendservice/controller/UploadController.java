package teampcc.backendservice.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import teampcc.backendservice.model.FileInfo;
import teampcc.backendservice.service.FilesStorageService;

@RestController("/upload")
public class UploadController {
    @Autowired
    FilesStorageService filesStorageService;

    @GetMapping("/listFile")
    public List<FileInfo> listFile() {
        List<FileInfo> fileInfos = filesStorageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            // String url = MvcUriComponentsBuilder
            // .fromMethodName(UploadController.class, "listFile",
            // path.getFileName().toString()).build().toString();
            return new FileInfo(filename, "url");
        }).collect(Collectors.toList());
        return fileInfos;
    }

    @GetMapping("file")
    public String name(@RequestParam String filename) {
        byte[] file = filesStorageService.load(filename);
        String base64 = Base64Utils.encodeToString(file);
        return base64;
    }

    @PostMapping("/upload")
    public ResponseEntity upload(HttpServletRequest request, HttpServletResponse response) {
        
        String host = request.getHeader("Host");
        String totalSize = request.getHeader("X-Upload-Content-Length");
        String contentLength = request.getHeader("Content-Length");
        String mimeType = request.getHeader("X-Upload-Content-Type");
        var uri = URI.create("//" + host + request.getRequestURI() + "?name=Hello.pdf");

        System.out.println(request.getContextPath().length());
        System.out.println(response.getBufferSize());

        // ServletFileUpload upload = new ServletFileUpload();
        // try {
        //     FileItemIterator iterStream = upload.getItemIterator(request);
        //     while (iterStream.hasNext()) {
        //         FileItemStream item = iterStream.next();
        //         String name = item.getFieldName();
        //         InputStream stream = item.openStream();
        //         if (!item.isFormField()) {
        //             // Process the InputStream
        //         } else {
        //             String formFieldValue = Streams.asString(stream);
        //         }
        //         System.out.println("fileName: "+name);
        //     }
        // } catch (FileUploadException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        return ResponseEntity.ok()
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Location,Range")
                .header(HttpHeaders.RANGE, "bytes=0-" + totalSize).header(HttpHeaders.CONNECTION, "keep-alive")
                .contentType(MediaType.APPLICATION_JSON).contentLength(Long.valueOf(contentLength))
                .body("test");
    }

    // public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files")
    // MultipartFile[] files) {
    // String message = "";
    // try {
    // List<String> fileNames = new ArrayList<>();

    // Arrays.asList(files).stream().forEach(file -> {
    // filesStorageService.save(file);
    // fileNames.add(file.getOriginalFilename());
    // });

    // message = "Uploaded the files successfully: " + fileNames;
    // return ResponseEntity.status(HttpStatus.OK).body(new
    // ResponseMessage(message));
    // } catch (Exception e) {
    // message = "Fail to upload files!";
    // return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new
    // ResponseMessage(message));
    // }
    // }
}
