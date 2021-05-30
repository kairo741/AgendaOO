package Service;

import DAO.DaoContato;
import DAO.DaoContatoImpl;
import entity.Contato;
import sistema.Input;

import java.util.List;
import java.util.Scanner;

public class ContatoServiceImpl implements ContatoService {

    private static DaoContato daoContato = new DaoContatoImpl();
    private static Scanner scan = new Scanner(System.in);
    private static Input input = new Input();


    @Override
    public void salvarContato(){
        Contato contato = preencherContato();

        validateContato(contato);

        daoContato.salvarOuAtualizar(contato);
    }


    private Contato preencherContato() {

        return Contato.builder()
                .email(input.get("Email: "))
                .numTelefone(input.get("Numero de telefone: "))
                .nome(input.get("Nome: "))
                .build();

    }

    private void validateContato(Contato contato) {

        if(contato.getEmail() == null || contato.getEmail().isEmpty()){
            throw new RuntimeException("E-mail não pode ser nulo!");
        }
        if(contato.getNome() == null || contato.getNome().isEmpty()){
            throw new RuntimeException("Nome não pode ser nulo!");
        }
        if(contato.getNumTelefone() == null || contato.getNumTelefone().isEmpty()){
            throw new RuntimeException("Número de telefone não pode ser nulo!");
        }
    }

    @Override
    public void buscarContato(){
        String opcao = input.get("--MENU DE BUSCA--\n" +
                "1 - Busca por nome\n" +
                "2 - Busca por telefone\n" +
                "3 - Busca por e-mail\n" +
                "0 - Voltar\n" +
                "Op: ");
        if (opcao.equals("1")) {
            exibirBusca(input.get("Nome do contato que deseja buscar: "), "nome");
        }else if(opcao.equals("2")){
            exibirBusca(input.get("Telefone do contato que deseja buscar: "), "numTelefone");
        }else if(opcao.equals("3")){
            exibirBusca(input.get("E-mail do contato que deseja buscar: "), "email");
        } else {
            System.out.println("Opção inválida!");
        }
    }

    //Tipo: 1 - Nome | 2 - Telefone | 3 - Email
    private void exibirBusca(String argumento, String tipo){
        List<Contato> list = daoContato.getContato(argumento, tipo);
        System.out.println("Os contatos encontrados foram: ");
        list.forEach(contato -> {
            System.out.println("_______________________________________________________");
            System.out.println("Id do contato: "+ contato.getId());
            System.out.println("Nome: "+ contato.getNome());
            System.out.println("E-mail: "+ contato.getEmail());
            System.out.println("Numero de Telefone: "+ contato.getNumTelefone());
            System.out.println("_______________________________________________________");
        });

    }

    @Override
    public void deletarContato(){
        processardelete(Long.parseLong(input.get("Id do contato que deseja deletar: ")));
    }

    private void processardelete(Long id){
        daoContato.deletarContato(id);
    }

}
