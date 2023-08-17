package vn.techmaster.demo.database;

import vn.techmaster.demo.modal.Job;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JobDB {
    public static String generateUniqueId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
    public static List<Job> jobList = new ArrayList<>(
            List.of(
                    new Job(generateUniqueId(),"title 1","description1","location 1",1000,2000,"emailTo1"),
                    new Job(generateUniqueId(),"title 2","description2","location 2",1200,2400,"emailTo2"),
                    new Job(generateUniqueId(),"title 3","description3","location 3",1100,2200,"emailTo3")
            )
    );
}
