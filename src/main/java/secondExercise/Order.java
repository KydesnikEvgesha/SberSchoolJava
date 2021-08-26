package secondExercise;

import java.util.Objects;

public class Order extends Document {

  /** Сотрудник {@link Employee#Employee()} */
  private Employee employee;

  /** Текст приказа */
  private String text;

  /** Основание */
  private String reason;

  /** Тип приказа {@link OrderType} */
  private OrderType orderType;

  /**
   * Создаем приказ с указанием сотрудника, текста приказа и тип приказа
   *
   * @param employee Сотрудник
   * @param text Текст приказа
   * @param orderType Тип приказа {@link Document#Document()} {@link OrderType}
   */
  public Order(Employee employee, String text, OrderType orderType) {
    super();
    this.employee = employee;
    this.text = text;
    setStatus(OrderStatus.CREATED);
    this.orderType = orderType;
    this.reason = "";
  }

  /**
   * Создаем приказ с указанием сотрудника, текста приказа и тип приказа
   *
   * @param employee Сотрудник
   * @param text Текст приказа
   * @param orderType Тип приказа {@link Document#Document()} {@link OrderType}
   */
  public Order(Employee employee, String text, OrderType orderType, String reason) {
    super();
    this.employee = employee;
    this.text = text;
    setStatus(OrderStatus.CREATED);
    this.orderType = orderType;
    this.reason = reason;
  }

  /**
   * Создаем приказ с указанием номера документа, название документа, сотрудника и тип приказа
   *
   * @param number Номер приказа
   * @param title Название приказа
   * @param employee Сотрудник
   * @param orderType Тип приказа
   * @param reason Основание приказа
   * {@link Document#Document(String, String, Status)}
   * {@link Employee}
   * {@link OrderType}
   */
  public Order(
          String number, String title, Employee employee, OrderType orderType, String reason) {
    super(number, title, OrderStatus.CREATED);
    this.text = "";
    this.employee = employee;
    this.orderType = orderType;
    this.reason = reason;
  }

  /**
   * Создаем приказ с указанием номера документа, название документа, сотрудника, текста приказа и
   * тип приказа
   *
   * @param number Номер приказа
   * @param title Название приказа
   * @param employee Сотрудник
   * @param text Текст приказа
   * @param orderType Тип приказа {@link Document} {@link Employee} {@link OrderType}
   */
  public Order(String number, String title, Employee employee, String text, OrderType orderType) {
    super(number, title, OrderStatus.CREATED);
    this.employee = employee;
    this.text = text;
    this.orderType = orderType;
    this.reason = "";
  }

  /** Получить сотрудника */
  public Employee getEmployee() {
    return employee;
  }

  /** Получить текст приказа */
  public String getText() {
    return text;
  }

  /**
   * Указать текст приказа
   *
   * @param text текст приказа
   */
  public void setText(String text) {
    this.text = text;
  }

  /** Получить основание приказа */
  public String getReason() {
    return reason;
  }

  /** Указать основание приказа */
  public void setReason(String reason) {
    this.reason = reason;
  }

  /** Получить тип приказа */
  public OrderType getOrderType() {
    return orderType;
  }

  /** Установить статус приказа исполнено {@link OrderStatus#EXECUTED} */
  @Override
  public void setStatus(Status status) {
    if (this.status != OrderStatus.EXECUTED) this.status = status;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Order order = (Order) o;
    return Objects.equals(employee, order.employee)
        && Objects.equals(text, order.text)
        && getStatus() == order.getStatus()
        && orderType == order.orderType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), employee, text, getStatus(), orderType);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Приказ: ");
    sb.append("Номер документа: ").append(super.getNumber());
    sb.append(" Название документа: ").append(super.getTitle());
    sb.append(employee);
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
