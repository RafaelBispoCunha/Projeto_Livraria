package com.livraria.view;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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

import com.livraria.controller.FuncionarioController;
import com.livraria.model.FuncionarioModel;
import com.livraria.utility.*;

public class FuncionarioView extends Application {
	private static Stage stage;
	private Pane painelPrincipal;
	
	private TextField txtCodigo;
	private TextField txtNome;
	private TextField txtCPF;
	private TextField txtEmail;
	private PasswordField txtSenha;
	private PasswordField txtSenha2;
	private TextField txtPesquisa;

	private Label lblTituloPagina;
	private Label lblCodigo;
	private Label lblCPF;
	private Label lblNome;
	private Label lblEmail;
	private Label lblSenha;
	private Label lblSenha2;
	private Label lblnivelAcesso;
	private Label lblSairImg;
	private Label lblMenuImg;
	private Image imgSair;
	private Image imgMenu;

	private ComboBox nivelAcesso;
	private Button btnSalvar;
	private Button btnNovo;
	private Button btnAlterar;
	private Button btnCancelar;
	private Button btnExcluir;

	private boolean flagOperacao = false;
	private TableView<FuncionarioModel> tabela;

	private FuncionarioModel funcionario;
	private FuncionarioController funcionarioController;
	private MensagemAlerta msgAlerta = new MensagemAlerta();
	private String lblBorda = "-fx-border-color: #848484;" + "-fx-border-width: 3;";
	@Override
	public void start(Stage stage) throws Exception {
		initComponents();
		initTable();
		desabilitarCampos();
		desabilitarBotoes();
		initListeners();
		validaCampos();
		Scene scene = new Scene(painelPrincipal, 1024, 600);
		
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);

		stage.setTitle("Funcionario");
		stage.show();

