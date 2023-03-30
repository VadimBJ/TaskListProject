import java.util.Comparator;

public class SortPriorityAndStatus implements Comparator<Task> {
  @Override
  public int compare(Task o1, Task o2) {
    if (o1.getPriority() == o2.getPriority()) {
      return Boolean.compare(o1.getIsDone(), o2.getIsDone());
    }
    return o1.getPriority().compareTo(o2.getPriority());
  }
}