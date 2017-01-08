package service.Utils;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import service.Model.Customer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by manish on 21/7/16.
 */
public class InputValidator implements Validator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String ID_PATTERN = "[0-9]+";
    private static final String STRING_PATTERN = "[a-zA-Z]+";
    private static final String MOBILE_PATTERN = "[0-9]{10}";

    public boolean supports(Class<?> clazz) {
        return false;
    }

    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;

        ValidationUtils.rejectIfEmpty(errors, "name", "required.name",
                    "Name is required.");

        // input string conatains characters only
        if (!StringUtils.isEmpty(customer.getName())) {
                pattern = Pattern.compile(STRING_PATTERN);
                matcher = pattern.matcher(customer.getName());
                if (!matcher.matches()) {
                    errors.rejectValue("name", "name.containNonChar",
                            "Enter a valid name");
                }
            }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNo",
                    "required.phoneNo", "Phone is required.");

        // phone number validation
        if (customer.getPhoneNo() != null) {
                pattern = Pattern.compile(MOBILE_PATTERN);
                matcher = pattern.matcher(customer.getPhoneNo().toString());
                if (!matcher.matches()) {
                    errors.rejectValue("phoneNo", "phoneNo.incorrect",
                            "Enter a correct phone number");
                }
            }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.email", "Email is required.");

        //Validating Email
        if(!StringUtils.isEmpty(customer.getEmail())) {
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(customer.getEmail());
            if(!matcher.matches())
                errors.rejectValue("email", "email.incorrect", "Enter a correct email Id");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                    "required.password", "Password is required.");

        // password matching validation
        if (!customer.getPassword().equals(customer.getConfirmPassword())) {
                errors.rejectValue("confirmPassword", "password.mismatch",
                        "Password does not match");
        }

    }
}
