package com.livraria.view;


import com.livraria.controller.EstoqueController;
import com.livraria.model.EstoqueModel;
import com.livraria.utility.MensagemAlerta;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EstoqueView extends Application {
	private Pane painelPrincipal;
	private Label lblTituloPagina;
	
	private Label lblSairImg;
	private Label lblMenuImg;
	
	private Button btnEntrada;
	private Button btnHistoricoEntrada;
	private Button btnHistoricoVenda;
	
	private Image imgSair;
	private Image imgMenu;
	
	private TableView<EstoqueModel> tabela;
	private MensagemAlerta msgAlerta = new MensagemAlerta();
	private static Stage stage;
	private String lblBorda = "-fx-border-color: #848484;" + "-fx-border-width: 3;";
	public void start(Stage stage) throws Exception {
		initComponents();
		initTable();
		initListeners();
	        
		Scene scene = new Scene(painelPrincipal, 1024, 600);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(scene);
		stage.setTitle("Estoque");
		stage.show();
		initLayout();
		EstoqueView.setStage(stage);

	}

	public static Stage getStage() {
		return stage;
	}

	private static void setStage(Stage stage) {
		EstoqueView.stage = stage;

	}
	
	private void initComponents() {
		painelPrincipal = new Pane();

		lblTituloPagina = new Label("Estoque");

		btnEntrada = new Button("Entrada");
		btnHistoricoEntrada = new Button("Historico Entrada");
		btnHistoricoVenda = new Button("Historico Venda");
		lblSairImg = new Label();
		lblMenuImg = new Label();
		imgSair = new Image(getClass().getResourceAsStream("/img/close.png"));
		imgMenu = new Image(getClass().getResourceAsStream("/img/home.png"));
		lblMenuImg.setGraphic(new ImageView(imgMenu));
		lblSairImg.setGraphic(new ImageView(imgSair));
		
		tabela = new TableView<>();
        tabela.setItems(getProduto());
        
		painelPrincipal.getChildren().addAll( lblTituloPagina, btnEntrada,lblMenuImg,btnHistoricoEntrada,btnHistoricoVenda, lblSairImg, tabela);
		
	}

	private void initLayout() {
		
		lblTituloPagina.setLayoutX(450);
		lblTituloPagina.setLayoutY(10);
		
		lblMenuImg.setLayoutX(900);
		lblMenuImg.setLayoutY(10);
		lblSairImg.setLayoutX(950);
		lblSairImg.setLayoutY(10);
		
		btnEntrada.setLayoutX(30);
		btnEntrada.setLayoutY(65);
		btnEntrada.setPrefWidth(130);
		btnHistoricoEntrada.setLayoutX(190);
		btnHistoricoEntrada.setLayoutY(65);
		btnHistoricoEntrada.setPrefWidth(130);
		btnHistoricoVenda.setLayoutX(350);
		btnHistoricoVenda.setLayoutY(65);
		btnHistoricoVenda.setPrefWidth(130);
		
		
		tabela.setLayoutX(30);
		tabela.setLayoutY(120);
		tabela.setPrefSize(970, 450);
		
		

		lblTituloPagina.setStyle("-fx-font-size: 25pt;" + 
						"-fx-font-family: \"Segoe UI Semibold\";"
					+ "-fx-text-fill: black; "+
					 "-fx-opacity: 0.8;");
		painelPrincipal.setStyle("-fx-background-color:#A9E2F3;");
		painelPrincipal.setStyle(lblBorda);
		tabela.setStyle(lblBorda);
		
	}
	
	private void initListeners() {

		lblSairImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				if(msgAlerta.alertaConfirmacao("Sair", "Sair do Sistema", "Voce Deseja Sair do Sistema?")){
					EstoqueView.stage.close();
				}
				
				
			}
		});

		lblSairImg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {

			}
		});

		lblSairImg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {

			}
		});

		lblMenuImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				if(msgAlerta.alertaConfirmacao("Menu", "Voltar ao Menu", "Voce Deseja Voltar ao Menu?")){
					try {
						new TelaPrincipalView().start(new Stage());
						EstoqueView.stage.close();
					} catch (Exception e) {

						e.printStackTrace();
					}
				}
				

			}
		});
		
		btnEntrada.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				try {
					new EntradaView().start(new Stage());
					EstoqueView.stage.close();
				} catch (Exception e) {

					e.printStackTrace();
				}

			}
		});

		btnHistoricoEntrada.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				try {
					new HistoricoEntradaView().start(new Stage());
					EstoqueView.stage.close();
				} catch (Exception e) {

					e.printStackTrace();
				}

			}
		});
	}

	private void initTable() {
		TableColumn<EstoqueModel, Integer> codigo = new TableColumn<>("Codigo");
		codigo.setMinWidth(50);
		codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		
		TableColumn<EstoqueModel, String> titulo = new TableColumn<>("Titulo");
		titulo.setMinWidth(100);
		titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		
		TableColumn<EstoqueModel, Integer> qtde = new TableColumn<>("Quantidade");
		qtde.setMinWidth(100);
		qtde.setCellValueFactory(new PropertyValueFactory<>("qtde"));
		
		TableColumn<EstoqueModel, Double> valorCompra = new TableColumn<>("Valor de Compra");
		valorCompra.setMinWidth(100);
		valorCompra.setCellValueFactory(new PropertyValueFactory<>("valorCompra"));
		
		TableColumn<EstoqueModel, String> valorVenda = new TableColumn<>("Valor de Venda");
		valorVenda.setMinWidth(100);
		valorVenda.setCellValueFactory(new PropertyValueFactory<>("valorVenda"));
		
		tabela.getColumns().addAll(codigo, titulo, qtde, valorCompra, valorVenda);
	}

	public ObservableList<EstoqueModel> getProduto(){
		ObservableList<EstoqueModel> livros = FXCollections.observableArrayList();

		EstoqueController livroMod = new EstoqueController();
		for (EstoqueModel livro : livroMod.listarEstoque()) {
			livro.getCodInclusao();
			livro.getQtdProduto();
			livro.getPrecoVenda();
			livro.getDataInclusao();

			livros.addAll(livro);
		}

		return livros;

	}
		
	public static void main(String[] args) {
		launch(args);
	}
}