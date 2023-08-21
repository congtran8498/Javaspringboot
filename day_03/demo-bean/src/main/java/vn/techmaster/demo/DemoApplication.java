package vn.techmaster.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private Random rd;
	private User user;

	public DemoApplication(User user) {
		this.user = user;
	}
	@Autowired
	@Qualifier("bike")
	private Vehicle vehicle;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		user.work();
		System.out.println(rd.nextInt(1000));
		vehicle.move();
	}
}
