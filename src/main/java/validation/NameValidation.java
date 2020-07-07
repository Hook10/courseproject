package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidation {
    public static final String regexEng = "^[aA-zZ]\\w{3,29}$";
    public static final String regexRu = "^[А-Я]{1,29}[а-я]{1,29}$";

    public  boolean isNotValidUserName(String name) {

        Pattern patternEng = Pattern.compile(regexEng);
        Pattern patternRu = Pattern.compile(regexRu);

        Matcher matcherEng = patternEng.matcher(name);
        Matcher matcherRu = patternRu.matcher(name);

        return !matcherEng.find() && !matcherRu.find();
    }

}
