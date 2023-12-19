package lk.ijse.todo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.todo.dto.tm.CompleteTm;
import lk.ijse.todo.model.TaskModel;

import java.sql.SQLException;
import java.util.List;

import static lk.ijse.todo.model.TaskModel.getCompiled;

public class CompletedTaskFormController {
    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<CompleteTm> tblComplete;

    public void initialize() {
        setCellValueFactory();
        loadCompletedTasks();
    }

    private void loadCompletedTasks() {
        ObservableList<CompleteTm> obList = FXCollections.observableArrayList();

        try {
            List<CompleteTm> list = TaskModel.getCompiledList();
            for (CompleteTm tm : list) {
                obList.add(tm);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        for (int i = 0; i < obList.size(); i++) {
            obList.get(i).getBtnDelete().setOnAction(event -> {

                try {
                    CompleteTm selectedItem = tblComplete.getSelectionModel().getSelectedItem();
                    try {
                        boolean deleted = TaskModel.delete(selectedItem.getId());
                        if (deleted) {
                            loadCompletedTasks();
                            new Alert(Alert.AlertType.CONFIRMATION, "Delete Succesfull").show();
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Something Went rong").show();
                        }
                    } catch (SQLException | ClassNotFoundException throwables) {
                        throwables.printStackTrace();
                    }
                } catch (NullPointerException e) {
                    new Alert(Alert.AlertType.WARNING, "Select the column").show();
                }


            });
        }

        tblComplete.setItems(obList);
    }

    private void setCellValueFactory() {
        colDescription.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("description"));
        colDueDate.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("dueDate"));
        colDelete.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("btnDelete"));
    }
}
