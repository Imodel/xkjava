package cloud.simple.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableAutoConfiguration
public class RestClient1Application {
	
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String hello(){
		System.out.println("============hello world");
		return "================hello world";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RestClient1Application.class, args);
	}
	
	@Bean
	public TomcatEmbeddedServletContainerFactory servletContainer(){
		TomcatEmbeddedServletContainerFactory container = new TomcatEmbeddedServletContainerFactory(8083);
		return container;
				
	}
}
