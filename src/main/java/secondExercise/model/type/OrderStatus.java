package secondExercise.model.type;

import lombok.AllArgsConstructor;

/**
 * Перечисление статусов {@link OrderStatus#CREATED Создан} {@link OrderStatus#EXECUTED Исполнен}
 */
@AllArgsConstructor
public enum OrderStatus implements Status {

  /** Создан */
  CREATED("СОЗДАН"),
  /** Исполнен */
  EXECUTED("ИСПОЛНЕН");

  String name;

  public String getName() {
    return name;
  }
}
