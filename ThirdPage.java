package smartRail;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import javafx.scene.image.Image;

public class ThirdPage {
  int intArray[ ][ ]= new int[20][23];
  GridPane grid = new GridPane();

  //Set every segment of stage by numbers (it will be replaced in method casebook(); below)
  public void Init(int i , int j){
    for (int a = 0; a < i ;a++){
      intArray[0][3*a] = (a+1);
      intArray[0][3*a+1] = (24);
    }
    for (int a = 0; a < j; a++){
      intArray[19][3*a] = (a+11);
      intArray[19][3*a+1] = (25);
    }
    if (i >= j) {
      for (int b = 0; b < j; b++) {
        for (int c = 0; c < 18; c++){
          intArray[c + 1][3 * b + 1] = (21);
        }
      }
      for ( int d = 0; d < (i - j); d ++){
        for (int c = 0; c < 9; c++){
          intArray[c+1][(j+d)*3+1] = (21);
        }
      }
    }
    if (j >= i) {
      for (int b = 0; b < i; b++) {
        for (int c = 0; c < 18; c++){
          intArray[c + 1][3 * b + 1] = (21);
        }
      }
      for ( int d = 0; d < (j - i); d ++){
        for (int c = 0; c < 9; c++){
          intArray[c+10][(i+d)*3+1] = (21);
        }
      }
    }
    for (int x = 0; x <(i-1);x++){
      intArray[8-x] [(x+1)*3-1] = (22);
    }
    for (int y = 0; y <(j-1);y++){
      intArray[11+y] [(y+1)*3-1] = (23);
    }
  }

  //Initialize stage with chosen left station, right station, and trains
  public void NumofTrains(int i , int j, int k) {
    ArrayList<Button> Leftbuttonbag = new ArrayList<Button>();
    ArrayList<Button> Rightbuttonbag = new ArrayList<Button>();

    Stage window = new Stage();
    window.setTitle("SmartRail");
    window.show();

    grid.setAlignment(Pos.TOP_LEFT);
    grid.setHgap(1);
    grid.setVgap(1);
    grid.setPadding(new Insets(0, 0, 0, 0));
    Scene scene = new Scene(grid, 1220, 800);
    window.setScene(scene);
    Init(i, j);
    casebook();
  }

  //Swap called rail to train
  public void change(int x, int y){
    if(intArray[x][y] == 21){
      intArray[x][y] = 26;
    }else if(intArray[x][y] == 22){
      intArray[x][y] = 27;
    }else if(intArray[x][y] == 23){
      intArray[x][y] = 28;
    }else if(intArray[x][y] == 26){
      intArray[x][y] = 21;
    }else if(intArray[x][y] == 27){
      intArray[x][y] = 22;
    }else if(intArray[x][y] == 28){
      intArray[x][y] = 23;
    }else if(intArray[x][y] == 29){
      intArray[x][y] = 30;
    }else if(intArray[x][y] == 30){
      intArray[x][y] = 29;
    }else{
      intArray[x][y] = 0;
    }
    casebook();
  }

