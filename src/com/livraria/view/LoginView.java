package com.livraria.view;


import com.livraria.controller.LogarController;
import com.livraria.utility.MensagemAlerta;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class LoginView extends Application {

	private static Stage stage;
	private Pane painelPrincipal;
	private TextField txtLogin;
	private PasswordField txtSenha;
	private Button btnEntrar, btnSair;
	private Image imgUser;
	private Label lblUserImg;
	private ImageView imgView;
	
	private MensagemAlerta msgAlerta = new MensagemAlerta();
	@Override
	public void start(Stage stage) throws Exception {
		initComponents();
		initListeners();
		Scene scene = new Scene(painelPrincipal);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setTitle("Login");
		stage.show();

		initLayout();
		LoginView.setStage(stage);
	}

	public static void setStage(Stage stage) {
		LoginView.stage = stage;
	}

	public static Stage getStage() {
		return stage;
	}

	private void initComponents() {
		painelPrincipal = new Pane();
		painelPrincipal.setPrefSize(300, 450);
		painelPrincipal.setStyle("-fx-background-color:linear-gradient(" + "from 0% 0% to 100% 100%, #585858 40%, #000000 100%);");
		
		lblUserImg = new Label();
		imgUser= new Image(getClass().getResourceAsStream("/img/user.png"));
		lblUserImg.setGraphic(new ImageView(imgUser));
		
		txtLogin = new TextField();
		txtLogin.setPromptText("Digite o Login...");

		txtSenha = new PasswordField();
		txtSenha.setPromptText("Senha");

		btnEntrar = new Button("Entrar");
		btnSair = new Button("Sair");

		painelPrincipal.getChildren().addAll( txtLogin, txtSenha, btnEntrar, btnSair, lblUserImg);
	}

	private void initLayout() {

		lblUserImg.setLayoutX(100);
		lblUserImg.setLayoutY(20);
		lblUserImg.setPrefSize(80, 80);
	
		txtLogin.setLayoutX(80);
		txtLogin.setLayoutY(150);

		txtSenha.setLayoutX(80);
		txtSenha.setLayoutY(200);

		btnEntrar.setPrefSize(150, 20);
		btnEntrar.setLayoutX(80);
		btnEntrar.setLayoutY(250);

		btnSair.setPrefSize(150, 20);
		btnSair.setLayoutX(80);
		btnSair.setLayoutY(300);

		lblUserImg.setStyle("-fx-border-color: white;" + "-fx-border-width: 1;");
	}

	private void initListeners() {

		btnEntrar.setOnAction(new EventHandler<ActionEvent>() {
			LogarController log = new LogarController();
			@Override
			public void handle(ActionEvent event) {
				
				try {
					if(txtLogin.getText().trim().isEmpty() || txtLogin.getText() == null ) {
						msgAlerta.alertaInformativo("Campo Login", null, "Campo Login Vazio");
						
					}else if(txtSenha.getText().trim().isEmpty() || txtSenha.getText() == null) {
				
						msgAlerta.alertaInformativo("Campo Senha", null, "Campo Senha Vazio");
					}else {
						if(log.logar(txtLogin.getText(), txtSenha.getText())) {
							new TelaPrincipalView().start(new Stage());
							
							msgAlerta.alertaInformativo("Logar", null, "Logado Com Sucesso");
							LoginView.stage.close();
						}else {

							msgAlerta.alertaInformativo("Logar", null, "Usuario ou Senha Invalida");
						}
					}
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}

			}
		});

		btnSair.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				LoginView.stage.close();

			}
		});
	}
	
	

	public static void main(String[] args) {
		launch(args);
	}

}
