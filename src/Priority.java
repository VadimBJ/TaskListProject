public enum Priority {
  HIGH(1,"\u001B[31mВысокий\u001B[0m"),
  MEDIUM(2,"\u001B[33mСредний\u001B[0m"),
  LOW(3,"\u001B[32mНизкий \u001B[0m");

  private final int num;
  private final String name;

  Priority(int num, String name) {
    this.num = num;
    this.name = name;
  }

  public int getNum() {
    return num;
  }

  public String getName() {
    return name;
  }
}
