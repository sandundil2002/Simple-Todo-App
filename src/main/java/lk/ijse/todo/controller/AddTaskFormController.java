package lk.ijse.todo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lk.ijse.todo.db.DBConnection;
import lk.ijse.todo.dto.TaskDTO;
import lk.ijse.todo.model.TaskModel;
import lk.ijse.todo.model.UserModel;

import java.sql.SQLException;

public class AddTaskFormController {
    @FXML
    private DatePicker txtDate;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtId;

    public void initialize() throws SQLException, ClassNotFoundException {
        txtId.setText(String.valueOf(TaskModel.generateId()));
    }

    @FXML
    void btnAddTaskOnAction(ActionEvent event) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTask_id(Integer.parseInt(txtId.getText()));
        taskDTO.setDescription(txtDescription.getText());
        taskDTO.setDueDate(txtDate.getValue());
        taskDTO.setEmail(DBConnection.email);
        taskDTO.setIsCompleted(0);

        try {
            boolean saved = TaskModel.saveTask(taskDTO);
            if (saved) {
                txtDescription.clear();
                txtId.clear();
                new Alert(Alert.AlertType.CONFIRMATION, "Task Saved").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something Went Wrong").show();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
