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
            5. Фильтр задач по категории/приоритету/статусу
            6. Сохранить список задач в файл
            7. Очистить список задач
            8. Выйти из приложения""");
      System.out.print("Введите номер пункта меню: ");
      int choice = Input.readIntLimited(1, 8);
      switch (choice) {
        case 1 -> Output.showAllTasks(br, taskList);
        case 2 -> menuAddTask(br, taskList);
        case 3 -> Task.taskDeleteById(taskList);
        case 4 -> menuSortChoice(br, taskList);
        case 5 -> menuFilterChoice(br, taskList);
        case 6 -> Output.writeTaskToFile(taskList);
        case 7 -> menuClearTasklist(taskList);
        case 8 -> menuExit(taskList);
      }
    }
  }

  public static void menuClearTasklist(List<Task> taskList) throws IOException {
    System.out.println();
    System.out.println(RED + "[ УДАЛЕНИЕ ВСЕХ ЗАДАЧ ]" + RESET);
    System.out.println("""
        Вы уверены что хотите удалить все задачи из списка?
          1. Да, я хочу удалить все даступные задачи
          2. Нет""");
    System.out.print("Введите номер пункта меню: ");
    int choice = Input.readIntLimited(1, 2);
    if (choice == 1) {
      taskList.clear();
      System.out.println(CYAN + "... Список задач очищен ..." + RESET);
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
          1. Отсортировать список задач по категориям
          2. Отсортировать список задач по приоритету
          3. Отсортировать список задач по дате
          4. Отсортировать список задач по статусу
          5. Вернуть стандартную сортировку""");
    System.out.print("Введите номер пункта меню: ");
    int choice = Input.readIntLimited(1, 5);
    switch (choice) {
      case 1 -> Output.sortByCategoryAndName(br, taskList);
      case 2 -> Output.sortByPriorityAndStatus(br, taskList);
      case 3 -> Output.sortByStatusAndName(br, taskList);
      case 4 -> Output.sortByStatusAndDataPlan(br, taskList);
      case 5 -> Output.sortById(br, taskList);
    }
  }

  public static void menuAddTask(BufferedReader br, List<Task> taskList) throws IOException {
    System.out.println();
    System.out.println(BLUE + "[ ДОБАВЛЕНИЕ НОВОЙ ЗАДАЧИ ]" + RESET);
    String title;
    System.out.println(CYAN + "Поле 'Название' " + RED + "НЕ МОЖЕТ" + CYAN + " быть пустым" + RESET);
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
      if (!readDate.trim().isEmpty()) {
        try {
          if (DataUtils.checkFormatData(readDate)) {
            date = DataUtils.getDataFromStr(readDate);
            if (!DataUtils.checkValidDate(date)) {
              System.out.println(RED + "Планируемая дата исполнения задачи не может быть раньше " +
                                 "текущей даты!" + RESET);
            } else {
              cycle = false;
            }
          } else {
            System.out.println(RED + "Некорректный формат даты. Повторите еще раз!" + RESET);
          }
        } catch (ParseException | NumberFormatException | StringIndexOutOfBoundsException e) {
          System.out.println(RED + "Некорректный формат даты. Повторите еще раз!" + RESET);
        }
      } else {
        cycle = false;
      }
    }
    taskList.add(new Task(title, description, Input.takeCategory(), Input.takePriority(), date,
        false, new Date()));
    System.out.println(CYAN + "... Задача добавлена в общий список задач ..." + RESET);
  }

  public static void menuTaskListShow(BufferedReader br, List<Task> taskList) throws IOException {
    System.out.println("""
        Доступные действия:
          1. Просмотреть данные задачи по Id
          2. Добавить новую задачу
          3. Удалить задачу по Id
          4. Пометить задачу как выполненную
          5. Пометить задачу как НЕвыполненную
          6. Изменить сортировку списка задач
          7. Изменить фильтр для списка задач
          8. Вернуться в главное меню""");
    System.out.print("Введите номер пункта меню: ");
    int choice = Input.readIntLimited(1, 8);
    switch (choice) {
      case 1 -> menuTaskView(taskList);
      case 2 -> menuAddTask(br, taskList);
      case 3 -> Task.taskDeleteById(taskList);
      case 4 -> Task.taskMarkAsDone(taskList);
      case 5 -> Task.taskMarkAsUndone(taskList);
      case 6 -> menuSortChoice(br, taskList);
      case 7 -> menuFilterChoice(br, taskList);
      case 8 -> mainMenu(br, taskList);
    }
  }

  public static void menuTaskView(List<Task> taskList) throws IOException {
    System.out.print("Введите Id задачи: ");
    int id = Input.readIntLimited(1, taskList.size());
    Output.showTaskById(taskList, id);
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


