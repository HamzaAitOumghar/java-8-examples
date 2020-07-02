package j8.localization;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationDemo {

    public static void main(String[] args) {

        Locale locale = Locale.getDefault();
        ResourceBundle resBundel = ResourceBundle.getBundle("j8.localization.ResourceBundle",locale);
        System.out.println(resBundel.getString("greeting"));


    }
}
