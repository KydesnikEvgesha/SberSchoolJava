package secondExercise.model;

import lombok.*;

@Data
public class Employee {

  @NonNull
  @ToString.Include(name = "Имя")
  /** Имя сотрудника */
  private String name;

  @NonNull
  @ToString.Include(name = "Фамилия")
  /** Фамилия сотрудника */
  private String lastName;

  @NonNull
  @ToString.Include(name = "Должность")
  /** Должность */
  private String position;
}
