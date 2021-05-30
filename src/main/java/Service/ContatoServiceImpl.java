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
        exibirBusca("kairo");
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
        exibirBusca(input.get("Nome do contato que deseja buscar: "));
    }


    private void exibirBusca(String nome){
        List<Contato> list = daoContato.getContato(nome);
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
