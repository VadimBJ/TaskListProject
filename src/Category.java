public enum Category {
  WORK(1, "Работа"),
  PERSONAL(2, "Личные"),
  SHOPPING(3, "Закупки"),
  HOME(4, "Дом"),
  OTHER(5, "Другие");
  private final int num;
  private final String categoryName;


  Category(int num, String categoryName) {
    this.num = num;
    this.categoryName = categoryName;
  }

  public int getNum() {
    return num;
  }

  public String getName() {
    return categoryName;
  }

}
