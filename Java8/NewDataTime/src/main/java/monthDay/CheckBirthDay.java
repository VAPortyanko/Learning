package monthDay;

import java.time.LocalDate;
import java.time.MonthDay;

public class CheckBirthDay {
	public static void main(String[] args) {
		
		LocalDate dateOfBirth = LocalDate.of(1985, 03, 26);
		MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
		MonthDay currentMonthDay = MonthDay.now();
		     
		if(currentMonthDay.equals(birthday)){
		   System.out.println("Many Many happy returns of the day !!");
		}else{
		   System.out.println("Sorry, today is not your birthday");
		}
	}
}
