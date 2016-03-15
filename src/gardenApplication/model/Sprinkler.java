package gardenApplication.model;

import gardenApplication.view.RootViewController;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Sprinkler {

	private StringProperty state;
	private int locationX;
	private int LocationY;
	private IntegerProperty interval;
	private IntegerProperty duration;
	private IntegerProperty lastStop;
	private ScheduledExecutorService exec;
	private BooleanProperty status;

	public Sprinkler(int x, int y) {
		// TODO Auto-generated constructor stub
		this.locationX = x;
		this.LocationY = y;
		this.state = new SimpleStringProperty("Sprinkler is OFF");
		this.interval = new SimpleIntegerProperty(60);
		this.status = new SimpleBooleanProperty(false);
		this.duration = new SimpleIntegerProperty(15);
		this.lastStop = new SimpleIntegerProperty(0);

		this.startAutoSprinkler();
	}

	private void startAutoSprinkler() {
		// TODO Auto-generated method stub
		this.exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				int sprinklerNextStart = lastStop.get() + interval.get();

				if (Garden.currentTime == sprinklerNextStart
						&& getSprinklerState().equals("Sprinkler is OFF")) {
					startSprinkler();
				}
				int sprinklerNextStop = sprinklerNextStart + duration.get();
				if (Garden.currentTime == sprinklerNextStop
						&& getSprinklerState().equals("Sprinkler is ON"))
					stopSprinkler();
			}
		}, 0, 10, TimeUnit.MILLISECONDS);

	}

	public void setSprinklerState(final String newState) {
		if (state.get().equals("Sprinkler Disabled")) {
			setSprinklerLastUsed(Garden.currentTime);
			System.out.println("Day: " + Garden.currDay + ", Time: "
					+ Garden.currHour + "/" + Garden.currMin + " : " + newState
					+ " for " + " locationX " + this.locationX + " Y "
					+ this.LocationY + " changed to " + this.interval.intValue());
			Garden.setConsoleAreaTextProperty("\nDay: " + Garden.currDay
					+ ", Time: " + Garden.currHour + "/" + Garden.currMin
					+ " : " + newState + " for " + " location X "
					+ this.locationX + " Y " + this.LocationY);
		}
		if(newState.equals("Sprinkler Disabled")){
			Garden.setConsoleAreaTextProperty("\nDay: " + Garden.currDay
					+ ", Time: " + Garden.currHour + "/" + Garden.currMin
					+ " : " + newState + " for " + " location X "
					+ this.locationX + " Y " + this.LocationY);
			System.out.println("\nDay: " + Garden.currDay
					+ ", Time: " + Garden.currHour + "/" + Garden.currMin
					+ " : " + newState + " for " + " location X "
					+ this.locationX + " Y " + this.LocationY);
		}
		Platform.runLater(new Runnable() {
			public void run() {
				state.set(newState);
			}
		});
	}

	public String getSprinklerState() {
		return this.state.get();
	}

	public StringProperty getSprinklerStateProperty() {
		return this.state;
	}

	public void setInterval(final int newInterval) {
		// TODO Auto-generated method stub
		setSprinklerLastUsed(Garden.currentTime);
		Platform.runLater(new Runnable() {
			public void run() {
				interval.set(newInterval);
			}
		});
		System.out.println("Day: " + Garden.currDay + ", Time: "
				+ Garden.currHour + "/" + Garden.currMin
				+ " : Sprinkler Interval for location X " + this.locationX
				+ " Y " + this.LocationY + " changed to " + newInterval);
		Garden.setConsoleAreaTextProperty("\nDay: " + Garden.currDay + ", Time: "
				+ Garden.currHour + "/" + Garden.currMin
				+ " : Sprinkler Interval for location X " + this.locationX
				+ " Y " + this.LocationY + "changed to " + newInterval);
	}

	public void setDuration(final int newDuration) {
		// TODO Auto-generated method stub
		Platform.runLater(new Runnable() {
			public void run() {
				duration.set(newDuration);
			}
		});
		System.out.println("Day: " + Garden.currDay + ", Time: "
				+ Garden.currHour + "/" + Garden.currMin
				+ " : Sprinkler Duration for location X " + this.locationX
				+ " Y " + this.LocationY + "changed to " + this.duration);
		Garden.setConsoleAreaTextProperty("\nDay: " + Garden.currDay + ", Time: "
				+ Garden.currHour + "/" + Garden.currMin
				+ " : Sprinkler Duration for location X " + this.locationX
				+ " Y " + this.LocationY + "changed to " + newDuration);
	}

	public int getSprinklerInterval() {
		// TODO Auto-generated method stub
		return this.interval.get();
	}

	public IntegerProperty getSprinklerIntervalProperty() {
		// TODO Auto-generated method stub
		return this.interval;
	}

	public int getSprinklerDuration() {
		// TODO Auto-generated method stub
		return this.duration.get();
	}

	public IntegerProperty getSprinklerDurationProperty() {
		// TODO Auto-generated method stub
		return this.duration;
	}

	public void startSprinkler() {
		// TODO Auto-generated method stub
		if(getSprinklerState().equals("Sprinkler Disabled")){
			Garden.setConsoleAreaTextProperty("\nDay: " + Garden.currDay
					+ ", Time: " + Garden.currHour + "/" + Garden.currMin
					+ " : ERROR "+ getSprinklerState() +" at location X " + this.locationX
					+ " Y " + this.LocationY);
			System.out.println("\nDay: " + Garden.currDay
					+ ", Time: " + Garden.currHour + "/" + Garden.currMin
					+ " : ERROR "+ getSprinklerState() +" at location X " + this.locationX
					+ " Y " + this.LocationY);
		}
		else if (getSprinklerState().equals("Sprinkler is OFF")) {
			System.out.println("Day: " + Garden.currDay + ", Time: "
					+ Garden.currHour + "/" + Garden.currMin
					+ " : Sprinkler Started at location X " + this.locationX
					+ " Y " + this.LocationY);
			Garden.setConsoleAreaTextProperty("\nDay: " + Garden.currDay
					+ ", Time: " + Garden.currHour + "/" + Garden.currMin
					+ " : Sprinkler Started at location X " + this.locationX
					+ " Y " + this.LocationY);
			setSprinklerState("Sprinkler is ON");
			setSprinklerStatus(true);
		} else {
			System.out.println("Day: " + Garden.currDay + ", Time: "
					+ Garden.currHour + "/" + Garden.currMin
					+ " : ERROR Sprinkler is On at location X "
					+ this.locationX + " Y " + this.LocationY);
			Garden.setConsoleAreaTextProperty("\nDay: " + Garden.currDay
					+ ", Time: " + Garden.currHour + "/" + Garden.currMin
					+ " : ERROR Sprinkler is Off at location X "
					+ this.locationX + " Y " + this.LocationY);
		}
	}

	public void stopSprinkler() {
		// TODO Auto-generated method stub
		if(getSprinklerState().equals("Sprinkler Disabled")){
			Garden.setConsoleAreaTextProperty("\nDay: " + Garden.currDay
					+ ", Time: " + Garden.currHour + "/" + Garden.currMin
					+ " : ERROR "+ getSprinklerState() +" at location X " + this.locationX
					+ " Y " + this.LocationY);
			System.out.println("\nDay: " + Garden.currDay
					+ ", Time: " + Garden.currHour + "/" + Garden.currMin
					+ " : ERROR "+ getSprinklerState() +" at location X " + this.locationX
					+ " Y " + this.LocationY);
		}
		else if (getSprinklerState().equals("Sprinkler is ON")) {
			this.setSprinklerLastUsed(Garden.currentTime);
			System.out.println("Day: " + Garden.currDay + ", Time: "
					+ Garden.currHour + "/" + Garden.currMin
					+ " : Sprinkler Stopped at location X " + this.locationX
					+ " Y " + this.LocationY);
			Garden.setConsoleAreaTextProperty("\nDay: " + Garden.currDay
					+ ", Time: " + Garden.currHour + "/" + Garden.currMin
					+ " : Sprinkler Stopped at location X " + this.locationX
					+ " Y " + this.LocationY);
			setSprinklerState("Sprinkler is OFF");
			setSprinklerStatus(false);
		} else {
			System.out.println("Day: " + Garden.currDay + ", Time: "
					+ Garden.currHour + "/" + Garden.currMin
					+ " : ERROR Sprinkler is Off at location X "
					+ this.locationX + " Y " + this.LocationY);
			Garden.setConsoleAreaTextProperty("\nDay: " + Garden.currDay
					+ ", Time: " + Garden.currHour + "/" + Garden.currMin
					+ " : ERROR Sprinkler is Off at location X "
					+ this.locationX + " Y " + this.LocationY);
		}
	}

	public IntegerProperty getSprinklerLastUsedProperty() {
		// TODO Auto-generated method stub
		return this.lastStop;
	}

	public int getSprinklerLastUsed() {
		// TODO Auto-generated method stub
		return this.lastStop.get();
	}

	public void setSprinklerLastUsed(final int newLastUsed) {
		// TODO Auto-generated method stub

		Platform.runLater(new Runnable() {
			public void run() {
				lastStop.set(newLastUsed);
			}
		});
	}

	public BooleanProperty getSprinklerStatusProperty() {
		// TODO Auto-generated method stub
		return this.status;
	}

	public boolean getSprinklerStatus() {
		// TODO Auto-generated method stub
		return this.status.get();
	}

	public void setSprinklerStatus(final boolean newValue) {
		// TODO Auto-generated method stub
		Platform.runLater(new Runnable() {
			public void run() {
				status.set(newValue);
			}
		});
	}

	public void stopAutoSprinker() {
		// TODO Auto-generated method stub
		this.exec.shutdownNow();
	}

}
