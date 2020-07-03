package j8.localization;

import java.util.Locale;
import java.util.ResourceBundle;

public class ListRessouceTest  {

    public static void main(String[] args) {

        Locale locale = Locale.ITALY;
        ResourceBundle resourceBundle = ResourceBundle.getBundle("j8.localization.ResBundle",locale,new TalkativeResourceBundleControl());
        printMovieDetails(resourceBundle);
    }

    static void printMovieDetails(ResourceBundle resBundle){
        String movieName = resBundle.getString("MovieName");
        Long revenue = (Long) (resBundle.getObject("GrossRevenue"));
        Integer year = (Integer) resBundle.getObject("Year");
        System.out.println("Movie " + movieName + "(" + year + ")" + " grossed "+ revenue);
    }
}
