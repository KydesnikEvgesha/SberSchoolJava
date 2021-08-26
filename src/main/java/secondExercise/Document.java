package secondExercise;

import java.util.Objects;
import java.util.Random;

/**
 *
 */
public abstract class Document {

  /** Номер документа */
  private String number;

  /** Название документа */
  private String title;

  /** Статус документа {@link Status} */
  protected Status status;

  /** Создаем документ, номер присваивается через Random, название пустое */
  public Document() {
    this.number = String.valueOf(new Random().nextInt(Integer.MAX_VALUE));
    this.title = "";
  }

  /**
   * Создаем документ с указанием номера документа, название пустое
   *
   * @param number номер документа
   */
  public Document(String number) {
    this.number = number;
    this.title = "";
  }

  /**
   * Создаем документ с указанием номера и названия
   *
   * @param number номер документа
   * @param title название документа
   */
  public Document(String number, String title, Status status) {
    this.number = number;
    this.title = title;
    this.status = status;
  }

  /** Получить номер документа */
  public String getNumber() {
    return number;
  }

  /** Получить название документа */
  public String getTitle() {
    return title;
  }

  /**
   * Присвоить документу название
   *
   * @param title название
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /** Получить текущий статус документа */
  public Status getStatus() {
    return status;
  }

  /**
   * Присвоить документу статус
   *
   * @param status статус {@link Status}
   */
  abstract void setStatus(Status status);

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Document document = (Document) o;
    return number.equals(document.number) && Objects.equals(title, document.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number, title);
  }

  @Override
  public String toString() {
    return "Документ: "
        + "Номер документа ='"
        + number
        + '\''
        + ", название документа='"
        + title
        + '\'';
  }

  public abstract String printSimple();
}
