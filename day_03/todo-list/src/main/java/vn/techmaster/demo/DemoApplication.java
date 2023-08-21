package vn.techmaster.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vn.techmaster.demo.database.TodoDB;
import vn.techmaster.demo.utils.IFileReader;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private IFileReader fileReader;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		 TodoDB.todoList = fileReader.readFile("static/todo.json");
	}
}
