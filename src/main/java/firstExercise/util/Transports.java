package firstExercise.util;

import firstExercise.model.type.Attribute;
import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public final class Transports {
  /**
   * Получаем значение аттрибута машины в строковом литерале
   *
   * @param attribute требуемый аттрибут машины {@link Attribute}
   * @param carInfo входная строка с данными о машине
   * @return значение аттрибута
   */
  public String getAttribute(Attribute attribute, String carInfo) {
    if (carInfo == null) return "";
    Pattern pattern;
    String errorMessage = "null";
    switch (attribute) {
      case CODE_CAR:
        {
          pattern = Pattern.compile("([A-Z][0-9]+)");
          break;
        }
      case NUMBER:
        {
          pattern = Pattern.compile("_([0-9])");
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
          break;
        }
      default:
        return errorMessage;
    }
    Matcher matcher = pattern.matcher(carInfo);
    if (!matcher.find()) {
      return errorMessage;
    }
    return matcher.group(1);
  }
}
