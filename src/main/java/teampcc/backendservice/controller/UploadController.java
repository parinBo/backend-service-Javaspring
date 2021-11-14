package teampcc.backendservice.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.nio.file.Files;
import com.google.gson.Gson;

import org.hibernate.mapping.Collection;
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
            return new FileInfo(filename, path.getRoot().toString());
        }).collect(Collectors.toList());
        return fileInfos;
    }

    @GetMapping("file")
    public String name(@RequestParam String filename) {
        byte[] file = filesStorageService.load(filename);
        String base64 = Base64Utils.encodeToString(file);
        return base64;
    }

    private Path root = Paths.get("./src/main/resources/uploads");
    Map<String, ByteArrayOutputStream> dataMap = new HashMap<String, ByteArrayOutputStream>();

    @PostMapping(value = "/upload")
    public ResponseEntity uploadFiles(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BufferedReader reader = request.getReader();
        var data = new Gson().fromJson(reader.readLine(), Map.class);
        System.out.println("------POST--------" + "\n" + data.get("name") + "\n" + "---------------------");
        System.out.println(data.get("data"));
        return ResponseEntity.created(URI.create(request.getRequestURI() + "?uid=" + data.get("name")))
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Location").header(HttpHeaders.RANGE, "bytes 0-/*")
                .header(HttpHeaders.CONNECTION, "keep-alive").body("");
    }

    @PutMapping(value = "/upload")
    public ResponseEntity uploadFilesWithName(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("uid") String uid) {
        var message = "";
        HttpHeaders header = new HttpHeaders();
        var match = request.getHeader("content-range").split("[0-9]*-")[1].split("/");
        try {
            var reqInputStream_To_Bytes = StreamUtils.copyToByteArray(request.getInputStream());
            dataMap.get(uid).write(reqInputStream_To_Bytes);
            System.out.println("********  Put : "+uid+"  ********");
            System.out.println(
                    "content-length Now: " + request.getContentLength() + " ::: " + request.getHeader("content-range"));
            System.out.println(
                    uid + " sum : " + dataMap.get(uid).toByteArray().length + "\n" + "************************");
            if (Long.valueOf(match[0]) + 1 == Long.valueOf(match[1])) {
                System.out.println("-----------------------");
                System.out.println("success File: " + uid + " " + match[0] + " : " + match[1] + " == "
                        + dataMap.get(uid).toByteArray().length);
                Files.write(root.resolve(uid), dataMap.get(uid).toByteArray());
                message = "Upload Complete";
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(uid);
            System.out.println(e.getMessage());

        }

        header.set(HttpHeaders.CONNECTION, "keep-alive");
        header.set("Keep-Alive", "timeout=5");
        return ResponseEntity.ok().body(message);
    }
}