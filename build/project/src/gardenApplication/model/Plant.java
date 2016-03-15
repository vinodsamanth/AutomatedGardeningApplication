package gardenApplication.model;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Plant {

	private int locationX;
	private int locationY;
	private IntegerProperty age;
	private IntegerProperty height;
	private int growPlan;
	private ScheduledExecutorService exec;
	private StringProperty plantType;

	public Plant(int x, int y, int growPlan) {
		// TODO Auto-generated constructor stub
		this.locationX = x;
		this.locationY = y;
		this.age = new SimpleIntegerProperty(0);
		this.height = new SimpleIntegerProperty(0);
		this.growPlan = growPlan;
		this.plantType = new SimpleStringProperty((growPlan == 1)? "Flower" : "Fruit");
		this.autoGrow();
	}

	private void autoGrow() {
		// TODO Auto-generated method stub
		this.exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
			private boolean grown = false;

			@Override
			public void run() {
				if(Garden.currHour == 24 && !grown){
					growPlant();
					grown = true;
				}
				if(Garden.currHour != 24)
					grown = false;
				
			}
		}, 0, 1, TimeUnit.SECONDS);
	}

	public void growPlant() {
		// TODO Auto-generated method stub
		setPlantHeight(growPlan);
		setPlantAge(1);
	}
	
	

	public StringProperty getPlantTypeProperty() {
		// TODO Auto-generated method stub
		return this.plantType;
	}
	public String getPlantType() {
		// TODO Auto-generated method stub
		return this.plantType.get();
	}

	public IntegerProperty getPlantAgeProperty() {
		// TODO Auto-generated method stub
		return this.age;
	}
	public int getPlantAge() {
		// TODO Auto-generated method stub
		return this.age.get();
	}
	public void setPlantAge(final int newAge) {
		// TODO Auto-generated method stub
		Platform.runLater(new Runnable() {
            public void run() {
            	age.set(age.get() + newAge);
            }
          });
	}

	public IntegerProperty getPlantHeightProperty() {
		// TODO Auto-generated method stub
		return this.height;
	}
	
	public int getPlantHeight(){
		return this.height.get();
	}
	
	public void setPlantHeight(final int newHeight){
		System.out.println("Day: " + Garden.currDay + ", Time: "
				+ Garden.currHour + "/" + Garden.currMin
				+ " : Plant at location X " + this.locationX
				+ " Y " + this.locationY + " Grew by "+ this.growPlan + " mtr");
		Garden.setConsoleAreaTextProperty("Day: " + Garden.currDay + ", Time: "
				+ Garden.currHour + "/" + Garden.currMin
				+ " : Plant at location X " + this.locationX
				+ " Y " + this.locationY + " Grew by "+ this.growPlan + " mtr");
		Platform.runLater(new Runnable() {
            public void run() {
            	height.set(height.get() + newHeight);
            }
          });
	}

	public void stopAutoGrow() {
		// TODO Auto-generated method stub
		this.exec.shutdownNow();
	}

}
