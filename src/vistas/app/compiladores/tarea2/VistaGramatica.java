package app.compiladores.tarea2;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class VistaGramatica  extends Parent implements Initializable {
    @FXML
    TableView input,output;

    ObservableList<modela> m1=FXCollections.observableArrayList(modela->new Observable[]{
            modela.keyProperty(),
            modela.valueProperty()
    });
    ObservableList<modelb> m2=FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        input();
    }
    public void input(){
        TableColumn key = new TableColumn();
        key.setText("First");
        key.setCellValueFactory(new PropertyValueFactory("key"));
        key.setCellFactory(TextFieldTableCell.forTableColumn(sc));
        input.getColumns().add(key);

        TableColumn value = new TableColumn();
        value.setText("First");
        value.setCellValueFactory(new PropertyValueFactory("value"));
        value.setCellFactory(TextFieldTableCell.forTableColumn(sc));
        input.getColumns().add(value);

        input.setItems(m1);
        input.setOnMouseClicked(e->{
            if(e.getClickCount()==2) {
                if(input.getSelectionModel().getSelectedItem()==null){
                    m1.add(new modela("newkey","new Value"));
                    input.getSelectionModel().selectLast();
                }
            }
        });
        m1.addListener(new ListChangeListener<modela>() {
            @Override
            public void onChanged(Change<? extends modela> c) {
             /*   m2.clear();
                gramatica a=new gramatica();
                m1.forEach(e->{
                    a.add(e.getKey(),e.getValue());
                });
                m1.forEach(e->{

                });*/
            }
        });

    }
    public void output(){
        TableColumn key = new TableColumn();
        key.setText("Key");
        key.setCellValueFactory(new PropertyValueFactory("key"));
        key.setCellFactory(TextFieldTableCell.forTableColumn(sc));
        output.getColumns().add(key);

        TableColumn first = new TableColumn();
        first.setText("First");
        first.setCellValueFactory(new PropertyValueFactory("first"));
        first.setCellFactory(TextFieldTableCell.forTableColumn(sc));
        output.getColumns().add(first);


        TableColumn follow = new TableColumn();
        follow.setText("Follow");
        follow.setCellValueFactory(new PropertyValueFactory("follow"));
        follow.setCellFactory(TextFieldTableCell.forTableColumn(sc));
        output.getColumns().add(follow);

        output.setItems(m2);
    }
    public void code(Event e){
        System.out.println(Arrays.toString(m1.toArray()));

        System.out.println();
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

