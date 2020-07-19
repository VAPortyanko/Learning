package internationalization.listResourceBundleAndPropertyResourceBundleEx;

import java.util.ListResourceBundle;
// default bundle.
public class MyClassBundle extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            { "price"   , new Double(10.00) },
            { "currency", "EUR" },
    };
}
