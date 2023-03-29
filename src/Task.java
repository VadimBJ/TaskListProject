import java.util.Date;

public class Task {
    private static int count = 0;
    private int id; //уникальный идентификатор задачи
    private String title;//название задачи
    private String description; // описание задачи
    private Category category; // enum категории
    private Priority priority; // enum приоритет
    private Date planDate; // дата, на которую запланирована задача

    private Date createDate;
    private boolean status; // флаг выполнения

    public Task(String title, String description, Category category, Priority priority,
                Date planDate, boolean status) {
        this.id = ++count;
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.planDate = planDate;
        this.status = status;
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

    public Date getPlanDate() {
        return planDate;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}


