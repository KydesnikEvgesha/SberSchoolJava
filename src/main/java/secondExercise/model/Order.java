package secondExercise.model;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import secondExercise.model.type.OrderStatus;
import secondExercise.model.type.OrderType;
import secondExercise.model.type.Status;

@EqualsAndHashCode
@SuperBuilder(toBuilder = true)
public class Order extends AbstractDocument {

  @NonNull
  /** Сотрудник {@link Employee} */
  private Employee employee;

  @NonNull
  /** Текст приказа */
  private String text;

  /** Основание */
  private String reason;

  @NonNull
  /** Тип приказа {@link OrderType} */
  private OrderType orderType;

  /** Установить статус приказа исполнено {@link OrderStatus#EXECUTED} */
  @Override
  public void changeStatus(Status status) {
    if (this.status != OrderStatus.EXECUTED) this.status = status;
  }

  @Override
  public Status createDoc() {
    return OrderStatus.CREATED;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Приказ: ");
    sb.append("Номер документа: ").append(super.getNumber());
    sb.append(" Название документа: ").append(super.getTitle());
    sb.append(", Текст приказа='").append(text).append('\'');
    sb.append(", Статус приказа=").append(super.getStatus().getName());
    sb.append(", Тип приказа=").append(orderType.getName());
    if (orderType == OrderType.DISMISSAL)
      sb.append(" ,Причина увольнения='").append(reason).append('\'');
    return sb.toString();
  }

  @Override
  public String printSimple() {
    return super.toString();
  }
}
