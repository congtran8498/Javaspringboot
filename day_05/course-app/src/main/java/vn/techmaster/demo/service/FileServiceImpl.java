package vn.techmaster.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{
    //forder luu tru file
    private static String uploadDir = System.getProperty("user.dir")
            .concat(File.separator)
            .concat("uploads");

    private void createFolder(String filePath){
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdirs();
        }
    }

    public FileServiceImpl() {
        createFolder(uploadDir);
    }

    @Override
    public String uploadFile(MultipartFile file) {
        // ../course-app/uploads/fileId
        String fileId = UUID.randomUUID().toString();
        File fileServer = new File(uploadDir.concat(File.separator).concat(fileId));

        try {
            file.transferTo(fileServer);
            return "api/vi/files/".concat(fileId);
        } catch (IOException e) {
            throw new RuntimeException("Gặp lỗi khi upload file");
        }
    }

    @Override
    public byte[] readFile(String id) {
        File fileServer = new File(uploadDir.concat(File.separator).concat(id));
        if(!fileServer.exists()){
            throw new RuntimeException("lỗi khi đọc file");
        }

        try {
            return Files.readAllBytes(fileServer.toPath());
        } catch (IOException e) {
            throw new RuntimeException("lỗi khi đọc file");
        }
    }
}
