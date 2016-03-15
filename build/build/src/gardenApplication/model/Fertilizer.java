package gardenApplication.model;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Fertilizer {

	private int locationX;
	private int locationY;
	private IntegerProperty interval;
	private IntegerProperty lastFertilized;
	private ScheduledExecutorService exec;
	private StringProperty state;

	public Fertilizer(int x, int y) {
		// TODO Auto-generated constructor stub
		this.locationX = x;
		this.locationY = y;
		this.interval = new SimpleIntegerProperty(24*60);
		this.lastFertilized = new SimpleIntegerProperty(0);
		this.state = new SimpleStringProperty("Running");
		this.autoFertilizer();
	}

	private void autoFertilizer() {
		// TODO Auto-generated method stub
		this.exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				int fertilizerNextStart = getLastFertilized() + getFertilizerInterval();
				if (Garden.currentTime == fertilizerNextStart)
					addFertilizer();
			}
		}, 0, 1, TimeUnit.SECONDS);
	}


	public void addFertilizer() {
		// TODO Auto-generated method stub
		this.setLastFertilized(Garden.currentTime);
		System.out.println("Day: " +Garden.currDay+ ", Time: " +Garden.currHour+ "/"+Garden.currMin+" : Fertilizer Added at Location X " + this.locationX + " Y " + this.locationY);
		Garden.setConsoleAreaTextProperty("\nDay: " + Garden.currDay + ", Time: "
				+ Garden.currHour + "/" + Garden.currMin
				+ " : Fertilizer Added to for Location X " + this.locationX + " Y "
				+ this.locationY);
	}
	

	public StringProperty getFertilizerStatusProperty() {
		// TODO Auto-generated method stub
		return this.state;
	}
	public String getFertilizerStatus() {
		// TODO Auto-generated method stub
		return this.state.get();
	}
	public void setFertilizerStatus(final String newState) {
		// TODO Auto-generated method stub
		Garden.setConsoleAreaTextProperty("\nDay: " + Garden.currDay + ", Time: "
				+ Garden.currHour + "/" + Garden.currMin
				+ " : Fertilizer is " + newState + " for Location X " + this.locationX + " Y "
				+ this.locationY);
		System.out.println("\nDay: " + Garden.currDay + ", Time: "
				+ Garden.currHour + "/" + Garden.currMin
				+ " : Fertilizer is " + newState + " for Location X " + this.locationX + " Y "
				+ this.locationY);
		Platform.runLater(new Runnable() {
            public void run() {
            	state.set(newState);
            }
          });
	}

	public IntegerProperty getFertilizerIntervalProperty() {
		// TODO Auto-generated method stub
		return this.interval;
	}
	public int getFertilizerInterval() {
		// TODO Auto-generated method stub
		return this.interval.get();
	}
	public void setFertilizerInterval(final int newInterval) {
		// TODO Auto-generated method stub
		setLastFertilized(Garden.currentTime);
		Platform.runLater(new Runnable() {
            public void run() {
            	interval.set(newInterval);
            }
          });
		Garden.setConsoleAreaTextProperty("\nDay: " + Garden.currDay + ", Time: "
				+ Garden.currHour + "/" + Garden.currMin
				+ " : Fertilizer Interval changed for Location X " + this.locationX + " Y "
				+ this.locationY + " to " + newInterval);
		System.out.println("\nDay: " + Garden.currDay + ", Time: "
				+ Garden.currHour + "/" + Garden.currMin
				+ " : Fertilizer Interval changed for Location X " + this.locationX + " Y "
				+ this.locationY + " to " + newInterval);
	}

	public IntegerProperty getLastFertilizedProperty() {
		// TODO Auto-generated method stub
		return this.lastFertilized;
	}
	public int getLastFertilized() {
		// TODO Auto-generated method stub
		return this.lastFertilized.get();
	}
	public void setLastFertilized(final int newLastFertilized) {
		// TODO Auto-generated method stub
		Platform.runLater(new Runnable() {
            public void run() {
            	lastFertilized.set(newLastFertilized);
            }
          });
	}

	public void stopAutoHeater() {
		// TODO Auto-generated method stub
		this.exec.shutdownNow();
	}

}
