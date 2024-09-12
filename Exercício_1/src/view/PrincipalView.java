package view;

import java.util.concurrent.Semaphore;
import controller.OperacoesThread;

public class PrincipalView {
    public static void main(String[] args) {
        int vagas = 1;
        Semaphore controle = new Semaphore(vagas);

        for (int contador = 0; contador < 21; contador++) {
            OperacoesThread thread = new OperacoesThread (controle, contador);
            thread.start();
        }
    }
}
