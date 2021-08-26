package secondExercise;

/**
 * Перечисление типа приказа {@link ORDER_TYPE#ACCEPTANCE Прием} {@link ORDER_TYPE#DISMISSAL
 * Увольнение}
 */
public enum ORDER_TYPE implements ENUM_STAT {
  /** Прием */
  ACCEPTANCE("ПРИЕМ"),
  /** Увольнение */
  DISMISSAL("УВОЛЬНЕНИЕ");

  String name;

  public String getName() {
    return name;
  }

  ORDER_TYPE(String name) {
    this.name = name;
  }
}
