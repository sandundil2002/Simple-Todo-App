package lk.ijse.todo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.todo.dto.tm.DueTm;
import lk.ijse.todo.model.TaskModel;
import java.sql.SQLException;
import java.util.List;

public class DueTaskFormController {

    @FXML
    private TableColumn<?, ?> colComplete;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<DueTm> tblDue;

    public void initialize(){
        setCellValueFactory();
        loadDueTasks();
    }

    private void setCellValueFactory() {
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        colComplete.setCellValueFactory(new PropertyValueFactory<>("btnComplete"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
    }

    private void loadDueTasks() {
        ObservableList<DueTm> obList = FXCollections.observableArrayList();

        try {
            List<DueTm> list= TaskModel.getDueList();
            for (DueTm tm:list){
                obList.add(tm);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        for (int i = 0; i < obList.size(); i++) {
            obList.get(i).getBtnComplete().setOnAction(event -> {

                try {
                    DueTm selectedItem = tblDue.getSelectionModel().getSelectedItem();
                    try {
                        boolean Updated=TaskModel.Update(selectedItem.getId());
                        if (Updated){
                            loadDueTasks();
                            new Alert(Alert.AlertType.CONFIRMATION,"Update Succesfull").show();
                        }else {
                            new Alert(Alert.AlertType.WARNING,"Something Went Wrong").show();
                        }
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                }catch (NullPointerException e){
                    new Alert(Alert.AlertType.WARNING,"Select the column").show();
                }



            });

            obList.get(i).getBtnDelete().setOnAction(event -> {
                try {
                    DueTm selectedItem = tblDue.getSelectionModel().getSelectedItem();
                    try {
                        boolean deleted=TaskModel.delete(selectedItem.getId());
                        if (deleted){
                            loadDueTasks();
                            new Alert(Alert.AlertType.CONFIRMATION,"Delete Succesfull").show();
                        }else {
                            new Alert(Alert.AlertType.WARNING,"Something Went Wrong").show();
                        }
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                }catch (NullPointerException e){
                    new Alert(Alert.AlertType.WARNING,"Select the column").show();
                }
            });
        }
        tblDue.setItems(obList);
    }
}
