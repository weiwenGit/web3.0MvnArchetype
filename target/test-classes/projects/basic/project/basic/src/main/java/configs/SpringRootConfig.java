package configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class SpringRootConfig {
	
	@Value("${jdbc.driverClassName}")
    private String driverClassName;
	
    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;
    
	@Value("${auth.usrs}")
    private String usrs;
	
    @Value("${auth.pwds}")
    private String pwds;
    
    private static final Resource[] dev_properties = new ClassPathResource[] {
    	new ClassPathResource("jdbc-dev.properties"), new ClassPathResource("authorization.properties")
    };
    
    private static final Resource[] prod_properties = new ClassPathResource[] {
        	new ClassPathResource("jdbc-production.properties"), new ClassPathResource("authorization.properties")
     };
	
	@Bean
	@Profile("dev")
    public static PropertySourcesPlaceholderConfigurer jdbcDevConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocations(dev_properties);

        return configurer;
    }
    
	@Bean
	@Profile("default")
    public static PropertySourcesPlaceholderConfigurer jdbcProdConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocations(prod_properties);

        return configurer;
    }
	
	@Bean
	public static ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasenames("i18n/exceptions");
		return source;
	}
}
