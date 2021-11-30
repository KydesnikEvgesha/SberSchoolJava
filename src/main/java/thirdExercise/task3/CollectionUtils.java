package thirdExercise.task3;

import java.util.*;
import java.util.function.Predicate;

public class CollectionUtils {
    public static void main(String[] args) {
        Collection<Integer> collection = new ArrayList<>();
        collection.add(2);
        collection.add(3);
        collection.add(1);
        collection.add(1);
        collection.add(2);
        collection.add(2);
        collection.add(2);
        System.out.println(removeDuplicates(collection));

        Collection<String> collectionString = new LinkedList<>();
        collectionString.add("TEST");
        collectionString.add("TEST1");
        collectionString.add("TEST");
        collectionString.add("TEST3");
        collectionString.add("TEST1");
        System.out.println(removeDuplicates(collectionString));

    }

    public static <T> Collection<T> removeDuplicates(Collection<T> collection) {
        //Создал предикат на проверку, что кол-во повторений элемента больше 1
        Predicate<T> checkDuplicate = itemCollection -> {
            long count = 0L;
            for (T t : collection) {
                if (t.equals(itemCollection)) {
                    count++;
                }
            }
            return count > 1;
        };
        //Итератором проходим по всем элементам и попутно проверяем выполнения условия предиката
        Iterator<T> each = collection.iterator();
        while (each.hasNext()) {
            if (checkDuplicate.test(each.next())) {
                //в случае если кол-во повторений больше 1, удаляем элемент
                each.remove();
            }
        }

        // OR
        //collection.stream().distinct().collect(Collectors.toList());

        return collection;
    }
}
