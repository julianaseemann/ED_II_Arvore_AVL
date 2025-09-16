package br.univille;

public class Main {
    public static void main(String[] args) {
        // arvore 1
        System.out.println("Árvore 1:");
        ArvoreALV arvore1 = new ArvoreALV();
        int[] valores1 = {40, 20, 60, 10, 30, 25};
        for (int v : valores1) arvore1.adicionar(v);
        arvore1.imprimirNivelPorNivel();

        // arvore 2
        System.out.println("\nÁrvore 2:");
        ArvoreALV arvore2 = new ArvoreALV();
        int[] valores2 = {60, 40, 80, 35, 50, 90, 20, 38, 37};
        for (int v : valores2) arvore2.adicionar(v);
        arvore2.imprimirNivelPorNivel();

        // arvore 3
        System.out.println("\nÁrvore 3:");
        ArvoreALV arvore3 = new ArvoreALV();
        int[] valores3 = {30, 20, 10, 25, 40, 50, 5, 35, 45};
        for (int v : valores3) arvore3.adicionar(v);
        arvore3.imprimirNivelPorNivel();
    }
}
