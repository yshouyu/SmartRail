package smartRail;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FirstPage extends Application {
  controller1 Con = new controller1();
  int NumofLS, NumofRS, NumofT;

  //Open first page and collect # of left station, right station and train
  @Override
  public void start(Stage primaryStage){
    //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    primaryStage.setTitle("Welcome to SmartRail");
    //primaryStage.setScene(new Scene(root, 300, 275));
    primaryStage.show();
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.TOP_LEFT);
    grid.setHgap(1);
    grid.setVgap(1);
    grid.setPadding(new Insets(25, 25, 25, 25));


    Scene scene = new Scene(grid, 400, 275);
    primaryStage.setScene(scene);

    // hbBtn.getChildren().add(button);
       /* Button button = ButtonBuilder.create().text("Start").onAction(new EventHandler<ActionEvent>(){

            @Override public void handle(ActionEvent e){
                Event.fireEvent(primaryStage, new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST ));
            }
        }).build();*/
    Button btn = new Button("Submit");
    HBox hbBtn = new HBox(10);
    hbBtn.getChildren().add(btn);
    hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
    grid.add(hbBtn, 50, 100);

    Label TextofLtrains = new Label("Number of left stations");
    Label TextofRtrains = new Label("Number of right stations");
    Label Textoftrain = new Label("Number of trains");

    grid.add(TextofLtrains, 0, 10);
    grid.add(TextofRtrains, 0, 20);
    grid.add(Textoftrain, 0, 30);


    TextField LTTextField = new TextField();
    TextField RTTextField = new TextField();
    TextField TrainTextField = new TextField();

    grid.add(LTTextField, 50, 10);
    grid.add(RTTextField, 50, 20);
    grid.add(TrainTextField, 50, 30);




    btn.setOnAction(e -> {

      NumofLS = Integer.valueOf(LTTextField.getText()).intValue();
      NumofRS = Integer.valueOf(RTTextField.getText()).intValue();
      NumofT = Integer.valueOf(TrainTextField.getText()).intValue();
      if(Con.checkOverFlow(NumofLS, NumofRS, NumofT)) {
        new SecondPage().NumofTrains(NumofLS, NumofRS, NumofT);
        //new AlertBox().display("abc","abc");
        primaryStage.hide();
      }//else(报错并留在pageone）{  }


    });

    //new WindowUI().start(new Stage());
    // primaryStage.hide();




  }


  //launch (The name is "maiN" not "main"
  public static void maiN(String[] args) {
    launch(args);
  }
}
