package validation;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidation {

    private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
    private final Pattern pattern;


    public PasswordValidation() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    public  boolean isValidPassword(String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
//    private static final String REGEX = "^[a-zA-Z0-9]{2,}$";
//    private Pattern pattern = Pattern.compile(REGEX);
//    private Matcher matcher;
//    private boolean result;
//
//    public boolean isPasswordValid(String password) {
//        matcher = pattern.matcher(password);
//
//        if (matcher.find()) {
//            result = true;
//        } else {
//            result = false;
//        }
//        return result;
//    }
//}
