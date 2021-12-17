package md.utm.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "confirmation-channels.email.smtp")
@Data
public class EmailProperties {

    private String host;
    private int port;
    private String sender;
    private String username;
    private String password;
}
