package com.livraria.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.livraria.connection.Conexao;
import com.livraria.model.FuncionarioModel;

public class FuncionarioDAO {
	private Connection conexao = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	private List<FuncionarioModel> listaFuncionarios = new ArrayList<>();

	public boolean salvarFuncionario(FuncionarioModel funcionario) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();

		try {

			String sql = "INSERT INTO tbl_funcionario( "
					+ "nome, cpf, email, senha, nivelAcesso)"
					+ "VALUES(?, ? ,? ,? ,?)";

			statement = conexao.prepareStatement(sql);
			statement.setString(1, funcionario.getNome());
			statement.setLong(2, funcionario.getCpf());
			statement.setString(3, funcionario.getEmail());
			statement.setString(4, funcionario.getSenha());
			statement.setString(5, funcionario.getNivelAcesso());

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

	public boolean alterarFuncionario(FuncionarioModel funcionario) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();

		try {

			String sql = "UPDATE tbl_funcionario SET nome = ?, cpf = ?, email = ?, "
					 + "senha = ?,  nivelAcesso = ? WHERE idFuncionario = ?";

			statement = conexao.prepareStatement(sql);
			statement.setString(1, funcionario.getNome());
			statement.setLong(2, funcionario.getCpf());
			statement.setString(3, funcionario.getEmail());
			statement.setString(4, funcionario.getSenha());
			statement.setString(5, funcionario.getNivelAcesso());
			statement.setInt(6, funcionario.getIdFuncionario());

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

	public boolean excluirFuncionario(FuncionarioModel funcionario) {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();

		try {

			String sql = "DELETE FROM tbl_funcionario WHERE idFuncionario = ?";

			statement = conexao.prepareStatement(sql);

			statement.setInt(1, funcionario.getIdFuncionario());

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

	public List<FuncionarioModel> listaFuncionario() {
		Conexao con = new Conexao();
		conexao = con.conectarBanco();
		FuncionarioModel funcionario;
		try {

			String sql = "SELECT * FROM tbl_funcionario";
			statement = conexao.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				funcionario = new FuncionarioModel();
				funcionario.setIdFuncionario(resultSet.getInt("idFuncionario"));
				funcionario.setNome(resultSet.getString("nome"));
				funcionario.setCpf(Long.parseLong(resultSet.getString("cpf")));
				funcionario.setEmail(resultSet.getString("email"));
				funcionario.setSenha(resultSet.getString("senha"));
				funcionario.setNivelAcesso(resultSet.getString("nivelAcesso"));

				listaFuncionarios.add(funcionario);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.fechaConexao();
			try {
				statement.close();
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return listaFuncionarios;

	}

}
