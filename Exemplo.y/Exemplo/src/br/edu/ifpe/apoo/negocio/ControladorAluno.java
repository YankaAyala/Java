package br.edu.ifpe.apoo.negocio;

import br.edu.ifpe.apoo.dao.AlunoDAO;
import br.edu.ifpe.apoo.dao.AlunoDAOAbstractFactory;
import br.edu.ifpe.apoo.entidades.Aluno;
import br.edu.ifpe.apoo.excecoes.ExcecaoAlunoInvalido;

public class ControladorAluno {

    private final AlunoDAO alunoDAO; 

    public ControladorAluno() {
        alunoDAO = AlunoDAOAbstractFactory.getDAO(); 
    }

    public void inserir(Aluno aluno) throws ExcecaoAlunoInvalido {
        if (!isValido(aluno)) {
            throw new ExcecaoAlunoInvalido("Aluno inválido");
        }

        alunoDAO.inserir(aluno); 
    }

    public void atualizar(Aluno aluno) {
        if (!isValido(aluno)) {
            
        }

        alunoDAO.atualizar(aluno); 
    }

    public boolean remover(long id) {
        return alunoDAO.remover(id); 
    }

    public Aluno consultar(long id) {
        return alunoDAO.get(id); 
    }

    private boolean isValido(Aluno aluno) {
        if (aluno.getNome() == null || aluno.getNome().length() < 5 || aluno.getNome().length() > 100) {
            return false;
        }

        if (aluno.getCpf() == null || !aluno.getCpf().matches("\\d{11}")) {
            return false;
        }

        return true;
    }
}
