package br.edu.ifpe.apoo.dao;

import br.edu.ifpe.apoo.entidades.Aluno;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAOList implements AlunoDAO {
	private List<Aluno> alunos;
    private static AlunoDAOList instancia;

    private AlunoDAOList() {
        alunos = new ArrayList<>();
    }

    public static AlunoDAOList getInstancia() {
        if (instancia == null) {
            instancia = new AlunoDAOList();
        }
        return instancia;
    }

    @Override
    public void inserir(Aluno aluno) {
        try {
            validarAluno(aluno);
            alunos.add(aluno);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao inserir o aluno: " + e.getMessage());
        }
    }

    @Override
    public void atualizar(Aluno aluno) {
        try {
            validarAluno(aluno);
            int index = obterIndice(aluno.getId());
            if (index != -1) {
                alunos.set(index, aluno);
            } else {
                throw new IllegalArgumentException("Aluno não encontrado para atualização.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao atualizar o aluno: " + e.getMessage());
        }
    }

    @Override
    public boolean remover(long id) {
        try {
            int index = obterIndice(id);
            if (index != -1) {
                alunos.remove(index);
                return true;
            } else {
                throw new IllegalArgumentException("Aluno não encontrado.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao remover o aluno: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Aluno get(long id) {
        try {
            int index = obterIndice(id);
            if (index != -1) {
                return alunos.get(index);
            } else {
                throw new IllegalArgumentException("Aluno não encontrado.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao consultar o aluno: " + e.getMessage());
            return null;
        }
    }

    private void validarAluno(Aluno aluno) {
        if (aluno.getNome().length() < 5 || aluno.getNome().length() > 100) {
            throw new IllegalArgumentException("O nome do aluno deve ter entre 5 e 100 caracteres.");
        }
        if (!aluno.getCpf().matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido.");
        }
    }

    private int obterIndice(long id) {
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
