package cloud.sleuth;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAutoConfiguration
public class SleuthClientApplication {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public AlwaysSampler alwaysSampler(){
		return new AlwaysSampler();
	}

	public static void main(String[] args) {
		SpringApplication.run(SleuthClientApplication.class, args);
	}
}

@RestController
class HomeController {

	private static final Log log = LogFactory.getLog(HomeController.class);
	@Autowired
	private RestTemplate restTemplate;
	
	private String url="http://localhost:9986";

	@RequestMapping("/service1")
	public String service1() throws Exception {
		log.info("service1");
		Thread.sleep(200L);
		String s = this.restTemplate.getForObject(url + "/service2", String.class);
		return s;
	}
	
	@RequestMapping("/service")
	public String service() throws Exception {
		log.info("service");
		Thread.sleep(200L);
		String s = this.restTemplate.getForObject(url + "/service3", String.class);
		return s;
	}
	
	@RequestMapping("/test")
	public String test() throws Exception {
		log.info("test");
		Thread.sleep(200L);
		String s = this.restTemplate.getForObject("http://localhost:9987/service", String.class);
		return s;
	}
}
