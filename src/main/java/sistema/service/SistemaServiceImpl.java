package sistema.service;

import Service.ContatoService;
import Service.ContatoServiceImpl;
import sistema.Input;

public class SistemaServiceImpl implements SistemaService {


    private static Input input = new Input();
    private static ContatoService contatoService = new ContatoServiceImpl();


    @Override
    public void init() {
        while (true) {
            String opcao = input.get("--MENU PRINCIPAL--\n" +
                    "1 - Menu contato\n" +
                    "0 - Voltar\n" +
                    "Op: ");
            if (opcao.equals("0")) {
                break;
            } else if (opcao.equals("1")) {
                menuContato();
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }

    private void menuContato() {
        String opcao = input.get("--MENU CONTATO--\n" +
                "1 - Inserir\n" +
                "2 - Buscar\n" +
                "3 - Excluir\n" +
                "0 - Voltar\n" +
                "Op: ");

        if (opcao.equals("1")) {
            contatoService.salvarContato();
        }else if(opcao.equals("2")){
            contatoService.buscarContato();
        }else if(opcao.equals("3")){
            contatoService.deletarContato();
        } else {
            System.out.println("Opção inválida!");
        }

    }
}
