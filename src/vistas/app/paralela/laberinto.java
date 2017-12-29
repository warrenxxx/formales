package app.paralela;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class laberinto extends Application {

    private Random random = new Random(42);
    private static  int N_COLS = 20;
    private static  int N_ROWS = 20;
    private static int MAX_DATA_VALUE = 100;
    private TextField x=new TextField("20");
    private TextField y=new TextField("20");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        ObservableList<double[]> data = generateData();

        TableView<double[]> table = new TableView<>(data);
        table.getColumns().setAll(createColumns());
        table.setPrefSize(500, 500);
        table.setOnMouseClicked(e->{
            TablePosition pos = table.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            int col=pos.getColumn();
            data.get(row)[col]=(data.get(row)[col]+1)%4;
            System.out.println(data.get(row)[col]);
            table.refresh();
        });
        Button b=new Button("go");
        VBox v=new VBox(b,table);
        stage.setScene(new Scene(v));
        stage.show();
        b.setOnAction(e->{
            int xx=Integer.parseInt(x.getText());
            int yy=Integer.parseInt(y.getText());
            int h[][]=new int[xx][yy];
            for(int i=0;i<xx;i++){
                for(int j=0;j<yy;j++){
                    h[i][j]= (int) data.get(i)[j];
                }
            }
        });
    }


    private ObservableList<double[]> generateData() {
        return FXCollections.observableArrayList(
                IntStream.range(0, N_ROWS)
                        .mapToObj(r ->
                                IntStream.range(0, N_COLS)
                                        .mapToDouble(c -> randomValue())
                                        .toArray()
                        ).collect(Collectors.toList())
        );
    }

    private List<TableColumn<double[], Double>> createColumns() {
        return IntStream.range(0, N_COLS)
                .mapToObj(this::createColumn)
                .collect(Collectors.toList());
    }

    private TableColumn<double[], Double> createColumn(int c) {
        TableColumn<double[], Double> col = new TableColumn<>("C" + (c + 1));
        col.setCellFactory(column -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        // Format date.
                        double h=Double.parseDouble( item.toString());
                        if(h==0){
                            setStyle("-fx-background-color: white");
                        }
                        if(h==1){
                            setStyle("-fx-background-color: black");
                        }
                        if(h==2){
                            setStyle("-fx-background-color: blue");
                        }
                        if(h==3){
                            setStyle("-fx-background-color: orange");
                        }
                        if(h==4){
                            setStyle("-fx-background-color: purple");
                        }
                    }
                }
            };
        });
        col.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()[c]));
        return col;
    }

    private double randomValue() {
        return Math.round(random.nextDouble() * 2) ;
    }
}