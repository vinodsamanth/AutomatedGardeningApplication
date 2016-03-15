package gardenApplication.model;

import gardenApplication.view.RootViewController;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import com.sun.glass.ui.Timer;

public class Garden {
	
	public static StringProperty consoleAreaText;

	private int width;
	private int height;
	private Grid[][] gardenGrid;
	private int dayCount;
	private static int timePerDay;
	private ScheduledExecutorService exec;
	public static int currentTime;
	public static int currDay;
	public static int currHour;
	public static int currMin;
	private StringProperty currentStringTime;
	

	public Garden() {
		this.width = 5;
		this.height = 5;
		this.dayCount = 0;
		this.currentTime = 0;
		this.currDay = 0;
		this.currHour = 0;
		this.currMin = 0;
		this.timePerDay = 24 * 60;
		
		
		currentStringTime = new SimpleStringProperty(":");
		consoleAreaText = new SimpleStringProperty("");

		

		this.gardenGrid = new Grid[this.width][this.height];
		for (int x = 0; x < this.width; x++)
			for (int y = 0; y < this.height; y++)
				gardenGrid[x][y] = new Grid(x, y, (int) (Math.random() * 2 + 1));
		
		//this.startGardenApplication();
	}

	/*public static void main(String[] argc) {
		Garden garden = new Garden();
		garden.startGardenApplication();
	}*/
	
	

	public void stopGardenApplication(){
		if(this.exec != null){
			this.exec.shutdown();
			for (int x = 0; x < this.width; x++)
				for (int y = 0; y < this.height; y++)
					gardenGrid[x][y].stopAutoGrid();
		}
	}
	
	
	public void startGardenApplication() {
		this.exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				// do stuff
				
				Garden.currentTime++;
				Garden.currMin++;
				if (Garden.currMin == 60) {
					Garden.currMin = 0;
					Garden.currHour++;
				}
				if (Garden.currHour == 24)
					Garden.currHour = 0;
				if (Garden.currentTime % Garden.timePerDay == 0)
					Garden.currDay++;
				
				Platform.runLater(new Runnable() {
		            public void run() {
		            	currentStringTime.set(Garden.currHour+":"+Garden.currMin);
		            }
		          });
				
				//System.out.println(currentStringTime.get());

			}
		}, 0, 1, TimeUnit.SECONDS);
	}


	public Grid getGridBlock(int x, int y){
		return gardenGrid[x][y];
	}
	
	
	public void setSprinklerDuration(int x, int y, int duration) {
		gardenGrid[x][y].setSprinklerInterval(duration);
	}

	public int getSprinklerDuration(int x, int y) {
		return gardenGrid[x][y].getSprinklerInterval();
	}

	public void startSprinkler(int x, int y) {
		gardenGrid[x][y].startSprinkler();
	}

	public void stopSprinkler(int x, int y) {
		gardenGrid[x][y].stopSprinkler();
	}

	
	public void setFertilizerDuration(int x, int y, int duration) {
		gardenGrid[x][y].setFertilizerInterval(duration);
	}

	public int getFertilizerDuration(int x, int y) {
		return gardenGrid[x][y].getFertilizerInterval();
	}

	public void addFertilizer(int x, int y) {
		gardenGrid[x][y].addFertilizer();
	}

	public void setTempratureRange(int x, int y, int low, int high) {
		gardenGrid[x][y].setTempratureRange(low, high);
	}

	public int getTempratureLow(int x, int y) {
		return gardenGrid[x][y].getTempratureLow();
	}

	public int getTempratureHigh(int x, int y) {
		return gardenGrid[x][y].getTempratureHigh();
	}

	public int getCurrentTemprature(int x, int y) {
		return gardenGrid[x][y].getCurrentTemprature();
	}

	public void growPlant(int x, int y) {
		gardenGrid[x][y].growPlant();
	}

	public int getPlantAge(int x, int y) {
		return gardenGrid[x][y].getPlantAge();
	}

	public int getPlantheight(int x, int y) {
		return gardenGrid[x][y].getPlantHeight();
	}

	public StringProperty getCurrentStringTime() {
		// TODO Auto-generated method stub
		return this.currentStringTime;
	}

	public StringProperty getConsoleAreaTextProperty() {
		// TODO Auto-generated method stub
		return consoleAreaText;
	}
	public static String getConsoleArea() {
		// TODO Auto-generated method stub
		return consoleAreaText.get();
	}
	public static void setConsoleAreaTextProperty(final String newString) {
		// TODO Auto-generated method stub
		Platform.runLater(new Runnable() {
            public void run() {
            	consoleAreaText.set(newString + getConsoleArea());
            }
          });
	}

}