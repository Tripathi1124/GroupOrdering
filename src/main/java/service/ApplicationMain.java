package service;


import com.dao.RegisterDAO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.Controller.RegistrationController;
import service.IdentityAuth.hotp.HOTPAlgo;
import service.di.ApplicationConfig;

import java.text.MessageFormat;

/**
 * Created by manish on 12/7/16.
 */
@SpringBootApplication
public class ApplicationMain {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        RegistrationController registrationController = context.getBean(RegistrationController.class);
        RegisterDAO dao = context.getBean(RegisterDAO.class);

        String name = "webnet" + "Manish";
        String phone = "9926546906";
        String pass = "";

        for(int j = 0; j< phone.length(); j++) {
            pass = MessageFormat.format("{0}{1}{2}", pass, Character.toString(phone.charAt(j)), Character.toString(name.charAt(j)));
        }
        System.out.println(pass);
        Long time = System.currentTimeMillis();
        byte[] code = pass.getBytes();

            try {
                System.out.println(HOTPAlgo.generateOTP(code, time, 6, false, 1));
            }
            catch (Exception e) {
                System.out.println("Exception = " + e);
            }


        SpringApplication.run(ApplicationMain.class, args);
    }
}
