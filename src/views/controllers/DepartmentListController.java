package views.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable {

	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department, String> tableColumnName;
	
	@FXML
	private Button buttonNew;
	
	private DepartmentService departmentService;

	private ObservableList<Department> obsListDepartment;
	
	public void setDepartmentService(DepartmentService service) {
		this.departmentService = service;
	}
	
	@FXML
	public void onButtonNewAction() {
		System.out.println("Button New!");
	}

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializateNodes();
	}


	private void initializateNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		//tableView acompanhar a altura da janela
		Stage stage = (Stage) Main.getScene().getWindow();
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if(departmentService == null) {
			throw new IllegalStateException("Service was null!!");
		}
		List<Department> listDepartment = departmentService.findAll();
		obsListDepartment = FXCollections.observableArrayList(listDepartment);
		tableViewDepartment.setItems(obsListDepartment);
	}
	
}
