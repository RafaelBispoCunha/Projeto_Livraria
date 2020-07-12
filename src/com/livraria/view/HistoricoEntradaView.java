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

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.livraria.controller.EntradaController;
import com.livraria.controller.LivroController;
import com.livraria.model.EntradaModel;
import com.livraria.model.LivroModel;
import com.livraria.utility.MensagemAlerta;

public class HistoricoEntradaView extends Application {
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
	private Button btnAlterar;
	private Button btnExcluir;
	private Button btnCancelar;
	private Button btnEstoque;
	
	private TableView<EntradaModel> tabela;
	private static Stage stage;
	private EntradaController entradaController = new EntradaController();
	private EntradaModel entrada = new EntradaModel();
	List<LivroModel> livros = new ArrayList<LivroModel>();
	ObservableList<EntradaModel> itens = FXCollections.observableArrayList();
	private MensagemAlerta msgAlerta = new MensagemAlerta();
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
		stage.setTitle("Historico Entrada");
		stage.show();
		initLayout();
		HistoricoEntradaView.setStage(stage);

	}

	public static Stage getStage() {
		return stage;
	}

	private static void setStage(Stage stage) {
		HistoricoEntradaView.stage = stage;

	}
	
	private void initComponents() {
		painelPrincipal = new Pane();
	
		lblTituloPagina = new Label("Historico de Entrada");
		lblIdEntrada = new Label("Codigo de Venda");
		lblCodigo = new Label("Codigo do Livro");
		lblTitulo = new Label("Titulo");
		lblQtde = new Label("Quantidade");
		lblValorCompra = new Label("Valor Unitario");
		
		lblEstoqueImg  = new Label();
		lblSairImg = new Label();
		lblMenuImg = new Label();
		imgEstoque =  new Image(getClass().getResourceAsStream("/img/seta_esq.png"));
		imgSair = new Image(getClass().getResourceAsStream("/img/close.png"));
		imgMenu = new Image(getClass().getResourceAsStream("/img/home.png"));
		
		lblEstoqueImg.setGraphic(new ImageView(imgEstoque));
		lblMenuImg.setGraphic(new ImageView(imgMenu));
		lblSairImg.setGraphic(new ImageView(imgSair));
		
		
		txtIdEntrada = new TextField();
		txtCodigo = new TextField();
		txtTitulo = new TextField();
		txtQtde = new TextField();
		txtValorCompra = new TextField();
		btnAlterar	= new Button("Alterar");
		btnCancelar = new Button("Cancelar");
		btnExcluir =  new Button("Excluir");
		btnEstoque =  new Button("Estoque");
		
		tabela = new TableView<>();
		tabela.setItems(entradaController.preencherTabela());
        
		painelPrincipal.getChildren().addAll(lblTituloPagina, lblEstoqueImg, lblMenuImg, lblSairImg,lblIdEntrada, lblCodigo,lblTitulo,lblQtde,lblValorCompra);
		painelPrincipal.getChildren().addAll(txtIdEntrada,txtCodigo, txtTitulo, txtQtde,txtValorCompra);
		painelPrincipal.getChildren().addAll(btnAlterar,btnCancelar,btnExcluir, btnEstoque, tabela);
		
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
		

		btnAlterar.setLayoutX(20);
		btnAlterar.setLayoutY(330);
		btnAlterar.setPrefWidth(70);
		btnCancelar.setLayoutX(150);
		btnCancelar.setLayoutY(330);
		btnCancelar.setPrefWidth(70);
		btnExcluir.setLayoutX(280);
		btnExcluir.setLayoutY(330);
		btnExcluir.setPrefWidth(70);
		btnEstoque.setLayoutX(20);
		btnEstoque.setLayoutY(550);
		btnEstoque.setPrefWidth(100);
		
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
		
		tabela.setLayoutX(400);
		tabela.setLayoutY(75);
		tabela.setPrefSize(600, 500);
		
		lblTituloPagina.setStyle("-fx-font-size: 25pt;" + 
				"-fx-font-family: \"Segoe UI Semibold\";"+
			"-fx-text-fill: black; "+
			 "-fx-opacity: 0.8;");
		painelPrincipal.setStyle("-fx-background-color:#A9E2F3;");
		painelPrincipal.setStyle(lblBorda);
		tabela.setStyle(lblBorda);
		
	}

	private void initListeners() {
		
		lblEstoqueImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				if(msgAlerta.alertaConfirmacao("Voltar", "Voltar Para Estoque Livro", "Voce Deseja Voltar ao Estoque?")){
					try {
						
						new EstoqueView().start(new Stage());
						HistoricoEntradaView.stage.close();
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
				if(msgAlerta.alertaConfirmacao("Sair", "Sair do Sistema", "Voce Deseja Sair do Sistema?")){
					HistoricoEntradaView.stage.close();
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
						HistoricoEntradaView.stage.close();
					} catch (Exception e) {

						e.printStackTrace();
					}
				}
				

			}
		});
		
		btnAlterar.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				entradaController.alterarEntrada(operacao());
				limparCampos();
				desabilitarBotao();
				desabilitarCampos();
				JOptionPane.showMessageDialog(null, "Alterado");
				tabela.setItems(entradaController.preencherTabela());
			}

		});
		
		btnCancelar.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				limparCampos();
				desabilitarBotao();
				desabilitarCampos();
			}

		});
		
		btnExcluir.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				limparCampos();
				desabilitarBotao();
				desabilitarCampos();
				JOptionPane.showMessageDialog(null, "Excluido");
				tabela.setItems(entradaController.preencherTabela());
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
		TableColumn<EntradaModel, Integer> idEntrada = new TableColumn<>("Id Entrada");
		idEntrada.setMinWidth(50);
		idEntrada.setCellValueFactory(new PropertyValueFactory<>("idEntrada"));
		
		TableColumn<EntradaModel, Integer> codigo = new TableColumn<>("Cod Livro");
		codigo.setMinWidth(50);
		codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		
		TableColumn<EntradaModel, String> titulo = new TableColumn<>("Titulo");
		titulo.setMinWidth(200);
		titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		
		TableColumn<EntradaModel, Integer> qtde = new TableColumn<>("Quantidade");
		qtde.setMinWidth(100);
		qtde.setCellValueFactory(new PropertyValueFactory<>("qtde"));
	
		TableColumn<EntradaModel, Double> valorCompra = new TableColumn<>("Valor Compra");
		valorCompra.setMinWidth(100);
		valorCompra.setCellValueFactory(new PropertyValueFactory<>("valorCompra"));
		
		tabela.getColumns().addAll(idEntrada,codigo, titulo, qtde, valorCompra);
	}
	
	public EntradaModel operacao() {
		entrada.setIdEntrada(Integer.parseInt(txtIdEntrada.getText()));
		entrada.setCodigo(Integer.parseInt(txtCodigo.getText()));
		entrada.setTitulo(txtTitulo.getText());
		entrada.setQtde(Integer.parseInt(txtQtde.getText()));
		entrada.setValorCompra(Double.parseDouble(txtValorCompra.getText()));
		
		return entrada;
	}
	
	public void limparCampos() {
		txtIdEntrada.setText(null);
		txtCodigo.setText(null);
		txtTitulo.setText(null);
		txtQtde.setText(null);
		txtValorCompra.setText(null);

	}
	
	public void setCampos() {
		txtIdEntrada.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getCodigo()));
		txtCodigo.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getCodigo()));
		txtTitulo.setText(tabela.getSelectionModel().getSelectedItem().getTitulo());
		txtQtde.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getQtde()));
		txtValorCompra.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getValorCompra()));
	}
	
	public void habilitarCampos() {
		txtQtde.setDisable(false);
		txtValorCompra.setDisable(false);
	}
	
	public void desabilitarCampos() {
		txtIdEntrada.setDisable(true);
		txtCodigo.setDisable(true);
		txtTitulo.setDisable(true);
		txtQtde.setDisable(true);
		txtValorCompra.setDisable(true);
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
	
	public static void main(String[] args) {
		launch(args);
	}
}