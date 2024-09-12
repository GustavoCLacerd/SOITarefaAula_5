package view;

import java.util.concurrent.Semaphore;
import controller.CozinhaThread;

public class PrincipalCozinha {
    public static void main(String[] args) {
        Semaphore permissoes = new Semaphore(1);
        for (int id = 0; id < 5; id++) {
            CozinhaThread tarefa = new CozinhaThread(permissoes, id);
            tarefa.start();
        }
    }
}
