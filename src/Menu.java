import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class Menu {
  public static void mainMenu(BufferedReader br, List<Task> taskList) throws IOException {
    System.out.println("""
        Доступные действия:
          1. Посмотреть список задач
          2. Добавить новую задачу
          3. Удалить существующую задачу
          4. Сортировка списка задач
          5. Фильтр задач по категории/статусу/приоритету
          6. Сохранить список задач в файл
          7. Прочитать список задач из файла
          8. Выйти из приложения""");
    System.out.print("Введите номер пункта меню: ");
    int choice = Integer.parseInt(br.readLine());
    switch (choice) {
      case 1 -> System.out.println("1");
      case 2 -> System.out.println("2");
      case 3 -> System.out.println("3");
      case 4 -> System.out.println("4");
      case 5 -> System.out.println("5");
      case 6 -> System.out.println("6");
      case 7 -> System.out.println("7");
      case 8 -> System.out.println("exit");
    }
  }

  public static void menuFilterChoice(BufferedReader br, List<Task> taskList) throws IOException {
    System.out.println("""
        Доступные фильтры:
          1. Отфильтровать список задач по категориям
          2. Отфильтровать список задач по приоритету
          3. Отфильтровать список задач по статусу выполнения
          4. Вернуться в главное меню""");
    System.out.print("Введите номер пункта меню: ");
    int choice = Integer.parseInt(br.readLine());
    switch (choice) {
      case 1 -> System.out.println("1");
      case 2 -> System.out.println("2");
      case 3 -> System.out.println("3");
      case 4 -> System.out.println("4");

    }
  }

  public static void menuAddTask(BufferedReader br, List<Task> taskList) {
    //todo добавление задачи
  }

  public void menuSort() {
//todo меню сортировки
  }

  public static void menuTaskListShow(BufferedReader br, List<Task> taskList) throws IOException {
    System.out.println("""
        Доступные действия:
          1. Просмотреть данные задачи по Id
          2. Добавить новую задачу
          3. Пометить задачу как выполненную
          4. Удалить задачу по Id
          5. Изменить сортировку списка задач
          6. Вернуться в главное меню""");
    System.out.print("Введите номер пункта меню: ");
    int choice = Integer.parseInt(br.readLine());
    switch (choice) {
      case 1 -> System.out.println("1");
      case 2 -> System.out.println("2");
      case 3 -> System.out.println("3");
      case 4 -> System.out.println("4");
      case 5 -> System.out.println("5");
      case 6 -> System.out.println("6");
    }
  }

  public static void menuTaskView(List<Task> taskList) throws IOException {
    // todo просмотр задачи
  }

  public static void menuShowTasksByIsDone(BufferedReader br, List<Task> taskList) throws IOException {
    System.out.println("""
        Доступные варианты:
          1. Просмотреть выполненные задачи
          2. Просмотреть не выполненные задачи
          3. Вернуться в главное меню""");
    System.out.print("Введите номер пункта меню: ");
    int choice = Integer.parseInt(br.readLine());
    switch (choice) {
      case 1 -> System.out.println("1");
      case 2 -> System.out.println("2");
      case 3 -> System.out.println("3");

    }
  }

  public static void menuExit(List<Task> taskList) throws IOException {
    System.out.println("""
        Доступные действия:
          1. Сохранить новые задачи в файл
          2. Выйти без сохранения""");
    System.out.print("Введите номер пункта меню: ");

    //todo меню для выхода
  }
}

