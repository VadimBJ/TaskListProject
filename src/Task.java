import java.io.IOException;
import java.util.Date;
import java.util.List;

public class Task {
  private static int count = 0;
  private int id; //уникальный идентификатор задачи
  private String title;//название задачи
  private String description; // описание задачи
  private Category category; // enum категории
  private Priority priority; // enum приоритет
  private Date planDate; // дата, на которую запланирована задача

  private Date createDate;
  private boolean isDone; // флаг выполнения

  public Task(String title, String description, Category category, Priority priority,
              Date planDate, boolean isDone, Date createDate) {
    this.id = ++count;
    this.title = title;
    this.description = description;
    this.category = category;
    this.priority = priority;
    this.planDate = planDate;
    this.isDone = isDone;
    this.createDate = createDate;
  }

  @Override
  public String toString() {
    String id1 = "00" + id;
    id1 = id1.substring(id1.length() - 2);
    String title1 = title;
    if (title1.length() > 24) {
      title1 = title1.substring(0, 22) + "..";
    }
    String description1 = description;
    if (description1.isEmpty()) {
      description1 = " -не указано-";
    } else if (description1.length() > 39) {
      description1 = description1.substring(0, 37) + "..";
    }
    String category1 = category.getName();
    String priority1 = priority.getName();
    String date1 = DataUtils.getDateToStr(planDate);
    String isDone1 = isDone ? "✔️" : "⏰";
    return String.format("""
            │%3s │%-25s│%-40s│%10s │   %10s │%11s│%s  %s️   │%n""",
        id1, title1, description1, category1, priority1, date1, Menu.SPACE, isDone1);
  }

  public int getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public Category getCategory() {
    return category;
  }

  public Priority getPriority() {
    return priority;
  }

  public Date getPlanDate() {
    return planDate;
  }

  public boolean getIsDone() {
    return isDone;
  }

  public static void taskDeleteById(List<Task> taskList) throws IOException {
    System.out.print("Введите Id задачи которую хотите удалить: ");
    int id = Input.readIntLimited(0, count);
    boolean isNotFound = true;
    for (int i = 0; i < taskList.size(); i++) {
      if (taskList.get(i).getId() == id) {
        taskList.remove(i);
        System.out.println(Menu.CYAN + "Задача с Id:" + id + " удалена!" + Menu.RESET);
        isNotFound = false;
        break;
      }
    }
    if (isNotFound) {
      System.out.println(Menu.RED + "Задача с Id:" + id + " не найдена!" + Menu.RESET);
    }
  }

  public void markAsDone() {
    this.isDone = true;
  }

  public void markAsUndone() {
    this.isDone = false;
  }

  public Date getCreateDate() {
    return createDate;
  }
}
