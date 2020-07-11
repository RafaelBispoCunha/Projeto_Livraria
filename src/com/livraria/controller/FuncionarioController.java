package com.livraria.controller;

import java.util.List;

import com.livraria.DAO.FuncionarioDAO;
import com.livraria.model.FuncionarioModel;

public class FuncionarioController {
	private FuncionarioDAO funcionarioDao = new FuncionarioDAO();

	public boolean salvarFuncionario(FuncionarioModel funcionario) {
		return funcionarioDao.salvarFuncionario(funcionario);
	}

	public boolean alterarFuncionario(FuncionarioModel funcionario) {
		return funcionarioDao.alterarFuncionario(funcionario);
	}

	public boolean excluirFuncionario(FuncionarioModel funcionario) {
		return funcionarioDao.excluirFuncionario(funcionario);
	}

	public List<FuncionarioModel> listaFuncionario() {

		return funcionarioDao.listaFuncionario();
	}
}
