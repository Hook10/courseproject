package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidation {
    public  boolean isValidUserName(String name) {
        String regexEng = "^[aA-zZ]\\w{5,29}$";
        String regexRu = "^[А-Я]{1}[а-я]{1,29}$";

        Pattern patternEng = Pattern.compile(regexEng);
        Pattern patternRu = Pattern.compile(regexRu);

        Matcher matcherEng = patternEng.matcher(name);
        Matcher matcherRu = patternRu.matcher(name);

        return matcherEng.find() || matcherRu.find();
    }

}
