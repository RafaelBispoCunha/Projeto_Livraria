package com.livraria.controller;

import java.util.List;

import com.livraria.DAO.LivroDAO;
import com.livraria.model.LivroModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LivroController {
	LivroDAO livroDAO = new LivroDAO();
	EstoqueController estoqueController = new EstoqueController();

	public boolean inlcuirLivro(LivroModel livro) {
		estoqueController.salvarLivroEstoque(livro);
		return livroDAO.incluirLivro(livro);
	}

	public boolean alterarLivro(LivroModel livro) {
		estoqueController.alterarLivroEstoque(livro);
		return livroDAO.alterarLivro(livro);
	}

	public boolean excluirLivro(LivroModel livro) {
		estoqueController.excluirLivroEstoque(livro);
		return livroDAO.excluirLivro(livro);
	}

	public List<LivroModel> listarLivros() {

		return livroDAO.listarLivros();
	}

	public ObservableList<LivroModel> preencherTabela() {
		ObservableList<LivroModel> livros = FXCollections.observableArrayList();

		LivroController livroMod = new LivroController();
		for (LivroModel livro : livroMod.listarLivros()) {
			livro.getCodigo();
			livro.getTitulo();
			livro.getEdicao();
			livro.getAutor();
			livro.getEditora();
			livro.getGenero();

			livros.addAll(livro);
		}

		return livros;

	}

	public int proximoCodigo() {
		return livroDAO.proximoCodigo();

	}

}