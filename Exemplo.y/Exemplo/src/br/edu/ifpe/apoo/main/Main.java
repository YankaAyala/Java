package br.edu.ifpe.apoo.main;

import br.edu.ifpe.apoo.apresentacao.AlunoApresentacao;
import br.edu.ifpe.apoo.excecoes.ExcecaoAlunoInvalido;

public class Main {

	public static void main(String[] args) throws ExcecaoAlunoInvalido {
		AlunoApresentacao apresentacao = new AlunoApresentacao();
		
		apresentacao.exibirMenu();
	}
}
