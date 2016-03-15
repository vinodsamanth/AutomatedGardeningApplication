package gardenApplication.view;

import gardenApplication.model.Garden;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;

public class RandomEventViewController implements Initializable{
	
	final Garden garden;
	final  int locationX;
	final  int locationY;
	
	public RandomEventViewController(Garden garden, int locationX, int locationY){
		this.garden = garden;
		this.locationX = locationX;
		this.locationY = locationY;
	}

	@FXML
	public ToggleButton startRain;
	
	@FXML
	public void rainButton(){
		if(this.locationX == -1 && this.locationY == -1){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Select A grid to make changes");

			alert.showAndWait();
		}
		else if(startRain.isSelected()){
			Garden.setConsoleAreaTextProperty("\nRain Started at location X " + this.locationX +" Y " + this.locationY);
			System.out.println("\nRain Started at location X " + this.locationX +" Y " + this.locationY);
			garden.getGridBlock(locationX, locationY).setIsRaining(true);
		}
		else{
			Garden.setConsoleAreaTextProperty("\nRain Stopped at location X " + this.locationX +" Y " + this.locationY);
			System.out.println("\nRain Stopped at location X " + this.locationX +" Y " + this.locationY);
			garden.getGridBlock(locationX, locationY).setIsRaining(false);
		}
	}
	
	@FXML 
	public ToggleButton startSnow;
	
	@FXML
	public void snowButton(){
		if(this.locationX == -1 && this.locationY == -1){
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Select A grid to make changes");

			alert.showAndWait();
		}
		else if(startSnow.isSelected()){
			Garden.setConsoleAreaTextProperty("\nSnow Started at location X " + this.locationX +" Y " + this.locationY);
			System.out.println("\nRain Started at location X " + this.locationX +" Y " + this.locationY);
			garden.getGridBlock(locationX, locationY).setIsSnowing(true);
		}
		else{
			Garden.setConsoleAreaTextProperty("\nSnow Stopped at location X " + this.locationX +" Y " + this.locationY);
			System.out.println("\nRain Stopped at location X " + this.locationX +" Y " + this.locationY);
			garden.getGridBlock(locationX, locationY).setIsSnowing(false);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		startRain.selectedProperty().bindBidirectional(garden.getGridBlock(locationX, locationX).getIsRainingProperty());
		startSnow.selectedProperty().bindBidirectional(garden.getGridBlock(locationX, locationX).getIsSnowingProperty());
	}

}
