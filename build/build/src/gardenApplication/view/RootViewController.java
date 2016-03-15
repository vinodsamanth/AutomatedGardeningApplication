package gardenApplication.view;

import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import gardenApplication.model.Garden;

public class RootViewController implements Initializable {
	public int locationX = -1;
	public int locationY = -1;
	
	private final Garden garden;

	public RootViewController(Garden garden) {
		// TODO Auto-generated constructor stub
		this.garden = garden;
	}
	

	@FXML
    public TextArea consoleArea;
	

	@FXML
	public Label currentStringTime;

	@FXML
	public Label sprinklerStatus;
	@FXML
	public Label sprinklerInterval;
	@FXML
	public Label sprinklerDuration;
	@FXML
	public Label sprinklerLastUsed;

	@FXML
	public Label heaterStatus;
	@FXML
	public Label currentTemprature;
	@FXML
	public Label tempratureRangeLow;
	@FXML
	public Label tempratureRangeHigh;

	@FXML
	public Label fertilizerStatus;
	@FXML
	public Label fertilizerInterval;
	@FXML
	public Label lastFertilized;

	@FXML
	public Label plantType;
	@FXML
	public Label plantAge;
	@FXML
	public Label plantHeight;

	@FXML
	public Button editButton;
	
	@FXML
	public ToggleButton sprinklerToggle;
	
	@FXML
	public Button addFertilizer;
	
	
	
	
	@FXML
	public Button grid00;
	@FXML
	public Button grid01;
	@FXML
	public Button grid02;
	@FXML
	public Button grid03;
	@FXML
	public Button grid04;
	@FXML
	public Button grid10;
	@FXML
	public Button grid11;
	@FXML
	public Button grid12;
	@FXML
	public Button grid13;
	@FXML
	public Button grid14;
	@FXML
	public Button grid20;
	@FXML
	public Button grid21;
	@FXML
	public Button grid22;
	@FXML
	public Button grid23;
	@FXML
	public Button grid24;
	@FXML
	public Button grid30;
	@FXML
	public Button grid31;
	@FXML
	public Button grid32;
	@FXML
	public Button grid33;
	@FXML
	public Button grid34;
	@FXML
	public Button grid40;
	@FXML
	public Button grid41;
	@FXML
	public Button grid42;
	@FXML
	public Button grid43;
	@FXML
	public Button grid44;
	
