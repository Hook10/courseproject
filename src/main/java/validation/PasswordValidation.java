package validation;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidation {

    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
    private final Pattern pattern;


    public PasswordValidation() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    public  boolean isNotValidPassword(String password) {
        Matcher matcher = pattern.matcher(password);
        return !matcher.matches();
    }

}

