package internationalization.locale;

import java.util.Locale;

public class LocaleEx {
	public static void main(String[] args) {
		Locale lo = new Locale("en", "US");
		System.out.println(lo.toString());
		System.out.println(Locale.getDefault());
		System.out.println(lo.getDisplayCountry());
		System.out.println(lo.getDisplayLanguage());
		System.out.println(lo.getDisplayName());
	}
}
