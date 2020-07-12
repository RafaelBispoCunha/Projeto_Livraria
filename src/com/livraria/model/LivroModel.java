package com.livraria.model;


public class LivroModel {

	private int codigoLivro;
	private String titulo;
	private int edicao;
	private String autor;
	private String editora;
	private String genero;
	private String ISBN;
	
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
	public int getEdicao() {
		return edicao;
	}
	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	
	
}
