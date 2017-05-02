package cloud.simple.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableDiscoveryClient
public class ClientApplication {
	
	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	public String hello(){
		System.out.println("############hello world");
		return "######################hello world";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}
}
