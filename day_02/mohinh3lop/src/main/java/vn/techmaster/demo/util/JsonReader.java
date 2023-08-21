package vn.techmaster.demo.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import vn.techmaster.demo.modal.Job;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonReader implements iFileReader {
    //    @Override
//    public List<Job> readFile(String filePath) {
//        ObjectMapper objectMapper = new ObjectMapper();
//             TypeReference<List<Job>> typeReference = new TypeReference<List<Job>>() {};
//
//             // Load the JSON file from the classpath
//             try {
//                 ClassPathResource resource = new ClassPathResource(filePath);
//                 InputStream inputStream = resource.getInputStream();
//
//                 // Convert JSON to List of Person objects
//                 List<Job> personList = objectMapper.readValue(inputStream, typeReference);
//
//                 return personList;
//             } catch (IOException e) {
//                 System.out.println(e.getMessage());
//             }
//        return null;
//    }
    @Override
    public List<Job> readFile(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Read the JSON file using java.nio.file.Path
            Path jsonFilePath = Paths.get(filePath);
            byte[] jsonData = Files.readAllBytes(jsonFilePath);

            // Convert JSON to List of Person objects
            List<Job> jobList = objectMapper.readValue(jsonData, new TypeReference<List<Job>>() {
            });

            return jobList;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
