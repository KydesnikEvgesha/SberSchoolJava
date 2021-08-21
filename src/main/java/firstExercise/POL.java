package firstExercise;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class POL {
    enum ATTRIBUTE{
        CODE_CAR,
        NUMBER,
        MILEAGE,
        OTHER_PARAM
    }
    public static void main(String[] args) {
        //Initialize var
        Scanner scan = new Scanner(System.in);
        String inputString = scan.nextLine();
        scan.close();
        Pattern pattern = Pattern.compile("[A-Z][0-9]+_[0-9](-[0-9]+)+");
        Matcher matcher = pattern.matcher(inputString);


        int countObjectInArrCarInfo = 0; //(int) matcher.results().count();
        while(matcher.find()){
            countObjectInArrCarInfo++;
        }
        matcher.reset();
        String[] arrCarInfo = new String[countObjectInArrCarInfo]; // matcher.results().map(MatchResult::group).toArray(String[]::new);
        for(countObjectInArrCarInfo = 0; matcher.find(); countObjectInArrCarInfo++){
            arrCarInfo[countObjectInArrCarInfo] = matcher.group();
            System.out.println(getAttribute(ATTRIBUTE.CODE_CAR,arrCarInfo[countObjectInArrCarInfo]));
        }
        //Arrays.stream(arrCarInfo).filter(s -> getAttribute(ATTRIBUTE.CODE_CAR,s) == "100").forEach(System.out::println);
    }
    //Получаем значение атрибута машины
    private static String getAttribute(ATTRIBUTE attribute,String carInfo){
        switch (attribute){
            case CODE_CAR: {
                return Pattern.compile("[A-Z][0-9]+").matcher(carInfo). ? carInfo.().substring(1,4) : "Не найден код машины"; //.toMatchResult().map(MatchResult::group).findFirst().get().split("C")[1];
            }
            case NUMBER:{
                return carInfo != null ? carInfo.substring(1,4) : "Не найден номер машины"; //Pattern.compile("_[0-9]").matcher(carInfo).results().map(MatchResult::group).findFirst().get().split("_")[1];
            }
//            case MILEAGE:{
//                return null;//Pattern.compile("-[0-9]+").matcher(carInfo).results().map(MatchResult::group).findFirst().get().split("-")[1];
//            }
//            case OTHER_PARAM:{
//                if(Pattern.compile("(-[0-9]+)").matcher(carInfo).results().count() > 1){
//                    return Pattern.compile("(-[0-9]+)$").matcher(carInfo).results().map(MatchResult::group).findFirst().get().split("-")[1];
//                }else{
//                    return "Доп. параметр отсутствует";
//                }
//            }
        }
        return null;
    }
}
