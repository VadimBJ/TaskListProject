import java.util.Comparator;


public class SortStatusAndDatePlan implements Comparator<Task> {

//по статусу/дата запланированная/дата создания

    @Override
    public int compare(Task o1, Task o2) {
        if (Boolean.compare(o1.getIsDone(), o2.getIsDone()) == 0) {
            if (o1.getPlanDate() == null && o2.getPlanDate() == null) {
                return o1.getCreateDate().compareTo(o2.getCreateDate());
            }
            if (o1.getPlanDate() == null && o2.getPlanDate() != null) {
                return -1;
            }
            if (o1.getPlanDate() != null && o2.getPlanDate() == null) {
                return 1;
            }
            if (o1.getPlanDate().compareTo(o2.getPlanDate()) == 0) {
                return o1.getCreateDate().compareTo(o2.getCreateDate());
            }
            return o1.getPlanDate().compareTo(o2.getPlanDate());
        }
        return Boolean.compare(o1.getIsDone(), o2.getIsDone());

    }
}
