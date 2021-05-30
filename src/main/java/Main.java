import Service.ContatoService;
import Service.ContatoServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sistema.service.SistemaService;
import sistema.service.SistemaServiceImpl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;



@RequiredArgsConstructor
public class Main {
    private static SistemaService service = new SistemaServiceImpl();


    public static void main(String[] args){


        service.init();



    }


}
