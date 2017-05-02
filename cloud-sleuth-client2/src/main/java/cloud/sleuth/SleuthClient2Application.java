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
public class SleuthClient2Application {
	
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
	@Bean
	public AlwaysSampler alwaysSampler(){
		return new AlwaysSampler();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SleuthClient2Application.class, args);
	}
}

@RestController
class HiController {

	private static final Log log = LogFactory.getLog(HiController.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	private String url="http://localhost:9987";

	@RequestMapping("/service2")
	public String hi() throws Exception {
		log.info("service2");
		Thread.sleep(100L);
		return "service2";
	}
	
	@RequestMapping("/service3")
	public String hi3() throws Exception {
		log.info("service3");
		Thread.sleep(200L);
		String s = this.restTemplate.getForObject(url + "/service1", String.class);
		return s;
	}
}