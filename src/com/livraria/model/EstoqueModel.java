package com.livraria.model;

import java.time.LocalDate;

public class EstoqueModel {
	private int codInclusao;
	private int qtdProduto;
	private double precoVenda;
	private LocalDate dataInclusao;
	
	public int getCodInclusao() {
		return codInclusao;
	}
	public void setCodInclusao(int codInclusao) {
		this.codInclusao = codInclusao;
	}
	public int getQtdProduto() {
		return qtdProduto;
	}
	public void setQtdProduto(int qtdProduto) {
		this.qtdProduto = qtdProduto;
	}
	public double getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}
	public LocalDate getDataInclusao() {
		return dataInclusao;
	}
	public void setDataInclusao(LocalDate localDate) {
		this.dataInclusao = localDate;
	}
	
}
