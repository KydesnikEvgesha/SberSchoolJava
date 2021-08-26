package secondExercise;

import java.util.Objects;

public class Letter extends Document {

  /** От кого письмо */
  String fromDest;

  /** Кому письмо */
  String toDest;

  /** Создаем пустой документ типа письмо */
  public Letter() {
    super();
  }

  /**
   * Создаем письмо с указанием номера и названия
   *
   * @param number номер письма
   * @param title название письма
   */
  public Letter(String number, String title) {
    super(number, title, null);
  }

  /**
   * Создаем письмо с указанием названия, от кого письмо, кому письмо
   *
   * @param title название
   * @param fromDest от кого
   * @param toDest кому
   */
  public Letter(String title, String fromDest, String toDest) {
    super(title);
    this.fromDest = fromDest;
    this.toDest = toDest;
  }

  /**
   * Создаем письмо с указанием номера документа, названия документа, от кого письмо, кому письмо
   *
   * @param number номер документа
   * @param title название документа
   * @param fromDest от кого письмо
   * @param toDest кому письмо
   */
  public Letter(String number, String title, String fromDest, String toDest) {
    super(number, title, null);
    this.fromDest = fromDest;
    this.toDest = toDest;
  }

  /** Получить отправителя письма */
  public String getFromDest() {
    return fromDest;
  }

  /**
   * Указать отправителя письма
   *
   * @param fromDest отправитель письма
   */
  public void setFromDest(String fromDest) {
    this.fromDest = fromDest;
  }

  /** Получить получателя письма */
  public String getToDest() {
    return toDest;
  }

  /**
   * Указать получателя письма
   *
   * @param toDest получатель письма
   */
  public void setToDest(String toDest) {
    this.toDest = toDest;
  }

  @Override
  void setStatus(Enum status) {}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Letter letter = (Letter) o;
    return Objects.equals(fromDest, letter.fromDest) && Objects.equals(toDest, letter.toDest);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), fromDest, toDest);
  }

  @Override
  public String toString() {
    return "Письмо: "
        + "Номер документа ='"
        + super.getNumber()
        + '\''
        + ", название документа ='"
        + super.getTitle()
        + '\''
        + ", от кого ='"
        + fromDest
        + '\''
        + ", кому ='"
        + toDest
        + '\'';
  }

  @Override
  public String printSimple() {
    return super.toString();
  }
}
