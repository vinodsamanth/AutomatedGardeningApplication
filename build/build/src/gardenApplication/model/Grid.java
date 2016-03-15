package gardenApplication.model;

import java.util.Date;

import javafx.application.Platform;
import javafx.beans.binding.NumberExpressionBase;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class Grid {
	private Sprinkler sprinkler;
	private Heater heater;
	private Plant plant;
	private Fertilizer fertilizer;
	public static BooleanProperty isRaining;
	public static BooleanProperty isSnowing;
	
	public Grid(int x, int y, int growPlan){
		sprinkler = new Sprinkler(x,y);
		heater = new Heater(x,y);
		plant = new Plant(x,y,growPlan);
		fertilizer = new Fertilizer(x,y);
		this.isRaining = new SimpleBooleanProperty(false);
		this.isSnowing = new SimpleBooleanProperty(false);
	}
	

	public void setSprinklerInterval(int duration) {
		// TODO Auto-generated method stub
		sprinkler.setInterval(duration);
	}
	public StringProperty getSprinklerStateProperty(){
		return sprinkler.getSprinklerStateProperty();
	}
	public void setSprinklerState(String newState){
		sprinkler.setSprinklerState(newState);
	}
	public String getSprinklerState(){
		return sprinkler.getSprinklerState();
	}
	public int getSprinklerInterval() {
		// TODO Auto-generated method stub
		return sprinkler.getSprinklerInterval();
	}
	public IntegerProperty getSprinklerIntervalProperty() {
		// TODO Auto-generated method stub
		return sprinkler.getSprinklerIntervalProperty();
	}
	public int getSprinklerLastStop(){
		return sprinkler.getSprinklerLastUsed();
	}
	public void startSprinkler() {
		// TODO Auto-generated method stub
		sprinkler.startSprinkler();
	}
	public void stopSprinkler() {
		// TODO Auto-generated method stub
		sprinkler.stopSprinkler();
	}
	public boolean getSprinklerStatus(){
		return sprinkler.getSprinklerStatus();
	}
	
	
	
	public void setFertilizerInterval(int duration) {
		// TODO Auto-generated method stub
		fertilizer.setFertilizerInterval(duration);
	}
	public int getFertilizerInterval() {
		// TODO Auto-generated method stub
		return fertilizer.getFertilizerInterval();
	}
	public void addFertilizer() {
		// TODO Auto-generated method stub
		fertilizer.addFertilizer();
	}
	
	
	
	
	public void setTempratureRange(int low, int high) {
		// TODO Auto-generated method stub
		heater.setTempratureRange(low,high);
		
	}
	public int getTempratureLow() {
		// TODO Auto-generated method stub
		return heater.getTempratureLow();
	}
	public int getTempratureHigh() {
		// TODO Auto-generated method stub
		return heater.getTempratureHigh();
	}
	public void increaseTemprature(){
		heater.increaseTemprature();
	}
	public void reduceTemprature(){
		heater.reduceTemprature();
	}
	public int getCurrentTemprature(){
		return heater.getCurrentTemprature();
	}
	
	
	public void growPlant() {
		// TODO Auto-generated method stub
		plant.growPlant();
	}
	public int getPlantAge(){
		return plant.getPlantAge();
	}
	public int getPlantHeight(){
		return plant.getPlantHeight();
	}

	public int getSprinklerDuration() {
		// TODO Auto-generated method stub
		return sprinkler.getSprinklerDuration();
	}

	public int getFertilizerLastFertilized() {
		// TODO Auto-generated method stub
		return fertilizer.getLastFertilized();
	}

	public void increaseTemprature(int i) {
		// TODO Auto-generated method stub
		heater.increaseTemprature(i);
	}


	public IntegerProperty getSprinklerDurationProperty() {
		// TODO Auto-generated method stub
		return sprinkler.getSprinklerDurationProperty();
	}


	public IntegerProperty getSprinklerLastUsedProperty() {
		// TODO Auto-generated method stub
		return sprinkler.getSprinklerLastUsedProperty();
	}


	public StringProperty getHeaterStatusProperty() {
		// TODO Auto-generated method stub
		return heater.getHeaterStatusProperty();
	}


	public IntegerProperty getCurrentTempratureProperty() {
		// TODO Auto-generated method stub
		return heater.getCurrentTempratureProperty();
	}


	public IntegerProperty getTempratureRangeLowProperty() {
		// TODO Auto-generated method stub
		return heater.getTempratureLowProperty();
	}
	
	public IntegerProperty getTempratureRangeHighProperty() {
		// TODO Auto-generated method stub
		return heater.getTempratureHighProperty();
	}


	public StringProperty getFertilizerStatusProperty() {
		// TODO Auto-generated method stub
		return fertilizer.getFertilizerStatusProperty();
	}


	public IntegerProperty getFertilizerIntervalProperty() {
		// TODO Auto-generated method stub
		return fertilizer.getFertilizerIntervalProperty();
	}


	public IntegerProperty getLastFertilizedProperty() {
		// TODO Auto-generated method stub
		return fertilizer.getLastFertilizedProperty();
	}


	public StringProperty getPlantTypeProperty() {
		// TODO Auto-generated method stub
		return plant.getPlantTypeProperty();
	}


	public IntegerProperty getPlantAgeProperty() {
		// TODO Auto-generated method stub
		return plant.getPlantAgeProperty();
	}


	public IntegerProperty getPlantHeightProperty() {
		// TODO Auto-generated method stub
		return plant.getPlantHeightProperty();
	}


	public void setSprinklerDuration(int newDuration) {
		// TODO Auto-generated method stub
		sprinkler.setDuration(newDuration);
	}


	public void setHeaterLow(int newLow) {
		// TODO Auto-generated method stub
		heater.setTempratureLow(newLow);
	}


	public void setHeaterHigh(int newHigh) {
		// TODO Auto-generated method stub
		heater.setTempratureHigh(newHigh);
	}


	public void setFertilizerStatus(String newState) {
		// TODO Auto-generated method stub
		fertilizer.setFertilizerStatus(newState);
	}


	public void setHeaterStatus(String newState) {
		// TODO Auto-generated method stub
		heater.setHeaterStatus(newState);
	}


	public BooleanProperty getSprinklerStatusProperty() {
		// TODO Auto-generated method stub
		return sprinkler.getSprinklerStatusProperty();
	}


	public void stopAutoGrid() {
		// TODO Auto-generated method stub
		this.sprinkler.stopAutoSprinker();
		this.heater.stopAutoHeater();
		this.fertilizer.stopAutoHeater();
		this.plant.stopAutoGrow();
	}


	public void setIsSnowing(boolean value) {
		// TODO Auto-generated method stub
		setIsSnowingProperty(value);
		if(!value){
			sprinkler.setSprinklerLastUsed(Garden.currentTime);
			sprinkler.setSprinklerState("Sprinkler is OFF");
		}
		else{
			sprinkler.setSprinklerState("Sprinkler Disabled");
		}
	}

	public BooleanProperty getIsSnowingProperty(){
		return this.isSnowing;
	}
	
	private void setIsSnowingProperty(final boolean value) {
		// TODO Auto-generated method stub
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				isSnowing.set(value);
			}
		});
	}
	
	private boolean getIsSnowing(){
		return this.isSnowing.get();
	}


	public void setIsRaining(boolean value) {
		// TODO Auto-generated method stub
		setIsRainingProperty(value);
		if(!value){
			sprinkler.setSprinklerLastUsed(Garden.currentTime);
			sprinkler.setSprinklerState("Sprinkler is OFF");
		}
		else{
			sprinkler.setSprinklerState("Sprinkler Disabled");
		}
	}
	
	public BooleanProperty getIsRainingProperty(){
		return this.isRaining;
	}
	
	private void setIsRainingProperty(final boolean value) {
		// TODO Auto-generated method stub
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				isRaining.set(value);
			}
		});
	}
	
	private boolean getIsRaining(){
		return this.isRaining.get();
	}
}
