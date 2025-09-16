package br.univille;

import java.util.LinkedList;
import java.util.Queue;

public class ArvoreALV {
    No raiz;

    public void adicionar(int valor) {
        raiz = adicionar(valor, raiz, null);
    }

    private No adicionar(int valor, No noAtual, No noPai) {
        if (noAtual == null) {
            No novoNo = new No(valor);
            novoNo.pai = noPai;
            return novoNo;
        }

        if (valor < noAtual.valor) {
            No novoNo = adicionar(valor, noAtual.filhoEsquerdo, noAtual);
            noAtual.filhoEsquerdo = novoNo;
            noAtual.calcularAltura();
        } else if (valor > noAtual.valor) {
            No novoNo = adicionar(valor, noAtual.filhoDireito, noAtual);
            noAtual.filhoDireito = novoNo;
            noAtual.calcularAltura();
        } else {
            return noAtual; // valor já existe, não insere
        }

        return balancear(noAtual, valor);
    }

    private No balancear(No noAtual, int valor) {
        int fatorBalanceamento = noAtual.calcularFatorBalanceamento();

        if (fatorBalanceamento > 1 && valor < noAtual.filhoEsquerdo.valor)
            return rotacaoDireita(noAtual);

        if (fatorBalanceamento < -1 && valor > noAtual.filhoDireito.valor)
            return rotacaoEsquerda(noAtual);

        if (fatorBalanceamento > 1 && valor > noAtual.filhoEsquerdo.valor) {
            noAtual.filhoEsquerdo = rotacaoEsquerda(noAtual.filhoEsquerdo);
            return rotacaoDireita(noAtual);
        }

        if (fatorBalanceamento < -1 && valor < noAtual.filhoDireito.valor) {
            noAtual.filhoDireito = rotacaoDireita(noAtual.filhoDireito);
            return rotacaoEsquerda(noAtual);
        }

        return noAtual;
    }

    private No rotacaoDireita(No noDesbalanceado) {
        No filhoEsquerdo = noDesbalanceado.filhoEsquerdo;
        No subArvoreDireitaDoFilho = filhoEsquerdo.filhoDireito;

        filhoEsquerdo.filhoDireito = noDesbalanceado;
        noDesbalanceado.filhoEsquerdo = subArvoreDireitaDoFilho;

        if (subArvoreDireitaDoFilho != null)
            subArvoreDireitaDoFilho.pai = noDesbalanceado;

        filhoEsquerdo.pai = noDesbalanceado.pai;
        noDesbalanceado.pai = filhoEsquerdo;

        noDesbalanceado.calcularAltura();
        filhoEsquerdo.calcularAltura();

        return filhoEsquerdo;
    }

    private No rotacaoEsquerda(No noDesbalanceado) {
        No filhoDireito = noDesbalanceado.filhoDireito;
        No subArvoreEsquerdaDoFilho = filhoDireito.filhoEsquerdo;

        filhoDireito.filhoEsquerdo = noDesbalanceado;
        noDesbalanceado.filhoDireito = subArvoreEsquerdaDoFilho;

        if (subArvoreEsquerdaDoFilho != null)
            subArvoreEsquerdaDoFilho.pai = noDesbalanceado;

        filhoDireito.pai = noDesbalanceado.pai;
        noDesbalanceado.pai = filhoDireito;

        noDesbalanceado.calcularAltura();
        filhoDireito.calcularAltura();

        return filhoDireito;
    }

    public void imprimirNivelPorNivel() {
        if (raiz == null) {
            System.out.println("(árvore vazia)");
            return;
        }

        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            int tamanhoNivel = fila.size();

            for (int i = 0; i < tamanhoNivel; i++) {
                No atual = fila.poll();
                System.out.print(atual.valor + " ");

                if (atual.filhoEsquerdo != null) fila.add(atual.filhoEsquerdo);
                if (atual.filhoDireito != null) fila.add(atual.filhoDireito);
            }

            System.out.println(); // nova linha a cada nível
        }
    }
}
