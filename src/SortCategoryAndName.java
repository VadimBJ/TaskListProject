import java.util.Comparator;

public class SortCategoryAndName implements Comparator<Task> {
  @Override
  public int compare(Task o1, Task o2) {
    if (o1.getCategory().getName().compareTo(o2.getCategory().getName()) == 0) {
      return o1.getTitle().compareTo(o2.getTitle());
    }
    return o1.getCategory().getName().compareTo(o2.getCategory().getName());
  }
}