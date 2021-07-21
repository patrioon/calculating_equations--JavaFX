import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.*;
public class Program extends Application {

    private Text actionStatus;
    private Stage savedStage;
    private Button open = new Button("Open");
    private GridPane gridPane = new GridPane();

    public static void main(String [] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        open.setOnAction(new SingleFcButtonListener());
        actionStatus = new Text();

        open.setMaxWidth(Double.MAX_VALUE);

        //Dodajemy do Grida
        gridPane.add(open, 0, 0,4,1);
        gridPane.add(actionStatus, 0, 4,4,1);

        Scene scene = new Scene(gridPane, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
        savedStage = primaryStage;
    }

    private class SingleFcButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            try {
                showSingleFileChooser();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void showSingleFileChooser() throws IOException {
        try {
            String path= "C:\\Users\\DELL\\Desktop\\szkola\\JavaFX_02\\src\\text.txt";
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                actionStatus.setText("File selected: " + selectedFile.getName());
            }

            FileReader fr = new FileReader(path);  //Chcesz wybierać ? -> selectedFile.getName()
            BufferedReader bfr = new BufferedReader(fr);
            String linia = "";
            while ((linia = bfr.readLine()) != null) {
                int wymiary[] = new int[4];
                int counter = 0;
                int counterInLinia = 0;
                String regex[] = linia.split(" ");
                boolean flagIsADigit = true;
                for (String s : regex) {
                    int t = s.length();
                    for (int i = 0; i < t; i++) {
                        if (!Character.isDigit(s.charAt(i))) {
                            flagIsADigit = false;
                        }
                    }
                    if (flagIsADigit) {
                        counterInLinia++;
                        if (counter <= 3) {
                            wymiary[counter] = Integer.parseInt(s);
                            counter++;
                        }
                    }
                }
                if (counterInLinia == 3)
                    getCircle(wymiary[0], wymiary[1], wymiary[2]);

                if (counterInLinia == 4)
                    getRectangle(wymiary[0], wymiary[1], wymiary[2], wymiary[3]);
            }
        }catch (FileNotFoundException e){
            System.err.println("Sory Batory plik nie ten");


        }

    }

    public void getRectangle(int x, int y, int z, int t) {
        Rectangle rect = new Rectangle(x, y, z,t);
        Canvas canvas = new Canvas(x, y);
        canvas.getGraphicsContext2D();
        rect.setStroke(Color.WHITE);
        rect.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        gridPane.add(rect, 2, 3, 1, 1);
        System.out.println("Elo Kwadrat");
    }

    public void getCircle(int x, int y, int r){

        Circle circle = new Circle(x, y, r);
        Canvas canvas = new Canvas(x, y);
        canvas.getGraphicsContext2D();
        circle.setStroke(Color.RED);
        circle.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        gridPane.add(circle, 2, 3, 4, 1);
        System.out.println("Elo Koło");

    }



}