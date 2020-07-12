package com.livraria.model;

import java.util.List;

import javafx.collections.ObservableList;


public class EntradaModel {
	private int idEntrada;
	private int codigo;
	private String titulo;
	private int qtde;
	private double valorCompra;
	private List<LivroModel> itens;
	
	public int getIdEntrada() {
		return idEntrada;
	}
	public void setIdEntrada(int idEntrada) {
		this.idEntrada = idEntrada;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getQtde() {
		return qtde;
	}
	public void setQtde(int qtde) {
		this.qtde = qtde;
	}
	public double getValorCompra() {
		return valorCompra;
	}
	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}
	public List<LivroModel> getItens() {
		return itens;
	}
	public void setItens(List<LivroModel> itens) {
		this.itens = itens;
	}
	
		
}
	
