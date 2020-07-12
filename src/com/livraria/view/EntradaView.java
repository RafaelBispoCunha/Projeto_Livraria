package com.livraria.view;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import com.livraria.controller.EntradaController;
import com.livraria.controller.EstoqueController;
import com.livraria.model.EntradaModel;
import com.livraria.utility.*;

public class EntradaView extends Application {
	private Pane painelPrincipal;
	private Label lblTituloPagina;
	private Label lblValorCompra;
	private Label lblIdEntrada;
	private Label lblCodigo;
	private Label lblTitulo;
	private Label lblQtde;
	private Label lblSairImg;
	private Label lblMenuImg;
	private Label lblEstoqueImg;

	private Image imgEstoque;
	private Image imgSair;
	private Image imgMenu;

	private TextField txtIdEntrada;
	private TextField txtCodigo;
	private TextField txtValorCompra;
	
	private TextField txtTitulo;
	private TextField txtQtde;
	private Button btnBuscar;
	private Button btnAdicionar;
	private Button btnAlterar;
	private Button btnExcluir;
	private Button btnFinalizar;
	private Button btnLimpar;

	private TableView<EntradaModel> tabela;
	private static Stage stage;
	private EntradaController entradaController = new EntradaController();
	private EstoqueController estoqueController = new EstoqueController();
	private EntradaModel entrada = new EntradaModel();
	private MensagemAlerta msgAlerta = new MensagemAlerta();
	private ObservableList<EntradaModel> itens = FXCollections.observableArrayList();
	private String lblBorda = "-fx-border-color: #848484;" + "-fx-border-width: 3;";
	
	public void start(Stage stage) throws Exception {
		initComponents();
		initTable();
		initListeners();
		txtIdEntrada.setDisable(true);
		txtTitulo.setDisable(true);
		desabilitarModificacao();

		Scene scene = new Scene(painelPrincipal, 1024, 600);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(scene);
		stage.setTitle("Entrada");
		stage.show();
		initLayout();
		EntradaView.setStage(stage);

	}

	public static Stage getStage() {
		return stage;
	}

	private static void setStage(Stage stage) {
		EntradaView.stage = stage;

	}

	private void initComponents() {
		painelPrincipal = new Pane();
		lblTituloPagina = new Label("Entrada");
		lblIdEntrada = new Label("Id Entrada");
		lblCodigo = new Label("Codigo do Livro");
		lblTitulo = new Label("Titulo");
		lblQtde = new Label("Qtde");
		lblValorCompra = new Label("Valor Unitario");
		lblEstoqueImg = new Label();
		lblSairImg = new Label();
		lblMenuImg = new Label();
		imgEstoque = new Image(getClass().getResourceAsStream("/img/seta_esq.png"));
		imgSair = new Image(getClass().getResourceAsStream("/img/close.png"));
		imgMenu = new Image(getClass().getResourceAsStream("/img/home.png"));

		lblEstoqueImg.setGraphic(new ImageView(imgEstoque));
		lblMenuImg.setGraphic(new ImageView(imgMenu));
		lblSairImg.setGraphic(new ImageView(imgSair));

		txtIdEntrada = new TextField();
		txtIdEntrada.setText(String.valueOf(entradaController.proximoId()));
		txtCodigo = new TextField();
		txtTitulo = new TextField();
		txtQtde = new TextField();
		txtValorCompra = new TextField();
		btnBuscar = new Button("Buscar");
		btnAdicionar = new Button("Adicionar");
		btnAlterar = new Button("Alterar");
		btnExcluir = new Button("Excluir");
		btnFinalizar = new Button("Finalizar");
		btnLimpar = new Button("Limpar");

		tabela = new TableView<>();

		painelPrincipal.getChildren().addAll(lblTituloPagina, lblEstoqueImg, lblMenuImg, lblSairImg, lblIdEntrada,
				lblCodigo, lblTitulo, lblQtde, lblValorCompra);
		painelPrincipal.getChildren().addAll(txtIdEntrada, txtCodigo, txtTitulo, txtQtde, txtValorCompra);
		painelPrincipal.getChildren().addAll(btnBuscar, btnAdicionar, btnAlterar, btnExcluir, btnFinalizar, btnLimpar,
				tabela);

	}

