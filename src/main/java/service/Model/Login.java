package service.Model;

/**
 * Created by manish on 21/7/16.
 */
public class Login {
    private Long phoneNo;
    private String password;

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

}
