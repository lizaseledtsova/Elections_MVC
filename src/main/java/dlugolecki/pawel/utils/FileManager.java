package dlugolecki.pawel.utils;

import dlugolecki.pawel.exceptions.MyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FileManager {

    @Value("${img.path}")
    private String imgPath;

    private String createFilename(MultipartFile file) {
        try {
            final String[] fileInfo = file.getOriginalFilename().split("\\.");
            final String extension = fileInfo[fileInfo.length - 1];
            final String filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
            return filename + "." + extension;
        } catch (Exception e) {
            throw new MyException(
                    "File is empty",
                    FileManager.class.getCanonicalName(),
                    "Create filename");
        }
    }

    public String addFile(MultipartFile file) {
        try {
            if (file == null || file.getBytes().length == 0) {
                throw new IllegalAccessException("FILE IS NOT CORRECT");
            }
            final String fileName = createFilename(file);
            final String fullPath = imgPath + fileName;
            FileCopyUtils.copy(file.getBytes(), new File(fullPath));
            return fileName;
        } catch (Exception e) {
            throw new MyException(
                    "File is empty",
                    FileManager.class.getCanonicalName(),
                    "Ad file");
        }
    }

    public boolean removeFile(String fileName) {
        try {
            if (fileName == null) {
                throw new NullPointerException("FILE NAME IS NULL");
            }
            return new File(fileName).delete();
        } catch (Exception e) {
            throw new MyException(
                    "File is empty",
                    FileManager.class.getCanonicalName(),
                    "Remove filename");
        }
    }
}