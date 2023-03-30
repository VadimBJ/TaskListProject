import java.io.BufferedReader;

public class MarkAsDone {
  public static void markAsDone(BufferedReader br, Task task) {
    if (task.getIsDone() == false) {
      task.markAsDone();
    } else {
      System.out.println("Оно уже выполнено!");
    }
  }
}
