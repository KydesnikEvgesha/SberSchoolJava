package secondExercise;

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
    Document letterAccept =
        new Letter("1", "Письмо о приеме на работу", "Company@company.com", "IvanIvanov@mail.com");
    // создаем документ о приеме на работу с автоматической генерацией номера документа
    Document orderAccept =
        new Order(
            employee,
            "Зачислить "
                + employee.getName()
                + " "
                + employee.getLastName()
                + " в штат компании на должность: "
                + employee.getPosition(),
            ORDER_TYPE.ACCEPTANCE);
    // создаем документ об увольнении сотрудника с автоматической генерацией номера документа
    Document orderDiss =
        new Order(
            employee,
            "Уволить "
                + employee.getName()
                + " "
                + employee.getLastName()
                + " из штата компании с должности: "
                + employee.getPosition(),
            ORDER_TYPE.DISMISSAL,
            "По собственному желанию");
    // Коллекция для хранения документов
    ArrayList<Document> documents = new ArrayList<>();
    documents.add(letterAccept);
    documents.add(orderAccept);
    documents.add(orderDiss);

    // Выводим информацию по документам и меняем статус у документов
    for (Document doc : documents) {
      System.out.println(!isSimple ? doc : doc.printSimple());
      if (doc instanceof Order) doc.setStatus(ORDER_STATUS.EXECUTED);
    }
    System.out.println("_____________________________________________________________");
    // убеждаемся, что статус у документов изменился
    for (Document doc : documents) {
      System.out.println(!isSimple ? doc : doc.printSimple());
    }
  }
}
