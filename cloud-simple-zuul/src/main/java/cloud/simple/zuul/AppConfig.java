package cloud.simple.zuul;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cloud.simple.filter.AccessPasswordFilter;
import cloud.simple.filter.AccessTokenFilter;
import cloud.simple.filter.AccessUserNameFilter;

@Configuration
public class AppConfig {
	@Bean
	public AccessUserNameFilter accessUserNameFilter() {
		return new AccessUserNameFilter();
	}
	
	@Bean
	public AccessPasswordFilter accessPasswordFilter(){
		return new AccessPasswordFilter();
	}
	
	@Bean
	public AccessTokenFilter accessTokenFilter(){
		return new AccessTokenFilter();
	}
	

}
