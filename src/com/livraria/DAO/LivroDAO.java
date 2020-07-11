package com.livraria.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.livraria.connection.Conexao;
import com.livraria.model.LivroModel;

public class LivroDAO {
	private Connection conexao = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	private List<LivroModel> listaLivros = new ArrayList<>();

	public boolean incluirLivro(LivroModel livro) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();

		try {

			String sql = "INSERT INTO tbl_livro(codigo,titulo, edicao, autor, editora, genero)"
					+ "VALUES(?, ? ,? ,? ,?, ?)";

			statement = conexao.prepareStatement(sql);
			statement.setInt(1, livro.getCodigo());
			statement.setString(2, livro.getTitulo());
			statement.setInt(3, livro.getEdicao());
			statement.setString(4, livro.getAutor());
			statement.setString(5, livro.getEditora());
			statement.setString(6, livro.getGenero());

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

	public boolean alterarLivro(LivroModel livro) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();

		try {

			String sql = "UPDATE tbl_livro SET titulo = ?, edicao = ?, autor = ?, editora = ?, genero = ? WHERE codigo = ?";

			statement = conexao.prepareStatement(sql);
			statement.setString(1, livro.getTitulo());
			statement.setInt(2, livro.getEdicao());
			statement.setString(3, livro.getAutor());
			statement.setString(4, livro.getEditora());
			statement.setString(5, livro.getGenero());
			statement.setInt(6, livro.getCodigo());

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

	public boolean excluirLivro(LivroModel livro) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();

		try {

			String sql = "DELETE FROM tbl_livro WHERE codigo = ?";

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

	public List<LivroModel> listarLivros() {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();
		LivroModel livro;
		try {

			String sql = "SELECT * FROM tbl_livro";
			statement = conexao.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				livro = new LivroModel();
				livro.setCodigo(resultSet.getInt("codigo"));
				livro.setTitulo(resultSet.getString("titulo"));
				livro.setEdicao(resultSet.getInt("edicao"));
				livro.setAutor(resultSet.getString("autor"));
				livro.setEditora(resultSet.getString("editora"));
				livro.setGenero(resultSet.getString("genero"));
				listaLivros.add(livro);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.fechaConexao();
		}

		return listaLivros;

	}

	public int proximoCodigo() {

		Conexao con = new Conexao();
		conexao = con.conectarBanco();
		int codigo = 0;
		try {

			String sql = "SELECT codigo FROM tbl_livro";
			statement = conexao.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				codigo = (resultSet.getInt("codigo")) + 1;
			}

			return codigo;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.fechaConexao();

		}

		return 0;

	}

	public List<LivroModel> buscarLivroCodigo(int codigo) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();
		LivroModel livro;
		try {

			String sql = "SELECT * FROM tbl_livro WHERE codigo = '" + codigo + "'";
			statement = conexao.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				livro = new LivroModel();
				livro.setCodigo(resultSet.getInt("codigo"));
				livro.setTitulo(resultSet.getString("titulo"));
				livro.setEdicao(resultSet.getInt("edicao"));
				livro.setAutor(resultSet.getString("autor"));
				livro.setEditora(resultSet.getString("editora"));
				livro.setGenero(resultSet.getString("genero"));
				listaLivros.add(livro);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.fechaConexao();
		}
		return listaLivros;
	}

	public List<LivroModel> buscarLivroTitulo(String titulo) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();
		LivroModel livro;
		try {

			String sql = "SELECT * FROM tbl_livro WHERE titulo = '" + titulo + "'";
			statement = conexao.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				livro = new LivroModel();
				livro.setCodigo(resultSet.getInt("codigo"));
				livro.setTitulo(resultSet.getString("titulo"));
				livro.setEdicao(resultSet.getInt("edicao"));
				livro.setAutor(resultSet.getString("autor"));
				livro.setEditora(resultSet.getString("editora"));
				livro.setGenero(resultSet.getString("genero"));
				listaLivros.add(livro);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.fechaConexao();
		}
		return listaLivros;
	}
}
