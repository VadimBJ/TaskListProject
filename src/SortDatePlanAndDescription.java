import java.util.Comparator;

//по дате запланированная/название

public class SortDatePlanAndDescription implements Comparator<Task> {
    @Override
    public int compare(Task o1, Task o2) {
        if (o1.getPlanDate() == null && o2.getPlanDate() == null) {
            return o1.getDescription().compareTo(o2.getDescription());
        }
        if (o1.getPlanDate() == null && o2.getPlanDate() != null) {
            return -1;
        }
        if (o1.getPlanDate() != null && o2.getPlanDate() == null) {
            return 1;
        }
        if (o1.getPlanDate().compareTo(o2.getPlanDate()) == 0) {
            return o1.getDescription().compareTo(o2.getDescription());
        }
        return o1.getPlanDate().compareTo(o2.getPlanDate());
    }
}
