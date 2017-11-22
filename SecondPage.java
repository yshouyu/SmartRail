package smartRail;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SecondPage {
  controller1 Con = new controller1();
  char[] leftStation = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
  char[] rightStation = {'s', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
  char[] LeftSaved = new char[8];
  //ArrayList LeftSaved;
  char[] RightSaved = new char[8];
  //ArrayList RightSaved;
  char[] tempSaved;
  int b;

  //Set start stations and destination stations
  public void NumofTrains(int i, int j, int k) {
    Stage window = new Stage();
    window.setTitle("SmartRail");
    window.show();
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.TOP_LEFT);
    grid.setHgap(1);
    grid.setVgap(1);
    grid.setPadding(new Insets(0, 0, 0, 0));
    grid.setPrefSize(1000, 800);
    Scene scene = new Scene(grid, 400, 580);
    window.setScene(scene);

    ArrayList<ChoiceBox> STlistCB = new ArrayList<>();
    ArrayList<ChoiceBox> EDlistCB = new ArrayList<>();

    for (int a = 0; a < k; a++) {
      int temp = (a * 160) + 80;
      Label Train = new Label("Start of train" + (a + 1));
      grid.add(Train, 0, a * 160);
      Label Destination = new Label("Destination of train" + (a + 1));
      grid.add(Destination, 0, temp);

      final ChoiceBox cbStart = new ChoiceBox();
      final ChoiceBox cbEnd = new ChoiceBox();

      for (b = 0; b < i; b++) {
        cbStart.getItems().addAll(leftStation[b]);
        cbEnd.getItems().addAll(leftStation[b]);
        cbStart.setTooltip(new Tooltip("Select the Start of train"));
      }
      grid.add(cbStart, 150, a * 160);
      STlistCB.add(cbStart);

      for (int c = 0; c < j; c++) {
        cbStart.getItems().addAll(rightStation[c]);
        cbEnd.getItems().addAll(rightStation[c]);
        cbEnd.setTooltip(new Tooltip("Select the Destination of train"));
      }
      grid.add(cbEnd, 150, temp);
      EDlistCB.add(cbEnd);
    }
    Button btnSub = new Button("Submit");
    HBox hbBtnSub = new HBox(10);
    hbBtnSub.getChildren().add(btnSub);
    grid.add(hbBtnSub, 170, 200);
    btnSub.setOnAction(e -> {
      for (int d = 0; d < k; d++) {
        int ST = STlistCB.get(d).getSelectionModel().getSelectedIndex();
        int ED = EDlistCB.get(d).getSelectionModel().getSelectedIndex();
//        System.out.println("ST" + d + ": " + ST);
//        System.out.println("ED" + d + ": " + ED);
        char tempRT;
        char tempLF;
        if (ED < i) {
          tempRT = leftStation[ED];
        } else {
          tempRT = rightStation[ED - i];
        }
        if (ST < i) {
          tempLF = leftStation[ST];
        } else {
          tempLF = rightStation[ST - i];
        }

        LeftSaved[d] = tempLF;
        RightSaved[d] = tempRT;
      }

      Con.inputValid(LeftSaved, RightSaved, i, j, k);
      window.hide();
    });
  }
}
