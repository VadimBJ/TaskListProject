import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Output implements Finals {
  public static String headerColorSort = String.format(HEADER, RESET, RESET, RESET, RESET, RESET, RESET,
      RESET, RESET);
  public static String headerColorFilter = String.format(HEADER, RESET, RESET, RESET, RESET, RESET,
      RESET,
      RESET, RESET);

  public static void showTasksByCategory(BufferedReader br, List<Task> taskList) throws IOException {
    Category category = Input.takeCategory();
    System.out.println();
    headerColorFilter = String.format(HEADER, PURPLE, RESET, RESET, RESET, RESET, RESET, RESET, RESET);
    System.out.println(headerColorFilter);
    for (Task task : taskList) {
      if (task.getCategory().equals(category)) {
        System.out.print(task);
      }
    }
    System.out.println(FOOTER);
    Menu.menuTaskListShow(br, taskList);
  }

  public static void showTasksByPriority(BufferedReader br, List<Task> taskList) throws IOException {
    Priority priority = Input.takePriority();
    System.out.println();
    headerColorFilter = String.format(HEADER, RESET, RESET, PURPLE, RESET, RESET, RESET, RESET, RESET);
    System.out.println(headerColorFilter);
    for (Task task : taskList) {
      if (task.getPriority().equals(priority)) {
        System.out.print(task);
      }
    }
    System.out.println(FOOTER);
    Menu.menuTaskListShow(br, taskList);
  }

  public static void showTasksByIsDone(BufferedReader br, List<Task> taskList, boolean flag) throws IOException {
    System.out.println();
    headerColorFilter = String.format(HEADER, RESET, RESET, RESET, RESET, RESET, RESET, PURPLE, RESET);
    System.out.println(headerColorFilter);
    for (Task task : taskList) {
      if (task.getIsDone() == flag) {
        System.out.print(task);
      }
    }
    System.out.println(FOOTER);
    Menu.menuTaskListShow(br, taskList);
  }

  public static void showAllTasks(BufferedReader br, List<Task> taskList) throws IOException {
    System.out.println();
    System.out.println(headerColorSort);
    for (Task task : taskList) {
      System.out.print(task);
    }
    System.out.println(FOOTER);
    Menu.menuTaskListShow(br, taskList);
    showAllTasks(br, taskList);
  }

  public static void showTaskById(List<Task> taskList, int id) {
    Task task = taskList.get(id); //todo сделать поиск id перебором
    System.out.println("─".repeat(95));
    System.out.println(BLUE + "Задача " + task.getId() + ": " + RESET + task.getTitle());
    System.out.println();
    if (!task.getDescription().trim().isEmpty() && !task.getDescription().contains("-не указано-")) {
      String[] description = task.getDescription().split(" ");
      int symbolCount = 0;
      for (String s : description) {
        symbolCount += s.length();
        System.out.print(s + " ");
        if (symbolCount > 70) {
          System.out.println();
          symbolCount = 0;
        }
      }
    }
    System.out.println("\nДата: " + DataUtils.getDateToStr(task.getPlanDate()));
    System.out.println();
    System.out.println(BLUE + "Категория: " + RESET + task.getCategory().getName());
    System.out.println(BLUE + "Приоритет: " + task.getPriority().getName());
    System.out.println(BLUE + "Статус задачи: " + RESET + (task.getIsDone() ? "Выполнено" : "Не выполнено"));
    System.out.println("─".repeat(60));
    System.out.println();
  }

  public static void sortByStatusAndDataPlan(BufferedReader br, List<Task> taskList) throws IOException {
    System.out.println();
    headerColorSort = String.format(HEADER, RESET, RESET, RESET, RESET, RESET, RESET, PURPLE, RESET);
    System.out.println(headerColorSort);
    SortStatusAndDatePlan sortRule = new SortStatusAndDatePlan();
    Collections.sort(taskList, sortRule);
    for (Task task : taskList) {
      System.out.print(task);
    }
    System.out.println(FOOTER);
    Menu.menuSortChoice(br, taskList);
  }

  public static void sortByStatusAndName(BufferedReader br, List<Task> taskList) throws IOException {
    System.out.println();
    headerColorSort = String.format(HEADER, RESET, RESET, RESET, RESET, PURPLE, RESET, RESET, RESET);
    System.out.println(headerColorSort);
    SortDatePlanAndDescription sortRule = new SortDatePlanAndDescription();
    Collections.sort(taskList, sortRule);
    for (Task task : taskList) {
      System.out.print(task);
    }
    System.out.println(FOOTER);
    Menu.menuSortChoice(br, taskList);
  }

  public static void sortByPriorityAndStatus(BufferedReader br, List<Task> taskList) throws IOException {
    System.out.println();
    headerColorSort = String.format(HEADER, RESET, RESET, RESET, RESET, PURPLE, RESET, RESET, RESET);
    System.out.println(headerColorSort);
    SortPriorityAndStatus sortRule = new SortPriorityAndStatus();
    Collections.sort(taskList, sortRule);
    for (Task task : taskList) {
      System.out.print(task);
    }
    System.out.println(FOOTER);
    Menu.menuSortChoice(br, taskList);
  }

  public static void sortByCategoryAndName(BufferedReader br, List<Task> taskList) throws IOException {
    System.out.println();
    headerColorSort = String.format(HEADER, RESET, RESET, RESET, RESET, PURPLE, RESET, RESET, RESET);
    System.out.println(headerColorSort);
    SortCategoryAndName sortRule = new SortCategoryAndName();
    Collections.sort(taskList, sortRule);
    for (Task task : taskList) {
      System.out.print(task);
    }
    System.out.println(FOOTER);
    Menu.menuSortChoice(br, taskList);
  }

  public static void writeTaskToFile(List<Task> taskList) throws IOException {
    File file = new File("res/TaskList.csv");
    if (!file.exists()) {
      System.out.print(RED + "... файл не найден ..." + RESET);
      if (file.createNewFile()) {
        System.out.print(YELLOW + "... файл создан ..." + RESET);
      } else {
        System.out.print(RED + "... файл не создан ..." + RESET);
        return;
      }
    } else {
      System.out.print(YELLOW + "... файл найден ..." + RESET);
    }
    FileWriter fileWriter = new FileWriter(file);
    for (Task task : taskList) {
      String isDone = task.getIsDone() ? "1" : "0";
      String planeDateStr = DataUtils.getDateToStrToFile(task.getPlanDate());
      String createDateStr = DataUtils.getDateToStrToFile(task.getCreateDate());
      String description = task.getDescription().equals(" -не указано-") ? "" : task.getDescription();
      String result =
          task.getTitle() + ";" + description + ";" + task.getCategory() + ";"
          + task.getPriority() + ";" + planeDateStr + ";" + isDone + ";" + createDateStr;
      fileWriter.write(result + "\n");
    }
    fileWriter.close();
    System.out.println(GREEN + "... файл сохранен!" + RESET);

  }

}