	private void initLayout() {
		lblTituloPagina.setLayoutX(450);
		lblTituloPagina.setLayoutY(10);

		lblEstoqueImg.setLayoutX(850);
		lblEstoqueImg.setLayoutY(10);
		lblMenuImg.setLayoutX(900);
		lblMenuImg.setLayoutY(10);
		lblSairImg.setLayoutX(950);
		lblSairImg.setLayoutY(10);
		lblIdEntrada.setLayoutX(20);
		lblIdEntrada.setLayoutY(80);
		lblCodigo.setLayoutX(20);
		lblCodigo.setLayoutY(130);
		lblTitulo.setLayoutX(20);
		lblTitulo.setLayoutY(180);
		lblQtde.setLayoutX(20);
		lblQtde.setLayoutY(230);
		lblValorCompra.setLayoutX(20);
		lblValorCompra.setLayoutY(280);

		btnBuscar.setLayoutX(370);
		btnBuscar.setLayoutY(125);
		btnBuscar.setPrefWidth(70);
		btnAdicionar.setLayoutX(20);
		btnAdicionar.setLayoutY(330);
		btnAdicionar.setPrefWidth(70);
		btnAlterar.setLayoutX(150);
		btnAlterar.setLayoutY(330);
		btnAlterar.setPrefWidth(70);
		btnExcluir.setLayoutX(280);
		btnExcluir.setLayoutY(330);
		btnExcluir.setPrefWidth(70);

		txtIdEntrada.setLayoutX(150);
		txtIdEntrada.setLayoutY(75);
		txtIdEntrada.setPrefWidth(100);
		txtCodigo.setLayoutX(150);
		txtCodigo.setLayoutY(125);
		txtCodigo.setPrefWidth(200);
		txtTitulo.setLayoutX(150);
		txtTitulo.setLayoutY(175);
		txtTitulo.setPrefWidth(200);
		txtQtde.setLayoutX(150);
		txtQtde.setLayoutY(225);
		txtQtde.setPrefWidth(100);
		txtValorCompra.setLayoutX(150);
		txtValorCompra.setLayoutY(275);
		txtValorCompra.setPrefWidth(200);

		tabela.setLayoutX(500);
		tabela.setLayoutY(75);
		tabela.setPrefSize(500, 350);

		btnFinalizar.setLayoutX(620);
		btnFinalizar.setLayoutY(450);
		btnFinalizar.setPrefWidth(70);
		btnLimpar.setLayoutX(820);
		btnLimpar.setLayoutY(450);
		btnLimpar.setPrefWidth(70);

		lblTituloPagina.setStyle("-fx-font-size: 25pt;" + "-fx-font-family: \"Segoe UI Semibold\";"
				+ "-fx-text-fill: black; " + "-fx-opacity: 0.8;");
		painelPrincipal.setStyle("-fx-background-color:#A9E2F3;");
		painelPrincipal.setStyle(lblBorda);
		tabela.setStyle(lblBorda);

	}

