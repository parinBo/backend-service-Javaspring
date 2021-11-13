package teampcc.backendservice.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.nio.file.Files;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    Map<String,ByteArrayOutputStream> dataMap = new HashMap<String,ByteArrayOutputStream>();
    @PostMapping(value = "/upload")
    public ResponseEntity uploadFiles(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BufferedReader reader = request.getReader();
        System.out.println("****POST****");
        var data = new Gson().fromJson(reader.readLine(), Map.class);
        dataMap.put(data.get("name").toString(),new ByteArrayOutputStream());
        System.out.println(data.get("name"));
        return ResponseEntity.created(URI.create(request.getRequestURI() + "?uid=" + data.get("name")))
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Location")
                .header(HttpHeaders.RANGE, "bytes 0-/*").header(HttpHeaders.CONNECTION, "keep-alive")
                .body("");
    }

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private  Path root = Paths.get("./src/main/resources/uploads");
    @PutMapping(value = "/upload")
    public ResponseEntity uploadFilesWithName(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("uid") String uid) throws Exception {
        var message = "";
        HttpHeaders header = new HttpHeaders(); 
        var match = request.getHeader("content-range").split("[0-9]*-")[1].split("/");
        var reqToInputStream = StreamUtils.copyToByteArray(request.getInputStream());
        outputStream.write(reqToInputStream);
        // if finish write file
        if(Long.valueOf(match[0])+1 == Long.valueOf(match[1])){
            System.out.println("success File: "+uid+ " "+match[0]+" : "+match[1]);
            System.out.println(outputStream.size());
            System.out.println(outputStream.toByteArray());
            InputStream finishStream = new ByteArrayInputStream(outputStream.toByteArray());
            Files.copy(finishStream,root.resolve(uid),StandardCopyOption.REPLACE_EXISTING);
            message = "Upload Complete";
        } 

        System.out.println("********Put********");
        System.out.println(uid);
        System.out.println("content-length Now: "+request.getContentLength()+" ::: "+request.getHeader("content-range"));
        System.out.println(match[0]+"  "+match[1]);
        System.out.println(uid+" : "+reqToInputStream);
        System.out.println(uid+" sum : "+outputStream.toByteArray() +"  "+outputStream.toByteArray().length);
        header.set(HttpHeaders.CONNECTION, "keep-alive");
        header.set("Keep-Alive", "timeout=5");
        return ResponseEntity.ok().body(message);
    }
}