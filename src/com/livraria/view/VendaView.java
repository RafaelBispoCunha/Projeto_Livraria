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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.livraria.controller.LivroController;
import com.livraria.model.LivroModel;
import com.livraria.model.VendaModel;
import com.livraria.utility.MensagemAlerta;

public class VendaView extends Application {
	private static Stage stage;
	private Pane painelPrincipal;

	private Label lblVendedor;
	private Label lblCodigoVenda;
	private Label lblCodigoLivro;
	private Label lblTitulo;
	private Label lblQuantidade;
	private Label lblTotal;
	private Label lblValorPago;
	private Label lblTroco;
	private Label lblData;
	private Label lblValorPecela;
	private Label lblDesconto;
	private Label lblParcelas;
	private Label lblSairImg;
	private Label lblMenuImg;
	private Label lblTituloPagina;

	private Image imgSair;
	private Image imgMenu;

	private TextField txtCodigoVenda;
	private TextField txtCodigoLivro;
	private TextField txtVendedor;
	private TextField txtTitulo;
	private TextField txtQuantidade;
	private TextField txtTotal;
	private TextField txtValorParcela;
	private TextField txtDesconto;
	private TextField txtValorPago;
	private TextField txtTroco;

	private DatePicker data;
	private Spinner parcelas;

	private Button addItem;
	private Button btnBuscarLivroCodigo;
	private Button btnBuscarLivroTitulo;
	private Button btnAlterar;
	private Button btnSalvar;
	private Button btnRemover;
	private Button btnCancelar;
	private Button btnPagar;
	private Button btnLimpar;

	private double totalPagar = 0;
	private TableView<VendaModel> tabela;
	private ObservableList<VendaModel> itens = FXCollections.observableArrayList();
	private MensagemAlerta msgAlerta = new MensagemAlerta();
	private String lblBorda = "-fx-border-color: #848484;" + "-fx-border-width: 3;";

	public void start(Stage stage) throws Exception {
		initComponents();
		initTable();
		initListeners();

		desabilitarCampos();
		desabilitarBotoes();
		habilitarInicial();
		Scene scene = new Scene(painelPrincipal, 1024, 600);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(scene);
		stage.setTitle("Venda");
		stage.show();
		initLayout();
		VendaView.setStage(stage);

	}

	public static Stage getStage() {
		return stage;
	}

	private static void setStage(Stage stage) {
		VendaView.stage = stage;

	}

	private void initComponents() {
		painelPrincipal = new Pane();
		lblTituloPagina = new Label("Venda");

		lblVendedor = new Label("Vendedor");
		lblCodigoVenda = new Label("Codigo de Venda");
		lblCodigoLivro = new Label("Codigo do Livro");
		lblTitulo = new Label("Titulo");
		lblQuantidade = new Label("Qtde");
		lblTotal = new Label("Total");
		lblValorPago = new Label("Valor Pago");
		lblTroco = new Label("Troco");
		lblData = new Label("Data");
		lblVendedor = new Label("Vendedor");
		lblParcelas = new Label("Parcelas");
		lblValorPecela = new Label("Valor da Parcela");
		lblDesconto = new Label("Desconto %");
		lblSairImg = new Label();
		lblMenuImg = new Label();
		imgSair = new Image(getClass().getResourceAsStream("/img/close.png"));
		imgMenu = new Image(getClass().getResourceAsStream("/img/home.png"));
		lblMenuImg.setGraphic(new ImageView(imgMenu));
		lblSairImg.setGraphic(new ImageView(imgSair));

		txtVendedor = new TextField();
		txtCodigoVenda = new TextField();
		txtCodigoLivro = new TextField();
		txtTitulo = new TextField();
		txtQuantidade = new TextField();
		txtTotal = new TextField();
		txtDesconto = new TextField();
		txtValorParcela = new TextField();
		txtValorPago = new TextField();
		txtTroco = new TextField();

		addItem = new Button("Add Item");
		btnBuscarLivroCodigo = new Button("Buscar");
		btnBuscarLivroTitulo = new Button("Buscar");
		btnAlterar = new Button("Alterar");
		btnSalvar = new Button("Salvar");
		btnRemover = new Button("Remover");

		btnPagar = new Button("Pagar");
		btnLimpar = new Button("Limpar");
		btnCancelar = new Button("Cancelar");

		tabela = new TableView<>();
		data = new DatePicker();
		parcelas = new Spinner(1, 10, 1);

		painelPrincipal.getChildren().addAll(lblTituloPagina, lblMenuImg, lblSairImg, lblCodigoVenda, lblCodigoLivro,
				lblTitulo, lblParcelas, lblQuantidade, lblData, lblVendedor, lblValorPecela, lblTotal, lblValorPago,
				lblTroco, lblDesconto);
		painelPrincipal.getChildren().addAll(txtCodigoVenda, txtCodigoLivro, txtTitulo, txtQuantidade, txtVendedor,
				txtTotal, txtValorPago, txtTroco, txtValorParcela, txtDesconto);
		painelPrincipal.getChildren().addAll(addItem, btnBuscarLivroCodigo, btnBuscarLivroTitulo, btnAlterar, btnSalvar,
				btnRemover, btnCancelar, btnPagar, btnLimpar);
		painelPrincipal.getChildren().addAll(data, tabela, parcelas);
	}

