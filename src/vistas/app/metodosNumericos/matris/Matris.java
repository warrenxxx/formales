package app.metodosNumericos.matris;

import app.metodosNumericos.matrices;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Matris extends Parent implements Initializable{
    @FXML
    Label name;
    @FXML
    TableView table;
    int n,m;

//    ObservableList <modelMatris>data= FXCollections.observableArrayList();
    final ObservableList<modelMatris> data = FXCollections.observableArrayList();
    private Random random = new Random(42);
    private static final int N_COLS = 4;
    private static final int N_ROWS = 100;
    private static final int MAX_DATA_VALUE = 100;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<double[]> data = generateData();

        table.setItems(data);
        table.getColumns().setAll(createColumns());
        table.setPrefSize(200, 250);

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
        col.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()[c]));
        return col;
    }

    private double randomValue() {
        return Math.round(random.nextDouble() * MAX_DATA_VALUE  * 100) / 100.0;
    }

    public void setRows(int k){
        if(data.size()<k){
            while(data.size()!=k){
                addRow(null);
            }
        }else{
            while(data.size()!=k){
                quitRow(null);
            }
        }
    }
    public void setColumnss(int k){
        if(table.getColumns().size()<k){
            while(table.getColumns().size()!=k){
                addColumn(null);
            }
        }else{
            while(data.size()!=k){
                quitRow(null);
            }
        }
    }
    public void setmatris(matrices a){
        n=a.n;
        m=a.m;
        data.clear();
        for(int i=0;i<m;i++){
            modelMatris m=new modelMatris();
            for(int j=0;j<n;j++)
                m.setitem(j,a.matris[i][j]);
            data.add(m);
        }
    }
    public matrices getMatris(){
        double res[][]=new double[m][n];
        for(int i=0;i<this.m;i++){
            for(int j=0;j<this.n;j++){
                res[i][j]=data.get(i).getitem(j);
            }
        }
        return new matrices(this.m,this.n,res);
    }
    public void setName(String str){
        this.name.setText(str);
    }
    public void addColumn(Event e){
        TableColumn firstNameCol = new TableColumn();
        firstNameCol.setText("First");
        firstNameCol.setCellValueFactory(new PropertyValueFactory("num"+n));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn(sc));
        table.getColumns().add(firstNameCol);
        n++;
    }
    public void quitColumn(Event e){
        table.getColumns().remove(table.getColumns().size()-1);
        n--;
    }
    public void addRow(Event e){
        this.data.add(new modelMatris());
        m++;
    }
    public void quitRow(Event e){
        this.data.remove(this.data.size()-1);
        m--;
    }
    StringConverter<Object> sc = new StringConverter<Object>() {
        @Override
        public String toString(Object t) {

            return t == null ? null : t.toString();
        }

        @Override
        public Object fromString(String string) {
            return string;
        }
    };
}
