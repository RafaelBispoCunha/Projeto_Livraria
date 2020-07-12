package com.livraria.view;

import java.util.Optional;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TelaPrincipalView extends Application {
	private static Stage stage;
	private Pane painelPrincipal;
	
	private Label lblTituloPagina;
	private Label lblSairImg;
	private Label lblVenda;
	private Label lblVendaImg;
	private Label lblFuncionario;
	private Label lblFuncionarioImg;
	private Label lblLivro;
	private Label lblLivroImg;
	private Label lblEstoque;
	private Label lblEstoqueImg;
	private Label lblRelatorio;
	private Label lblRelatorioImg;
	private Image imgSair;
	private Image imgVenda;
	private Image imgFuncionario;
	private Image imgLivro;
	private Image imgEstoque;
	private Image imgRelatorio;

	private String lblBorda = "-fx-border-color: #848484;" + "-fx-border-width: 3;";
	private String fonte = "-fx-font-size: 12pt;" + "-fx-font-family: \"Segoe UI Semibold\";\r\n"
			+ "-fx-text-fill: black;\r\n" + "-fx-opacity: 0.8;";

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		initComponents();
		initListeners();

		stage.initStyle(StageStyle.UNDECORATED);// remover a barra de titulo
		Scene scene = new Scene(painelPrincipal, 1024, 600);
		stage.setScene(scene);
		stage.setTitle("Login");
		stage.show();
		initLayout();
		TelaPrincipalView.setStage(stage);

	}

	public static Stage getStage() {
		return stage;
	}

	private static void setStage(Stage stage) {
		TelaPrincipalView.stage = stage;

	}

	private void initLayout() {
		lblTituloPagina.setLayoutX(400);
		lblTituloPagina.setLayoutY(10);
		
		lblSairImg.setLayoutX(950);
		lblSairImg.setLayoutY(10);
		lblSairImg.setStyle(lblBorda);

		lblVendaImg.setLayoutX(200);
		lblVendaImg.setLayoutY(130);
		lblVendaImg.setStyle(lblBorda);
		lblVenda.setLayoutX(225);
		lblVenda.setLayoutY(250);
		lblVenda.setStyle(fonte);
		
		lblLivroImg.setLayoutX(450);
		lblLivroImg.setLayoutY(130);
		lblLivroImg.setStyle(lblBorda);
		lblLivro.setLayoutX(480);
		lblLivro.setLayoutY(250);
		lblLivro.setStyle(fonte);
		
		lblEstoqueImg.setLayoutX(700);
		lblEstoqueImg.setLayoutY(130);
		lblEstoqueImg.setStyle(lblBorda);
		lblEstoque.setLayoutX(720);
		lblEstoque.setLayoutY(250);
		lblEstoque.setStyle(fonte);
		
		lblFuncionarioImg.setLayoutX(450);
		lblFuncionarioImg.setLayoutY(350);
		lblFuncionarioImg.setStyle(lblBorda);
		lblFuncionario.setLayoutX(455);
		lblFuncionario.setLayoutY(470);
		lblFuncionario.setStyle(fonte);
		
		lblRelatorioImg.setLayoutX(200);
		lblRelatorioImg.setLayoutY(350);
		lblRelatorioImg.setStyle(lblBorda);
		lblRelatorio.setLayoutX(215);
		lblRelatorio.setLayoutY(470);
		lblRelatorio.setStyle(fonte);
		
		lblTituloPagina.setStyle("-fx-font-size: 25pt;" + 
				"-fx-font-family: \"Segoe UI Semibold\";"
			+ "-fx-text-fill: black; "+
			 "-fx-opacity: 0.8;");
		
		painelPrincipal.setStyle(lblBorda);
		
	}

	private void initComponents() {
		painelPrincipal = new Pane();

		lblSairImg = new Label();
		lblTituloPagina = new Label("Menu Principal");
		lblVenda = new Label("Venda");
		lblFuncionario = new Label("Funcionario");
		lblEstoque = new Label("Estoque");
		lblRelatorio = new Label("Relatorio");
		lblLivro = new Label("Livro");

		lblVendaImg = new Label();
		lblFuncionarioImg = new Label();
		lblEstoqueImg = new Label();
		lblRelatorioImg = new Label();
		lblLivroImg = new Label();

		
		imgSair = new Image(getClass().getResourceAsStream("/img/close.png"));
		lblSairImg.setGraphic(new ImageView(imgSair));

		imgVenda = new Image(getClass().getResourceAsStream("/img/venda.png"));
		imgFuncionario = new Image(getClass().getResourceAsStream("/img/funcionario.png"));
		imgLivro = new Image(getClass().getResourceAsStream("/img/livro.png"));
		imgEstoque = new Image(getClass().getResourceAsStream("/img/estoque.png"));
		imgRelatorio = new Image(getClass().getResourceAsStream("/img/relatorio.png"));

		lblVendaImg.setGraphic(new ImageView(imgVenda));
		lblFuncionarioImg.setGraphic(new ImageView(imgFuncionario));
		lblEstoqueImg.setGraphic(new ImageView(imgEstoque));
		lblRelatorioImg.setGraphic(new ImageView(imgRelatorio));
		lblLivroImg.setGraphic(new ImageView(imgLivro));

		painelPrincipal.setStyle("-fx-border-color: #FF0040;"+
				"-fx-border-width: 3;");
		
		painelPrincipal.getChildren().addAll(lblTituloPagina, lblSairImg, lblVenda, lblFuncionario, lblLivro,
				lblEstoque, lblRelatorio, lblVendaImg, lblFuncionarioImg, lblLivroImg, lblEstoqueImg, lblRelatorioImg);
		
	}

	private void initListeners() {

		lblSairImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Sair");
				alert.setHeaderText("Sair do Sistema");
				alert.setContentText("Voce Deseja Sair?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {

					TelaPrincipalView.stage.close();

				}

			}
		});

		lblSairImg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				lblSairImg.setStyle(
						"-fx-background-color:transparent;" + "-fx-border-color: #FF0040;" + "-fx-border-width: 3;");
			}
		});

		lblSairImg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				lblSairImg.setStyle(lblBorda);
			}
		});

		lblVendaImg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				lblVendaImg.setStyle(
						"-fx-background-color:transparent;" + "-fx-border-color: #FF0040;" + "-fx-border-width: 3");
			}
		});

		lblVendaImg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				lblVendaImg.setStyle(lblBorda);
			}
		});

		lblVendaImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				try {
					new VendaView().start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
				TelaPrincipalView.stage.close();
			}
		});

		lblFuncionarioImg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				lblFuncionarioImg.setStyle(
						"-fx-background-color:transparent;" + "-fx-border-color: #FF0040;" + "-fx-border-width: 3");
			}
		});

		lblFuncionarioImg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				lblFuncionarioImg.setStyle(lblBorda);
			}
		});

		lblFuncionarioImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				try {
					new FuncionarioView().start(new Stage());
				} catch (Exception e) {

					e.printStackTrace();
				}
				TelaPrincipalView.stage.close();
			}
		});

		lblEstoqueImg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				lblEstoqueImg.setStyle(
						"-fx-background-color:transparent;" + "-fx-border-color: #FF0040;" + "-fx-border-width: 3;");
			}
		});

		lblEstoqueImg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				lblEstoqueImg.setStyle(lblBorda);
			}
		});

		lblEstoqueImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				try {
					new EstoqueView().start(new Stage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				TelaPrincipalView.stage.close();
			}
		});

		lblLivroImg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				lblLivroImg.setStyle(
						"-fx-background-color:transparent;" + "-fx-border-color: #FF0040;" + "-fx-border-width: 3;");
			}
		});

		lblLivroImg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				lblLivroImg.setStyle(lblBorda);
			}
		});

		lblLivroImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				try {
					new LivroView().start(new Stage());
					TelaPrincipalView.stage.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		lblRelatorioImg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				lblRelatorioImg.setStyle(
						"-fx-background-color:transparent;" + "-fx-border-color: #FF0040;" + "-fx-border-width: 3;");
			}
		});

		lblRelatorioImg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				lblRelatorioImg.setStyle(lblBorda);
			}
		});

		lblRelatorioImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				try {
					new RelatorioView().start(new Stage());
				} catch (Exception e) {

					e.printStackTrace();
				}
				TelaPrincipalView.stage.close();
			}
		});

	}

}
