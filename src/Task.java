public class Task {
  private static int count = 0;
  private int id; //уникальный идентификатор задачи
  private String title;//название задачи
  private String description; // описание задачи
  private Category category; // enum категории
  private Priority priority; // enum приоритет
  private String date; // дата, на которую запланирована задача
  private boolean isDone; // флаг выполнения

  public Task(String title, String description, Category category, Priority priority,
              String date, boolean isDone) {
    this.id = ++count;
    this.title = title;
    this.description = description;
    this.category = category;
    this.priority = priority;
    this.date = date;
    this.isDone = isDone;
  }

  public static int getCount() {
    return count;
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

  public String getDate() {
    return date;
  }

  public boolean isDone() {
    return isDone;
  }
}
