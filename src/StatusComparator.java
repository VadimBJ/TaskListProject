import java.util.Comparator;

public class StatusComparator implements Comparator<Task> {
  @Override
  public int compare(Task o1, Task o2) {
    if (o1.getIsDone() == true) {
      return o1.getId();
    } else if (o2.getIsDone()== true){
      return o2.getId();
    } else {
      return o1.getTitle().compareTo(o2.getTitle());
    }
  }
}
