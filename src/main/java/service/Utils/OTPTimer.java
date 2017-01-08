package service.Utils;

import com.dao.RegisterDAO;
import com.model.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import service.IdentityAuth.OTP.OTPGenerator;
import service.Model.Verification;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by manish on 24/7/16.
 */
public class OTPTimer {

    @Autowired
    private static RegisterDAO dao;

    public OTPTimer() {

    }

    public static void startTimer(final Register register) {
       // String otp = OTPGenerator.getOTP(phoneNo, name);

        long in1Minutes = 60 * 1000;
        Timer timer = new Timer();
        timer.schedule( new TimerTask(){
            public void run() {
                if(!StringUtils.isEmpty(register.getOtp())) {
                    try {
                        dao.updateOTP(register, "");
                      //  System.out.print("OTP Timer is not working");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("OTP is Empty now");
                }
            }
        },  in1Minutes );

        System.out.println("OTP Timer is for one minute");
    }
}
