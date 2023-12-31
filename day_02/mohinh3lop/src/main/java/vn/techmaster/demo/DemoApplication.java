package vn.techmaster.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vn.techmaster.demo.database.JobDB;
import vn.techmaster.demo.util.iFileReader;

import java.io.File;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private iFileReader fileReader;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		JobDB.jobList = fileReader.readFile("static/Job.json");
		String filePath = System.getProperty("user.dir").concat(File.separator).concat("Job.json");
		JobDB.jobList = fileReader.readFile(filePath);
	}
}
