package gardenApplication;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import gardenApplication.model.Garden;
import gardenApplication.view.GridSettingsViewController;
import gardenApplication.view.RootViewController;
import gardenApplication.view.StartViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;


public class Main extends Application {
	final Garden garden = new Garden();
	public static Stage mainStage;
	@Override
	public void start(Stage primaryStage) {
		try {
			System.setOut(outputFile("Garden.log"));
			mainStage = primaryStage;
			final StartViewController startViewController = new StartViewController(garden); 
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/gardenApplication/view/StartView.fxml"));
			loader.setControllerFactory(new Callback<Class<?>, Object>() {
	            public Object call(Class<?> aClass) {
	                return startViewController;
	            }
	        });
			AnchorPane rootLayout = (AnchorPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setTitle("Automated Gardening Application");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected PrintStream outputFile(String name) throws FileNotFoundException {
	       return new PrintStream(new BufferedOutputStream(new FileOutputStream("Garden.log")), true);
	}
	
	@Override
	public void stop(){
		System.out.println("Exit");
	    garden.stopGardenApplication();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
