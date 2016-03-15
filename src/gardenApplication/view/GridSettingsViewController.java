package gardenApplication.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.glass.ui.MenuItem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import gardenApplication.model.Garden;

public class GridSettingsViewController implements Initializable {
	private final Garden garden;
	private final int locationX;
	private final int locationY;
	

	public GridSettingsViewController(Garden garden, int locationX, int locationY) {
		// TODO Auto-generated constructor stub
		this.garden = garden;
		this.locationX = locationX;
		this.locationY = locationY;
	}

	ObservableList<String> sprinklerStatus = FXCollections.observableArrayList(
			"Enable", "Disable");
	ObservableList<String> fertilizerStatus = FXCollections
			.observableArrayList("Enable", "Disable");
	ObservableList<String> heaterStatus = FXCollections.observableArrayList(
			"Enable", "Disable");

	@FXML
	public Button saveSettings;
	@FXML
	public ChoiceBox editSprinklerStatus;
	@FXML
	public ChoiceBox editFertilizerStatus;
	@FXML
	public ChoiceBox editHeaterStatus;
	@FXML
	public Button closeSettings;
	@FXML
	public TextField editFertilizerInterval;
	@FXML
	public TextField editSprinklerInterval;
	@FXML
	public TextField editSprinklerDuration;
	@FXML
	public TextField editHeaterLow;
	@FXML
	public TextField editHeaterHigh;

	@FXML
	public void saveSettings() throws Exception {
		try {
			if (editSprinklerStatus.getValue().equals("Enable")) {
				if (!garden.getGridBlock(locationX, locationY).getSprinklerState()
						.equals("Sprinkler Disabled"))
					garden.getGridBlock(locationX, locationY).setSprinklerState(
							garden.getGridBlock(locationX, locationY).getSprinklerState());
				else
					garden.getGridBlock(locationX, locationY).setSprinklerState(
							"Sprinkler is OFF");
			} else
				garden.getGridBlock(locationX, locationY).setSprinklerState(
						"Sprinkler Disabled");
			
			if (editFertilizerStatus.getValue().equals("Enable")) {
				if(!garden.getGridBlock(locationX, locationY).getFertilizerStatusProperty().get().equals("Running"))
					garden.getGridBlock(locationX, locationY).setFertilizerStatus("Running");
			}
			else
				garden.getGridBlock(locationX, locationY).setFertilizerStatus("Disabled");
			
			
			if (editHeaterStatus.getValue().equals("Enable")) {
				if(!garden.getGridBlock(locationX, locationY).getHeaterStatusProperty().get().equals("Running")){
					garden.getGridBlock(locationX, locationY).setHeaterStatus("Running");
				}
			}
			else
				garden.getGridBlock(locationX, locationY).setHeaterStatus("Disabled");
			
			
			if (!editSprinklerInterval.getText().isEmpty())
				garden.getGridBlock(locationX, locationY).setSprinklerInterval(
						Integer.parseInt(editSprinklerInterval.getText()));
			if (!editSprinklerDuration.getText().isEmpty())
				garden.getGridBlock(locationX, locationY).setSprinklerDuration(
						Integer.parseInt(editSprinklerDuration.getText()));
			if (!editHeaterLow.getText().isEmpty())
				garden.getGridBlock(locationX, locationY).setHeaterLow(
						Integer.parseInt(editHeaterLow.getText()));
			if (!editHeaterHigh.getText().isEmpty())
				garden.getGridBlock(locationX, locationY).setHeaterHigh(
						Integer.parseInt(editHeaterHigh.getText()));
			if (!editFertilizerInterval.getText().isEmpty())
				garden.getGridBlock(locationX, locationY).setFertilizerInterval(
						Integer.parseInt(editFertilizerInterval.getText()));
			closeWindow();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void closeWindow() {
		Stage stage = (Stage) closeSettings.getScene().getWindow();
		// do what you have to do
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		editSprinklerStatus.setItems(sprinklerStatus);
		editSprinklerStatus.setValue("Enable");
		editFertilizerStatus.setItems(fertilizerStatus);
		editFertilizerStatus.setValue("Enable");
		editHeaterStatus.setItems(heaterStatus);
		editHeaterStatus.setValue("Enable");
	}

}
