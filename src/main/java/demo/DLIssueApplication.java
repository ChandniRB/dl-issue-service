package demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "demo")
public class DLIssueApplication {

	public static void main(String[] args) {
		SpringApplication.run(DLIssueApplication.class, args);
	}


}
