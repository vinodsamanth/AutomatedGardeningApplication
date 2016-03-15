package gardenApplication.view;

import gardenApplication.Main;
import gardenApplication.model.Garden;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class StartViewController {
	private final Garden garden;
	
	public StartViewController(Garden garden){
		this.garden = garden;
	}
	
	@FXML
	public Button startApplication;
	
	public void startApplication(){
		try{
			this.garden.startGardenApplication();
			final RootViewController rootViewController = new RootViewController(this.garden);
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/gardenApplication/view/rootView.fxml"));
			loader.setControllerFactory(new Callback<Class<?>, Object>() {
	            public Object call(Class<?> aClass) {
	                return rootViewController;
	            }
	        });
			AnchorPane rootLayout = (AnchorPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			Main.mainStage.setScene(scene);
		}catch(Exception e){
			
		}
	}

}
