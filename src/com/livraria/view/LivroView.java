package com.livraria.view;

import java.util.Optional;

import com.livraria.controller.LivroController;
import com.livraria.model.LivroModel;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LivroView extends Application {
	static Stage stage;
	private Pane painelPrincipal;

	private TextField txtCodigo;
	private TextField txtTitulo;
	private TextField txtEdicao;
	private TextField txtAutor;
	private TextField txtEditora;
	private TextField txtISBN;
	private TextField txtPesquisa;
	
	private Label lblTituloPagina;
	private Label lblCodigo;
	private Label lblTitulo;
	private Label lblEdicao;
	private Label lblAutor;
	private Label lblEditora;
	private Label lblISBN;
	private Label lblGenero;
	private Label lblSairImg;
	private Label lblMenuImg;

	private Image imgSair;
	private Image imgMenu;

	private ComboBox cmbGenero;
	private Button btnSalvar;
	private Button btnNovo;
	private Button btnAlterar;
	private Button btnCancelar;
	private Button btnExcluir;
	

	private boolean flagOperacao = false;
	private TableView<LivroModel> tabela;
	private LivroController livroController = new LivroController();
	private LivroModel livro = new LivroModel();
	private String lblBorda = "-fx-border-color: #848484;" + "-fx-border-width: 3;";
	@Override
	public void start(Stage stage) throws Exception {
		initComponents();
		initTable();
		initListeners();
		desabilitarCampos();
		desabilitarBotoes();
		Scene scene = new Scene(painelPrincipal, 1024, 600);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Livro");
		stage.show();

		initLayout();
		LivroView.setStage(stage);
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		LivroView.stage = stage;
	}

	private void initComponents() {
		painelPrincipal = new Pane();
		
		lblTituloPagina = new Label("Livro"); 
		txtCodigo = new TextField();
		txtTitulo = new TextField();
		txtEdicao = new TextField();
		txtAutor = new TextField();
		txtEditora = new TextField();
		txtISBN = new TextField();
		txtPesquisa = new TextField();

		
		lblCodigo = new Label("Codigo");
		lblTitulo = new Label("Titulo");
		lblEdicao = new Label("Ediçao");
		lblAutor = new Label("Autor");
		lblEditora = new Label("Editora");
		lblISBN = new Label("ISBN");
		lblGenero = new Label("Genero");

		lblSairImg = new Label();
		lblMenuImg = new Label();
		imgSair = new Image(getClass().getResourceAsStream("/img/close.png"));
		imgMenu = new Image(getClass().getResourceAsStream("/img/home.png"));
		lblMenuImg.setGraphic(new ImageView(imgMenu));
		lblSairImg.setGraphic(new ImageView(imgSair));

		btnSalvar = new Button("Salvar");
		btnNovo = new Button("Novo");
		btnAlterar = new Button("Alterar");
		btnCancelar = new Button("Cancelar");
		btnExcluir = new Button("Excluir");
		

		cmbGenero = new ComboBox();
		cmbGenero.getItems().addAll("Highest", "High", "Normal", "Low", "Lowest");

		tabela = new TableView<>();
		tabela.setItems(getLivros());

		painelPrincipal.getChildren().addAll(lblTituloPagina, lblMenuImg, lblSairImg, lblCodigo, lblTitulo, lblEdicao,lblAutor, lblEditora, lblISBN, lblGenero);
		painelPrincipal.getChildren().addAll(txtCodigo, txtTitulo, txtEdicao, txtAutor, txtEditora,txtISBN);
		painelPrincipal.getChildren().addAll(btnSalvar, btnNovo, btnAlterar, btnCancelar, btnExcluir);
		painelPrincipal.getChildren().addAll(cmbGenero,  tabela);
	}

	private void initLayout() {
		lblTituloPagina.setLayoutX(470);
		lblTituloPagina.setLayoutY(10);

		lblMenuImg.setLayoutX(900);
		lblMenuImg.setLayoutY(10);
		lblSairImg.setLayoutX(950);
		lblSairImg.setLayoutY(10);
		
		lblCodigo.setLayoutX(20);
		lblCodigo.setLayoutY(80);
		lblTitulo.setLayoutX(20);
		lblTitulo.setLayoutY(130);
		lblEdicao.setLayoutX(20);
		lblEdicao.setLayoutY(180);
		lblAutor.setLayoutX(20);
		lblAutor.setLayoutY(230);
		lblEditora.setLayoutX(500);
		lblEditora.setLayoutY(80);
		lblISBN.setLayoutX(500);
		lblISBN.setLayoutY(130);
		lblGenero.setLayoutX(500);
		lblGenero.setLayoutY(180);

		txtPesquisa.setLayoutX(670);
		txtPesquisa.setLayoutY(20);
		txtPesquisa.setPrefWidth(200);

		txtCodigo.setLayoutX(100);
		txtCodigo.setLayoutY(75);
		txtCodigo.setPrefWidth(300);
		txtTitulo.setLayoutX(100);
		txtTitulo.setLayoutY(125);
		txtTitulo.setPrefWidth(300);
		txtEdicao.setLayoutX(100);
		txtEdicao.setLayoutY(175);
		txtEdicao.setPrefWidth(300);
		txtAutor.setLayoutX(100);
		txtAutor.setLayoutY(225);
		txtAutor.setPrefWidth(300);
		txtEditora.setLayoutX(600);
		txtEditora.setLayoutY(75);
		txtEditora.setPrefWidth(300);
		txtISBN.setLayoutX(600);
		txtISBN.setLayoutY(125);
		txtISBN.setPrefWidth(300);

		cmbGenero.setLayoutX(600);
		cmbGenero.setLayoutY(175);
		cmbGenero.setPrefWidth(300);

		btnSalvar.setLayoutX(100);
		btnSalvar.setLayoutY(565);
		btnSalvar.setPrefWidth(100);

		btnNovo.setLayoutX(300);
		btnNovo.setLayoutY(565);
		btnNovo.setPrefWidth(100);

		btnAlterar.setLayoutX(500);
		btnAlterar.setLayoutY(565);
		btnAlterar.setPrefWidth(100);

		btnCancelar.setLayoutX(700);
		btnCancelar.setLayoutY(565);
		btnCancelar.setPrefWidth(100);

		btnExcluir.setLayoutX(900);
		btnExcluir.setLayoutY(565);
		btnExcluir.setPrefWidth(100);

		tabela.setLayoutX(20);
		tabela.setLayoutY(300);
		tabela.setPrefSize(990, 200);
		
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

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Sair");
				alert.setHeaderText("Sair do Sistema");
				alert.setContentText("Voce Deseja Sair?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
				
					LivroView.stage.close();
					 
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
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Menu");
				alert.setHeaderText("Menu Inicial");
				alert.setContentText("Voce Deseja Ir ao Menu?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
				
					try {
						new TelaPrincipalView().start(new Stage());
						LivroView.stage.close();
					} catch (Exception e) {

						e.printStackTrace();
					}
					 
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
		btnSalvar.setOnMouseClicked(new EventHandler<MouseEvent>() {
			Alert alert = new Alert(AlertType.INFORMATION);

			@Override
			public void handle(MouseEvent event) {

				if (flagOperacao) {
					if (livroController.alterarlivro(operacao())) {

						alert.setTitle("Alterar");
						alert.setHeaderText(null);
						alert.setContentText("Alterado com Sucesso");

						alert.showAndWait();

					}
				} else {
					if(txtTitulo.getText().trim().isEmpty()) {
						alert.setTitle("Afgs");
						alert.setHeaderText(null);
						alert.setContentText("Afs");

						alert.showAndWait();
					}else {
						if (livroController.salvarlivro(operacao())) {
							alert.setTitle("Salvar");
							alert.setHeaderText(null);
							alert.setContentText("Salvo com Sucesso");

							alert.showAndWait();
						}
					}
					
				}
				desabilitarBotoes();
				limparCampos();
				btnSalvar.setDisable(false);
				tabela.setItems(getLivros());

			}
		});

		btnNovo.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				flagOperacao = false;
				habilitarCampos();
				desabilitarBotoes();
				limparCampos();
				btnCancelar.setDisable(false);
				btnSalvar.setDisable(false);
				btnNovo.setDisable(true);

			}
		});

		btnAlterar.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				desabilitarBotoes();
				btnCancelar.setDisable(false);
				btnSalvar.setDisable(false);
				btnNovo.setDisable(true);
				flagOperacao = true;

				habilitarCampos();

			}
		});

		btnCancelar.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				desabilitarBotoes();
				btnNovo.setDisable(false);
				limparCampos();
				desabilitarCampos();

			}
		});

		btnExcluir.setOnMouseClicked(new EventHandler<MouseEvent>() {
			Alert alert;
			@Override
			public void handle(MouseEvent event) {
				

				alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Excluir");
				alert.setHeaderText("Excluir Livro");
				alert.setContentText("Voce Deseja Excluir?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
				
					flagOperacao = true;
					livroController.excluirlivro(operacao());
					limparCampos();
					tabela.setItems(getLivros());
					 
				}
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Excluir");
				alert.setHeaderText(null);
				alert.setContentText("Excluido com Sucesso");

				alert.showAndWait();
				
				

			}
		});

		tabela.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				setCampos();
				desabilitarCampos();
				desabilitarBotoes();
				btnAlterar.setDisable(false);
				btnExcluir.setDisable(false);

			}
		});

	}
	
	public void salvar() {
		
	}

	private void initTable() {
		TableColumn<LivroModel, Integer> codigo = new TableColumn<>("Codigo");
		codigo.setMinWidth(50);
		codigo.setCellValueFactory(new PropertyValueFactory<>("codigoLivro"));

		TableColumn<LivroModel, String> titulo = new TableColumn<>("Titudo");
		titulo.setMinWidth(100);
		titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));

		TableColumn<LivroModel, Integer> edicao = new TableColumn<>("Edicao");
		edicao.setMinWidth(100);
		edicao.setCellValueFactory(new PropertyValueFactory<>("edicao"));

		TableColumn<LivroModel, String> autor = new TableColumn<>("Autor");
		autor.setMinWidth(100);
		autor.setCellValueFactory(new PropertyValueFactory<>("autor"));

		TableColumn<LivroModel, String> editora = new TableColumn<>("Editora");
		editora.setMinWidth(100);
		editora.setCellValueFactory(new PropertyValueFactory<>("editora"));

		TableColumn<LivroModel, String> ISBN = new TableColumn<>("ISBN");
		ISBN.setMinWidth(100);
		ISBN.setCellValueFactory(new PropertyValueFactory<>("ISBN"));

		TableColumn<LivroModel, String> genero = new TableColumn<>("Genero");
		genero.setMinWidth(100);
		genero.setCellValueFactory(new PropertyValueFactory<>("genero"));

		tabela.getColumns().addAll(codigo, titulo, edicao, autor, editora, ISBN, genero);
	}

	public ObservableList<LivroModel> getLivros() {
		ObservableList<LivroModel> livros = FXCollections.observableArrayList();

		LivroController livroMod = new LivroController();
		for (LivroModel livro : livroMod.listaLivros()) {
			livro.getCodigoLivro();
			livro.getTitulo();
			livro.getEdicao();
			livro.getAutor();
			livro.getEditora();
			livro.getISBN();
			livro.getGenero();

			livros.addAll(livro);
		}

		return livros;

	}

	public LivroModel operacao() {

		livro.setTitulo(txtTitulo.getText());
		livro.setEdicao(Integer.parseInt(txtEdicao.getText()));
		livro.setAutor(txtAutor.getText());
		livro.setEditora(txtEditora.getText());
		livro.setISBN(txtISBN.getText());
		livro.setGenero(cmbGenero.getValue().toString());

		if (flagOperacao) {
			livro.setCodigoLivro(Integer.parseInt(txtCodigo.getText()));
		}

		return livro;
	}

	public void habilitarCampos() {
		txtCodigo.setDisable(true);
		txtTitulo.setDisable(false);
		txtEdicao.setDisable(false);
		txtAutor.setDisable(false);
		txtEditora.setDisable(false);
		txtISBN.setDisable(false);
		cmbGenero.setDisable(false);

	}

	public void desabilitarCampos() {
		txtCodigo.setDisable(true);
		txtTitulo.setDisable(true);
		txtEdicao.setDisable(true);
		txtAutor.setDisable(true);
		txtEditora.setDisable(true);
		txtISBN.setDisable(true);
		cmbGenero.setDisable(true);

	}

	public void habilitarBotoes() {
		btnSalvar.setDisable(false);
		btnAlterar.setDisable(false);
		btnCancelar.setDisable(false);
		btnExcluir.setDisable(false);
		btnNovo.setDisable(false);

	}

	public void desabilitarBotoes() {
		btnSalvar.setDisable(true);
		btnAlterar.setDisable(true);
		btnCancelar.setDisable(true);
		btnExcluir.setDisable(true);

	}

	public void limparCampos() {
		txtCodigo.setText(null);
		txtTitulo.setText(null);
		txtEdicao.setText(null);
		txtAutor.setText(null);
		txtEditora.setText(null);
		txtISBN.setText(null);

	}

	public void setCampos() {
		txtCodigo.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getCodigoLivro()));
		txtTitulo.setText(tabela.getSelectionModel().getSelectedItem().getTitulo());
		txtEdicao.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getEdicao()));
		txtAutor.setText(tabela.getSelectionModel().getSelectedItem().getAutor());
		txtEditora.setText(tabela.getSelectionModel().getSelectedItem().getEditora());
		txtISBN.setText(tabela.getSelectionModel().getSelectedItem().getISBN());
		cmbGenero.setValue(tabela.getSelectionModel().getSelectedItem().getGenero());
	}

	public static void main(String[] args) {
		launch(args);
	}

}
