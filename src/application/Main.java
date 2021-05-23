package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	private static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainView.fxml")); 
			ScrollPane parent = loader.load();
			
			parent.setFitToWidth(true);
			parent.setFitToHeight(true);
			
			mainScene = new Scene(parent);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Sample JavaFX Application");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Scene getScene() {
		return mainScene;
	}
	
	public static void main(String[] args) {
		launch(args);
		
		System.out.println(System.getProperty("java.version"));
		System.out.println(System.getProperty("javafx.version"));
	}
}
