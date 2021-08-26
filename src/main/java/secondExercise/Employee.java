package secondExercise;

import java.util.Objects;

public class Employee {
  private String name;
  private String lastName;
  private String position;

  /** Создание пустого объекта класса Сотрудник */
  public Employee() {}

  /**
   * Создание объекта класса сотрудник с указанием имени, фамилии, должности
   *
   * @param name имя
   * @param lastName фамилия
   * @param position должность
   */
  public Employee(String name, String lastName, String position) {
    this.name = name;
    this.lastName = lastName;
    this.position = position;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Employee employee = (Employee) o;
    return Objects.equals(name, employee.name)
        && Objects.equals(lastName, employee.lastName)
        && Objects.equals(position, employee.position);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, lastName, position);
  }

  @Override
  public String toString() {
    return "Сотрудник: "
        + "Имя ='"
        + name
        + '\''
        + ", Фамилия='"
        + lastName
        + '\''
        + ", Должность='"
        + position
        + '\'';
  }
}
