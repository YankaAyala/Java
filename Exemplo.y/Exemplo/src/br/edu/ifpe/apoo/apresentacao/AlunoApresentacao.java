package br.edu.ifpe.apoo.apresentacao;

import java.util.Scanner;

import br.edu.ifpe.apoo.entidades.Aluno;
import br.edu.ifpe.apoo.excecoes.ExcecaoAlunoInvalido;
import br.edu.ifpe.apoo.negocio.ControladorAluno;

public class AlunoApresentacao {

    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        ControladorAluno controlador = new ControladorAluno();

        while (true) {
            System.out.println("Digite a opção desejada:");
            System.out.println("1 para inserir um aluno;");
            System.out.println("2 para consultar um aluno;");
            System.out.println("3 para remover um aluno;");
            System.out.println("4 para alterar um aluno;");
            System.out.println("0 para sair.");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 0:
                    System.out.println("Encerrando o programa.");
                    return;
                case 1:
                    inserirAluno(scanner, controlador);
                    break;
                
                case 2:
                	System.out.println("Digite o ID do aluno que deseja consultar:");
                    long idConsulta = Long.parseLong(scanner.nextLine());

                    Aluno alunoConsultado = controlador.consultar(idConsulta);

                    if (alunoConsultado != null) {
                        System.out.println("Aluno encontrado:");
                        System.out.println("ID: " + alunoConsultado.getId());
                        System.out.println("Nome: " + alunoConsultado.getNome());
                        System.out.println("CPF: " + alunoConsultado.getCpf());
                        System.out.println("E-mail: " + alunoConsultado.getEmail());
                    } else {
                        System.out.println("Aluno não encontrado.");
                    }
                    break;
                
                case 3:
                	System.out.println("Digite o ID do aluno que deseja remover:");
                    long idRemocao = Long.parseLong(scanner.nextLine());

                    boolean alunoRemovido = controlador.remover(idRemocao);

                    if (alunoRemovido) {
                        System.out.println("Aluno removido com sucesso!");
                    } else {
                        System.out.println("Aluno não encontrado ou não foi possível remover.");
                    }
                    break;
                case 4:
                	System.out.println("Digite o ID do aluno que deseja alterar:");
                    long idAlteracao = Long.parseLong(scanner.nextLine());

                    Aluno alunoParaAlterar = controlador.consultar(idAlteracao);

                    if (alunoParaAlterar != null) {
                        System.out.println("Aluno encontrado. Digite os novos dados:");

                        System.out.print("Novo nome: ");
                        String novoNome = scanner.nextLine();
                        alunoParaAlterar.setNome(novoNome);

                        System.out.print("Novo CPF: ");
                        String novoCpf = scanner.nextLine();
                        alunoParaAlterar.setCpf(novoCpf);

                        System.out.print("Novo e-mail: ");
                        String novoEmail = scanner.nextLine();
                        alunoParaAlterar.setEmail(novoEmail);

                        controlador.atualizar(alunoParaAlterar);
                        System.out.println("Aluno atualizado com sucesso!");
                    } else {
                        System.out.println("Aluno não encontrado.");
                    }
                    
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void inserirAluno(Scanner scanner, ControladorAluno controlador) {
        Aluno aluno = new Aluno();
        System.out.println("Digite o CPF do aluno:");
        String cpf = scanner.nextLine();
        aluno.setCpf(cpf);

        System.out.println("Digite o nome do aluno:");
        String nome = scanner.nextLine();
        aluno.setNome(nome);

        System.out.println("Digite o e-mail do aluno:");
        String email = scanner.nextLine();
        aluno.setEmail(email);

        try {
            controlador.inserir(aluno);
            System.out.println("Aluno inserido com sucesso!");
        } catch (ExcecaoAlunoInvalido e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
