package com.livraria.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

import com.livraria.controller.EstoqueController;
import com.livraria.controller.VendaController;
import com.livraria.model.VendaModel;
import com.livraria.utility.MensagemAlerta;

public class HistoricoVendaView extends Application {
	private Pane painelPrincipal;
	
	private Label lblTituloPagina;
	private Label lblValorVenda;
	private Label lblIdVenda;
	private Label lblCodigo;
	private Label lblTitulo;
	private Label lblQtde;

	private Label lblSairImg;
	private Label lblMenuImg;
	private Label lblEstoqueImg;

	private Image imgEstoque;

	private Image imgSair;
	private Image imgMenu;

	private TextField txtIdVenda;
	private TextField txtCodigo;
	private TextField txtValorVenda;
	private TextField txtTitulo;
	private TextField txtQtde;
	private Button btnAlterar;
	private Button btnExcluir;
	private Button btnCancelar;

	private TableView<VendaModel> tabela;
	private static Stage stage;
	private VendaController vendaController = new VendaController();
	private EstoqueController estoqueController = new EstoqueController();
	private VendaModel venda = new VendaModel();
	private MensagemAlerta mensagem = new MensagemAlerta();
	private String lblBorda = "-fx-border-color: #848484;" + "-fx-border-width: 3;";
	public void start(Stage stage) throws Exception {
		initComponents();
		initTable();
		initListeners();
		desabilitarCampos();
		desabilitarBotao();

		Scene scene = new Scene(painelPrincipal, 1024, 600);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setTitle("Historico de Venda");
		stage.show();
		initLayout();
		HistoricoVendaView.setStage(stage);

	}

	public static Stage getStage() {
		return stage;
	}

	private static void setStage(Stage stage) {
		HistoricoVendaView.stage = stage;

	}

	private void initComponents() {
		painelPrincipal = new Pane();
		
		lblTituloPagina = new Label("Historico de Vendas");
		lblIdVenda = new Label("IdVenda");
		lblCodigo = new Label("Codigo");
		lblTitulo = new Label("Titulo");
		lblQtde = new Label("Qtde");
		lblValorVenda = new Label("Valor Unitario");

		lblSairImg = new Label();
		lblMenuImg = new Label();
		lblEstoqueImg = new Label();
		imgEstoque = new Image(getClass().getResourceAsStream("/img/seta_esq.png"));
		imgSair = new Image(getClass().getResourceAsStream("/img/close.png"));
		imgMenu = new Image(getClass().getResourceAsStream("/img/home.png"));
		lblEstoqueImg.setGraphic(new ImageView(imgEstoque));
		lblMenuImg.setGraphic(new ImageView(imgMenu));
		lblSairImg.setGraphic(new ImageView(imgSair));

		txtIdVenda = new TextField();
		txtCodigo = new TextField();
		txtTitulo = new TextField();
		txtQtde = new TextField();
		txtValorVenda = new TextField();
		btnAlterar = new Button("Alterar");
		btnCancelar = new Button("Cancelar");
		btnExcluir = new Button("Excluir");

		tabela = new TableView<>();
		tabela.setItems(vendaController.preencherTabela());

		painelPrincipal.getChildren().addAll(lblTituloPagina, lblEstoqueImg, lblMenuImg, lblSairImg, lblIdVenda, lblCodigo, lblTitulo,
				lblQtde, lblValorVenda, txtIdVenda, txtCodigo, txtTitulo, txtQtde, txtValorVenda, btnAlterar,
				btnCancelar, btnExcluir, tabela);

	}

	private void initLayout() {
		lblTituloPagina.setLayoutX(350);
		lblTituloPagina.setLayoutY(10);
		lblEstoqueImg.setLayoutX(850);
		lblEstoqueImg.setLayoutY(10);
		lblMenuImg.setLayoutX(900);
		lblMenuImg.setLayoutY(10);
		lblSairImg.setLayoutX(950);
		lblSairImg.setLayoutY(10);

		lblIdVenda.setLayoutX(20);
		lblIdVenda.setLayoutY(80);
		lblCodigo.setLayoutX(20);
		lblCodigo.setLayoutY(130);
		lblTitulo.setLayoutX(20);
		lblTitulo.setLayoutY(180);
		lblQtde.setLayoutX(20);
		lblQtde.setLayoutY(230);
		lblValorVenda.setLayoutX(20);
		lblValorVenda.setLayoutY(280);

		btnAlterar.setLayoutX(20);
		btnAlterar.setLayoutY(330);
		btnAlterar.setPrefWidth(70);
		btnCancelar.setLayoutX(150);
		btnCancelar.setLayoutY(330);
		btnCancelar.setPrefWidth(70);
		btnExcluir.setLayoutX(280);
		btnExcluir.setLayoutY(330);
		btnExcluir.setPrefWidth(70);

		txtIdVenda.setLayoutX(150);
		txtIdVenda.setLayoutY(75);
		txtIdVenda.setPrefWidth(100);
		txtCodigo.setLayoutX(150);
		txtCodigo.setLayoutY(125);
		txtCodigo.setPrefWidth(200);
		txtTitulo.setLayoutX(150);
		txtTitulo.setLayoutY(175);
		txtTitulo.setPrefWidth(200);
		txtQtde.setLayoutX(150);
		txtQtde.setLayoutY(225);
		txtQtde.setPrefWidth(100);
		txtValorVenda.setLayoutX(150);
		txtValorVenda.setLayoutY(275);
		txtValorVenda.setPrefWidth(200);

		tabela.setLayoutX(450);
		tabela.setLayoutY(75);
		tabela.setPrefSize(550, 450);
		
		
		lblTituloPagina.setStyle("-fx-font-size: 25pt;" + 
				"-fx-font-family: \"Segoe UI Semibold\";"
			+ "-fx-text-fill: black; "+
			 "-fx-opacity: 0.8;");
		painelPrincipal.setStyle("-fx-background-color:#A9E2F3;");
		painelPrincipal.setStyle(lblBorda);
		tabela.setStyle(lblBorda);

	}

