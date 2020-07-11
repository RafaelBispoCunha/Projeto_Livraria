package com.livraria.controller;

import java.util.List;

import com.livraria.DAO.VendaDAO;
import com.livraria.model.VendaModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class VendaController {
	private VendaDAO vendaDAO = new VendaDAO();

	public boolean registrarVenda(TableView<VendaModel> venda) {
		return vendaDAO.registrarVenda(venda);
	}

	public boolean alterarVenda(VendaModel venda) {
		return vendaDAO.alterarVenda(venda);
	}

	public boolean excluirVenda(VendaModel venda) {
		return vendaDAO.excluirVenda(venda);
	}

	public VendaModel buscarLivro(int codigo) {
		return vendaDAO.buscarLivro(codigo);
	}

	public int proximoId() {
		return vendaDAO.proximoId();
	}

	public VendaModel buscarVenda(VendaModel venda) {

		return vendaDAO.buscarVenda(venda);
	}

	public List<VendaModel> listarVenda() {

		return vendaDAO.listarVenda();
	}

	public ObservableList<VendaModel> preencherTabela() {
		ObservableList<VendaModel> vendas = FXCollections.observableArrayList();

		VendaController vendaMod = new VendaController();
		for (VendaModel venda : vendaMod.listarVenda()) {
			venda.getIdVenda();
			venda.getCodigo();
			venda.getTitulo();
			venda.getQtde();
			venda.getValorVenda();

			vendas.addAll(venda);
		}

		return vendas;

	}
}
