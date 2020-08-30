package internationalization.listResourceBundleAndPropertyResourceBundleEx;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleEx {
   public static void main(String[] args) {
	   
	   Locale locale = new Locale("de", "DE"); //no bundle for German -> default
	   ResourceBundle bundle = ResourceBundle.getBundle("internationalization.listResourceBundleAndPropertyResourceBundleEx.MyClassBundle", locale);
	   
	   System.out.println("From classes:");
	   System.out.println("price   : " + bundle.getObject("price"));
	   System.out.println("currency: " + bundle.getObject("currency"));


	   locale = new Locale("da", "DK");
	   bundle = ResourceBundle.getBundle("internationalization.listResourceBundleAndPropertyResourceBundleEx.MyClassBundle", locale);
	   System.out.println();
	   System.out.println("price   : " + bundle.getObject("price"));
	   System.out.println("currency: " + bundle.getObject("currency"));
	   
	   System.out.println();
	   locale = new Locale("en");
	   bundle = ResourceBundle.getBundle("internationalization.listResourceBundleAndPropertyResourceBundleEx.MyClassBundle", locale);
	   
	   System.out.println("From properties file:");
	   System.out.println("price   : " + bundle.getObject("price"));
	   System.out.println("currency: " + bundle.getObject("currency"));
   }
}

