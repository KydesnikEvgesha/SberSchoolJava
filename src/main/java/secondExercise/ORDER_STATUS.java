package secondExercise;

/**
 * Перечисление статусов {@link ORDER_STATUS#CREATED Создан} {@link ORDER_STATUS#EXECUTED Исполнен}
 */
public enum ORDER_STATUS implements ENUM_STAT {

  /** Создан */
  CREATED("СОЗДАН"),
  /** Исполнен */
  EXECUTED("ИСПОЛНЕН");

  String name;

  public String getName() {
    return name;
  }

  ORDER_STATUS(String name) {
    this.name = name;
  }
}
