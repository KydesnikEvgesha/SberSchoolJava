package thirdExercise.task4;

import java.util.*;

public class SwapMap {
    public static void main(String[] args) {
        Map<Integer, String> mapTest = new HashMap<>();
        mapTest.put(1,"TEST1");
        mapTest.put(2,"TEST2");
        mapTest.put(3,"TEST1");
        System.out.println(swapMap(mapTest));
    }

    public static <K,V> Map<V,Collection<K>> swapMap (Map<K,V> inputMap){
        //Создаем конечный map
        Map<V, Collection<K>> resultMap = new HashMap<>();
        //Объявляем коллекцию для хранения ключей исходной map
        // Берем элемент map "ключ-значение"
        for (Map.Entry<K, V> entry : inputMap.entrySet()) {
            //Проверяем, если в конечной map уже есть элемент исходной map "значение - ключ"
            if (resultMap.containsKey(entry.getValue())) {
                //Инициализируем коллекцию для хранения значений их уже существующей записи конечной Map
                Collection<K> collection = new ArrayList<>(resultMap.get(entry.getValue()));
                //Добавляем (дополняем) значение новыми данными
                collection.add(entry.getKey());
                //Добавляем (перезаписываем) запись в конечный map
                resultMap.put(entry.getValue(), collection);
                continue;
            }
            //Если значение новое, добавляем его в конечный map
            resultMap.put(entry.getValue(), Collections.singletonList(entry.getKey()));
        }
        return resultMap;
    }
}
