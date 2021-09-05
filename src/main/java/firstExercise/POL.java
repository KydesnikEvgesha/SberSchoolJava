package firstExercise;

import firstExercise.model.type.Attribute;
import firstExercise.model.type.Fuel;
import firstExercise.model.Transport;
import firstExercise.util.Transports;
import util.LombokStrings;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    Transport car;
    while (matcher.find()) {
      car =
          new Transport(
              Transports.getAttribute(Attribute.CODE_CAR, matcher.group()),
              Integer.parseInt(Transports.getAttribute(Attribute.NUMBER, matcher.group())),
              Integer.parseInt(Transports.getAttribute(Attribute.MILEAGE, matcher.group())),
              !Transports.getAttribute(Attribute.CODE_CAR, matcher.group()).equals("C100")
                  ? Integer.parseInt(
                      Transports.getAttribute(Attribute.OTHER_PARAM, matcher.group()))
                  : 0);
      Transport finalCar = car;
      if (transportList.stream()
          .anyMatch(
              transport ->
                  transport.getCodeCar().equals(finalCar.getCodeCar())
                      && transport.getNumber() == finalCar.getNumber())) {
        for (Transport transport : transportList) {
          if (transport.getCodeCar().equals(finalCar.getCodeCar())
              && transport.getNumber() == finalCar.getNumber()) {
            transport.setMileage(transport.getMileage() + finalCar.getMileage());
            transport.setOtherParam(transport.getOtherParam() + finalCar.getOtherParam());
          }
        }
      } else {
        transportList.add(car);
      }
    }
    /* Выводим общую информацию по расходам ГСМ */
    getInformationPOL(transportList);

    System.out.println("Введите тип авто для вывода информации в отсортированном виде");
    System.out.println(
        "(100 - легковой авто, 200 - грузовой авто, 300 - пассажирский транспорт, 400 - тяжелая техника)");

    String typeCar = "C" + scan.nextLine();
    scan.close();
    System.out.println("Вывод отсортированного списка DESC");
    // Вывод отсортированной информации по типу машины DESCENDING
    transportList.stream()
        .filter(transport -> transport.getCodeCar().equals(typeCar))
        .sorted(
            Comparator.comparingInt(Transport::getMileage)
                .thenComparing(Transport::getOtherParam)
                .reversed())
        .collect(Collectors.toList())
        .forEach(
            transport ->
                System.out.println(LombokStrings.removeToStringNullValues(transport.toString())));
    System.out.println("_______________________________________________________________________");
    // Вывод отсортированной информации по типу машины ASCENDING
    System.out.println("Вывод отсортированного списка ASC");
    transportList.stream()
        .filter(transport -> transport.getCodeCar().equals(typeCar))
        .sorted(
            Comparator.comparingInt(Transport::getMileage).thenComparing(Transport::getOtherParam))
        .collect(Collectors.toList())
        .forEach(
            transport ->
                System.out.println(LombokStrings.removeToStringNullValues(transport.toString())));
    System.out.println("_______________________________________________________________________");
  }

  /**
   * Вывод общей информации по расходам ГСМ на каждый вид авто. Вывод класса авто с минимальным и
   * максимальным расходом на ГСМ.
   *
   * @param arrCar коллекция значений со всеми авто
   */
  private static void getInformationPOL(List<Transport> arrCar) {
    Map<String, Double> typeCarCost = new HashMap<>();
    System.out.println(
        "Общая стоимость расходов на ГСМ: "
            + arrCar.stream()
                .mapToDouble(
                    transport ->
                        Fuel.valueOf(transport.getCodeCar()).getFuelConsumption()
                            * transport.getMileage())
                .sum());
    ;

    for (Fuel typeCar : Fuel.values()) {
      double costTypeCar =
          arrCar.stream()
              .filter(transport -> transport.getCodeCar().equals(typeCar.name()))
              .mapToDouble(transport -> typeCar.getFuelConsumption() * transport.getMileage())
              .sum();
      typeCarCost.put(typeCar.name(), costTypeCar);
      System.out.println("Стоимость расходов на класс авто " + typeCar.name() + ": " + costTypeCar);
    }

    System.out.println(
        "Класс авто имеющий наименьшую стоимость расходов: "
            + typeCarCost.entrySet().stream()
                .min(Comparator.comparingDouble(o -> o.getValue()))
                .get());

    System.out.println(
        "Класс авто, имеющий наибольшую стоимость расходов: "
            + typeCarCost.entrySet().stream()
                .max(Comparator.comparingDouble(o -> o.getValue()))
                .get());
  }
}
