import java.io.*;
import java.util.Date;
import java.text.ParseException;
import java.util.List;

public class Input implements Finals {

  public static void readTaskFromFile(List<Task> taskList) throws IOException {
    File file = new File("res/TaskList.csv");
    if (file.exists()) {
      BufferedReader fr = new BufferedReader(new FileReader(file));
      for (String line = fr.readLine(); line != null; line = fr.readLine()) {
        String[] lines = line.split(";", -1);
        String title = lines[0];
        String description = lines[1];
        if (description.isEmpty()) {
          description = " -не указано-";
        }
        Category category = Category.valueOf(lines[2]);
        Priority priority = Priority.valueOf(lines[3]);
        boolean isDone = lines[5].equals("1");
        Date planDate = null;
        Date createDate = null;
        try {
          planDate = DataUtils.getDataFromStr(lines[4]);
          createDate = DataUtils.getDataFromStr(lines[6]);

        } catch (ParseException e) {
          System.out.println();
        }
        Task tmpTask = new Task(title, description, category, priority, planDate, isDone, createDate);
        taskList.add(tmpTask);
      }
      fr.close();
      System.out.println(CYAN + "... Файл прочитан ..." + RESET);
    }
  }

  public static int readIntLimited(int min, int max) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int num = 0;
    do {
      try {
        num = Integer.parseInt(br.readLine());
      } catch (NumberFormatException e) {
        System.out.println("Вводите только цифры!");
      }
      if (!(num >= min && num <= max)) {
        System.out.printf("Введите число от %d до %d: ", min, max);
      }
    } while (!(num >= min && num <= max));
    return num;
  }

  public static Priority takePriority() throws IOException {
    int lastOfNum = 1;
    System.out.println("Выберите приоритет задачи:");
    for (Priority values : Priority.values()) {
      System.out.println("  " + values.getNum() + ". " + values.getName());
      lastOfNum = values.getNum();
    }
    System.out.print("Введите номер пункта меню: ");
    int choice = readIntLimited(1, lastOfNum);
    Priority priority = null;
    for (Priority values : Priority.values()) {
      if (values.getNum() == choice) {
        priority = values;
      }
    }
    return priority;
  }

  public static Category takeCategory() throws IOException {
    int lastOfNum = 1;
    System.out.println("Выберите категорию задачи:");
    for (Category values : Category.values()) {
      System.out.println("  " + values.getNum() + ". " + values.getName());
      lastOfNum = values.getNum();
    }
    System.out.print("Введите номер пункта меню: ");
    int choice = readIntLimited(1, lastOfNum);
    Category category = null;
    for (Category values : Category.values()) {
      if (values.getNum() == choice) {
        category = values;
      }
    }
    return category;
  }
}
