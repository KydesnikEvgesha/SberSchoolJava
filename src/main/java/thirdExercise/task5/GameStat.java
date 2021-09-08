package thirdExercise.task5;

import java.util.HashMap;
import java.util.Map;

public class GameStat {
    public static void main(String[] args) {
        String[] inputArray = { "Ivan 5", "Petr 3", "Alex 10", "Petr 8", "Ivan 6", "Alex 5", "Ivan 1", "Petr 5", "Alex 1" };
        System.out.println(getWinner(inputArray));
    }

    public static String getWinner(String[] arrayValue){
        if(arrayValue.length == 0)
            return null;
        //Инициализируем map для хранения пары ключ-значение (имя игрока - кол-во очков)
        Map<String, Integer> gameStat = new HashMap<>();
        //Инициализируем имя лидера по умолчанию Null
        String leaderName = null;
        //Пробегаемся по массиву
        for (String round : arrayValue){
            //Получаем отдельно имя игрока и его кол-во очков для удобства использования
            String namePlayer = round.split(" ")[0];
            int playerScore = Integer.parseInt(round.split(" ")[1]);
            //Назначаем лидером первого игрока из массива
            if(gameStat.size() == 0){
                leaderName = namePlayer;
            }
            //Проверяем, если такой игрок уже есть в map, то перезаписываем его значение
            if (gameStat.containsKey(namePlayer)){
                gameStat.put(namePlayer, gameStat.get(namePlayer) + playerScore);
                //если сумма очков игрока выше чем сумма очков лидера, то меняем имя лидера
                if(gameStat.get(namePlayer) > gameStat.get(leaderName))
                    leaderName = namePlayer;
                continue;
            }
            //если игрок отсутствует, записываем его в map
            gameStat.put(namePlayer, playerScore);
        }
        return leaderName;
    }
}