  //refresh stage
  public void casebook(){
    grid.getChildren().removeAll();
    for (int a = 0; a < 23 ; a++){
      for (int b = 0; b < 20; b ++){
        switch(intArray[b][a]){
          case 1:
            Label NameofRS1 = new Label("StationA");
            grid.add(NameofRS1, 0, 0);
            break;
          case 2:
            Label NameofRS2 = new Label("StationB");
            grid.add(NameofRS2, 0, 3);
            break;
          case 3:
            Label NameofRS3 = new Label("StationC");
            grid.add(NameofRS3, 0, 6);
            break;
          case 4:
            Label NameofRS4 = new Label("StationD");
            grid.add(NameofRS4, 0, 9);
            break;
          case 5:
            Label NameofRS5 = new Label("StationE");
            grid.add(NameofRS5, 0, 12);
            break;
          case 6:
            Label NameofRS6 = new Label("StationF");
            grid.add(NameofRS6, 0, 15);
            break;
          case 7:
            Label NameofRS7 = new Label("StationG");
            grid.add(NameofRS7, 0, 18);
            break;
          case 8:
            Label NameofRS8 = new Label("StationH");
            grid.add(NameofRS8, 0, 21);
            break;
          case 11:
            Label NameofRS11 = new Label("StationS");
            grid.add(NameofRS11, 19, 0);
            break;
          case 12:
            Label NameofRS12 = new Label("StationT");
            grid.add(NameofRS12, 19, 3);
            break;
          case 13:
            Label NameofRS13 = new Label("StationU");
            grid.add(NameofRS13, 19, 6);
            break;
          case 14:
            Label NameofRS14 = new Label("StationV");
            grid.add(NameofRS14, 19, 9);
            break;
          case 15:
            Label NameofRS15 = new Label("StationW");
            grid.add(NameofRS15, 19, 12);
            break;
          case 16:
            Label NameofRS16 = new Label("StationX");
            grid.add(NameofRS16, 19, 15);
            break;
          case 17:
            Label NameofRS17 = new Label("StationY");
            grid.add(NameofRS17, 19, 18);
            break;
          case 18:
            Label NameofRS18 = new Label("StationZ");
            grid.add(NameofRS18, 19, 21);
            break;
          case 21:
            Image train = new Image(getClass().getResourceAsStream("Train.png"));
            ImageView IVTrain = new ImageView();
            IVTrain.setImage(train);
            grid.add(IVTrain, b, a);
            break;
          case 22:
            Image trainToR = new Image(getClass().getResourceAsStream("TraintoR.png"));
            ImageView IVTrainToR = new ImageView();
            IVTrainToR.setImage(trainToR);
            grid.add(IVTrainToR, b, a);
            break;
          case 23:
            Image trainToL = new Image(getClass().getResourceAsStream("TraintoL.png"));
            ImageView IVTrainToL = new ImageView();
            IVTrainToL.setImage(trainToL);
            grid.add(IVTrainToL, b, a);
            break;
          case 24:
            Image Lstation = new Image(getClass().getResourceAsStream("ToRight.png"));
            ImageView IVLstation = new ImageView();
            IVLstation.setImage(Lstation);
            grid.add(IVLstation, b, a);
            break;
          case 25:
            Image Rstation = new Image(getClass().getResourceAsStream("ToLeft.png"));
            ImageView IVRstation = new ImageView();
            IVRstation.setImage(Rstation);
            grid.add(IVRstation, b, a);
            break;
          case 26:
            Image Train = new Image(getClass().getResourceAsStream("TrainTrain.png"));
            ImageView IVMoving = new ImageView();
            IVMoving.setImage(Train);
            grid.add(IVMoving, b, a);
//            System.out.println("11111111");
            break;
          case 27:
            Image TraintoR = new Image(getClass().getResourceAsStream("TraintoRTrain.png"));
            ImageView IVMovingtoR = new ImageView();
            IVMovingtoR.setImage(TraintoR);
            grid.add(IVMovingtoR, b, a);
            break;
          case 28:
            Image TraintoL = new Image(getClass().getResourceAsStream("TraintoLTrain.png"));
            ImageView IVMovingtoL = new ImageView();
            IVMovingtoL.setImage(TraintoL);
            grid.add(IVMovingtoL, b, a);
            break;
          case 29:
            Image LightR = new Image(getClass().getResourceAsStream("red.png"));
            ImageView Red = new ImageView();
            Red.setImage(LightR);
            grid.add(Red, b, a);
            break;
          case 30:
            Image LightG = new Image(getClass().getResourceAsStream("green.png"));
            ImageView green = new ImageView();
            green.setImage(LightG);
            grid.add(green, b, a);
            break;
        }
      }
    }
  }
}
