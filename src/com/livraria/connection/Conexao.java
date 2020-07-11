package com.livraria.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexao {

	private final String DRIVER = "com.mysql.cj.jdbc.Driver"; // "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/dblivraria?useTimezone=true&serverTimezone=UTC";
	private final String USER = "root";
	private final String PASS = "";
	private Connection conexao = null;

	public Conexao() {
		conectarBanco();
	}

	public Connection conectarBanco() {
		try {

			if (conexao != null) {
				return conexao;
			} else {

				Class.forName(DRIVER);

				conexao = DriverManager.getConnection(URL, USER, PASS);
				// conexao.setAutoCommit(false);

			}

		} catch (ClassNotFoundException erro) {
			JOptionPane.showMessageDialog(null, "Erro na Conexao com o Drive " + erro.toString());

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro na Conexao com o Banco " + erro.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conexao;
	}

	public Connection getConnection() {
		JOptionPane.showMessageDialog(null, "Drive" + conexao);
		return conexao;
	}

	public void fechaConexao() {
		if (conexao != null) {

			try {
				conexao.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	public void main(String[] args) {
		conectarBanco();
	}

}