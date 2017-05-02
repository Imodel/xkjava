package cloud.simple.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FeignClientApplication {
	
	@Autowired
	HelloClient hello;
	
	@RequestMapping("/hello")
	public String hello(){
		System.out.println("hello");
		return hello.hello();
	}
	
	@RequestMapping("/test")
	public String test(){
		System.out.println("hello");
		return hello.test();
	}

	public static void main(String[] args) {
		SpringApplication.run(FeignClientApplication.class, args);
	}
	
	@FeignClient("hello")
	interface HelloClient{
		@RequestMapping(value="/",method = RequestMethod.GET)
		String hello();
		
		@RequestMapping(value="/test",method = RequestMethod.GET)
		String test();
	}
}
