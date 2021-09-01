package firstExercise.util;

import firstExercise.Attribute;
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
                pattern = Pattern.compile("[A-Z]([0-9]+)");
                break;
            }
            case NUMBER:
            {
                pattern =
                        Pattern.compile(
                                "_([0-9])");
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
        int matcherCount = 0;
        if (!matcher.find()) {
            return errorMessage;
        } else {
            while (attribute == Attribute.OTHER_PARAM) {
                if (matcherCount > 1) {
                    return matcher.group(1);
                }
                ++matcherCount;
            }
            return matcher.group(1);
        }
    }

    /**
     * Removes the null values from String generated through the @ToString annotation.
     * For example:
     * - replaces:  AddressEntity(id=null, adrType=null, adrStreet=null, adrStreetNum=null, adrComplement=null, adrPoBox=null, adrNip=null, adrCity=city, adrCountry=null, adrNameCorresp=nameCorresp, adrSexCorresp=null, adrSource=null, adrSelectionReason=null, validityBegin=null, validityEnd=null, lastModification=null, dataQuality=null)
     * - by:        AddressEntity(adrCity=city, adrNameCorresp=nameCorresp)
     * Note: does not support tricky attribute content such as "when, x=null, it fails".
     * @param lombokToString a String generated by Lombok's @ToString method
     * @return a string without null values
     */
    public static String removeToStringNullValues(String lombokToString) {
        //Pattern
        return lombokToString != null ? lombokToString
                .replaceAll("(?<=(, |\\())[^\\s(]+?=(null|0)(?:, )?", "")
                .replaceFirst(", \\)$", ")") : null;
    }
}
