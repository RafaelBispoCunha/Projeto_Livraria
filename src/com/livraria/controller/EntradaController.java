package com.livraria.controller;

import java.util.List;

import com.livraria.DAO.EntradaDAO;
import com.livraria.model.EntradaModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class EntradaController {
	private EntradaDAO entradaDAO = new EntradaDAO();


	public boolean registrarEntrada(TableView<EntradaModel> entrada) {
		return entradaDAO.registrarEntrada(entrada);
	}

	public boolean alterarEntrada(EntradaModel entrada) {
		return entradaDAO.alterarEntrada(entrada);
	}

	public boolean excluirEntrada(EntradaModel entrada) {
		return entradaDAO.excluirEntrada(entrada);
	}

	public EntradaModel buscarLivro(int codigo) {
		return entradaDAO.buscarLivro(codigo);
	}

	public int proximoId() {
		return entradaDAO.proximoId();
	}

	public List<EntradaModel> listarEntrada() {

		return entradaDAO.listarEntrada();
	}

	public EntradaModel buscarEntrada(EntradaModel entrada) {

		return entradaDAO.buscarEntrada(entrada);
	}

	public ObservableList<EntradaModel> preencherTabela() {
		ObservableList<EntradaModel> entradas = FXCollections.observableArrayList();

		EntradaController entradaMod = new EntradaController();
		for (EntradaModel entrada : entradaMod.listarEntrada()) {
			entrada.getIdEntrada();
			entrada.getCodigo();
			entrada.getTitulo();
			entrada.getQtde();
			entrada.getValorCompra();

			entradas.addAll(entrada);
		}

		return entradas;

	}
}
