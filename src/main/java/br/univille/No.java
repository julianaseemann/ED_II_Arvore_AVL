package br.univille;

public class No {
    int valor;
    int altura;
    No pai;
    No filhoEsquerdo;
    No filhoDireito;

    public No(int valor) {
        this.valor = valor;
        this.altura = 1;
    }

    public void calcularAltura() {
        int alturaEsquerda = (filhoEsquerdo != null) ? filhoEsquerdo.altura : 0;
        int alturaDireita = (filhoDireito != null) ? filhoDireito.altura : 0;
        altura = 1 + Math.max(alturaEsquerda, alturaDireita);
    }

    public int calcularFatorBalanceamento() {
        int alturaEsquerda = (filhoEsquerdo != null) ? filhoEsquerdo.altura : 0;
        int alturaDireita = (filhoDireito != null) ? filhoDireito.altura : 0;
        return alturaEsquerda - alturaDireita;
    }
}
