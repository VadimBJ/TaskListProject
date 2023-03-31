import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
  private Date date;
  @BeforeEach
  public void BeforeEach() throws ParseException {
    date = DataUtils.getDataFromStr("2023.03.30");
  }

  @DisplayName("Тест конструктора со всеми параметрами")
  @Test
  public void taskConstructorTest() {
    Task task = new Task("A", "B", Category.HOME, Priority.HIGH, date, false, date);
    assertEquals("A", task.getTitle());
    assertEquals("B", task.getDescription());
    assertEquals(Category.HOME, task.getCategory());
    assertEquals(Priority.HIGH, task.getPriority());
    assertEquals("2023.03.30", DataUtils.getDateToStr(task.getPlanDate()));
    assertFalse(task.getIsDone());
  }

  @DisplayName("Тест конструктора с пустым полем 'описание'")
  @Test
  public void taskConstructorEmptyDescriptionTest() {
    Task task = new Task("A", "", Category.HOME, Priority.HIGH, date, false,date);
    assertEquals("A", task.getTitle());
    assertEquals("", task.getDescription());
    assertEquals(Category.HOME, task.getCategory());
    assertEquals(Priority.HIGH, task.getPriority());
    assertEquals("2023.03.30", DataUtils.getDateToStr(task.getPlanDate()));
    assertFalse(task.getIsDone());
  }

  @DisplayName("Тест конструктора с пустым полем 'дата'")
  @Test
  public void taskConstructorEmptyDateTest() {
    Task task = new Task("A", "B", Category.HOME, Priority.HIGH, null, false,date);
    assertEquals("A", task.getTitle());
    assertEquals("B", task.getDescription());
    assertEquals(Category.HOME, task.getCategory());
    assertEquals(Priority.HIGH, task.getPriority());
    assertEquals("не указано", DataUtils.getDateToStr(task.getPlanDate()));
    assertFalse(task.getIsDone());
  }

  @DisplayName("Тест toString")
  @ParameterizedTest
  @CsvSource({"A,B,HOME,HIGH,2023.03.30,false"})
  public void toStringTest(String title, String description, Category category, Priority priority,
                           String strDate, boolean isDone) throws ParseException {
    Task task = new Task(title, description, category, priority, DataUtils.getDataFromStr(strDate),
        isDone,date);
    String actual = "" + task;
    String expected = String.format("""
            │%3s │%-25s│%-40s│%10s │   %10s │%11s│%s  %s️   │%n""",
        "03", "A", "B", "Дом", "\u001B[31mВысокий\u001B[0m", "2023.03.30", Menu.SPACE, "⏰");
    assertEquals(expected, actual);
  }
}