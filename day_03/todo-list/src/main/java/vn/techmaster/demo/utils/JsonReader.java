package vn.techmaster.demo.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import vn.techmaster.demo.modal.Todo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class JsonReader implements IFileReader{
     @Override
     public List<Todo> readFile(String filePath){
         ObjectMapper objectMapper = new ObjectMapper();
         TypeReference<List<Todo>> typeReference = new TypeReference<List<Todo>>() {};

         // Load the JSON file from the classpath
         try {
             ClassPathResource resource = new ClassPathResource(filePath);
             InputStream inputStream = resource.getInputStream();

             // Convert JSON to List of Person objects
             List<Todo> todoList = objectMapper.readValue(inputStream, typeReference);

             return todoList;
         } catch (IOException e) {
             System.out.println(e.getMessage());
         }

         return null;
     }
}
