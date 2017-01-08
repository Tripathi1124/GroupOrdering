package service.di;


import com.configuration.HibernateConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import service.Controller.RegistrationController;
import service.Utils.OTPTimer;

/**
 * ApplicationConfig
 */
@Configuration
@Import({HibernateConfiguration.class})
public class ApplicationConfig {

    @Bean
    public RegistrationController registrationController() {
        return new RegistrationController();
    }

    @Bean
    public OTPTimer otpTimer() {
        return new OTPTimer();
    }
}

