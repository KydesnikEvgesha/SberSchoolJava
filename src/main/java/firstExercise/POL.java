package firstExercise;

import firstExercise.util.Transports;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class POL {
  static String inputLine =
      "{\"C100_1-100\", \"C200_1-120-1200\", \"C300_1-120-30\", \"C400_1-80-20\", \"C100_2-50\", \"C200_2-40-1000\", \"C300_2-200-45\", \"C400_2-10-20\", \"C100_3-10\", \"C200_3-170-1100\", \"C300_3-150-29\", \"C400_3-100-28\", \"C100_1-300\", \"C200_1-100-750\", \"C300_1-32-15\"}\n";

  public static void main(String[] args) {
    // Initialize variable
    List<Transport> transportList = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    System.out.println(
        "Введите входные данные, либо нажмите Enter, чтобы продолжить \n(автоматически подставится строка с массивом данных)");
    String inputString = !scan.nextLine().equals("") ? scan.nextLine() : inputLine;
    Pattern pattern = Pattern.compile("[A-Z][0-9]+_[0-9](-[0-9]+)+");
    Matcher matcher = pattern.matcher(inputString);

//    int countObjectInArrCarInfo = 0; // (int) matcher.results().count(); --for Java 9+
//    while (matcher.find()) {
//      countObjectInArrCarInfo++;
//    }
//    matcher.reset();
//    String[] arrCarInfo =
//        new String
//            [countObjectInArrCarInfo]; // matcher.results().map(MatchResult::group).toArray(String[]::new); --for Java 9+
    while(matcher.find()) {
      transportList.add(new Transport(
              Integer.parseInt(Transports.getAttribute(Attribute.CODE_CAR, matcher.group())),
              Integer.parseInt(Transports.getAttribute(Attribute.NUMBER, matcher.group())),
              Integer.parseInt(Transports.getAttribute(Attribute.MILEAGE, matcher.group())),
              Integer.parseInt(Transports.getAttribute(Attribute.OTHER_PARAM, matcher.group()))
      ));
    }
    /* Выводим общую информацию по расходам ГСМ */
    getInformationPOL(arrCarInfo);

    System.out.println("Введите тип авто для вывода информации в отсортированном виде");
    System.out.println(
        "(100 - легковой авто, 200 - грузовой авто, 300 - пассажирский транспорт, 400 - тяжелая техника)");

    // Вывод отсортированной информации по типу машины
    getSortInformationTypeCar(scan.nextLine(), arrCarInfo);
    scan.close();
  }

  /**
   * Вывод общей информации по расходам ГСМ на каждый вид авто. Вывод класса авто с минимальным и
   * максимальным расходом на ГСМ.
   *
   * @param arrCar входной массив значений со всеми авто
   */
  private static void getInformationPOL(List<Transport> arrCar) {

    /* Расходы ГСМ на каждую машину. Первый индекс - класс авто, второй индекс - номер машины*/
    Map<Integer,Double> arrCarConsumption = new HashMap<>();

    /* Общая стоимость расходов ГСМ*/
    double sumCostPOL = 0;

    /* Стоимость расходов ГСМ на класс авто*/
    double costCarType;

    /* Расходы ГСМ на каждый вид авто*/
    //double[] arrCostCarType = new double[Fuel.values().length];
    Map<Integer,Double> arrCostCarType = new HashMap<>();
    /* Мин. и макс. значение расхода ГСМ по классу авто */
    double minCarTypeCost, maxCarTypeCost;
    
    for (Transport car : arrCar) {
        sumCostPOL += Fuel.valueOf("C"+car.getCodeCar()).getFuelConsumption() * car.getMileage();
    }

    /* Расходы на ГСМ каждой машины */
    for (Transport car : arrCar) {
      arrCarConsumption.put(car.getCodeCar(),Fuel.valueOf("C"+car.getCodeCar()).getFuelConsumption() * car.getMileage());
    }
    /* Расходы на ГСМ на каждый вид авто */
    for (int carType = 0; carType < arrCarConsumption.size(); carType++) {
      costCarType = 0;
      for (double cost : arrCarConsumption) {
        costCarType += cost;
      }
      sumCostPOL += costCarType;
      arrCostCarType.put();
    }
    /* Находим мин. и макс. сумму трат на ГСМ среди авто */
    minCarTypeCost = arrCostCarType.length != 0 ? arrCostCarType[0] : 0;
    maxCarTypeCost = arrCostCarType.length != 0 ? arrCostCarType[0] : 0;
    for (double cost : arrCostCarType) {
      if (cost > maxCarTypeCost) maxCarTypeCost = cost;
      if (cost < minCarTypeCost) minCarTypeCost = cost;
    }

    System.out.println("Общая стоимость расходов на ГСМ: " + sumCostPOL);

    for (int carType = 0; carType < arrCostCarType.length; carType++) {
      System.out.println(
          new StringBuilder()
              .append("    стоимость расходов на класс авто C")
              .append(carType + 1)
              .append("00: ")
              .append(arrCostCarType[carType]));
      if (arrCostCarType[carType] == maxCarTypeCost)
        System.out.println(
            new StringBuilder()
                .append("Тип авто имеющий наибольшую стоимость расходов: ")
                .append(carType + 1)
                .append("00"));
      if (arrCostCarType[carType] == minCarTypeCost)
        System.out.println(
            new StringBuilder()
                .append("Тип авто имеющий наименьшую стоимость расходов: ")
                .append(carType + 1)
                .append("00"));
    }
  }

  private static void getSortInformationTypeCar(String typeCar, String[] arrCar) {
    // счетчик вхождения строк с данными о машине по типу машины
    int carCount = 0;
    // считаем кол-во вхождений строк с данными о машине по типу машины
    for (String car : arrCar) {
      if (getAttribute(ATTRIBUTE.CODE_CAR, car).equals(typeCar)) carCount++;
    }
    // Массив для хранения данных о машинах
    String[] arrCarInfo = new String[carCount];
    for (int i = 0, j = 0; i < arrCar.length; i++) {
      // проверяем, что тип машины из входящего массива arrCar совпадает с выбранным нами типом
      // машины typeCar
      if (getAttribute(ATTRIBUTE.CODE_CAR, arrCar[i]).equals(typeCar)) {
        // Проходимся по массиву данных с копированной информацией о машинах arrCarInfo
        for (int carIndex = 0; carIndex < arrCarInfo.length; carIndex++) {
          // если номер машины во входящем массиве совпадает с номером машины в массиве arrCarInfo,
          // то мы суммируем данные по пробегу
          if (getAttribute(ATTRIBUTE.NUMBER, arrCar[i])
              .equals(getAttribute(ATTRIBUTE.NUMBER, arrCarInfo[carIndex]))) {
            arrCarInfo[carIndex] =
                arrCarInfo[carIndex].replaceFirst(
                    "-[0-9]+",
                    "-"
                        + (Integer.parseInt(getAttribute(ATTRIBUTE.MILEAGE, arrCar[i]))
                            + Integer.parseInt(
                                getAttribute(ATTRIBUTE.MILEAGE, arrCarInfo[carIndex]))));
            // Если у машины есть доп параметр, его информация также суммируется
            if (!getAttribute(ATTRIBUTE.OTHER_PARAM, arrCar[i]).equals("null")) {
              arrCarInfo[carIndex] =
                  arrCarInfo[carIndex].replaceFirst(
                      "-[0-9]+$",
                      "-"
                          + (Integer.parseInt(getAttribute(ATTRIBUTE.OTHER_PARAM, arrCar[i]))
                              + Integer.parseInt(
                                  getAttribute(ATTRIBUTE.OTHER_PARAM, arrCarInfo[carIndex]))));
              break;
            }
          } else {
            // если данной машины нет в массиве arrCarInfo - то добавляем
            arrCarInfo[j] = arrCar[i];
            break;
          }
        }
        j++;
      }
    }
    // Счетчик кол-ва машин
    carCount = 0;
    // считаем кол-во машин в массиве
    for (String car : arrCarInfo) {
      if (car != null) carCount++;
    }
    // Объявляем массив для хранения отсортированных данных
    String[] arrCarSortInfo = new String[carCount];
    // пересоздаем массив, для корректной сортировки
    for (int i = 0; arrCarInfo[i] != null; i++) {
      arrCarSortInfo[i] = arrCarInfo[i];
    }
    // Пузырьковая сортировка по возрастанию
    optimizedBubbleSort(arrCarSortInfo);
    System.out.println(
        "Отсортированный массив с данными по машинам (пузырькова сортировка по пробегу (по возрастанию)): ");
    // Выводим информацию по машинам
    for (String car : arrCarSortInfo) {
      String otherParam =
          !getAttribute(ATTRIBUTE.OTHER_PARAM, car).equals("null")
              ? getAttribute(ATTRIBUTE.OTHER_PARAM, car)
              : "Отсутствует";
      System.out.println(
          new StringBuilder()
              .append("\n------------------------------------------------\n")
              .append("Тип авто: ")
              .append("C" + getAttribute(ATTRIBUTE.CODE_CAR, car) + "\n")
              .append("Гос. номер авто: ")
              .append(getAttribute(ATTRIBUTE.NUMBER, car) + "\n")
              .append("Пробег авто: ")
              .append(getAttribute(ATTRIBUTE.MILEAGE, car) + "\n")
              .append("Доп параметр: " + otherParam + "\n")
              .append("------------------------------------------------"));
    }
  }

  private static void optimizedBubbleSort(String[] arr) {
    int i = 0, n = arr.length;

    boolean swapNeeded = true;
    while (i < n - 1 && swapNeeded) {
      swapNeeded = false;
      for (int j = 1; j < n - i; j++) {
        if (Integer.parseInt(getAttribute(ATTRIBUTE.MILEAGE, arr[j - 1]))
            > Integer.parseInt(getAttribute(ATTRIBUTE.MILEAGE, arr[j]))) {

          String temp = arr[j - 1];
          arr[j - 1] = arr[j];
          arr[j] = temp;
          swapNeeded = true;
        }
      }
      if (!swapNeeded) break;
      i++;
    }
  }
}
