import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataUtils {
  public static String getDateToStr(Date date) {
    if (date != null) {
      SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
      return format.format(date);
    }
    return "не указано";
  }

  public static String getDateToStrToFile(Date date) {
    String dateStr = "";
    if (date != null) {
      SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
      return format.format(date);
    }
    return dateStr;
  }

  public static Date getDataFromStr(String dateStr) throws ParseException {
    if (dateStr == null || dateStr.isEmpty()) {
      return null;
    }
    SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
    return format.parse(dateStr);
  }

  public static boolean checkValidDate(Date date) {
    boolean result = false;
    if (date != null) {
      Date dataNow = dateRemoveTime(new Date());
      if (date.compareTo(dataNow) == 0 || date.after(dataNow)) {
        result = true;
      }
    }
    return result;
  }

  private static Date dateRemoveTime(Date date) {
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    return calendar.getTime();
  }

  public static boolean checkFormatData(String dateStr) {
    boolean result = false;
    if (dateStr != null) {
      int month = Integer.parseInt(dateStr.substring(5, 7));
      int day = Integer.parseInt(dateStr.substring(8, 10));
      if (month >= 1 && month <= 12) {
        if (day >= 1 && day <= 31) {
          return true;
        }
      }
    }
    return result;
  }
}
