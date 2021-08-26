package secondExercise;

/**
 * Перечисление типа приказа {@link OrderType#ACCEPTANCE Прием} {@link OrderType#DISMISSAL
 * Увольнение}
 */
public enum OrderType implements Status {
  /** Прием */
  ACCEPTANCE("ПРИЕМ"),
  /** Увольнение */
  DISMISSAL("УВОЛЬНЕНИЕ");

  String name;

  public String getName() {
    return name;
  }

  OrderType(String name) {
    this.name = name;
  }
}