	private void initListeners() {

		lblEstoqueImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				if (msgAlerta.alertaConfirmacao("Voltar", "Voltar Para Estoque Livro",
						"Voce Deseja Voltar ao Estoque?")) {
					try {

						new EstoqueView().start(new Stage());
						EntradaView.stage.close();
					} catch (Exception e) {

						e.printStackTrace();
					}
				}

			}
		});

		lblEstoqueImg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {

			}
		});

		lblEstoqueImg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {

			}
		});

		lblSairImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				if (msgAlerta.alertaConfirmacao("Sair", "Sair do Sistema", "Voce Deseja Sair do Sistema?")) {
					EntradaView.stage.close();
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
				if (msgAlerta.alertaConfirmacao("Menu", "Voltar ao Menu", "Voce Deseja Voltar ao Menu?")) {
					try {
						new TelaPrincipalView().start(new Stage());
						EntradaView.stage.close();
					} catch (Exception e) {

						e.printStackTrace();
					}
				}

			}
		});

		btnBuscar.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (txtCodigo.getText().trim().isEmpty() || txtCodigo.getText() == null) {
					msgAlerta.alertaConfirmacao("Campo Vazio", null, "Insira o Codigo do Livro");
					txtCodigo.setStyle("-fx-border-color: red;");
					limparCampos();
				} else {
					entrada = entradaController.buscarLivro(Integer.parseInt(txtCodigo.getText()));
					txtTitulo.setText(entrada.getTitulo());
					txtCodigo.setStyle(null);
				}

			}
		});

		btnAdicionar.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (campoVazio()) {
					bordaCampos("-fx-border-color: red;");
					msgAlerta.alertaConfirmacao("Campos Vazios", null, "Insira Dados nos Campos");

				} else {
					tabela.setItems(getItem());
					txtQtde.setStyle(null);
					txtValorCompra.setStyle(null);
					msgAlerta.alertaInformativo("Entrada", null, "Item adicionado com Sucesso");
					limparCampos();
				}

			}

		});

		btnAlterar.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				int codigo = tabela.getSelectionModel().getSelectedItem().getCodigo();
				removerItem();
				EntradaModel selectedItem = tabela.getSelectionModel().getSelectedItem();
				getItem();
				limparCampos();
				btnAdicionar.setDisable(false);
			}

		});

		btnExcluir.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				btnAdicionar.setDisable(false);
				removerItem();
				limparCampos();
			}

		});

		btnFinalizar.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				entradaController.registrarEntrada(tabela);
				limparCampos();
			}

		});

		tabela.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				setCampos();
				btnAdicionar.setDisable(true);
				btnAlterar.setDisable(false);
				btnExcluir.setDisable(false);

			}
		});

	}

	private void initTable() {
		TableColumn<EntradaModel, Integer> codigo = new TableColumn<>("Codigo");
		codigo.setMinWidth(50);
		codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

		TableColumn<EntradaModel, String> titulo = new TableColumn<>("Titulo");
		titulo.setMinWidth(218);
		titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));

		TableColumn<EntradaModel, Integer> quantidade = new TableColumn<>("QTD");
		quantidade.setMinWidth(100);
		quantidade.setCellValueFactory(new PropertyValueFactory<>("qtde"));

		TableColumn<EntradaModel, Double> valorUnitario = new TableColumn<>("Valor Unitario");
		valorUnitario.setMinWidth(100);
		valorUnitario.setCellValueFactory(new PropertyValueFactory<>("valorCompra"));

		tabela.getColumns().addAll(codigo, titulo, quantidade, valorUnitario);
	}

	public ObservableList<EntradaModel> getItem() {

		EntradaModel item = new EntradaModel();
		entrada = entradaController.buscarLivro(Integer.parseInt(txtCodigo.getText()));
		int quantidade = 1;
		if (!txtQtde.getText().isEmpty()) {
			quantidade = Integer.parseInt(txtQtde.getText());
		}

		for (int i = 0; i < 1; i++) {
			item.setIdEntrada(Integer.parseInt(txtIdEntrada.getText()));
			item.setCodigo(Integer.parseInt(txtCodigo.getText()));
			item.setTitulo(txtTitulo.getText());
			item.setQtde(quantidade);
			item.setValorCompra(Double.parseDouble(txtValorCompra.getText()));
			itens.add(item);
		}
		return itens;

	}

	public void limparCampos() {
		txtIdEntrada.setText(String.valueOf(entradaController.proximoId()));
		txtCodigo.setText(null);
		txtTitulo.setText(null);
		txtQtde.setText(null);
		txtValorCompra.setText(null);

	}

	public void desabilitarModificacao() {
		btnAlterar.setDisable(true);
		btnExcluir.setDisable(true);

	}

	public void setCampos() {
		txtCodigo.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getCodigo()));
		txtTitulo.setText(tabela.getSelectionModel().getSelectedItem().getTitulo());
		txtQtde.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getQtde()));
		txtValorCompra.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getValorCompra()));
	}

	public void removerItem() {
		EntradaModel selectedItem = tabela.getSelectionModel().getSelectedItem();
		tabela.getItems().remove(selectedItem);
	}

	public boolean campoVazio() {
		return txtCodigo.getText() == null || txtCodigo.getText().trim().isEmpty() || txtTitulo.getText() == null
				|| txtTitulo.getText().trim().isEmpty() || txtQtde.getText() == null
				|| txtQtde.getText().trim().isEmpty() || txtValorCompra.getText() == null
				|| txtValorCompra.getText().trim().isEmpty();

	}

	public void bordaCampos(String cor) {
		txtCodigo.setStyle(cor);
		txtTitulo.setStyle(cor);
		txtQtde.setStyle(cor);
		txtValorCompra.setStyle(cor);

	}

	public static void main(String[] args) {
		launch(args);
	}
}