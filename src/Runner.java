import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Runner {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    List<Task> taskList = new ArrayList<>();
    System.out.println(Output.logo);
    Menu.mainMenu(br, taskList);

  }
}