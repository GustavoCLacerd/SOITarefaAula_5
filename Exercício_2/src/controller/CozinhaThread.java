package controller;

import java.util.concurrent.Semaphore;

public class CozinhaThread extends Thread {
    private int identificador;
    private Semaphore controleSemaforo;

    public CozinhaThread(Semaphore controleSemaforo, int identificador) {
        this.controleSemaforo = controleSemaforo;
        this.identificador = identificador;
    }

    public void run() {
        String prato;
        if (identificador % 2 == 0) {
            prato = "Lasanha";
            prepararPrato(prato, 1200, 600, identificador);
            realizarEntrega(controleSemaforo, identificador, prato);
        } else {
            prato = "Sopa de Cebola";
            prepararPrato(prato, 800, 500, identificador);
            realizarEntrega(controleSemaforo, identificador, prato);
        }
    }

    private void realizarEntrega(Semaphore semaforo, int id, String prato) {
        try {
            System.err.println("Preparando para entregar. ID: " + id);
            semaforo.acquire();
            sleep(5000);
            System.err.println("Prato entregue! " + prato +
                               "\nIDThread: " + id);
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            semaforo.release();
        }
    }

    private void prepararPrato(String prato, int tempoMaximo, int tempoMinimo, int id) {
        int tempoTotal = (int) ((Math.random() * tempoMaximo) + tempoMinimo);
        int tempoAtual = 0;
        double progresso = 0;

        System.err.println("Iniciou o preparo do prato: " + prato);
        while (tempoAtual < tempoTotal) {
            try {
                sleep(tempoTotal);
                tempoAtual += 100;
                progresso = ((tempoAtual / (float) tempoTotal) * 100);
                String progressoFormatado = String.format("%.1f", progresso);
                System.err.println("Porcentagem de preparo: " + progressoFormatado + "%" +
                                   "\nPrato: " + prato + "\nIDThread: " + id);
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
