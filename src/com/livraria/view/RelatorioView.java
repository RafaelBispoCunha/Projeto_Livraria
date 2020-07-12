 package com.livraria.view;


import java.io.FileOutputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.livraria.connection.Conexao;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class RelatorioView extends Application {

	private Pane painelPrincipal;
	private static Stage stage;
	private Label lblTituloPagina;
	private Label lblSairImg;
	private Label lblMenuImg;
	private Label lblDataInicial;
	private Label lblDataFinal;
	private Label lblRelatorio;
	
	private Image imgSair;
	private Image imgMenu;
	private DatePicker dataInicial;
	private DatePicker dataFinal;
	private ComboBox opcaoRelatorio;
	private Button btnGerar;
	private Date data = new Date();
	LocalDate localDate = Instant.ofEpochMilli(data.getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
	
	private String lblBorda = "-fx-border-color: #848484;" + "-fx-border-width: 3;";
	
	public void start(Stage stage) throws Exception {
		
		initComponents();
		initListeners();

		Scene scene = new Scene(painelPrincipal, 1024, 600);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(scene);
		stage.setTitle("Relatorio");
		stage.show();
		initLayout();
		RelatorioView.setStage(stage);

	}

	public static Stage getStage() {
		return stage;
	}

	private static void setStage(Stage stage) {
		RelatorioView.stage = stage;

	}

	private void initComponents() {
		painelPrincipal = new Pane();
		
		lblTituloPagina = new Label("Relatorios");
		lblSairImg = new Label();
		lblMenuImg = new Label();
		lblDataInicial = new Label("Data Inicial");
		lblDataFinal = new Label("Data Final");
		lblRelatorio = new Label("Tipo de Relatorio");
		dataInicial = new DatePicker();
		dataFinal = new DatePicker();
		btnGerar = new Button("Gerar");
		
		imgSair = new Image(getClass().getResourceAsStream("/img/close.png"));
		imgMenu = new Image(getClass().getResourceAsStream("/img/home.png"));
		lblMenuImg.setGraphic(new ImageView(imgMenu));
		lblSairImg.setGraphic(new ImageView(imgSair));
		
		opcaoRelatorio = new ComboBox();
		opcaoRelatorio.getItems().addAll("Estoque", "Vendas", "Entradas");
		
		painelPrincipal.getChildren().addAll(lblTituloPagina, lblMenuImg, lblSairImg, lblDataInicial, lblDataFinal, lblRelatorio);
		painelPrincipal.getChildren().addAll(dataInicial, dataFinal, opcaoRelatorio, btnGerar);
	}

	private void initLayout() {
		lblTituloPagina.setLayoutX(430);
		lblTituloPagina.setLayoutY(10);
		
		lblMenuImg.setLayoutX(900);
		lblMenuImg.setLayoutY(10);
		lblSairImg.setLayoutX(950);
		lblSairImg.setLayoutY(10);

		lblDataInicial.setLayoutX(30);
		lblDataInicial.setLayoutY(200);
		lblDataFinal.setLayoutX(30);
		lblDataFinal.setLayoutY(250);
		lblRelatorio.setLayoutX(30);
		lblRelatorio.setLayoutY(305);
		
		dataInicial.setLayoutX(130);
		dataInicial.setLayoutY(195);
		dataInicial.setPrefWidth(150);
		dataInicial.setValue(localDate);
		
		dataFinal.setLayoutX(130);
		dataFinal.setLayoutY(245);
		dataFinal.setPrefWidth(150);
		dataFinal.setValue(localDate);
		
		opcaoRelatorio.setLayoutX(130);
		opcaoRelatorio.setLayoutY(300);
		opcaoRelatorio.setPrefWidth(150);
		opcaoRelatorio.getSelectionModel().selectFirst();
		btnGerar.setLayoutX(130);
		btnGerar.setLayoutY(350);
		btnGerar.setPrefWidth(150);
		
		lblTituloPagina.setStyle("-fx-font-size: 25pt;" + 
				"-fx-font-family: \"Segoe UI Semibold\";"+
			"-fx-text-fill: black; "+
			 "-fx-opacity: 0.8;");
		painelPrincipal.setStyle("-fx-background-color:#A9E2F3;");
		painelPrincipal.setStyle(lblBorda);
		

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
					RelatorioView.stage.close();
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
		
		btnGerar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				gerarRelatorio() ;
			}
		});
	}
	
	public void gerarRelatorio() {
		LocalDate localDate = LocalDate.now();
		Document doc = new Document();
		Connection conexao = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		PdfPTable table = null;
		String dtInicial = dataInicial.getValue().toString();
		String dtFinal =  dataFinal.getValue().toString();
		try {
			String  nomeRelatorio = "relatorio_" + opcaoRelatorio.getValue().toString() +"_"+ localDate;
			Conexao con = new Conexao();
			conexao = con.conectarBanco();
			PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Rafael\\eclipse-workspace\\Livraria\\src\\relatorios\\"+nomeRelatorio.toLowerCase()+".pdf"));
			
			doc.open();
			doc.addCreationDate();
			doc.add(new Paragraph("RELATORIO DE " + opcaoRelatorio.getValue().toString().toUpperCase() + "                                               Gerado: " + localDate.now()));
			doc.add(new Paragraph("Parametros"));
			doc.add(new Paragraph("Data de "  + dtInicial));
			doc.add(new Paragraph("ate " + dtFinal));
			doc.add(new Paragraph(" "));
		
			
		if(opcaoRelatorio.getValue().toString().equalsIgnoreCase("estoque")) {
			String SQL = "SELECT * FROM tbl_estoque WHERE dataInclusao BETWEEN ('"+ dtInicial+"') AND ('"+dtFinal+"') ";
			
			statement = conexao.prepareStatement(SQL);
			resultSet = statement.executeQuery();
			
			table = new PdfPTable(5);
			PdfPCell c1 = new PdfPCell(new Phrase("Cod Inclusao"));
			table.addCell(c1);
			

			c1 = new  PdfPCell(new Phrase("Quantidade"));
			table.addCell(c1);
			
			c1 = new  PdfPCell(new Phrase("Preco de Venda"));
			table.addCell(c1);
			
			c1 = new  PdfPCell(new Phrase("Data de Inclusao"));
			table.addCell(c1);
			table.setHeaderRows(1);
			
			while (resultSet.next()) {
				
				table.addCell(resultSet.getString("codInclusao"));
				table.addCell(resultSet.getString("quantidade"));
				table.addCell(resultSet.getString("precoVenda"));
				table.addCell(resultSet.getString("dataInclusao"));
				
				
			}
		}else if(opcaoRelatorio.getValue().toString().equalsIgnoreCase("vendas")) {
			String SQL = "SELECT * FROM tbl_vendas WHERE dataVenda BETWEEN ('"+ dtInicial+"') AND ('"+dtFinal+"') ";
			statement = conexao.prepareStatement(SQL);
			resultSet = statement.executeQuery();
			
			table = new PdfPTable(5);
			PdfPCell c1 = new PdfPCell(new Phrase("Id Entrada"));
			table.addCell(c1);
			
			c1 = new  PdfPCell(new Phrase("Codigo"));
			table.addCell(c1);
			
			c1 = new  PdfPCell(new Phrase("Titulo"));
			table.addCell(c1);
			
			c1 = new  PdfPCell(new Phrase("Quantidade"));
			table.addCell(c1);
			
			c1 = new  PdfPCell(new Phrase("Valor Compra"));
			table.addCell(c1);
			table.setHeaderRows(1);
			
			while (resultSet.next()) {
			
				table.addCell(resultSet.getString("idEntrada"));
				table.addCell(resultSet.getString("codigo"));
				table.addCell(resultSet.getString("titulo"));
				table.addCell(resultSet.getString("qtde"));
				table.addCell(resultSet.getString("ValorCompra"));
			
				
			}
		}else {
			
			String SQL = "SELECT * FROM tbl_entrada WHERE dataEntrada BETWEEN ('"+ dtInicial+"') AND ('"+dtFinal+"') ";
			statement = conexao.prepareStatement(SQL);
			resultSet = statement.executeQuery();
			
			table = new PdfPTable(6);
			PdfPCell c1 = new PdfPCell(new Phrase("Id Entrada"));
			table.addCell(c1);
			
			c1 = new  PdfPCell(new Phrase("Codigo"));
			table.addCell(c1);
			
			c1 = new  PdfPCell(new Phrase("Titulo"));
			table.addCell(c1);
			
			c1 = new  PdfPCell(new Phrase("Quantidade"));
			table.addCell(c1);
			
			c1 = new  PdfPCell(new Phrase("Valor Compra"));
			table.addCell(c1);
			
			c1 = new  PdfPCell(new Phrase("Data Entrada"));
			table.addCell(c1);
			table.setHeaderRows(1);
			
			while (resultSet.next()) {
			
				table.addCell(resultSet.getString("idEntrada"));
				table.addCell(resultSet.getString("codigo"));
				table.addCell(resultSet.getString("titulo"));
				table.addCell(resultSet.getString("qtde"));
				table.addCell(resultSet.getString("valorCompra"));
				table.addCell(resultSet.getString("dataEntrada"));
				
			}
			
		}
		
		doc.add(table);	
		doc.close();
		
		} catch (Exception e) {
			System.out.println("Error on DB connection");
			return;
		}
		
		
	}
		
	public static void main(String[] args) {
		launch(args);
	}

}