	@FXML
	public void editSettings() throws Exception {
		try {
			
			if(this.locationX == -1 && this.locationY == -1){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Select A grid to make changes");

				alert.showAndWait();
			}
			else{
				// System.out.println("Edit");
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
						"/gardenApplication/view/gridSettingsView.fxml"));
				fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
					public Object call(Class<?> aClass) {
						return new GridSettingsViewController(garden,locationX,locationY);
					}
				});

				AnchorPane root1 = (AnchorPane) fxmlLoader.load();
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setTitle("Grid Settings");
				stage.setScene(new Scene(root1));
				stage.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	Stage stage = new Stage();
	public void showRandomEventWindow(final int x, final int y){
		try{
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
		
				"/gardenApplication/view/RandomEventView.fxml"));
		fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
			public Object call(Class<?> aClass) {
				return new RandomEventViewController(garden,x, y);
			}
		});

		AnchorPane root1 = (AnchorPane) fxmlLoader.load();
		stage.setTitle("Grid Settings");
		stage.setScene(new Scene(root1));
		
		stage.show();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	private void bindStatus(int x, int y){
		showRandomEventWindow(x, y);
		sprinklerStatus.textProperty().bind(garden.getGridBlock(x,y).getSprinklerStateProperty());
		sprinklerInterval.textProperty().bind(garden.getGridBlock(x,y).getSprinklerIntervalProperty().asString());
		sprinklerDuration.textProperty().bind(garden.getGridBlock(x,y).getSprinklerDurationProperty().asString());
		sprinklerLastUsed.textProperty().bind(garden.getGridBlock(x,y).getSprinklerLastUsedProperty().asString());
		heaterStatus.textProperty().bind(garden.getGridBlock(x,y).getHeaterStatusProperty());
		currentTemprature.textProperty().bind(garden.getGridBlock(x,y).getCurrentTempratureProperty().asString());
		tempratureRangeLow.textProperty().bind(garden.getGridBlock(x,y).getTempratureRangeLowProperty().asString());
		tempratureRangeHigh.textProperty().bind(garden.getGridBlock(x,y).getTempratureRangeHighProperty().asString());
		fertilizerStatus.textProperty().bind(garden.getGridBlock(x,y).getFertilizerStatusProperty());
		fertilizerInterval.textProperty().bind(garden.getGridBlock(x,y).getFertilizerIntervalProperty().asString());
		lastFertilized.textProperty().bind(garden.getGridBlock(x,y).getLastFertilizedProperty().asString());
		plantType.textProperty().bind(garden.getGridBlock(x,y).getPlantTypeProperty());
		plantAge.textProperty().bind(garden.getGridBlock(x,y).getPlantAgeProperty().asString());
		plantHeight.textProperty().bind(garden.getGridBlock(x,y).getPlantHeightProperty().asString());
		sprinklerToggle.selectedProperty().bindBidirectional(garden.getGridBlock(x, y).getSprinklerStatusProperty());
	}
	
	private void unbindStatus(){
		stage.close();
		sprinklerStatus.textProperty().unbind();
		sprinklerInterval.textProperty().unbind();
		sprinklerDuration.textProperty().unbind();
		sprinklerLastUsed.textProperty().unbind();
		heaterStatus.textProperty().unbind();
		currentTemprature.textProperty().unbind();
		tempratureRangeLow.textProperty().unbind();
		tempratureRangeHigh.textProperty().unbind();
		fertilizerStatus.textProperty().unbind();
		fertilizerInterval.textProperty().unbind();
		lastFertilized.textProperty().unbind();
		plantType.textProperty().unbind();
		plantAge.textProperty().unbind();
		plantHeight.textProperty().unbind();
	}
	
	
	@FXML
	private void selectGrid(ActionEvent event){
		unbindStatus();

		if(event.getSource().equals(grid00)){
			this.locationX = 0;
			this.locationY = 0;
			bindStatus(0, 0);
		}
		else if(event.getSource().equals(grid01)){
			this.locationX = 0;
			this.locationY = 1;
			bindStatus(0, 1);
		}
		else if(event.getSource().equals(grid02)){
			this.locationX = 0;
			this.locationY = 2;
			bindStatus(0, 2);
		}
		else if(event.getSource().equals(grid03)){
			this.locationX = 0;
			this.locationY = 3;
			bindStatus(0, 3);
		}
		else if(event.getSource().equals(grid04)){
			this.locationX = 0;
			this.locationY = 4;
			bindStatus(0, 4);
		}
		else if(event.getSource().equals(grid10)){
			this.locationX = 1;
			this.locationY = 0;
			bindStatus(1, 0);
		}
		else if(event.getSource().equals(grid11)){
			this.locationX = 1;
			this.locationY = 1;
			bindStatus(1, 1);
		}
		else if(event.getSource().equals(grid12)){
			this.locationX = 1;
			this.locationY = 2;
			bindStatus(1, 2);
		}
		else if(event.getSource().equals(grid13)){
			this.locationX = 1;
			this.locationY = 3;
			bindStatus(1, 3);
		}
		else if(event.getSource().equals(grid14)){
			this.locationX = 1;
			this.locationY = 4;
			bindStatus(1, 4);
		}
		else if(event.getSource().equals(grid20)){
			this.locationX = 2;
			this.locationY = 0;
			bindStatus(2, 0);
		}
		else if(event.getSource().equals(grid21)){
			this.locationX = 2;
			this.locationY = 1;
			bindStatus(2, 1);
		}
		else if(event.getSource().equals(grid22)){
			this.locationX = 2;
			this.locationY = 2;
			bindStatus(2, 2);
		}
		else if(event.getSource().equals(grid23)){
			this.locationX = 2;
			this.locationY = 3;
			bindStatus(2, 3);
		}
		else if(event.getSource().equals(grid24)){
			this.locationX = 2;
			this.locationY = 4;
			bindStatus(2, 4);
		}
		else if(event.getSource().equals(grid30)){
			this.locationX = 3;
			this.locationY = 0;
			bindStatus(3, 0);
		}
		else if(event.getSource().equals(grid31)){
			this.locationX = 3;
			this.locationY = 1;
			bindStatus(3, 1);
		}
		else if(event.getSource().equals(grid32)){
			this.locationX = 3;
			this.locationY = 2;
			bindStatus(3, 2);
		}
		else if(event.getSource().equals(grid33)){
			this.locationX = 3;
			this.locationY = 3;
			bindStatus(3, 3);
		}
		else if(event.getSource().equals(grid34)){
			this.locationX = 3;
			this.locationY = 4;
			bindStatus(3, 4);
		}
		else if(event.getSource().equals(grid40)){
			this.locationX = 4;
			this.locationY = 0;
			bindStatus(4, 0);
		}
		else if(event.getSource().equals(grid41)){
			this.locationX = 4;
			this.locationY = 1;
			bindStatus(4, 1);
		}
		else if(event.getSource().equals(grid42)){
			this.locationX = 4;
			this.locationY = 2;
			bindStatus(4, 2);
		}
		else if(event.getSource().equals(grid43)){
			this.locationX = 4;
			this.locationY = 3;
			bindStatus(4, 3);
		}
		else if(event.getSource().equals(grid44)){
			this.locationX = 4;
			this.locationY = 4;
			bindStatus(4, 4);
		}
	}
	
	
	@FXML
	public void addFertilizer(){
		if(locationX == -1 && locationY == -1){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Select A grid to add fertilizer");
			alert.showAndWait();
		}
		else{
			garden.getGridBlock(locationX, locationY).addFertilizer();
		}
	}
	
	@FXML
	public void startSprinkler(){
		if(locationX == -1 && locationY == -1){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Select A grid to start sprinkler");
			sprinklerToggle.selectedProperty().set(false);
			alert.showAndWait();
		}
		else{
			if(sprinklerToggle.isSelected()){
				if(garden.getGridBlock(locationX, locationY).getSprinklerState().equals("Sprinkler Disabled")){
					sprinklerToggle.selectedProperty().set(false);
				}
				garden.getGridBlock(locationX, locationY).startSprinkler();
			}
			else
				garden.getGridBlock(locationX, locationY).stopSprinkler();
		}
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle resourseBundle) {
		// TODO Auto-generated method stub1
		currentStringTime.textProperty().bind(garden.getCurrentStringTime());
		consoleArea.textProperty().bind(garden.getConsoleAreaTextProperty());
	}

}
