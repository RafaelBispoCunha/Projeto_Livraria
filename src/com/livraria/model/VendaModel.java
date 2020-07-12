package com.livraria.model;

import java.time.LocalDate;

import javafx.collections.ObservableList;

public class VendaModel {
	private int codigoVenda;
	private int codigoLivro;
	private String titulo;
	private int quantidade;
	private double valorTotal;
	private double valorVenda;
	private LocalDate data;
	private FuncionarioModel  vendedor;
	private LivroModel livro;
	
	public int getCodigoVenda() {
		return codigoVenda;
	}
	public void setCodigoVenda(int codigoVenda) {
		this.codigoVenda = codigoVenda;
	}
	public int getCodigoLivro() {
		return codigoLivro;
	}
	public void setCodigoLivro(int codigoLivro) {
		this.codigoLivro = codigoLivro;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public FuncionarioModel getVendedor() {
		return vendedor;
	}
	public void setVendedor(FuncionarioModel vendedor) {
		this.vendedor = vendedor;
	}
	public LivroModel getLivro() {
		return livro;
	}
	public void setLivro(LivroModel livro) {
		this.livro = livro;
	}
	
	
}