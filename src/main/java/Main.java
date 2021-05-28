import Service.ContatoService;
import Service.ContatoServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;



@RequiredArgsConstructor
public class Main {
    public static Scanner scan = new Scanner(System.in);
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("agenda");
    private static ContatoService contatoService;


    public static void main(String[] args){

        System.out.println("Hello world!");


        contatoService.salvarContato();



    }


}
