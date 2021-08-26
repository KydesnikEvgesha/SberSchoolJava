package secondExercise;

/**
 * Перечисление статусов {@link OrderStatus#CREATED Создан} {@link OrderStatus#EXECUTED Исполнен}
 */
public enum OrderStatus implements Status {

  /** Создан */
  CREATED("СОЗДАН"),
  /** Исполнен */
  EXECUTED("ИСПОЛНЕН");

  String name;

  public String getName() {
    return name;
  }

  OrderStatus(String name) {
    this.name = name;
  }
}
