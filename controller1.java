package smartRail;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;


public class controller1 {
  static FirstPage first = new FirstPage();
  static SecondPage second = new SecondPage();
  static ThirdPage third = new ThirdPage();
  static char[] leftStation = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
  static char[] rightStation = {'s', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
  static int[] railStatement = new int[24];


  //check input #s are valid
  public boolean checkOverFlow(int ls, int rs, int tn){
    if(ls < tn || rs < tn){
      System.out.println("# of train cannot be greater than # of stations!");
//      fakeXml();
      return false;
    }
    return true;
  }
  //pick start and destination stations
  public static boolean inputValid(char[] start, char[] destination, int ls, int rs, int tn){
    char[] s = new char[tn];
    char[] d = new char[tn];
    for(int i = 0; i < tn; i++){
      if(!ifStartStationExist(start[i], destination[i], ls, rs)){
        System.out.println("That station doesn't exist!!");
        return false;
      }
      s[i] = start[i];
      d[i] = destination[i];
    }
    if(conflict(s, tn) || conflict(d, tn)){
      System.out.println("Trains can't start or end at same station!");
      return false;
    }else{
      third.NumofTrains(ls, rs, tn);
      for(int i = 0; i < tn; i++){
        AllClear(ls, rs, s[i], d[i], tn);
      }
      return true;
    }
  }

  private static void toTheLeft(int x, int y){
    third.change(x, y);
    third.change(x - 1, y);
//    System.out.println("11111");
  }

  private static void toTheRight(int x, int y){
    third.change(x, y);
    third.change(x + 1, y);
  }

  private static void toDownLeft(int x, int y){
    third.change(x, y);
    third.change(x - 1, y + 3);
  }

  private static void toDownRight(int x, int y){
    third.change(x, y);
    third.change(x + 1, y + 3);
  }

  private static void toUpLeft(int x, int y){
    third.change(x, y);
    third.change(x - 1, y - 3);
  }

  private static void toUpRight(int x, int y){
    third.change(x, y);
    third.change(x + 1, y - 3);
  }

  //check if ways of each train is all clear
  private static void AllClear(int ls, int rs, char s, char d, int tn){
    for(int i = 0; i < ls; i++) {
      if (s == leftStation[i]) {
        if (s - 97 <= d - 115) {
          if (railAvailable(3 * (s - 97), 3 * (s - 97) + 1, (d - 115) * 3 + 2)){
            moveLeftHigherRight(s, d, 0, tn, 1, 1 + (s - 'a') * 3);
          }else{
            for (int j = 0; j < s - 97; j++) {
              if (railAvailable(3 * (s - 97), 3 * (s - 97) + 1 - 3 * i, (d - 115) * 3 + 2)){
//                third.railToTrain(1, 1 + (s - 'a') * 3, grid);
                moveLeftHigherRight(s, d, j, tn, 1, 1 + (s - 'a') * 3);
              }
            }
          }
        }else{
          if (railAvailable(3 * (s - 97), (d - 115) * 3 + 1, (d - 115) * 3 + 2)) {
            moveLeftRightHigher(s, d, 0, tn, 1, 1 + (s - 'a') * 3);
          } else {
            for (int j = 0; j < s - 115; j++) {
              if (railAvailable(3 * (s - 97), (d - 115) * 3 + 1 - 3 * j, (d - 115) * 3 + 2)){
                moveLeftRightHigher(s, d, j, tn, 1, 1 + (s - 'a') * 3);
              }
            }
          }
        }
      }
    }
    for (int i = 0; i < rs; i++) {
      if (s == rightStation[i]) {
        if (s - 115 <= d - 97) {
          //use rails s[i] - 97, s[i] - 96, and (d[i] - 115) * 3 + 2
          if (railAvailable(3 * (s - 115) + 2, 3 * (s - 115) + 1, (d - 97) * 3)) {
            moveRightHigherLeft(s, d, 0, tn, 18, (s - 's') * 3 + 1);
          }else{
            for (int j = 0; j < s - 115; j++) {
              if (railAvailable(3 * (s - 115) + 2, 3 * (s - 115) + 1 - 3 * j, (d - 97) * 3)) {
                moveRightHigherLeft(s, d, j, tn, 18, (s - 's') * 3 + 1);
              }
            }
          }
        } else {
          if (railAvailable(3 * (s - 115) + 2, 3 * (d - 97) + 1, (d - 97) * 3)) {
            moveRightLeftHigher(s, d, 0, tn, 18, (s - 's') * 3 + 1);
          } else {
            for (int j = 0; j < s - 115; j++) {
              if (railAvailable(3 * (s - 115) + 2, 3 * (d - 97) + 1 - 3 * j, (d - 97) * 3)) {
                moveRightLeftHigher(s, d, j, tn, 18, (s - 's') * 3 + 1);
              }
            }
          }
        }
      }
    }
  }

  //move trains from left to right and change rail statements
  private static void moveLeftHigherRight(char s, char d, int jp, int tn, int x, int y){
    third.change(x, y);
    final int[] temp0 = {jp};
    final int[] temp1 = {jp};
    final int[] i = {x};
    final int[] j = {y};
    Timeline timeline = new Timeline(new KeyFrame(
        Duration.seconds(1), new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent ae) {
        if(i[0] < 8 - (s - 97) && railStatement[3 * (s - 97) + 1] != 0){
          if(railStatement[3 * (s - 97) + 1] == 1) {
            toTheRight(i[0], j[0]);
            i[0]++;
          }
          railStatement[3 * (s - 97)] = 0;
          railStatement[3 * (s - 97) + 1] = 1;
        }
        else if(i[0] >= 8 + (97 - s) && i[0] < 10 + (d - 115) - ((d - 115) - (s - 97)) && railStatement[3 * (d - 115) + 2] != 0){
          if(temp0[0] != 0){
            toUpRight(i[0], j[0]);
            temp0[0]--;
            i[0]++;
            j[0] -= 3;
            railStatement[3 * (s - 97)] = 1;
          }else if(temp1[0] == jp) {
            if(railStatement[3 * (d - 115) + 2] == 1) {
              toTheRight(i[0], j[0]);
              i[0]++;
            }
            railStatement[3 * (s - 97)] = 1;
            railStatement[3 * (s - 97) + 1] = 0;
          }else if(temp1[0] != 0){
            toDownRight(i[0], j[0]);
            temp1[0]--;
            i[0]++;
            j[0]+=3;
          }
        }else if(i[0] >= 10 + (d - 115) - ((d - 115) - (s - 97)) && i[0] < 10 + (d - 115)){
          railStatement[3 * (s - 97) + 1] = 1;
          toDownRight(i[0], j[0]);
          i[0]++;
          j[0]+=3;
        }else if(i[0] >= 10 + (d - 115) && i[0] < 18){
          toTheRight(i[0], j[0]);
          i[0]++;
          railStatement[3 * (s - 97) + 1] = 1;
          railStatement[3 * (d - 115) + 2] = 0;
        }else if(i[0] >= 18) {
          railStatement[3 * (d - 115) + 2] = 1;
          if(tn ==1) {
            moveRightLeftHigher(d, s, jp, 1, i[0], i[0]);
          }
        }
      }
    }));
    timeline.setCycleCount(100);
    timeline.play();
  }

  //move trains from left to right and change rail statements
  private static void moveLeftRightHigher(char s, char d, int jp, int tn, int x, int y) {
    third.change(x, y);
    final int[] temp0 = {jp};
    final int[] temp1 = {jp};
    final int[] i = {x};
    final int[] j = {y};
    Timeline timeline = new Timeline(new KeyFrame(
        Duration.seconds(1), new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent ae) {
        if(i[0] < 8 + 97 - s &&
            railStatement[3 * (d - 115) + 1] != 0){
          toTheRight(i[0], j[0]);
          railStatement[3 * (s - 97)] = 0;
          i[0]++;
        }else if(i[0] >= 8 + 97 - s && i[0] < 8 + (97 - s) + (s - 97) - (d - 115)){
          railStatement[3 * (s - 97)] = 1;
          toUpRight(i[0], j[0]);
          i[0]++;
          j[0]-=3;
        }else if(i[0] >= 8 + (97 - s) + (s - 97) - (d - 115) && i[0] < 10 + (d - 115) && railStatement[3 * (d - 115) + 2] == 1){
          if(temp0[0] != 0){
            toUpRight(i[0], j[0]);
            temp0[0]--;
            i[0]++;
            j[0]-=3;
          }else if(temp1[0] == jp) {
            if(railStatement[3 * (d - 115) + 1] == 2) {
              toTheRight(i[0], j[0]);
              i[0]++;
            }
            railStatement[3 * (d - 115) + 1] = 2;
          }else if(temp1[0] != 0){
            toDownRight(i[0], j[0]);
            temp1[0]--;
            i[0]++;
            j[0]+=3;
          }
        }else if(i[0] < 19 && i[0] >= 10 + (d - 115)){
          toTheRight(i[0], j[0]);
          railStatement[3 * (d - 115) + 1] = 1;
          railStatement[3 * (d - 115) + 2] = 0;
          i[0]++;
        }else if(i[0] >= 18) {
          railStatement[3 * (d - 115) + 2] = 1;
          if(tn == 1) {
            moveRightHigherLeft(d, s, jp, 1, x, y);
          }
        }
      }
    }));
    timeline.setCycleCount(100);
    timeline.play();
  }

  //move trains from left to right and change rail statements
  private static void moveRightHigherLeft(char s, char d, int jp, int tn, int x, int y){
    third.change(x, y);
    final int[] temp0 = {jp};
    final int[] temp1 = {jp};
    final int[] i = {x};
    final int[] j = {y};
    Timeline timeline = new Timeline(new KeyFrame(
        Duration.seconds(1), new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent ae){
        if(i[0] > 11 + (s - 115) && railStatement[3 * (s - 115) + 1] != 0){
          toTheLeft(i[0], j[0]);
          i[0]--;
          railStatement[3 * (s - 115) + 2] = 0;
        }else if(i[0] <= 11 + (s - 115) && i[0] > 8 - (s - 115) && railStatement[3 * (d - 97)] != 0){
          railStatement[3 * (s - 115) + 2] = 1;
          if(temp0[0] != 0){
            if(railStatement[3 * (s - 115) + 1] == 3) {
              toUpLeft(i[0], j[0]);
              temp0[0]--;
              i[0]--;
              j[0]-=3;
            }
            railStatement[3 * (s - 115) + 1] = 3;
          }else if(temp1[0] == jp) {
            if(railStatement[3 * (s - 115) + 1] == 3) {
              railStatement[3 * (s - 115) + 1] = 0;
              toTheLeft(i[0], j[0]);
            /*for(int m = 0; m < 24; m++){
              System.out.print(railStatement[m]);
              if(m % 3 == 2){
                System.out.println("4:");
              }
            }*/
              i[0]--;
            }
            railStatement[3 * (s - 115) + 1] = 3;
          }else if(temp1[0] != 0){
            if(railStatement[3 * (s - 115) + 1] == 3) {
              toDownLeft(i[0], j[0]);
              temp1[0]--;
              i[0]--;
              j[0]+=3;
            }
          }
        }else if(i[0] <= 8 - (s - 115) && i[0] > 8 - (d - 97) && railStatement[(d - 97) * 3] != 0){
          railStatement[3 * (s - 115) + 1] = 1;
          toDownLeft(i[0], j[0]);
          System.out.println("i: " + i[0] +  " j: " + j[0]);
          i[0]--;
          j[0]+=3;
        }else if(i[0] <= 8 - (d - 97) && i[0] > 1){
          if(railStatement[(d - 97) * 3] == 3) {
            railStatement[(d - 97) * 3] = 0;
            toTheLeft(i[0], j[0]);
            i[0]--;
          }
          railStatement[(d - 97) * 3] = 3;
        }else if(i[0] <= 1) {
          railStatement[(d - 97) * 3] = 1;
          if(tn == 1) {
            moveLeftRightHigher(d, s, jp, 1, x, y);
          }
        }
      }
    }));
    timeline.setCycleCount(100);
    timeline.play();
  }

  //move trains from left to right and change rail statements
  private static void moveRightLeftHigher(char s, char d, int jp, int tn, int x, int y){
    third.change(x, y);
    final int[] temp0 = {jp};
    final int[] temp1 = {jp};
    final int[] i = {x};
    final int[] j = {y};
    Timeline timeline = new Timeline(new KeyFrame(
        Duration.seconds(1), new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent ae) {
        if(i[0] > 11 + (s - 115) && railStatement[3 * (d - 97) + 1] != 0){
          toTheLeft(i[0], j[0]);
          i[0]--;
          railStatement[3 * (s - 115) + 2] = 0;
        }else if(i[0] <= 11 + (s - 115) && i[0] > 11 + (d - 97)){
          railStatement[3 * (s - 115) + 2] = 1;
          toUpLeft(i[0], j[0]);
          i[0]--;
          j[0]-=3;
        }else if(i[0] <= 11 + (s - 115) && i[0] > 8 - (d - 97) && railStatement[3 * (d - 97)] != 0){
          if(temp0[0] != 0){
            toUpLeft(i[0], j[0]);
            temp0[0]--;
            i[0]--;
            j[0]-=3;
          }else if(temp1[0] == jp) {
            if(railStatement[3 * (d - 97) + 1] == 4) {
              railStatement[3 * (d - 97) + 1] = 0;
              toTheLeft(i[0], j[0]);
              i[0]--;
            }
            railStatement[3 * (d - 97) + 1] = 4;
          }else if(temp1[0] != 0){
            toDownLeft(i[0], j[0]);
            temp1[0]--;
            i[0]--;
            j[0]+=3;
          }
        }else if(i[0] <= 8 - (d - 97) && i[0] > 1) {
          railStatement[3 * (d - 97) + 1] = 1;
          railStatement[3 * (d - 97)] = 0;
          toTheLeft(i[0], j[0]);
          i[0]--;
        }else if(i[0] <= 1) {
          railStatement[3 * (d - 97)] = 1;
          if(jp == 1){
            moveLeftHigherRight(d, s, jp, tn, x, y);
          }
        }
      }
    }));
    timeline.setCycleCount(100);
    timeline.play();
  }

  //check if rail statement is available
  private static boolean railAvailable(int railOne, int railTwo, int railThree){
    if(railStatement[railOne] == 1 && railStatement[railTwo] == 1
        && railStatement[railThree] == 1){
      return true;
    }
    return false;
  }

  //check if multiple trains has start or end at same stations
  private static boolean conflict(char[] temp, int tn){
    for(int i = 0; i < tn; i++){
      for(int j = i+1 ; j < tn; j++){
        if(temp[i] == temp[j]){
//          System.out.println("temp[i]: " + temp[i] + " temp[j]: " + temp[j]);
          return true;
        }
      }
    }
//    System.out.println("temp[0]: " + temp[0] + " temp[1]: " + temp[1] + " tn: " + tn);
    return false;
  }

  //check if start stations exist
  private static boolean ifStartStationExist(char s, char d, int ls, int rs){
    for(int i = 0; i < ls; i++){
      if(s == leftStation[i] && ifDesStationExistRight(d, rs)){
        return true;
      }else{
      }
    }
    for(int i = 0; i < rs; i++){
      if(s == rightStation[i] && ifDesStationExistLeft(d, ls)){
        return true;
      }else{
      }
    }
    return false;
  }

  //check if destination exist if start from right station
  private static boolean ifDesStationExistLeft(char d, int ls){
    for(int i = 0; i < ls; i++) {
      if (d == leftStation[i]) {
        return true;
      }
    }
    System.out.println("Destination station is invalid!");
    return false;
  }

  //check if destination exist if start from left station
  private static boolean ifDesStationExistRight(char d, int rs){
    for(int i = 0; i < rs; i++) {
      if (d == rightStation[i]) {
        return true;
      }
    }
    System.out.println("Destination station is invalid!");
    return false;
  }


  public static void main(String[] args) {
    for(int i = 0; i < 24; i++){
      railStatement[i] = 1;
    }
    first.maiN(args);
  }
}
