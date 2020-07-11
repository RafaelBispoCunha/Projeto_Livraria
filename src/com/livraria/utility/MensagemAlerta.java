package com.livraria.utility;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class MensagemAlerta {

	public void alertaInformativo(String titulo, String header, String mensagem) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensagem);
		alert.showAndWait();
	}

	public boolean alertaConfirmacao(String titulo, String header, String mensagem) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(mensagem);
		Optional<ButtonType> result = alert.showAndWait();

		return result.get() == ButtonType.OK;

	}
}
