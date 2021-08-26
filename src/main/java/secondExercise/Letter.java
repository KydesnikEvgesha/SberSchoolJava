package secondExercise;

import java.util.Objects;

public class Letter extends Document {

  /** От кого письмо */
  String author;

  /** Кому письмо */
  String recipient;

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
   * @param author от кого
   * @param recipient кому
   */
  public Letter(String title, String author, String recipient) {
    super(title);
    this.author = author;
    this.recipient = recipient;
  }

  /**
   * Создаем письмо с указанием номера документа, названия документа, от кого письмо, кому письмо
   *
   * @param number номер документа
   * @param title название документа
   * @param author от кого письмо
   * @param recipient кому письмо
   */
  public Letter(String number, String title, String author, String recipient) {
    super(number, title, null);
    this.author = author;
    this.recipient = recipient;
  }

  /** Получить отправителя письма */
  public String getAuthor() {
    return author;
  }

  /**
   * Указать отправителя письма
   *
   * @param author отправитель письма
   */
  public void setAuthor(String author) {
    this.author = author;
  }

  /** Получить получателя письма */
  public String getRecipient() {
    return recipient;
  }

  /**
   * Указать получателя письма
   *
   * @param recipient получатель письма
   */
  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }

  @Override
  void setStatus(Status status) {}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Letter letter = (Letter) o;
    return Objects.equals(author, letter.author) && Objects.equals(recipient, letter.recipient);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), author, recipient);
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
        + author
        + '\''
        + ", кому ='"
        + recipient
        + '\'';
  }

  @Override
  public String printSimple() {
    return super.toString();
  }
}
