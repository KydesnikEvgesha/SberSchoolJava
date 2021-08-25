package firstExercise;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class POL {
  /**
   * Список аттрибутов машины {@link #CODE_CAR} {@link #NUMBER} {@link #MILEAGE} {@link
   * #OTHER_PARAM}
   */
  enum ATTRIBUTE {
    /** Тип машины */
    CODE_CAR,
    /** Гос. номер машины */
    NUMBER,
    /** Пробег */
    MILEAGE,
    /** Другие параметры */
    OTHER_PARAM
  }
  /** Перечисление видов машин с информацией о расходе и стомости топлива за 1 л. */
  enum FUEL {
    C100(12.5, 46.10),
    C200(12, 48.90),
    C300(11.5, 47.5),
    C400(20, 48.9);

    /** Расход топлива */
    double consumption;
    /** Стоимость топлива за 1 л. */
    double price;

    FUEL(double consumption, double price) {
      this.consumption = consumption;
      this.price = price;
    }

    /** Получаем стоимость 1 км. пути */
    public double getFuelConsumption() {
      return (consumption * price) / 100;
    }
  }

  static String inputLine =
      "{\"C100_1-100\", \"C200_1-120-1200\", \"C300_1-120-30\", \"C400_1-80-20\", \"C100_2-50\", \"C200_2-40-1000\", \"C300_2-200-45\", \"C400_2-10-20\", \"C100_3-10\", \"C200_3-170-1100\", \"C300_3-150-29\", \"C400_3-100-28\", \"C100_1-300\", \"C200_1-100-750\", \"C300_1-32-15\"}\n";

  public static void main(String[] args) {
    // Initialize variable
    Scanner scan = new Scanner(System.in);
    System.out.println(
        "Введите входные данные, либо нажмите Enter, чтобы продолжить \n(автоматически подставится строка с массивом данных)");
    String inputString = !scan.nextLine().equals("") ? scan.nextLine() : inputLine;
    Pattern pattern = Pattern.compile("[A-Z][0-9]+_[0-9](-[0-9]+)+");
    Matcher matcher = pattern.matcher(inputString);

    int countObjectInArrCarInfo = 0; // (int) matcher.results().count(); --for Java 9+
    while (matcher.find()) {
      countObjectInArrCarInfo++;
    }
    matcher.reset();
    String[] arrCarInfo =
        new String
            [countObjectInArrCarInfo]; // matcher.results().map(MatchResult::group).toArray(String[]::new); --for Java 9+
    for (countObjectInArrCarInfo = 0; matcher.find(); countObjectInArrCarInfo++) {
      arrCarInfo[countObjectInArrCarInfo] = matcher.group();
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
   * Получаем значение аттрибута машины в строковом литерале
   *
   * @param attribute требуемый аттрибут машины {@link ATTRIBUTE}
   * @param carInfo входная строка с данными о машине
   * @return значение аттрибута
   */
  private static String getAttribute(ATTRIBUTE attribute, String carInfo) {
    if (carInfo == null) return "";
    Pattern pattern;
    String errorMessage = "null";
    switch (attribute) {
      case CODE_CAR:
        {
          pattern = Pattern.compile("[A-Z]([0-9]+)");
          break; // .toMatchResult().map(MatchResult::group).findFirst().get().split("C")[1]; --for
          // Java 9+
        }
      case NUMBER:
        {
          pattern =
              Pattern.compile(
                  "_([0-9])"); // .matcher(carInfo).results().map(MatchResult::group).findFirst().get().split("_")[1]; --for Java 9+
          break;
        }
      case MILEAGE:
        {
          pattern = Pattern.compile("-([0-9]+)");
          break;
        }
      case OTHER_PARAM:
        {
          pattern = Pattern.compile("-([0-9]+)$");
          break; // .matcher(carInfo).results().map(MatchResult::group).findFirst().get().split("-")[1]; -- for Java 9+
        }
      default:
        return errorMessage;
    }

    Matcher matcher = pattern.matcher(carInfo);
    int matcherCount = 0;
    if (!matcher.find()) {
      return errorMessage;
    } else {
      while (attribute == ATTRIBUTE.OTHER_PARAM) {
        if (matcherCount > 1) {
          return matcher.group(1);
        }
        ++matcherCount;
      }
      return matcher.group(1);
    }
  }
  /**
   * Вывод общей информации по расходам ГСМ на каждый вид авто. Вывод класса авто с минимальным и
   * максимальным расходом на ГСМ.
   *
   * @param arrCar входной массив значений со всеми авто
   */
  private static void getInformationPOL(String[] arrCar) {

    /* Расходы ГСМ на каждую машину. Первый индекс - класс авто, второй индекс - номер машины*/
    // TODO подумать, как адекватно задать размер массива
    double[][] arrCarConsumption = new double[FUEL.values().length][arrCar.length];

    /* Общая стоимость расходов ГСМ*/
    double sumCostPOL = 0;

    /* Стоимость расходов ГСМ на класс авто*/
    double costCarType;

    /* Расходы ГСМ на каждый вид авто*/
    double[] arrCostCarType = new double[FUEL.values().length];

    /* Мин. и макс. значение расхода ГСМ по классу авто */
    double minCarTypeCost, maxCarTypeCost;

    /* Расходы на ГСМ каждой машины */
    for (String car : arrCar) {
      arrCarConsumption[Byte.parseByte(getAttribute(ATTRIBUTE.CODE_CAR, car).substring(0, 1)) - 1][
              Byte.parseByte(getAttribute(ATTRIBUTE.NUMBER, car)) - 1] +=
          FUEL.valueOf("C" + getAttribute(ATTRIBUTE.CODE_CAR, car)).getFuelConsumption()
              * Integer.parseInt(getAttribute(ATTRIBUTE.MILEAGE, car));
    }
    /* Расходы на ГСМ на каждый вид авто */
    for (int carType = 0; carType < arrCarConsumption.length; carType++) {
      costCarType = 0;
      for (double cost : arrCarConsumption[carType]) {
        costCarType += cost;
      }
      sumCostPOL += costCarType;
      arrCostCarType[carType] = costCarType;
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
