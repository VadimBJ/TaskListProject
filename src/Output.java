import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    boolean isFound = false;
    for (int i = 0; i < taskList.size(); i++) {
      if (taskList.get(i).getId() == id) {
        id = i;
        isFound = true;
        break;
      }
    }
    if (isFound) {
      Task task = taskList.get(id);
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
    } else {
      System.out.println(Menu.RED + "Задача с Id: " + id + " не найдена!" + Menu.RESET);
    }
  }

  public static void sortByStatusAndDataPlan(BufferedReader br, List<Task> taskList) throws IOException {
    System.out.println();
    headerColorSort = String.format(HEADER, RESET, RESET, RESET, RESET, RESET, RESET, PURPLE, RESET);
    System.out.println(headerColorSort);
    SortStatusAndDatePlan sortRule = new SortStatusAndDatePlan();
    taskList.sort(sortRule);
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
    taskList.sort(sortRule);
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
    taskList.sort(sortRule);
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
    taskList.sort(sortRule);
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