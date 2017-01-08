package service.IdentityAuth.OTP;

import service.IdentityAuth.hotp.HOTPAlgo;

import java.text.MessageFormat;

/**
 * Created by manish on 24/7/16.
 */
public class OTPGenerator {

    public static String getOTP(Long phoneNo, String name, Long movingFactor) {

        String secretKey = "webnet" + name;
        String phone = phoneNo.toString();
        String pass = "";

        for(int j = 0; j< phone.length(); j++) {
            pass = MessageFormat.format("{0}{1}{2}", pass, Character.toString(phone.charAt(j)), Character.toString(secretKey.charAt(j)));
        }
        System.out.println(pass);
        byte[] code = pass.getBytes();

        try {
            String otp = HOTPAlgo.generateOTP(code, movingFactor, 6, false, 1);
            System.out.println(otp);
            return otp;
        }
        catch (Exception e) {
            System.out.println("Exception = " + e);
        }
        return null;
    }

}
