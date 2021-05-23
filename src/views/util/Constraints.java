package views.util;

import javafx.scene.control.TextField;

public class Constraints {
	
	
	//TextField aceitar apenas numeros
	public static void setTextFieldInteger(TextField txt) {
		txt.textProperty().addListener((obs, oldValue, newValue)->{
			if(newValue!=null && !newValue.matches("\\d*"))
				txt.setText(oldValue);
		});
	}
	
	//Determinar comprimento máximo de um TextField
	public static void setTextFieldMaxLength(TextField txt, int maxLength) {
		txt.textProperty().addListener( (obs, oldValue, newValue)->{
			if(newValue!=null && newValue.length()>maxLength)
				txt.setText(oldValue);
		});
		
	}
	
	//Permitir ponto no TextField com entrada decimal
	public static void setTextFieldDouble(TextField txt) {
		txt.textProperty().addListener((obs, oldValue, newValue)->{
			if(newValue!=null && !newValue.matches("\\d*([\\.]\\d*)?")) {
				txt.setText(oldValue);
			}
		});
	}
}
