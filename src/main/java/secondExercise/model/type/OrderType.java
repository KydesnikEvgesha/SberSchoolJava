package secondExercise.model.type;

import lombok.AllArgsConstructor;

/**
 * Перечисление типа приказа {@link OrderType#ACCEPTANCE Прием} {@link OrderType#DISMISSAL
 * Увольнение}
 */
@AllArgsConstructor
public enum OrderType implements Status {
  /** Прием */
  ACCEPTANCE("ПРИЕМ"),
  /** Увольнение */
  DISMISSAL("УВОЛЬНЕНИЕ");

  String name;

  public String getName() {
    return name;
  }
}
