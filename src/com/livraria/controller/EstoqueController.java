package com.livraria.controller;

import java.util.List;

import com.livraria.DAO.EstoqueDAO;
import com.livraria.model.EntradaModel;
import com.livraria.model.EstoqueModel;
import com.livraria.model.LivroModel;
import com.livraria.model.VendaModel;

import javafx.scene.control.TableView;

public class EstoqueController {
	private EstoqueDAO estoqueDAO = new EstoqueDAO();
	private EntradaController entradaController = new EntradaController();
	private VendaController vendaController = new VendaController();
	
	public boolean adicionarLivroEstoque (TableView<EntradaModel> entrada) {
		entradaController.registrarEntrada(entrada);
		return estoqueDAO.adicionarLivroEstoque(entrada);
	}
	
	public boolean subtrairLivroEstoque (TableView<VendaModel> venda) {
		vendaController.registrarVenda(venda);
		return estoqueDAO.subtrairLivroEstoque(venda);
	}
	
	public boolean alterarEntradaEstoque (EntradaModel entrada) {
		EntradaModel atual = entradaController.buscarEntrada(entrada);
		EntradaModel novo = new EntradaModel();
		novo.setIdEntrada(entrada.getIdEntrada());
		novo.setCodigo(entrada.getCodigo());
		novo.setTitulo(entrada.getTitulo());
		novo.setQtde((entrada.getQtde()) - (atual.getQtde()));
		novo.setValorCompra(entrada.getValorCompra());
		entradaController.alterarEntrada(entrada);
		return estoqueDAO.alterarEntradaEstoque(novo);
	} 
	
	public boolean alterarVendaEstoque (VendaModel venda) {
		VendaModel atual = vendaController.buscarVenda(venda);
		VendaModel novo = new VendaModel();
		novo.setIdVenda(venda.getIdVenda());
		novo.setCodigo(venda.getCodigo());
		novo.setTitulo(venda.getTitulo());
		novo.setQtde((venda.getQtde()) - (atual.getQtde()));
		novo.setValorVenda(venda.getValorVenda());
		vendaController.alterarVenda(venda);
		return estoqueDAO.alterarVendaEstoque(novo);
	}
	
	public boolean excluirEntradaEstoque (EntradaModel entrada) {
		entradaController.excluirEntrada(entrada);
		return estoqueDAO.excluirEntradaEstoque(entrada);
	} 
	
	public boolean excluirVendaEstoque (VendaModel venda) {
		vendaController.excluirVenda(venda);
		return estoqueDAO.excluirVendaEstoque(venda);
	}
	
	public boolean salvarLivroEstoque (LivroModel livro) {
		
		return estoqueDAO.salvarLivroEstoque(livro);
	}
	
	public boolean alterarLivroEstoque (LivroModel livro) {
		
		return estoqueDAO.alterarLivroEstoque(livro);
	}
	
	public boolean excluirLivroEstoque (LivroModel livro) {
		
		return estoqueDAO.excluirLivroEstoque(livro);
	}
	
	public List<EstoqueModel>  listarEstoque() {
		
		return estoqueDAO.listarEstoque();
	}
	
	public List<EstoqueModel>  buscarLivro(int codigo) {
		
		return estoqueDAO.buscarLivro(codigo);
	}
}
