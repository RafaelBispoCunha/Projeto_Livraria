package com.livraria.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.livraria.connection.Conexao;
import com.livraria.model.EntradaModel;

import javafx.scene.control.TableView;

public class EntradaDAO {
	private Connection conexao = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	private List<EntradaModel> listaEntrada = new ArrayList<>();

	public boolean registrarEntrada(TableView<EntradaModel> entrada) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();

		try {
			String sql = "INSERT INTO tbl_entrada  VALUES (?,?,?,?,?)";
			statement = conexao.prepareStatement(sql);

			for (EntradaModel itens : entrada.getItems()) {
				statement.setInt(1, itens.getIdEntrada());
				statement.setInt(2, itens.getCodigo());
				statement.setString(3, itens.getTitulo());
				statement.setInt(4, itens.getQtde());
				statement.setDouble(5, itens.getValorCompra());

				statement.executeUpdate();
			}
			con.fechaConexao();
		} catch (SQLException Exception) {

		}

		return true;
	}

	public boolean alterarEntrada(EntradaModel entrada) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();

		try {

			String sql = "UPDATE tbl_entrada SET qtde = ?, valorCompra = ? WHERE (idEntrada = ? and codigo = ?)";

			statement = conexao.prepareStatement(sql);
			statement.setInt(1, entrada.getQtde());
			statement.setDouble(2, entrada.getValorCompra());
			statement.setInt(3, entrada.getIdEntrada());
			statement.setInt(4, entrada.getCodigo());

			statement.executeUpdate();
			statement.close();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.fechaConexao();
		}
		return false;
	}

	public EntradaModel buscarLivro(int codigo) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();
		EntradaModel entrada = null;
		try {

			String sql = "SELECT * FROM tbl_estoque WHERE codigo = '" + codigo + "'";
			statement = conexao.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				entrada = new EntradaModel();
				entrada.setCodigo(resultSet.getInt("codigo"));
				entrada.setTitulo(resultSet.getString("titulo"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.fechaConexao();
		}
		return entrada;
	}

	public boolean excluirEntrada(EntradaModel entrada) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();

		try {

			String sql = "DELETE FROM tbl_entrada WHERE (codigo = ? and idEntrada = ?)";

			statement = conexao.prepareStatement(sql);

			statement.setInt(1, entrada.getCodigo());
			statement.setInt(2, entrada.getIdEntrada());

			statement.executeUpdate();
			statement.close();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.fechaConexao();
		}
		return false;
	}

	public int proximoId() {

		Conexao con = new Conexao();
		conexao = con.conectarBanco();
		int idEntrada = 0;
		try {

			String sql = "SELECT idEntrada FROM tbl_entrada";
			statement = conexao.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				idEntrada = (resultSet.getInt("idEntrada")) + 1;
			}

			return idEntrada;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.fechaConexao();

		}

		return 0;

	}

	public List<EntradaModel> listarEntrada() {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();
		EntradaModel entrada;
		try {

			String sql = "SELECT * FROM tbl_entrada";
			statement = conexao.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				entrada = new EntradaModel();
				entrada.setIdEntrada(resultSet.getInt("idEntrada"));
				entrada.setCodigo(resultSet.getInt("codigo"));
				entrada.setTitulo(resultSet.getString("titulo"));
				entrada.setQtde(resultSet.getInt("qtde"));
				entrada.setValorCompra(resultSet.getInt("valorCompra"));

				listaEntrada.add(entrada);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.fechaConexao();
		}

		return listaEntrada;

	}

	public EntradaModel buscarEntrada(EntradaModel entrada) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();
		try {
			String sql = "SELECT * FROM tbl_entrada WHERE (idEntrada = " + entrada.getIdEntrada() + " and codigo = "
					+ entrada.getCodigo() + ")";
			statement = conexao.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				entrada = new EntradaModel();
				entrada.setIdEntrada(resultSet.getInt("idEntrada"));
				entrada.setCodigo(resultSet.getInt("codigo"));
				entrada.setQtde(resultSet.getInt("qtde"));
				entrada.setValorCompra(resultSet.getDouble("valorCompra"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.fechaConexao();
		}
		return entrada;
	}
}
