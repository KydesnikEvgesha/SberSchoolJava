package firstExercise;

import firstExercise.util.Transports;

public class Maun {

  public static void main(String[] args) {
    //
      Transport car = new Transport(100,100,100);
      Transport carTwo = new Transport(100,100,100,1000);
    System.out.println(Transports.removeToStringNullValues(car.toString()));
    System.out.println(carTwo);
  }
}
