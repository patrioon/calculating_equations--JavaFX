import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import static javafx.scene.paint.Color.*;


public class Program extends Application {

    private TextField tfa = new TextField();
    private TextField tfb = new TextField();
    private TextField tfc = new TextField();
    private Button btsolve = new Button("Solve");
    private Button btnClear = new Button("Clear");
    private Tooltip toolTip = new Tooltip();

    private Label sumLabel = new Label();
    private Label sumLabelA = new Label();
    private Label sumLabel2A = new Label("x^2 + ");
    private Label sumLabelB = new Label();
    private Label sumLabelB2 = new Label("x +  ");
    private Label sumLabelC = new Label();

    private HBox textBox = new HBox(sumLabelA, sumLabel2A,sumLabelB,sumLabelB2,sumLabelC,sumLabel);

    public Program() { }

    @Override
    public void start(Stage primaryStage) throws Exception {
        tfa.setTooltip(new Tooltip("Enter a"));
        tfb.setTooltip(new Tooltip("Enter b"));
        tfc.setTooltip(new Tooltip("Enter c"));

        GridPane gridPane = new GridPane();

        sumLabelA.setTextFill(DARKBLUE);
        sumLabel2A.setTextFill(DARKBLUE);
        sumLabelB.setTextFill(DARKBLUE);
        sumLabel2A.setTextFill(DARKBLUE);
        sumLabelC.setTextFill(DARKBLUE);

        textBox.setAlignment(Pos.CENTER);
//        gridPane.add(sumLabelA, 0, 0,1,1);
//        gridPane.add(sumLabel2A, 0, 0,1,1);
//        gridPane.add(sumLabelB, 0, 0,1,1);
//        gridPane.add(sumLabelB2, 0, 0,1,1);
//        gridPane.add(sumLabelC, 0, 0,1,1);
        gridPane.add(textBox, 0, 1,4,1);


        gridPane.add(tfa, 1, 2);
        gridPane.add(tfb, 2, 2);
        gridPane.add(tfc, 3, 2);
        btsolve.setMaxWidth(Double.MAX_VALUE);
        btnClear.setMaxWidth(Double.MAX_VALUE);

        gridPane.add(btsolve, 0, 3, 4, 1);
        gridPane.add(btnClear, 0, 4, 4, 1);

//        sumLabel.textProperty().bind(tfa.textProperty());
//        VBox vboxPane = new VBox(10,textBox ,gridPane);

        //Teraz bindy
        sumLabelA.textProperty().bind(tfa.textProperty());
        sumLabelB.textProperty().bind(tfb.textProperty());
        sumLabelC.textProperty().bind(tfc.textProperty());

        btsolve.setOnAction(e -> calculateEquation());
        btnClear.setOnAction(e-> handleClear());



        Scene scene = new Scene(gridPane);
        primaryStage.setTitle("Quadratic equation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void calculateEquation() {
       try {
        double a = Double.parseDouble(tfa.getText());
        double b = Double.parseDouble(tfb.getText());
        double c = Double.parseDouble(tfc.getText());

//        DoubleProperty a1 = new SimpleDoubleProperty();
//        DoubleProperty b1 = new SimpleDoubleProperty();
//        DoubleProperty c1 = new SimpleDoubleProperty();

            if (a == 0 && b == 0 && c == 0) {
                sumLabel.setText(" = 0"+ "   ");
                textBox.setBackground(new Background(new BackgroundFill(WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                textBox.setAlignment(Pos.CENTER);
            } else if (a == 0 && b == 0 && c != 0 ||a == 0 && b != 0 && c == 0 ||a != 0 && b == 0 && c == 0 ) {
                sumLabel.setText("  =  Not a quadratic equation!");
                textBox.setBackground(new Background(new BackgroundFill(RED, CornerRadii.EMPTY, Insets.EMPTY)));
                textBox.setAlignment(Pos.CENTER);
//                textBox.setAccessibleText("a=0,b=0,c=0");

            } else if (a == 0 && b != 0) {
                textBox.setBackground(new Background(new BackgroundFill(WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                textBox.setAlignment(Pos.CENTER);
                double x = -c / b;
                sumLabel.setText(String.format(""+ x + "   "));
                textBox.setAccessibleText(""+ x);
                textBox.setAlignment(Pos.CENTER);
            } else if (a != 0) {
                textBox.setBackground(new Background(new BackgroundFill(WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                textBox.setAlignment(Pos.CENTER);
                double discriminant = b * b - 4 * a * c;
                if (discriminant < 0) {
                    textBox.setAlignment(Pos.CENTER);
                    sumLabel.setText(" = No x ");
                } else if (discriminant == 0) {
                    textBox.setAlignment(Pos.CENTER);
                    double x = -b / (2 * a);
                    sumLabel.setText(String.format(" = " + x+ "   "));
                } else if (discriminant > 0) {
                    textBox.setAlignment(Pos.CENTER);
                    double x = (-b + Math.sqrt(discriminant)) / (2 * a);
                    double x2 = (-b - Math.sqrt(b * b - 4 * (a * c))) / (2 * a);
                    sumLabel.setText(String.format(" =  x1= " + x + ", x2= "+x2));
                }
            }
         } catch (NumberFormatException nfe) {
           textBox.setAlignment(Pos.CENTER);
//             Not a number.
            sumLabel.setText(" = Number Format error!");
            textBox.setBackground(new Background(new BackgroundFill(RED, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }



    public void handleClear() {
        tfa.setText("0");
        tfb.setText("0");
        tfc.setText("0");
    }
}