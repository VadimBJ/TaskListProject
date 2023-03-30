import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Menu implements Finals {
  private static boolean isPlaying = true;

  public static void mainMenu(BufferedReader br, List<Task> taskList) throws IOException {
    while (isPlaying) {
      System.out.println();
      System.out.println(BLUE + "[ ОСНОВНОЕ МЕНЮ ]" + RESET);
      System.out.println("""
          Доступные действия:
            1. Посмотреть список задач
            2. Добавить новую задачу
            3. Удалить существующую задачу
            4. Сортировка списка задач
            5. Фильтр задач по категории/статусу/приоритету
            6. Сохранить список задач в файл
            7. Очистить список задач
            8. Выйти из приложения""");
      System.out.print("Введите номер пункта меню: ");
      int choice = Input.readIntLimited(1, 8);
      switch (choice) {
        case 1 -> Output.showAllTasks(br, taskList);
        case 2 -> menuAddTask(br, taskList);
        case 3 -> Task.taskDeleteById(taskList);
        case 4 -> menuSortChoice(br, taskList); //todo Сортировка списка задач
        case 5 -> menuFilterChoice(br, taskList);
        case 6 -> Output.writeTaskToFile(taskList);
        case 7 -> taskList.clear();
        case 8 -> menuExit(taskList);
      }
    }
  }

  public static void menuFilterChoice(BufferedReader br, List<Task> taskList) throws IOException {
    System.out.println();
    System.out.println(BLUE + "[ ФИЛЬТРАЦИЯ ЗАДАЧ ]" + RESET);
    System.out.println("""
        Доступные фильтры:
          1. Отфильтровать список задач по категориям
          2. Отфильтровать список задач по приоритету
          3. Отфильтровать список задач по статусу выполнения
          4. Вернуться в главное меню""");
    System.out.print("Введите номер пункта меню: ");
    int choice = Input.readIntLimited(1, 4);
    switch (choice) {
      case 1 -> Output.showTasksByCategory(br, taskList);
      case 2 -> Output.showTasksByPriority(br, taskList);
      case 3 -> menuShowTasksByIsDone(br, taskList);
      case 4 -> mainMenu(br, taskList);

    }
  }

  public static void menuSortChoice(BufferedReader br, List<Task> taskList) throws IOException {
    System.out.println();
    System.out.println(BLUE + "[ СOРТИРОВКА ЗАДАЧ ]" + RESET);
    System.out.println("""
        Доступные фильтры:
          1. Отсортировать список задач по статусу
          2. Отсортировать список задач по дате планированная
          3. Отсортировать список задач по приоритету
          4. Отсортировать список задач по категориям
          5. Вернуться в главное меню""");
    System.out.print("Введите номер пункта меню: ");
    int choice = Input.readIntLimited(1, 5);
    switch (choice) {
      case 1 -> Output.sortByStatusAndDataPlan(br, taskList);
      case 2 -> Output.sortByStatusAndName(br, taskList);
      case 3 -> System.out.println();//TODO
      case 4 -> System.out.println();//TODO
      case 5 -> mainMenu(br, taskList);

    }
  }

  public static void menuAddTask(BufferedReader br, List<Task> taskList) throws IOException {
    System.out.println();
    System.out.println(BLUE + "[ ДОБАВЛЕНИЕ НОВОЙ ЗАДАЧИ ]" + RESET);
    String title;
    System.out.println(CYAN + "Поле 'Название' не может быть пустым" + RESET);
    do {
      System.out.print("Введите название для задачи: ");
      title = br.readLine();
      if (title.trim().isEmpty()) {
        System.out.println(RED + "Поле 'Название' не может быть пустым!" + RESET);
      }
    } while (title.trim().isEmpty());
    System.out.println(CYAN + "Поле 'Описание' может быть пустым" + RESET);
    System.out.print("Введите детальное описание задачи: ");
    String description = br.readLine().trim();
    System.out.println(CYAN + "Поле 'Дата' может быть пустым" + RESET);
    boolean cycle = true;
    Date date = null;
    while (cycle) {
      System.out.print("Введите дату для задачи [ГГГГ.ММ.ДД]: ");
      String readDate = br.readLine();
      if (!readDate.isEmpty()) {
        try {
          if (DataUtils.checkFormatData(readDate)) {
            date = DataUtils.getDataFromStr(readDate);
            if (!DataUtils.checkValidDate(date)) {
              System.out.println(RED + "Планируемая дата исполнения задачи не может быть позже текущей даты" + RESET);
            } else {
              cycle = false;
            }
          } else {
            System.out.println(RED + "Некорректный формат даты. Повторите еще раз" + RESET);
          }
        } catch (ParseException | NumberFormatException | StringIndexOutOfBoundsException e) {
          System.out.println(RED + "Некорректный формат даты. Повторите еще раз" + RESET);
        }
      } else {
        cycle = false;
      }
    }
    taskList.add(new Task(title, description, Input.takeCategory(), Input.takePriority(), date,
        false, new Date()));
    System.out.println(CYAN + "... Задача добавлена в общий список задач ..." + RESET);
  }

  public void menuSort() {
//todo меню сортировки
  }


  public static void menuTaskListShow(BufferedReader br, List<Task> taskList) throws IOException {
    System.out.println("""
        Доступные действия:
          1. Просмотреть данные задачи по Id
          2. Добавить новую задачу
          3. Удалить задачу по Id
          4. Пометить задачу как выполненную
          5. Изменить сортировку списка задач
          6. Вернуться в главное меню""");
    System.out.print("Введите номер пункта меню: ");
    int choice = Input.readIntLimited(1, 6);
    switch (choice) {
      case 1 -> menuTaskView(taskList);
      case 2 -> menuAddTask(br, taskList);
      case 3 -> Task.taskDeleteById(taskList);
      case 4 -> Task.taskMarkAsDone(taskList);
      case 5 -> menuSortChoice(br, taskList);
      case 6 -> mainMenu(br, taskList);
    }
  }

  public static void menuTaskView(List<Task> taskList) throws IOException {
    System.out.print("Введите Id задачи: ");
    int id = Input.readIntLimited(1, taskList.size());
    Output.showTaskById(taskList, id - 1);
  }

  public static void menuShowTasksByIsDone(BufferedReader br, List<Task> taskList) throws IOException {
    System.out.println("""
        Доступные варианты:
          1. Просмотреть выполненные задачи
          2. Просмотреть не выполненные задачи
          3. Вернуться в главное меню""");
    System.out.print("Введите номер пункта меню: ");
    int choice = Input.readIntLimited(1, 3);
    switch (choice) {
      case 1 -> Output.showTasksByIsDone(br, taskList, true);
      case 2 -> Output.showTasksByIsDone(br, taskList, false);
      case 3 -> mainMenu(br, taskList);

    }
  }

  public static void menuExit(List<Task> taskList) throws IOException {
    System.out.println();
    System.out.println(BLUE + "[ ВЫХОД ИЗ ПРОГРАММЫ ]" + RESET);
    System.out.println("""
        Доступные действия:
          1. Сохранить новые задачи в файл
          2. Выйти без сохранения""");
    System.out.print("Введите номер пункта меню: ");
    int choice = Input.readIntLimited(1, 2);
    if (choice == 1) {
      Output.writeTaskToFile(taskList);
    }
    isPlaying = false;
    System.out.println(CYAN + "До скорых встреч!");
    System.exit(0);
  }
}


