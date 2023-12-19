package lk.ijse.todo.dto.tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DueTm {
    private int Id;
    private String description;
    private String dueDate;
    private JFXButton btnComplete;
    private JFXButton btnDelete;

    {
        btnComplete = new JFXButton("Complete");
        btnDelete = new JFXButton("Delete");

        // Set button styles
        btnDelete.setCursor(javafx.scene.Cursor.HAND);
        btnDelete.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff");

        btnComplete.setCursor(javafx.scene.Cursor.HAND);
        btnComplete.setStyle("-fx-background-color: #00ff00; -fx-text-fill: #ffffff");

        btnComplete.setPrefWidth(100);
        btnDelete.setPrefWidth(100);
        btnDelete.setPrefHeight(30);
        btnComplete.setPrefHeight(30);
    }

    public DueTm(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }
}