	private void initListeners() {
		lblEstoqueImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				if (mensagem.alertaConfirmacao("Voltar", null, "Voce Deseja Voltar ao Estoque?")) {
					try {

						new EstoqueView().start(new Stage());
						HistoricoVendaView.stage.close();
					} catch (Exception e) {

						e.printStackTrace();
					}
				}

			}
		});

		lblSairImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				System.exit(0);
			}
		});

		lblMenuImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				try {
					new TelaPrincipalView().start(new Stage());
					HistoricoVendaView.stage.close();
				} catch (Exception e) {

					e.printStackTrace();
				}

			}
		});

		btnAlterar.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (campoVazio()) {
					bordaCampos("-fx-border-color: red;");
					mensagem.alertaInformativo("Salvar", null, "Campos Obrigatorios Vazios");
				} else {
					if (mensagem.alertaConfirmacao("Alterar Item ", null, "Você deseja Alterar Item?")) {
						estoqueController.alterarVendaEstoque(operacao());
					}
					bordaCampos(null);
					limparCampos();
					desabilitarBotao();
					desabilitarCampos();
					tabela.setItems(vendaController.preencherTabela());
				}
			}
		});

		btnCancelar.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				limparCampos();
				desabilitarBotao();
				desabilitarCampos();
				bordaCampos(null);
			}

		});

		btnExcluir.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (mensagem.alertaConfirmacao("Excluir Item ", null, "Você deseja Excluir Item?")) {
					estoqueController.excluirVendaEstoque(operacao());
				}
				bordaCampos(null);
				limparCampos();
				desabilitarBotao();
				desabilitarCampos();
				tabela.setItems(vendaController.preencherTabela());
			}

		});

		tabela.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				setCampos();
				habilitarCampos();
				habilitarBotao();
			}
		});

	}

	private void initTable() {
		TableColumn<VendaModel, Integer> idVenda = new TableColumn<>("idVenda");
		idVenda.setMinWidth(50);
		idVenda.setCellValueFactory(new PropertyValueFactory<>("idVenda"));

		TableColumn<VendaModel, Integer> codigo = new TableColumn<>("Codigo");
		codigo.setMinWidth(50);
		codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

		TableColumn<VendaModel, String> titulo = new TableColumn<>("Titulo");
		titulo.setMinWidth(218);
		titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));

		TableColumn<VendaModel, Integer> qtde = new TableColumn<>("QTDE");
		qtde.setMinWidth(100);
		qtde.setCellValueFactory(new PropertyValueFactory<>("qtde"));

		TableColumn<VendaModel, Double> valorVenda = new TableColumn<>("Valor Venda");
		valorVenda.setMinWidth(100);
		valorVenda.setCellValueFactory(new PropertyValueFactory<>("valorVenda"));

		tabela.getColumns().addAll(idVenda, codigo, titulo, qtde, valorVenda);
	}

	public VendaModel operacao() {
		venda.setCodigoVenda(Integer.parseInt(txtIdVenda.getText()));
		venda.setCodigoLivro(Integer.parseInt(txtCodigo.getText()));
		venda.setTitulo(txtTitulo.getText());
		venda.setQuantidade(Integer.parseInt(txtQtde.getText()));
		venda.setValorVenda(Double.parseDouble(txtValorVenda.getText()));

		return venda;
	}

	public void limparCampos() {
		txtIdVenda.setText(null);
		txtCodigo.setText(null);
		txtTitulo.setText(null);
		txtQtde.setText(null);
		txtValorVenda.setText(null);

	}

	public void setCampos() {
		txtIdVenda.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getCodigoVenda()));
		txtCodigo.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getCodigoLivro()));
		txtTitulo.setText(tabela.getSelectionModel().getSelectedItem().getTitulo());
		txtQtde.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getQuantidade()));
		txtValorVenda.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getValorVenda()));
	}

	public void habilitarCampos() {
		txtQtde.setDisable(false);
		txtValorVenda.setDisable(false);
	}

	public void desabilitarCampos() {
		txtIdVenda.setDisable(true);
		txtCodigo.setDisable(true);
		txtTitulo.setDisable(true);
		txtQtde.setDisable(true);
		txtValorVenda.setDisable(true);
	}

	public void habilitarBotao() {
		btnAlterar.setDisable(false);
		btnCancelar.setDisable(false);
		btnExcluir.setDisable(false);
	}

	public void desabilitarBotao() {
		btnAlterar.setDisable(true);
		btnCancelar.setDisable(true);
		btnExcluir.setDisable(true);
	}

	public boolean campoVazio() {
		return txtCodigo.getText() == null || txtCodigo.getText().trim().isEmpty() || txtTitulo.getText() == null
				|| txtTitulo.getText().trim().isEmpty() || txtQtde.getText() == null
				|| txtQtde.getText().trim().isEmpty();

	}

	public void bordaCampos(String cor) {
		txtCodigo.setStyle(cor);
		txtTitulo.setStyle(cor);
		txtQtde.setStyle(cor);

	}

	public static void main(String[] args) {
		launch(args);
	}
}