	private void initLayout() {
		lblTituloPagina.setLayoutX(460);
		lblTituloPagina.setLayoutY(10);

		lblMenuImg.setLayoutX(900);
		lblMenuImg.setLayoutY(10);
		lblSairImg.setLayoutX(950);
		lblSairImg.setLayoutY(10);
		lblCodigoVenda.setLayoutX(20);
		lblCodigoVenda.setLayoutY(80);
		lblCodigoLivro.setLayoutX(20);
		lblCodigoLivro.setLayoutY(130);
		lblTitulo.setLayoutX(20);
		lblTitulo.setLayoutY(180);
		lblQuantidade.setLayoutX(20);
		lblQuantidade.setLayoutY(230);
		lblVendedor.setLayoutX(20);
		lblVendedor.setLayoutY(280);
		lblData.setLayoutX(20);
		lblData.setLayoutY(330);

		lblParcelas.setLayoutX(750);
		lblParcelas.setLayoutY(450);
		lblValorPecela.setLayoutX(750);
		lblValorPecela.setLayoutY(500);
		lblDesconto.setLayoutX(750);
		lblDesconto.setLayoutY(550);
		lblTotal.setLayoutX(500);
		lblTotal.setLayoutY(450);
		lblValorPago.setLayoutX(500);
		lblValorPago.setLayoutY(500);
		lblTroco.setLayoutX(500);
		lblTroco.setLayoutY(550);

		txtCodigoVenda.setLayoutX(150);
		txtCodigoVenda.setLayoutY(75);
		txtCodigoVenda.setPrefWidth(200);
		txtCodigoLivro.setLayoutX(150);
		txtCodigoLivro.setLayoutY(125);
		txtCodigoLivro.setPrefWidth(200);
		txtTitulo.setLayoutX(150);
		txtTitulo.setLayoutY(175);
		txtTitulo.setPrefWidth(200);
		txtQuantidade.setLayoutX(150);
		txtQuantidade.setLayoutY(225);
		txtQuantidade.setPrefWidth(100);
		txtVendedor.setLayoutX(150);
		txtVendedor.setLayoutY(275);
		txtVendedor.setPrefWidth(200);
		data.setLayoutX(150);
		data.setLayoutY(325);
		data.setPrefWidth(150);

		parcelas.setLayoutX(900);
		parcelas.setLayoutY(445);
		parcelas.setPrefWidth(60);

		txtValorParcela.setLayoutX(900);
		txtValorParcela.setLayoutY(495);
		txtValorParcela.setPrefWidth(100);
		txtDesconto.setLayoutX(900);
		txtDesconto.setLayoutY(545);
		txtDesconto.setPrefWidth(60);

		txtTotal.setLayoutX(600);
		txtTotal.setLayoutY(445);
		txtTotal.setPrefWidth(100);
		txtValorPago.setLayoutX(600);
		txtValorPago.setLayoutY(495);
		txtValorPago.setPrefWidth(100);
		txtTroco.setLayoutX(600);
		txtTroco.setLayoutY(545);
		txtTroco.setPrefWidth(100);

		tabela.setLayoutX(500);
		tabela.setLayoutY(75);
		tabela.setPrefSize(500, 350);

		addItem.setLayoutX(400);
		addItem.setLayoutY(75);
		btnBuscarLivroCodigo.setLayoutX(400);
		btnBuscarLivroCodigo.setLayoutY(125);
		btnBuscarLivroCodigo.setPrefWidth(65);
		btnBuscarLivroTitulo.setLayoutX(400);
		btnBuscarLivroTitulo.setLayoutY(175);
		btnBuscarLivroTitulo.setPrefWidth(65);

		btnAlterar.setLayoutX(400);
		btnAlterar.setLayoutY(225);
		btnAlterar.setPrefWidth(65);

		btnSalvar.setLayoutX(400);
		btnSalvar.setLayoutY(275);
		btnSalvar.setPrefWidth(65);

		btnRemover.setLayoutX(400);
		btnRemover.setLayoutY(325);
		btnRemover.setPrefWidth(65);

		btnLimpar.setLayoutX(400);
		btnLimpar.setLayoutY(375);
		btnLimpar.setPrefWidth(65);

		btnPagar.setLayoutX(50);
		btnPagar.setLayoutY(560);
		btnPagar.setPrefWidth(70);

		btnCancelar.setLayoutX(250);
		btnCancelar.setLayoutY(560);
		btnCancelar.setPrefWidth(70);

		lblTituloPagina.setStyle("-fx-font-size: 25pt;" + "-fx-font-family: \"Segoe UI Semibold\";"
				+ "-fx-text-fill: black; " + "-fx-opacity: 0.8;");
		painelPrincipal.setStyle("-fx-background-color:#A9E2F3;");
		painelPrincipal.setStyle(lblBorda);
		tabela.setStyle(lblBorda);

	}

