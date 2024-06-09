/*
package example.com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServiceInitListener;

@Configuration
@DependsOn("springSecurityFilterChain") // Ensure this runs before SecurityFilterChain
public class VaadinExcludeUrlConfig {

    @Value("${vaadin.exclude-urls:/soap/*}") // Use a placeholder for flexibility
    private String excludeUrls;

    @Bean
    public VaadinServiceInitListener vaadinServiceInitListener() {
        return new VaadinServiceInitListener() {
            @Override
            public void beforeInit(VaadinRequest request, VaadinService service) {
                service.getUIProvider().setOverrideUrlMapping(true); // Allow overriding url mapping
                service.getUIProvider().setServletMappingPatterns(excludeUrls); // Set excluded urls
            }
        };
    }
}
*/
