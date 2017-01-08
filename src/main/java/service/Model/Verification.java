package service.Model;

/**
 * Created by manish on 24/7/16.
 */
public class Verification {
    Long phoneNo;
    String otp;

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
