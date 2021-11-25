package secondExercise.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import secondExercise.model.type.Status;

@SuperBuilder
@EqualsAndHashCode
public class Letter extends AbstractDocument {

  @Getter @Setter
  /** От кого письмо */
  String author;

  @Getter @Setter
  /** Кому письмо */
  String recipient;

  @Override
  public void changeStatus(Status status) {}

  @Override
  Status createDoc() {
    return null;
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
