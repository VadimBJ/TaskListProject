import java.io.*;
import java.util.List;

public class Input {

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

  public static void readTaskFromFile(List<Task> taskList) {
    //todo метод для парсинга файла
  }

  public static Priority takePriority() {
    Priority priority = null;
    //todo метод для получение приоритета задачи
    return priority;
  }

  public static Category takeCategory() {
    Category category = null;
    //todo метод для получение категории задачи
    return category;
  }
}