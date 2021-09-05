package secondExercise;

import secondExercise.model.AbstractDocument;
import secondExercise.model.Employee;
import secondExercise.model.Letter;
import secondExercise.model.Order;
import secondExercise.model.type.OrderStatus;
import secondExercise.model.type.OrderType;

import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    boolean isSimple = false;
    // Проверяем, есть ли ключ, для упрощенного вывода информации
    for (String e : args) {
      if (e.equals("-simple")) isSimple = true;
    }

    System.out.println("Создаем документы");
    Employee employee = new Employee("Ivan", "Ivanov", "Java Developer");
    AbstractDocument letterAccept =
        Letter.builder()
            .number("1")
            .title("Письмо о приеме на работу")
            .author("Company@company.com")
            .recipient("IvanIvanov@mail.com")
            .build();
    // создаем документ о приеме на работу с автоматической генерацией номера документа
    AbstractDocument orderAccept =
        Order.builder()
            .employee(employee)
            .number("2")
            .title("Приказ о приеме в штат")
            .text(
                "Зачислить "
                    + employee.getName()
                    + " "
                    + employee.getLastName()
                    + " в штат компании на должность: "
                    + employee.getPosition())
            .orderType(OrderType.ACCEPTANCE)
            .build()
            .toBuilder()
            .build();
    // создаем документ об увольнении сотрудника с автоматической генерацией номера документа
    AbstractDocument orderDiss =
        Order.builder()
            .employee(employee)
            .number("3")
            .title("Приказ об увольнении")
            .text(
                "Уволить "
                    + employee.getName()
                    + " "
                    + employee.getLastName()
                    + " из штата компании с должности: "
                    + employee.getPosition())
            .orderType(OrderType.DISMISSAL)
            .reason("По собственному желанию")
            .build()
            .toBuilder()
            .build();
    // Коллекция для хранения документов
    ArrayList<AbstractDocument> abstractDocuments = new ArrayList<>();
    abstractDocuments.add(letterAccept);
    abstractDocuments.add(orderAccept);
    abstractDocuments.add(orderDiss);

    // Выводим информацию по документам и меняем статус у документов
    for (AbstractDocument doc : abstractDocuments) {
      System.out.println(!isSimple ? doc : doc.printSimple());
      doc.changeStatus(OrderStatus.EXECUTED);
    }
    System.out.println("_____________________________________________________________");
    // убеждаемся, что статус у документов изменился
    for (AbstractDocument doc : abstractDocuments) {
      System.out.println(!isSimple ? doc : doc.printSimple());
    }
  }
}
