package j8.localization;

import java.util.ListResourceBundle;

public class ResBundle extends ListResourceBundle {
    static final Object[][] contents = {
            { "MovieName", "Avatar" },
            { "GrossRevenue", (Long) 2782275172L },
            { "Year", (Integer)2009 }
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
