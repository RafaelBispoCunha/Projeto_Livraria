package com.livraria.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.livraria.connection.Conexao;
import com.livraria.model.EntradaModel;
import com.livraria.model.EstoqueModel;
import com.livraria.model.LivroModel;
import com.livraria.model.VendaModel;

import javafx.scene.control.TableView;

public class EstoqueDAO {
	private Connection conexao = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	private List<EstoqueModel> listaEstoque = new ArrayList<>();

	public boolean adicionarLivroEstoque(TableView<EntradaModel> entrada) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();

		try {
			String sql = "UPDATE tbl_estoque SET qtde = qtde + ?, valorCompra = ?, valorVenda = valorCompra * 1.5 WHERE codigo = ?";
			statement = conexao.prepareStatement(sql);

			for (EntradaModel itens : entrada.getItems()) {
				statement.setInt(1, itens.getQtde());
				statement.setDouble(2, itens.getValorCompra());
				statement.setInt(3, itens.getCodigo());
				statement.executeUpdate();
			}
			con.fechaConexao();
		} catch (SQLException Exception) {

		}

		return true;
	}

	public boolean subtrairLivroEstoque(TableView<VendaModel> venda) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();

		try {
			String sql = "UPDATE tbl_estoque SET qtde = qtde - ? WHERE codigo = ?";
			statement = conexao.prepareStatement(sql);

			for (VendaModel itens : venda.getItems()) {
				statement.setInt(1, itens.getQtde());
				statement.setInt(2, itens.getCodigo());
				statement.executeUpdate();
			}
			con.fechaConexao();
		} catch (SQLException Exception) {

		}

		return true;
	}

	public boolean alterarEntradaEstoque(EntradaModel entrada) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();

		try {
			String sql = "UPDATE tbl_estoque SET qtde = qtde + ? WHERE codigo = ?";
			statement = conexao.prepareStatement(sql);

			statement.setInt(1, entrada.getQtde());
			statement.setInt(2, entrada.getCodigo());
			statement.executeUpdate();

			con.fechaConexao();
		} catch (SQLException Exception) {

		}

		return true;
	}

	public boolean alterarVendaEstoque(VendaModel venda) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();

		try {
			String sql = "UPDATE tbl_estoque SET qtde = qtde - ? WHERE codigo = ?";
			statement = conexao.prepareStatement(sql);

			statement.setInt(1, venda.getQtde());
			statement.setInt(2, venda.getCodigo());
			statement.executeUpdate();

			con.fechaConexao();
		} catch (SQLException Exception) {

		}

		return true;
	}

	public boolean excluirEntradaEstoque(EntradaModel entrada) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();

		try {
			String sql = "UPDATE tbl_estoque SET qtde = qtde - ? WHERE codigo = ?";
			statement = conexao.prepareStatement(sql);

			statement.setInt(1, entrada.getQtde());
			statement.setInt(2, entrada.getCodigo());
			statement.executeUpdate();

			con.fechaConexao();
		} catch (SQLException Exception) {

		}

		return true;
	}

	public boolean excluirVendaEstoque(VendaModel venda) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();

		try {
			String sql = "UPDATE tbl_estoque SET qtde = qtde + ? WHERE codigo = ?";
			statement = conexao.prepareStatement(sql);

			statement.setInt(1, venda.getQtde());
			statement.setInt(2, venda.getCodigo());
			statement.executeUpdate();

			con.fechaConexao();
		} catch (SQLException Exception) {

		}

		return true;
	}

	public boolean salvarLivroEstoque(LivroModel livro) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();

		try {

			String sql = "INSERT INTO tbl_estoque(codigo,titulo, qtde, valorCompra, valorVenda)"
					+ "VALUES(?, ?, 0,0,0)";

			statement = conexao.prepareStatement(sql);
			statement.setInt(1, livro.getCodigo());
			statement.setString(2, livro.getTitulo());

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
	
	public boolean alterarLivroEstoque(LivroModel livro) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();

		try {

			String sql = "UPDATE tbl_estoque SET titulo = ? WHERE codigo = ?";

			statement = conexao.prepareStatement(sql);
			statement.setString(1, livro.getTitulo());
			statement.setInt(2, livro.getCodigo());

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
	
	public boolean excluirLivroEstoque(LivroModel livro) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();

		try {

			String sql = "DELETE FROM tbl_estoque WHERE codigo = ?";

			statement = conexao.prepareStatement(sql);

			statement.setInt(1, livro.getCodigo());

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

	public List<EstoqueModel> listarEstoque() {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();
		EstoqueModel livro;
		try {

			String sql = "SELECT * FROM tbl_estoque";
			statement = conexao.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				livro = new EstoqueModel();
				livro.setCodigo(resultSet.getInt("codigo"));
				livro.setTitulo(resultSet.getString("titulo"));
				livro.setQtde(resultSet.getInt("qtde"));
				livro.setValorCompra(resultSet.getDouble("valorCompra"));
				livro.setValorVenda(resultSet.getDouble("valorVenda"));
				listaEstoque.add(livro);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.fechaConexao();
		}

		return listaEstoque;

	}

	public List<EstoqueModel> buscarLivro(int codigo) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();
		EstoqueModel livro;
		try {

			String sql = "SELECT * FROM tbl_estoque WHERE codigo = '" + codigo + "'";
			statement = conexao.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				livro = new EstoqueModel();
				livro.setCodigo(resultSet.getInt("codigo"));
				livro.setTitulo(resultSet.getString("titulo"));
				livro.setQtde(resultSet.getInt("qtde"));
				livro.setValorVenda(resultSet.getDouble("valorVenda"));

				listaEstoque.add(livro);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.fechaConexao();
		}
		return listaEstoque;
	}
}
