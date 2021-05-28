package Service;

import DAO.DaoContato;
import entity.Contato;

import java.util.Scanner;

public class ContatoServiceImpl implements ContatoService {

    private static DaoContato daoContato;
    public static Scanner scan = new Scanner(System.in);

    @Override
    public void salvarContato(){
        Contato contato = preencherContato();

        validateContato(contato);

        daoContato.salvarOuAtualizar(contato);

    }


    private Contato preencherContato() {

        return Contato.builder()
                .email("scan.nextLine()")
                .numTelefone("scan.nextLine()")
                .nome("scan.nextLine()")
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

}
