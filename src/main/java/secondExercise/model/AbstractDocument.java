package secondExercise.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import secondExercise.model.type.Status;

@RequiredArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode
@ToString
public abstract class AbstractDocument {

  @NonNull
  @Getter
  @ToString.Include(name = "Номер")
  /** Номер документа */
  private final String number;

  @NonNull
  @Getter
  @ToString.Include(name = "Название")
  /** Название документа */
  private String title;

  @Getter
  @Builder.ObtainVia(method = "createDoc")
  /** Статус документа {@link Status} */
  protected Status status;

  /**
   * Присвоить документу статус
   *
   * @param status статус {@link Status}
   */
  public abstract void changeStatus(Status status);

  abstract Status createDoc();

  public abstract String printSimple();
}
