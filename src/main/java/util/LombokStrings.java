package util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LombokStrings {
    /**
     * Удаляет пустые ссылки свойств объекта (ссылочные типы) при использовании аннотации @ToString.
     *
     * @param lombokToString строковое представление из метода toString аннотации lombok @ToString
     * @return строка без пустых значений свойств объекта
     */
    public static String removeToStringNullValues(String lombokToString) {
        return lombokToString != null
                ? lombokToString
                .replaceAll("(?<=(, |\\())[^\\s(]+?=(null|0)(?:, )?", "")
                .replaceFirst(", \\)$", ")")
                : null;
    }
}