	private void initListeners() {

		lblSairImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				System.exit(0);
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
				try {
					new TelaPrincipalView().start(new Stage());
					VendaView.stage.close();
				} catch (Exception e) {

					e.printStackTrace();
				}

			}
		});

		lblMenuImg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {

			}
		});

		lblMenuImg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {

			}
		});

		addItem.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (txtCodigoLivro.getText().trim().isEmpty() || txtCodigoLivro.getText() == null) {
					msgAlerta.alertaInformativo("Codigo", null, "Campo Codigo Vazio");
				} else {

					if (txtQuantidade.getText().trim().isEmpty()) {
						txtQuantidade.setText("1");
					}

					tabela.setItems(getItem(Integer.parseInt(txtCodigoLivro.getText())));
					limparCampos();
				}

			}
		});

		tabela.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				setCampos();

				desabilitarCampos();
				desabilitarBotoes();
				btnAlterar.setDisable(false);
				btnLimpar.setDisable(false);
				btnRemover.setDisable(false);

			}
		});

		btnRemover.setOnAction(e -> {
			removerItem();

		});

		btnSalvar.setOnAction(e -> {

			int codigo = tabela.getSelectionModel().getSelectedItem().getCodigoLivro();
			removerItem();
			VendaModel selectedItem = tabela.getSelectionModel().getSelectedItem();
			getItem(codigo);

			limparCampos();
			desabilitarBotoes();
			habilitarInicial();
		});

		btnAlterar.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				btnSalvar.setDisable(false);
				btnAlterar.setDisable(false);
				btnLimpar.setDisable(false);

				txtQuantidade.setDisable(false);

			}
		});
		btnLimpar.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				limparCampos();
				desabilitarBotoes();
				habilitarInicial();

			}
		});

		parcelas.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				txtValorParcela.setText(String.valueOf((totalPagar) / (int) parcelas.getValue()));

			}
		});

		btnPagar.setOnAction(e -> {

			impressa();

		});

	}

	public void removerItem() {
		VendaModel selectedItem = tabela.getSelectionModel().getSelectedItem();
		double valor = tabela.getSelectionModel().getSelectedItem().getValorTotal();
		removerValor(valor);
		tabela.getItems().remove(selectedItem);

	}

	private void initTable() {
		TableColumn<VendaModel, Integer> idColuna = new TableColumn<>("Codigo");
		idColuna.setMinWidth(50);
		idColuna.setCellValueFactory(new PropertyValueFactory<>("codigoLivro"));

		TableColumn<VendaModel, String> nomeColuna = new TableColumn<>("Titulo");
		nomeColuna.setMinWidth(100);
		nomeColuna.setCellValueFactory(new PropertyValueFactory<>("titulo"));

		TableColumn<VendaModel, Integer> quantidade = new TableColumn<>("Quantidade");
		quantidade.setMinWidth(100);
		quantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

		TableColumn<VendaModel, Double> valorUnitario = new TableColumn<>("Valor Unitario");
		valorUnitario.setMinWidth(100);
		valorUnitario.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));

		TableColumn<VendaModel, Double> valorTotal = new TableColumn<>("Valor Total");
		valorTotal.setMinWidth(100);
		valorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));

		tabela.getColumns().addAll(idColuna, nomeColuna, quantidade, valorUnitario, valorTotal);
	}

	public ObservableList<VendaModel> getItem(int codigo) {

		VendaModel item = new VendaModel();
		LivroController livro = new LivroController();
		List<LivroModel> livros = new ArrayList<LivroModel>();
		int quantidade;
		double total;
		if (txtQuantidade.getText() == null || txtQuantidade.getText().trim().equals("")) {
			quantidade = 1;
		} else {
			quantidade = Integer.parseInt(txtQuantidade.getText());
		}
		double precoUnit = 10;
		livros.addAll(livro.buscarLivroCodigo(codigo));

		for (int i = 0; i < 1; i++) {
			item.setCodigoLivro(livros.get(0).getCodigoLivro());
			item.setTitulo(livros.get(0).getTitulo());

			item.setQuantidade(quantidade);
			item.setValorVenda(precoUnit);
			item.setValorTotal(quantidade * precoUnit);
			itens.add(item);
		}

		txtTitulo.setText(item.getTitulo());

		total = item.getValorTotal();
		somarValor(total);

		return itens;

	}

	public void somarValor(double valor) {

		totalPagar += valor;
		txtTotal.setText(String.valueOf(totalPagar));
		txtValorParcela.setText(String.valueOf((totalPagar) / (int) parcelas.getValue()));
	}

	public void removerValor(double valor) {
		totalPagar -= valor;
		txtTotal.setText(String.valueOf(totalPagar));
		txtValorParcela.setText(String.valueOf((totalPagar) / (int) parcelas.getValue()));
	}

	public void setCampos() {
		txtCodigoLivro.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getCodigoLivro()));
		txtTitulo.setText(tabela.getSelectionModel().getSelectedItem().getTitulo());
		txtQuantidade.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getQuantidade()));

	}

	public void limparCampos() {
		txtCodigoLivro.setText("");
		txtTitulo.setText("");
		txtQuantidade.setText("");
		txtCodigoVenda.setText("");
		txtCodigoLivro.setText("");
		txtTitulo.setText("");
		txtQuantidade.setText("");

		data.setValue(null);
		parcelas.setValueFactory(null);
	}

	public void habilitarCampos() {
		txtCodigoVenda.setDisable(true);
		txtCodigoLivro.setDisable(false);
		txtTitulo.setDisable(false);
		txtCodigoVenda.setDisable(false);
		txtCodigoLivro.setDisable(false);
		txtTitulo.setDisable(false);
		txtQuantidade.setDisable(false);
		txtVendedor.setDisable(false);
		txtTotal.setDisable(false);
		txtVendedor.setDisable(false);
		txtValorPago.setDisable(false);
		txtDesconto.setDisable(false);

	}

	public void desabilitarCampos() {

		txtCodigoVenda.setDisable(true);
		txtCodigoLivro.setDisable(true);
		txtTitulo.setDisable(true);
		txtQuantidade.setDisable(true);
		txtVendedor.setDisable(true);
		txtTotal.setDisable(true);
		txtVendedor.setDisable(true);
		txtValorPago.setDisable(true);
		txtDesconto.setDisable(true);

		data.setDisable(true);
		txtTroco.setDisable(true);
		parcelas.setDisable(true);
		txtValorParcela.setDisable(true);
		txtQuantidade.setDisable(true);
		txtVendedor.setDisable(true);

	}

	public void habilitarInicial() {
		addItem.setDisable(false);
		btnBuscarLivroCodigo.setDisable(false);
		btnBuscarLivroTitulo.setDisable(false);
		txtCodigoLivro.setDisable(false);
		txtTitulo.setDisable(false);
		txtQuantidade.setDisable(false);
		parcelas.setDisable(false);
		txtDesconto.setDisable(false);
		btnPagar.setDisable(false);
		btnCancelar.setDisable(false);
	}

	public void habilitarBotoes() {

		addItem.setDisable(false);
		btnBuscarLivroCodigo.setDisable(false);
		btnBuscarLivroTitulo.setDisable(false);
		btnAlterar.setDisable(false);
		btnSalvar.setDisable(false);
		btnRemover.setDisable(false);
		btnCancelar.setDisable(false);
		btnPagar.setDisable(false);
		btnLimpar.setDisable(false);

	}

	public void desabilitarBotoes() {
		addItem.setDisable(true);
		btnBuscarLivroCodigo.setDisable(true);
		btnBuscarLivroTitulo.setDisable(true);
		btnAlterar.setDisable(true);
		btnSalvar.setDisable(true);
		btnRemover.setDisable(true);
		btnCancelar.setDisable(true);
		btnPagar.setDisable(true);
		btnLimpar.setDisable(true);

	}

	public void impressa() {
		String linha;

		try {

			File file = new File("src\\cupomfiscal\\cupom.txt");
			System.out.print(file.getAbsolutePath());

			if (!file.exists()) {
				file.createNewFile();
			}
			try (FileWriter fw = new FileWriter(file.getAbsoluteFile()); BufferedWriter bw = new BufferedWriter(fw)) {

				bw.write("Codigo   " + "Titulo                                " + "Quantidade      "
						+ "Valor Unitario     " + "Valor Total     \r\n"); // 2 apos a quebra
				for (int i = 0; i < tabela.getItems().size(); i++) {

					// Alinhamento das Colunas
					linha = String.format("%-8.16s %-40.40s %6d %20.2f %15.2f",
							tabela.getItems().get(i).getCodigoLivro(), tabela.getItems().get(i).getTitulo(),
							tabela.getItems().get(i).getQuantidade(), tabela.getItems().get(i).getValorVenda(),
							tabela.getItems().get(i).getValorTotal());

					bw.write(linha + "\r\n");

				}

				bw.write(
						"---------------------------------------------------------------------------------------------\r\n"
								+ "Total Compra...........R$   " + txtTotal.getText() + "\r\n"
								+ "Parcelas...............     " + parcelas.getValue() + "\r\n"
								+ "Valor Parcela..........R$   " + txtValorParcela.getText() + "\r\n"
								+ "Desconto................%   " + txtDesconto.getText() + "\r\n"
								+ "Valor Pago.............R$   " + txtValorPago.getText() + "\r\n"
								+ "Valor Troco............R$   " + txtTroco.getText() + "\r\n");

				bw.write("\r\n");

			}

		} catch (IOException ex) {
			ex.printStackTrace();

		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
