package method_trace_analyser.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeStampToNanosCoverter {
	public static long toNanoSeconds(String timeStamp) {
		Pattern pattern = Pattern.compile("(?<hours>\\d{2}):(?<minutes>\\d{2}):(?<seconds>\\d{2})\\.(?<nanos>\\d{9})");
		Matcher matcher = pattern.matcher(timeStamp);
		if (matcher.find()) {
			long hours;
			long minutes;
			long seconds;
			long nanos;
			try {
				hours = Long.parseLong(matcher.group("hours"));
				minutes = Long.parseLong(matcher.group("minutes"));
				seconds = Long.parseLong(matcher.group("seconds"));
				nanos = Long.parseLong(matcher.group("nanos"));
				hours = hours * (3600L*(long)Math.pow(10, 9));
				minutes = minutes * (60L*(long)Math.pow(10, 9));
				seconds = seconds * (long)Math.pow(10, 9);
				return (hours+minutes+seconds+nanos);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
 		}
		return -1L;
	}

}
