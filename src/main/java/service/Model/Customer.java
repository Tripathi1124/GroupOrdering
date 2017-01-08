package service.Model;

/**
 * Created by manish on 13/7/16.
 */

public class Customer {
    private  String name;
    private  Long phoneNo;
    private  String email;
    private  String password;
    private  String confirmPassword;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }
    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
