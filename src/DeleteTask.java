import java.util.*;

public class DeleteTask {
  public static void deleteTask(List<Task> taskList, Task task) {
    for (Task taskInList : taskList) {
      if (taskInList.getId() == task.getId()) {
        taskList.remove(taskInList);
      }
    }
  }
}
