package tn.bfpme.controllers.RHC;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import tn.bfpme.models.Departement;
import tn.bfpme.services.ServiceDepartement;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class paneDepController implements Initializable {
    @FXML
    private ListView<Departement> departementListView;
    @FXML
    private TextField deptNameField, deptDescriptionField;
    @FXML
    private ComboBox<Departement> parentDeptComboBox;

    private final ServiceDepartement depService = new ServiceDepartement();
    private RHController RHC;
    private paneUserController PUC;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDepartments();
        departementListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                deptNameField.setText(newValue.getNom());
                deptDescriptionField.setText(newValue.getDescription());
                parentDeptComboBox.getSelectionModel().select(newValue.getParentDept() != 0 ? depService.getDepartmentById(newValue.getParentDept()) : null);
            }
        });
    }

    @FXML
    private void handleAddDepartment() {
        String name = deptNameField.getText();
        String description = deptDescriptionField.getText();
        Departement parent = parentDeptComboBox.getSelectionModel().getSelectedItem();

        if (parent.getNom().isEmpty()) {
            depService.addDepartement2(name, description);
        } else {
            depService.addDepartement(name, description, parent.getIdDepartement() != 0 ? parent.getIdDepartement() : 0);
        }
        loadDepartments();
    }

    @FXML
    private void handleEditDepartment() {
        Departement selectedDept = departementListView.getSelectionModel().getSelectedItem();
        if (selectedDept != null) {
            String name = deptNameField.getText();
            String description = deptDescriptionField.getText();
            Departement parent = parentDeptComboBox.getSelectionModel().getSelectedItem();
            depService.updateDepartment(selectedDept.getIdDepartement(), name, description, parent != null ? parent.getIdDepartement() : null);
            loadDepartments();
        }
    }

    @FXML
    private void handleDeleteDepartment() {
        Departement selectedDept = departementListView.getSelectionModel().getSelectedItem();
        if (selectedDept != null) {
            depService.deleteDepartment(selectedDept.getIdDepartement());
            loadDepartments();
        }
    }

    protected void loadDepartments() {
        try {
            List<Departement> departmentList = depService.getAllDepartments();
            Departement noParentDept = new Departement(0, "Pas de département", "", 0);
            departmentList.add(0, noParentDept);

            ObservableList<Departement> departments = FXCollections.observableArrayList(departmentList);
            departementListView.setItems(departments);
            departementListView.setCellFactory(param -> new ListCell<Departement>() {
                @Override
                protected void updateItem(Departement item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null || item.getNom() == null) {
                        setText(null);
                    } else {
                        setText(item.getNom());
                    }
                }
            });
            parentDeptComboBox.setItems(departments);
            parentDeptComboBox.setCellFactory(param -> new ListCell<Departement>() {
                @Override
                protected void updateItem(Departement item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null || item.getNom() == null) {
                        setText(null);
                    } else {
                        setText(item.getNom());
                    }
                }
            });
            parentDeptComboBox.setButtonCell(new ListCell<Departement>() {
                @Override
                protected void updateItem(Departement item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null || item.getNom() == null) {
                        setText(null);
                    } else {
                        setText(item.getNom());
                    }
                }
            });
            /*departmentComboBox.setItems(departments);
            departmentComboBox.setCellFactory(param -> new ListCell<Departement>() {
                @Override
                protected void updateItem(Departement item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null || item.getNom() == null) {
                        setText(null);
                    } else {
                        setText(item.getNom());
                    }
                }
            });
            departmentComboBox.setButtonCell(new ListCell<Departement>() {
                @Override
                protected void updateItem(Departement item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null || item.getNom() == null) {
                        setText(null);
                    } else {
                        setText(item.getNom());
                    }
                }
            });
            DepHComboBox.setItems(departments);
            DepHComboBox.setCellFactory(param -> new ListCell<Departement>() {
                @Override
                protected void updateItem(Departement item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null || item.getNom() == null) {
                        setText(null);
                    } else {
                        setText(item.getNom());
                    }
                }
            });
            DepHComboBox.setButtonCell(new ListCell<Departement>() {
                @Override
                protected void updateItem(Departement item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null || item.getNom() == null) {
                        setText(null);
                    } else {
                        setText(item.getNom());
                    }
                }
            });*/
        } catch (Exception e) {
            showError("Failed to load departments: " + e.getMessage());
        }
    }

    protected void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
