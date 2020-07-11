package com.livraria.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.livraria.connection.Conexao;
import com.livraria.model.VendaModel;

import javafx.scene.control.TableView;

public class VendaDAO {
	private Connection conexao = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	private List<VendaModel> listaVenda = new ArrayList<>();

	public boolean registrarVenda(TableView<VendaModel> venda) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();

		try {
			String sql = "INSERT INTO tbl_venda  VALUES (?,?,?,?,?)";
			statement = conexao.prepareStatement(sql);

			for (VendaModel itens : venda.getItems()) {
				statement.setInt(1, itens.getIdVenda());
				statement.setInt(2, itens.getCodigo());
				statement.setString(3, itens.getTitulo());
				statement.setInt(4, itens.getQtde());
				statement.setDouble(5, itens.getValorVenda());

				statement.executeUpdate();
			}
			con.fechaConexao();
		} catch (SQLException Exception) {

		}

		return true;
	}

	public boolean alterarVenda(VendaModel venda) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();

		try {

			String sql = "UPDATE tbl_venda SET qtde = ?, valorVenda = ? WHERE (idVenda = ? and codigo = ?)";

			statement = conexao.prepareStatement(sql);
			statement.setInt(1, venda.getQtde());
			statement.setDouble(2, venda.getValorVenda());
			statement.setInt(3, venda.getIdVenda());
			statement.setInt(4, venda.getCodigo());

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

	public boolean excluirVenda(VendaModel venda) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();

		try {

			String sql = "DELETE FROM tbl_venda WHERE (codigo = ? and idVenda = ?)";

			statement = conexao.prepareStatement(sql);

			statement.setInt(1, venda.getCodigo());
			statement.setInt(2, venda.getIdVenda());

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

	public VendaModel buscarLivro(int codigo) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();
		VendaModel venda = null;
		try {

			String sql = "SELECT * FROM tbl_estoque WHERE codigo = '" + codigo + "'";
			statement = conexao.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				venda = new VendaModel();
				venda.setCodigo(resultSet.getInt("codigo"));
				venda.setTitulo(resultSet.getString("titulo"));
				venda.setValorVenda(resultSet.getDouble("valorVenda"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.fechaConexao();
		}
		return venda;
	}

	public int proximoId() {

		Conexao con = new Conexao();
		conexao = con.conectarBanco();
		int idVenda = 0;
		try {

			String sql = "SELECT idVenda FROM tbl_venda";
			statement = conexao.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				idVenda = (resultSet.getInt("idVenda")) + 1;
			}

			return idVenda;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.fechaConexao();

		}

		return 0;

	}

	public List<VendaModel> listarVenda() {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();
		VendaModel venda;
		try {

			String sql = "SELECT * FROM tbl_venda";
			statement = conexao.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				venda = new VendaModel();
				venda.setIdVenda(resultSet.getInt("idVenda"));
				venda.setCodigo(resultSet.getInt("codigo"));
				venda.setTitulo(resultSet.getString("titulo"));
				venda.setQtde(resultSet.getInt("qtde"));
				venda.setValorVenda(resultSet.getDouble("valorVenda"));

				listaVenda.add(venda);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.fechaConexao();
		}

		return listaVenda;

	}

	public VendaModel buscarVenda(VendaModel venda) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();
		try {
			String sql = "SELECT * FROM tbl_venda WHERE (idVenda = " + venda.getIdVenda() + " and codigo = "
					+ venda.getCodigo() + ")";
			statement = conexao.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				venda = new VendaModel();
				venda.setIdVenda(resultSet.getInt("idVenda"));
				venda.setCodigo(resultSet.getInt("codigo"));
				venda.setTitulo("titulo");
				venda.setQtde(resultSet.getInt("qtde"));
				venda.setValorVenda(resultSet.getDouble("valorVenda"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.fechaConexao();
		}
		return venda;
	}

}