package controller;

import java.util.concurrent.Semaphore;

public class OperacoesThread extends Thread {

    private Semaphore controleAcesso;
    private int identificador;
    
    public OperacoesThread(Semaphore controleAcesso, int identificador) {
        this.controleAcesso = controleAcesso;
        this.identificador = identificador;
    }

    public void run() {
        if (identificador % 3 == 1) {
            executarCalculo(1000, 200, identificador);
            realizarTransacao(1000, identificador);
            executarCalculo(1000, 200, identificador);
            realizarTransacao(1000, identificador);            
        } else if (identificador % 3 == 2) {
            executarCalculo(1500, 500, identificador);
            realizarTransacao(1500, identificador);
            executarCalculo(1500, 500, identificador);
            realizarTransacao(1500, identificador);
        } else {
            executarCalculo(2000, 1000, identificador);
            realizarTransacao(1500, identificador);
            executarCalculo(2000, 1000, identificador);
            realizarTransacao(1500, identificador);
        }
    }

    private void realizarTransacao(int duracao, int id) {
        int tempo = duracao; 
        try {
            controleAcesso.acquire();
            sleep(tempo);
            System.out.println("Thread " + id + " está efetuando transações por " + tempo + "ms");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            controleAcesso.release();
        }
    }

    private void executarCalculo(int maxTempo, int minTempo, int id) {
        int tempo = (int) ((Math.random() * maxTempo) + minTempo); 
        System.out.println("Thread " + id + " está executando cálculos por " + tempo + "ms");
        try {
            sleep(tempo);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
