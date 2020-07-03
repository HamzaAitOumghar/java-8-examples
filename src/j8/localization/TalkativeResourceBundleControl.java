package j8.localization;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class TalkativeResourceBundleControl extends ResourceBundle.Control {

    @Override
    public List<Locale> getCandidateLocales(String baseName, Locale locale) {
        List<Locale> candidateLocales = super.getCandidateLocales(baseName, locale);
        candidateLocales.forEach(System.out::println);
        return candidateLocales;
    }
}
