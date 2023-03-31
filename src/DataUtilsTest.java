import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataUtilsTest {
  Calendar calendar = new GregorianCalendar();

  @Test
  public void getDateToStr_dataNull() {

    assertEquals("не указано", DataUtils.getDateToStr(null));
  }

  @Test
  public void getDateToStr_dateFull() {
    calendar.set(2022, Calendar.NOVEMBER, 23, 12, 11, 11);
    Date date = calendar.getTime();

    assertEquals("2022.11.23", DataUtils.getDateToStr(date));
  }

  @Test
  public void getDateToStr_dateShort() {
    calendar.set(2022, Calendar.NOVEMBER, 23);
    Date date = calendar.getTime();

    assertEquals("2022.11.23", DataUtils.getDateToStr(date));
  }

  @Test
  public void getDateToStrToFile_inputNull() {

    assertEquals("", DataUtils.getDateToStrToFile(null));
  }

  @Test
  public void getDateToStrToFile_dateFull() {
    calendar.set(2022, Calendar.NOVEMBER, 23, 12, 11, 11);
    Date date = calendar.getTime();

    assertEquals("2022.11.23", DataUtils.getDateToStrToFile(date));
  }

  @Test
  public void getDateToStrToFile_dateShort() {
    calendar.set(2022, Calendar.JUNE, 23);
    Date date = calendar.getTime();

    assertEquals("2022.06.23", DataUtils.getDateToStrToFile(date));
  }

  @Test
  public void getDataFromStr_inputEmpty() throws ParseException {

    assertNull(DataUtils.getDataFromStr(""));
  }

  @Test
  public void getDataFromStr_dataShort() throws ParseException {
    calendar.set(2023, Calendar.OCTOBER, 22);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    Date date = calendar.getTime();

    assertEquals(date, DataUtils.getDataFromStr("2023.10.22"));
  }

  @Test
  public void getDataFromStr_dataFull() throws ParseException {
    calendar.set(2023, Calendar.OCTOBER, 22);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    Date date = calendar.getTime();

    assertEquals(date, DataUtils.getDataFromStr("2023.10.22 00 00 0000"));
  }

  @Test
  public void getDataFromStr_dataInvalidFormat() {

    assertThrowsExactly(ParseException.class, () -> DataUtils.getDataFromStr("2023/10/22"));

  }

  @Test
  public void getDataFromStr_dataNull() throws ParseException {

    assertNull(DataUtils.getDataFromStr(null));

  }

  @Test
  public void checkValidDate_currentData() {
    Date date = new Date();

    assertTrue(DataUtils.checkValidDate(date));
  }

  @Test
  public void checkValidDate_dataFuture() {
    calendar.setTime(new Date());
    calendar.add(Calendar.MONTH, 1);

    assertTrue(DataUtils.checkValidDate(calendar.getTime()));
  }

  @Test
  public void checkValidDate_dataPast() {
    calendar.setTime(new Date());
    calendar.set(Calendar.YEAR, 1922);

    assertFalse(DataUtils.checkValidDate(calendar.getTime()));
  }

  @Test
  public void checkValidDate_dataNull() {

    assertFalse(DataUtils.checkValidDate(null));

  }

  @Test
  public void checkFormatData_date() {

    assertTrue(DataUtils.checkFormatData("2023.12.13"));
  }

  @Test
  public void checkFormatData_dataNull() {

    assertFalse(DataUtils.checkFormatData(null));
  }

  @Test
  public void checkFormatData_invalidFormat() {
    String dataStr = "1222.33.44";
    String dataStr2 = "12-33-1944";

    assertFalse(DataUtils.checkFormatData(dataStr));
    assertFalse(DataUtils.checkFormatData(dataStr2));
  }

  @Test
  public void checkFormatData_invalidFormat2() {

    assertThrowsExactly(StringIndexOutOfBoundsException.class, () -> DataUtils.checkFormatData("1ddddd"));
  }

  @Test
  public void checkFormatData_invalidFormat3() {

    assertThrowsExactly(NumberFormatException.class, () -> DataUtils.checkFormatData("2023..12"));
  }

  @Test
  public void checkFormatData_monthInvalidFormat() {

    assertFalse(DataUtils.checkFormatData("2023.13.12"));
    assertFalse(DataUtils.checkFormatData("2023.00.12"));
  }

  @Test
  public void checkFormatData_dayInvalidFormat() {

    assertFalse(DataUtils.checkFormatData("2023.10.00"));
    assertFalse(DataUtils.checkFormatData("2023.10.32"));
  }

}










