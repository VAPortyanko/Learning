package d_t_dt;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class D_T_DT_Format {

	private final static Locale BELARUS_LOCALE = new Locale("be", "BY");
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		LocalDate now = LocalDate.now();
		String basicIsoDate   = now.format(DateTimeFormatter.BASIC_ISO_DATE);   // 20180128
		String isoDate        = now.format(DateTimeFormatter.ISO_DATE);         // 2018-01-28
		String isoWeekDate    = now.format(DateTimeFormatter.ISO_WEEK_DATE);    // 2018-W04-7
		String isoLocalDate   = now.format(DateTimeFormatter.ISO_LOCAL_DATE);   // 2018-01-28
		String isoOrdinalDate = now.format(DateTimeFormatter.ISO_ORDINAL_DATE); // 2018-028
		
		String nativeFormat11 = now.format(DateTimeFormatter.ofPattern("d M y"));        // 24 3 2020         
		String nativeFormat12 = now.format(DateTimeFormatter.ofPattern("dd MM yy"));     // 24 03 20        
		String nativeFormat13 = now.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));  // 24 мар 2020           
		String nativeFormat14 = now.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")); // 24 марта 2020           
		String belarusFormat  = now.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", BELARUS_LOCALE)); 

		System.out.println(nativeFormat11);
		System.out.println(nativeFormat12);
		System.out.println(nativeFormat13);
		System.out.println(nativeFormat14);
		System.out.println(belarusFormat);
	
		LocalTime now2 = LocalTime.now();
		String isoLocalTime2 = now2.format(DateTimeFormatter.ISO_LOCAL_TIME); // 08:09:31.514569
		String isoTime2      = now2.format(DateTimeFormatter.ISO_TIME);       // 08:09:31.514569
		
		String nativeFormat2 = now2.format(DateTimeFormatter.ofPattern("hh:mm:ss ")); // 08:10:43
		String engFormat2    = now2.format(DateTimeFormatter.ofPattern("h:mm a"));    // 08:10 AM
		
		LocalDateTime now3 = LocalDateTime.now();
		String rfcFormat3        = now3.format(DateTimeFormatter.ofPattern("E, dd MMM yyyy hh:mm:ss")); // Sun, 28 Jan 2018 08:24:31
		String basicIsoDate3     = now3.format(DateTimeFormatter.BASIC_ISO_DATE);      // 20180128
		String isoDateTime3      = now3.format(DateTimeFormatter.ISO_DATE_TIME);       // 2018-01-28T08:24:31.412511
		String isoLocalDateTime3 = now3.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME); // 2018-01-28T08:24:31.412511
		String isoLocalDate3     = now3.format(DateTimeFormatter.ISO_LOCAL_DATE);      // 2018-01-28
	}
}
