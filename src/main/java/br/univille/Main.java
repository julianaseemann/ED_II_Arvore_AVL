package br.univille;

public class Main {
    public static void main(String[] args) {
        ArvoreALV arvore = new ArvoreALV();

        int[] valores = {40, 20, 60, 10, 30, 25};

        for (int v : valores) {
            arvore.adicionar(v);
        }

        arvore.imprimirArvore(); // imprime em ordem para verificar
    }
}
