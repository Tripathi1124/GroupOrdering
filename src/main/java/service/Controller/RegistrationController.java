package service.Controller;

import com.dao.RegisterDAO;
import com.model.Register;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.IdentityAuth.OTP.OTPGenerator;
import service.Model.Customer;
import service.Model.Login;
import service.Model.Verification;
import service.Utils.InputValidator;
import service.Utils.OTPTimer;


/**
 * RegistrationController
 */
@RestController
public class RegistrationController {

    @Autowired
    private RegisterDAO registerDAO;

    private Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Customer customer,  BindingResult result) {

        InputValidator inputValidator = new InputValidator();
        Register register = new Register();
        Long time = System.currentTimeMillis();

        inputValidator.validate(customer, result);
        if(result.hasErrors())
            return "input not valid!!!" + result.toString();

        register.setName(customer.getName());
        register.setPhoneNo(customer.getPhoneNo());
        register.setPassword(customer.getPassword());
        register.setEmail(customer.getEmail());

        String otp = OTPGenerator.getOTP(customer.getPhoneNo(), customer.getName(), time);

        register.setOtp(otp);

        try {
            registerDAO.insertRecord(register);
            OTPTimer.startTimer(register);
            logger.info("User registered with phone no {}", register.getPhoneNo());
        } catch (Exception e) {
            logger.info("User Registration failed with phone no {}", register.getPhoneNo());
            e.printStackTrace();
        }

        return "Registration Successful";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Login login) {

        try {
            Register register = registerDAO.getRecord(login.getPhoneNo());
            if(register.getPassword().equals(login.getPassword())) {
                return "You are successfully logged in!!!";
            }
            return "Please provide correct password!!!";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Incorrect Phone Number!!!";
    }

    @RequestMapping(value = "/verifyPhone", method = RequestMethod.POST)
    public String verifyPhone(Verification verify) {
        try {
            Register register = registerDAO.getRecord(verify.getPhoneNo());
            if(register.getOtp()!=null) {
                if (register.getOtp().equals(verify.getOtp()))
                    return "Phone is verified!!!";
                return "Phone is not verified";
            }
            return "OTP is null";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error in finding record for the given number";
    }

/*    @RequestMapping("/showDetails")
    public Customer showDetails( String name,
                                @RequestParam(value = "phoneNo", defaultValue = "0") Long phoneNo) {

    }*/
}