		initLayout();
		FuncionarioView.setStage(stage);
	}

	private void validaCampos() {
		ValidaCampos.limitarTamanhoCampo(txtNome, 30);
		ValidaCampos.limitarTamanhoCampo(txtEmail, 30);
		ValidaCampos.limitarTamanhoCampo(txtCPF, 11);
		ValidaCampos.limitarTamanhoCampo(txtSenha, 12);
		ValidaCampos.limitarTamanhoCampo(txtSenha2, 12);
	
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		FuncionarioView.stage = stage;
	}

	private void initComponents() {
		painelPrincipal = new Pane();
		funcionario = new FuncionarioModel();
		funcionarioController = new FuncionarioController();

		txtCPF = new TextField();
		txtCodigo = new TextField();
		txtNome = new TextField();
		txtEmail = new TextField();
		txtSenha = new PasswordField();
		txtSenha2 = new PasswordField();
		txtPesquisa = new TextField();
		
		
		lblTituloPagina = new Label("Funcionario");
		lblCodigo = new Label("Codigo");
		lblCPF = new Label("CPF");
		lblNome = new Label("Nome");
		lblEmail = new Label("E-mail");
		lblSenha = new Label("Senha");
		lblSenha2 = new Label("Repita Senha");
		lblnivelAcesso = new Label("Nivel Acesso");
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

		nivelAcesso = new ComboBox();
		nivelAcesso.getItems().addAll("Caixa", "Gerente", "Vendedor");

		tabela = new TableView<>();
		tabela.setItems(getFuncionarios());

		painelPrincipal.getChildren().addAll(lblTituloPagina, lblMenuImg, lblSairImg, lblCodigo, lblNome, lblCPF,
				lblEmail, lblSenha, lblSenha2, lblnivelAcesso);

		painelPrincipal.getChildren().addAll(txtCodigo, txtNome, txtCPF, txtEmail);
		painelPrincipal.getChildren().addAll(btnSalvar, btnNovo, btnAlterar, btnCancelar, btnExcluir);
		painelPrincipal.getChildren().addAll(txtSenha, txtSenha2, nivelAcesso, tabela);
	}

	private void initLayout() {
		

		lblTituloPagina.setLayoutX(430);
		lblTituloPagina.setLayoutY(10);

		lblMenuImg.setLayoutX(900);
		lblMenuImg.setLayoutY(10);
		lblSairImg.setLayoutX(950);
		lblSairImg.setLayoutY(10);

		lblCodigo.setLayoutX(20);
		lblCodigo.setLayoutY(80);
		lblNome.setLayoutX(400);
		lblNome.setLayoutY(80);
		lblCPF.setLayoutX(20);
		lblCPF.setLayoutY(130);
		lblEmail.setLayoutX(400);
		lblEmail.setLayoutY(130);
		lblSenha.setLayoutX(20);
		lblSenha.setLayoutY(180);
		lblSenha2.setLayoutX(20);
		lblSenha2.setLayoutY(230);
		lblnivelAcesso.setLayoutX(400);
		lblnivelAcesso.setLayoutY(180);

		txtPesquisa.setLayoutX(670);
		txtPesquisa.setLayoutY(20);
		txtPesquisa.setPrefWidth(200);

		txtCodigo.setLayoutX(100);
		txtCodigo.setLayoutY(75);
		txtCodigo.setPrefWidth(200);
		txtNome.setLayoutX(475);
		txtNome.setLayoutY(75);
		txtNome.setPrefWidth(400);
		
		
		txtCPF.setLayoutX(100);
		txtCPF.setLayoutY(125);
		txtCPF.setPrefWidth(200);
		
		txtEmail.setLayoutX(475);
		txtEmail.setLayoutY(125);
		txtEmail.setPrefWidth(400);
		txtSenha.setLayoutX(100);
		txtSenha.setLayoutY(175);
		txtSenha.setPrefWidth(250);
		txtSenha2.setLayoutX(100);
		txtSenha2.setLayoutY(225);
		txtSenha2.setPrefWidth(250);
		nivelAcesso.setLayoutX(475);
		nivelAcesso.setLayoutY(175);
		nivelAcesso.setPrefWidth(200);
		nivelAcesso.getSelectionModel().selectFirst();

		tabela.setLayoutX(20);
		tabela.setLayoutY(300);
		tabela.setPrefSize(990, 250);

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
				
				if (msgAlerta.alertaConfirmacao("Sair", "Sair do Sistema", "Voce Deseja Sair?" )) {

					FuncionarioView.stage.close();

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

				if (msgAlerta.alertaConfirmacao("Menu", "Menu Inicial","Voce Deseja Ir ao Menu?" )) {

					try {
						new TelaPrincipalView().start(new Stage());
						FuncionarioView.stage.close();
					} catch (Exception e) {

						e.printStackTrace();
					}

				}

			}
		});

		

		btnSalvar.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (flagOperacao) {
					alterar();

				} else {

					salvar();
					bordaCampos(null);
				}

				tabela.setItems(getFuncionarios());

			}
		});

		btnNovo.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				flagOperacao = false;
				habilitarCampos();
				desabilitarBotoes();
				btnCancelar.setDisable(false);
				btnSalvar.setDisable(false);
				btnNovo.setDisable(true);
				limparCampos();

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
				bordaCampos(null);
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

				if (msgAlerta.alertaConfirmacao("Excluir", "Excluir Livro", "Voce Deseja Excluir?")) {
					flagOperacao = true;
					funcionarioController.excluirFuncionario(operacao());
					limparCampos();
					tabela.setItems(getFuncionarios());
				}

				msgAlerta.alertaInformativo("Excluir", null, "Excluido com Sucesso");

			}
		});

		tabela.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bordaCampos(null);
				setCampos();
				desabilitarCampos();
				desabilitarBotoes();
				btnAlterar.setDisable(false);
				btnExcluir.setDisable(false);

			}
		});

	}

	private void initTable() {
		TableColumn<FuncionarioModel, Integer> idColuna = new TableColumn<>("Codigo");
		idColuna.setMinWidth(50);
		idColuna.setCellValueFactory(new PropertyValueFactory<>("codigoFuncionario"));

		TableColumn<FuncionarioModel, String> nomeColuna = new TableColumn<>("Nome");
		nomeColuna.setMinWidth(200);
		nomeColuna.setCellValueFactory(new PropertyValueFactory<>("nomeFuncionario"));

		TableColumn<FuncionarioModel, Long> cpf = new TableColumn<>("CPF");
		cpf.setMinWidth(100);
		cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

		TableColumn<FuncionarioModel, String> email = new TableColumn<>("E-mail");
		email.setMinWidth(200);
		email.setCellValueFactory(new PropertyValueFactory<>("emailFuncionario"));

		TableColumn<FuncionarioModel, String> senha = new TableColumn<>("Senha");
		senha.setMinWidth(100);
		senha.setCellValueFactory(new PropertyValueFactory<>("senha"));

		TableColumn<FuncionarioModel, String> nivelAcesso = new TableColumn<>("Nivel Acesso");
		nivelAcesso.setMinWidth(100);
		nivelAcesso.setCellValueFactory(new PropertyValueFactory<>("nivelAcesso"));

		tabela.getColumns().addAll(idColuna, nomeColuna, cpf, email, senha, nivelAcesso);
	}

	public ObservableList<FuncionarioModel> getFuncionarios() {
		ObservableList<FuncionarioModel> funcionarios = FXCollections.observableArrayList();

		FuncionarioController funcionarioMod = new FuncionarioController();
		for (FuncionarioModel f : funcionarioMod.listaFuncionario()) {
			f.getCodigoFuncionario();
			f.getNomeFuncionario();
			f.getCpf();
			f.getEmailFuncionario();
			f.getSenha();
			f.getNivelAcesso();

			funcionarios.add(f);
		}

		return funcionarios;

	}

	public FuncionarioModel operacao() {

		funcionario.setNomeFuncionario(txtNome.getText());
		funcionario.setCpf(Long.parseLong(txtCPF.getText()));
		funcionario.setEmailFuncionario(txtEmail.getText());
		funcionario.setNivelAcesso(nivelAcesso.getValue().toString());
		funcionario.setSenha(txtSenha.getText());

		if (flagOperacao) {
			funcionario.setCodigoFuncionario(Integer.parseInt(txtCodigo.getText()));
		}

		return funcionario;
	}

	public void habilitarCampos() {
		txtCodigo.setDisable(true);
		txtNome.setDisable(false);
		txtCPF.setDisable(false);
		txtEmail.setDisable(false);
		txtSenha.setDisable(false);
		txtSenha2.setDisable(false);
		nivelAcesso.setDisable(false);
	}

	public void desabilitarCampos() {
		txtCodigo.setDisable(true);
		txtNome.setDisable(true);
		txtCPF.setDisable(true);
		txtEmail.setDisable(true);
		txtSenha.setDisable(true);
		txtSenha2.setDisable(true);
		nivelAcesso.setDisable(true);

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
		txtNome.setText(null);
		txtCPF.setText(null);
		txtEmail.setText(null);
		txtSenha.setText(null);
		txtSenha2.setText(null);

	}

	public void setCampos() {
		txtCodigo.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getCodigoFuncionario()));
		txtNome.setText(tabela.getSelectionModel().getSelectedItem().getNomeFuncionario());
		txtCPF.setText(String.valueOf(tabela.getSelectionModel().getSelectedItem().getCpf()));
		txtEmail.setText(tabela.getSelectionModel().getSelectedItem().getEmailFuncionario());
		txtSenha.setText(tabela.getSelectionModel().getSelectedItem().getSenha());
		txtSenha2.setText(tabela.getSelectionModel().getSelectedItem().getSenha());

		nivelAcesso.setValue(tabela.getSelectionModel().getSelectedItem().getNivelAcesso());
	}

	// VALIDACAO
	public void alterar() {

		if (campoVazio()) {
			bordaCampos("-fx-border-color: red;");
			msgAlerta.alertaInformativo("Alterar", null, "Campos Obrigatorios Vazios");

		} else {
			if (verificaSenha()) {
				if (funcionarioController.alterarFuncionario(operacao())) {

					desabilitarBotoes();
					limparCampos();
					desabilitarCampos();
					btnNovo.setDisable(false);
					msgAlerta.alertaInformativo("Alterar", null, "Alterado com Sucesso");

				} else {
					msgAlerta.alertaInformativo("Alterar", null, "Alteracao Falhou");

				}
			} else {
				msgAlerta.alertaInformativo("Senha", null, "Senhas Diferentes");
			}

		}

	}

	public void salvar() {

		if (campoVazio()) {
			bordaCampos("-fx-border-color: red;");
			msgAlerta.alertaInformativo("Salvar", null, "Campos Obrigatorios Vazios");
		}else if(!ValidaCampos.validaEmail(txtEmail.getText())) {
			msgAlerta.alertaInformativo("Salvar", null, "Email Invalido");
			
		} else {
			if (verificaSenha()) {
				if (funcionarioController.salvarFuncionario(operacao())) {

					desabilitarBotoes();
					btnSalvar.setDisable(false);
					limparCampos();
					msgAlerta.alertaInformativo("Salvar", null, "Salvo com Sucesso");

				} else {

					msgAlerta.alertaInformativo("Salvar", null, "Erro ao Salvar");
				}
			} else {

				msgAlerta.alertaInformativo("Salvar", null, "Senha Estao Diferentes");

			}

		}
	}

	public boolean verificaSenha() {
		return txtSenha.getText().equals(txtSenha2.getText());
	}

	public boolean campoVazio() {
		return txtNome.getText() == null || txtNome.getText().trim().isEmpty() || txtCPF.getText() == null
				|| txtCPF.getText().trim().isEmpty() || txtEmail.getText() == null
				|| txtEmail.getText().trim().isEmpty() || txtSenha.getText() == null
				|| txtSenha.getText().trim().isEmpty() || txtSenha2.getText() == null
				|| txtSenha2.getText().trim().isEmpty();

	}

	public void bordaCampos(String cor) {
		txtNome.setStyle(cor);
		txtCPF.setStyle(cor);
		txtEmail.setStyle(cor);
		txtSenha.setStyle(cor);
		txtSenha2.setStyle(cor);

	}

	public static void main(String[] args) {
		launch(args);
	}

}
