package firstExercise.model;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Transport {
  /** Тип машины */
  @NonNull
  @ToString.Include(name = "тип_машины")
  private String codeCar;

  /** Гос. номер машины */
  @NonNull
  @ToString.Include(name = "гос.номер_машины")
  private int number;

  /** Пробег */
  @NonNull
  @ToString.Include(name = "пробег")
  private int mileage;

  /** Доп. параметр */
  @ToString.Include(name = "доп.параметр")
  private int otherParam;
}
