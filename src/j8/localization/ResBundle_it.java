package j8.localization;

import java.util.ListResourceBundle;

public class ResBundle_it extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {
            { "MovieName", "Che Bella Giornata" },
            { "GrossRevenue", (Long) 43000000L }, // in euros
            { "Year", (Integer)2011 }
    };
}
