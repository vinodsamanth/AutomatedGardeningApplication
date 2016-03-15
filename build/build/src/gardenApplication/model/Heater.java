package gardenApplication.model;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Heater {

	private int locationX;
	private int locationY;
	private IntegerProperty currentTemprature;
	private IntegerProperty low;
	private IntegerProperty high;
	private StringProperty status;
	private ScheduledExecutorService exec;

	public Heater(int x, int y) {
		// TODO Auto-generated constructor stub
		this.locationX = x;
		this.locationY = y;
		this.currentTemprature = new SimpleIntegerProperty(40);
		this.low = new SimpleIntegerProperty(35);
		this.high = new SimpleIntegerProperty(45);
		this.status = new SimpleStringProperty("Running");
		this.startAutoHeater();
	}

	private void startAutoHeater() {
		// TODO Auto-generated method stub
		this.exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
			private boolean changed = false;

			@Override
			public void run() {
				if (getHeaterStatus().equals("Running")) {
					if (getCurrentTemprature() < getTempratureLow())
						increaseTemprature();
					else if (getCurrentTemprature() > getTempratureHigh())
						reduceTemprature();
					else {
						int mid = (getTempratureLow() + getTempratureHigh()) / 2;
						if (getCurrentTemprature() < mid)
							increaseTemprature();
						if (getCurrentTemprature() > mid)
							reduceTemprature();
					}
					if (Garden.currMin == 10) {
						changed = true;
						if (isBetween(0, 6))
							increaseTemprature(-2);
						else if (isBetween(7, 12))
							increaseTemprature(1);
						else if (isBetween(13, 18))
							increaseTemprature(2);
						else
							increaseTemprature(-1);
					}
				}

			}

		}, 0, 1, TimeUnit.SECONDS);

	}

	private boolean isBetween(int low, int high) {
		// TODO Auto-generated method stub
		return low <= Garden.currHour && Garden.currHour <= Garden.currHour;
	}

	
	public StringProperty getHeaterStatusProperty(){
		return this.status;
	}
	private String getHeaterStatus() {
		// TODO Auto-generated method stub
		return status.get();
	}
	public void setHeaterStatus(final String newStatus) {
		// TODO Auto-generated method stub
		Garden.setConsoleAreaTextProperty("\nDay: " + Garden.currDay + ", Time: "
				+ Garden.currHour + "/" + Garden.currMin
				+ " : Heater is " + newStatus + " for Location X " + this.locationX + " Y "
				+ this.locationY);
		System.out.println("\nDay: " + Garden.currDay + ", Time: "
				+ Garden.currHour + "/" + Garden.currMin
				+ " : Heater is " + newStatus + " for Location X " + this.locationX + " Y "
				+ this.locationY);
		Platform.runLater(new Runnable() {
            public void run() {
            	status.set(newStatus);
            }
          });
	}
	
	public void setTempratureRange(int newLow, int newHigh) {
		// TODO Auto-generated method stub
		setTempratureLow(newLow);
		setTempratureHigh(newHigh);
	}

	public int getCurrentTemprature() {
		return this.currentTemprature.get();
	}
	public IntegerProperty getCurrentTempratureProperty() {
		return this.currentTemprature;
	}
	public void setCurrentTemprature(final int newTemprature) {
		Platform.runLater(new Runnable() {
            public void run() {
            	currentTemprature.set(getCurrentTemprature() + newTemprature);
            }
          });
	}
	

	public IntegerProperty getTempratureLowProperty() {
		// TODO Auto-generated method stub
		return this.low;
	}
	public int getTempratureLow() {
		// TODO Auto-generated method stub
		return this.low.get();
	}
	public void setTempratureLow(final int newLow) {
		// TODO Auto-generated method stub
		Platform.runLater(new Runnable() {
            public void run() {
            	low.set(newLow);
            }
          });
		Garden.setConsoleAreaTextProperty("\nDay: " + Garden.currDay + ", Time: "
				+ Garden.currHour + "/" + Garden.currMin
				+ " : Temprature Changed: Low " + newLow + " for Location X " + this.locationX + " Y "
				+ this.locationY);
		System.out.println("\nDay: " + Garden.currDay + ", Time: "
				+ Garden.currHour + "/" + Garden.currMin
				+ " : Temprature Changed: Low " + newLow + " for Location X " + this.locationX + " Y "
				+ this.locationY);
	}
	
	
	public IntegerProperty getTempratureHighProperty() {
		// TODO Auto-generated method stub
		return this.high;
	}
	public int getTempratureHigh() {
		// TODO Auto-generated method stub
		return this.high.get();
	}
	public void setTempratureHigh(final int newHigh) {
		// TODO Auto-generated method stub
		Platform.runLater(new Runnable() {
            public void run() {
            	high.set(newHigh);
            }
          });
		Garden.setConsoleAreaTextProperty("\nDay: " + Garden.currDay + ", Time: "
				+ Garden.currHour + "/" + Garden.currMin
				+ " : Temprature Changed: High " + newHigh + " for Location X " + this.locationX + " Y "
				+ this.locationY);
		System.out.println("\nDay: " + Garden.currDay + ", Time: "
				+ Garden.currHour + "/" + Garden.currMin
				+ " : Temprature Changed: High " + newHigh + " for Location X " + this.locationX + " Y "
				+ this.locationY);
		
	}


	public void increaseTemprature() {
		// TODO Auto-generated method stub
		this.setCurrentTemprature(1);
	}

	public void reduceTemprature() {
		// TODO Auto-generated method stub
		this.setCurrentTemprature(-1);
	}

	public void increaseTemprature(int i) {
		// TODO Auto-generated method stub
		this.setCurrentTemprature(i);
	}

	public void stopAutoHeater() {
		// TODO Auto-generated method stub
		this.exec.shutdownNow();
	}

}
