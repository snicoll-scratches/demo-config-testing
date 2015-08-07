package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SampleProperties.class)
public class SampleConfig {

	@Autowired
	private SampleProperties properties;

	@Bean
	public SampleService sampleService() {
		return new SampleService(properties.getName());
	}

}
