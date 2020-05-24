package validation;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {

    private String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    private Pattern emailPat;
    private Matcher matcher;

    public EmailValidation() {
        emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
    }

    public  boolean isValidEmail(String input) {
        matcher = emailPat.matcher(input);
        return matcher.matches();
    }

}
