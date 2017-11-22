package smartRail;

import another_sample.controller;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Gui extends Application {

  controller con = new controller();

  public static void damnLaunch(){
    launch();
  }

  @Override
  public void start(Stage primaryStage) throws Exception{
    primaryStage.setTitle("Smart Rail");
    GridPane grid = new GridPane();
    //grid.setAlignment(Pos.CENTER);
    grid.setHgap(5);
    grid.setVgap(5);
    grid.setPadding(new Insets(5));

    Text scenetitle = new Text("Smart Rail");
    scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
    grid.add(scenetitle, 60, 20, 2, 1);

    Text name = new Text("Xiao Liang" );
    name.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
    grid.add(name, 10, 10, 2, 1);

    Text name0 = new Text("Shouyu Yang" );
    name.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
    grid.add(name0, 10, 13, 2, 1);

    Text course = new Text("CS351" );
    course.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
    grid.add(course, 10, 20, 2, 1);

    Button btn = new Button("Start Game");
    grid.add(btn, 61, 70);


    btn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        grid.getChildren().removeAll(name, name0,course,scenetitle,btn);
//        con.drawToHands(grid);
      }
    });

    Scene scene = new Scene(grid, 1200, 700);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
