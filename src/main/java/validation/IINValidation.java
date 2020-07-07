package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IINValidation {
    private static final String IIN_PATTERN = "^[0-9]{12}$";
    private Pattern pattern;
    private Matcher matcher;

    public IINValidation() {
        pattern = Pattern.compile(IIN_PATTERN);
    }

    public boolean isNotValidIIN(String iin) {
        matcher = pattern.matcher(iin);
        return !matcher.matches();
    }
}
