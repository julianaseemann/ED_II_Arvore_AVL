package br.univille;

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

        // Caso LL (rotação simples à direita)
        if (fatorBalanceamento > 1 && valor < noAtual.filhoEsquerdo.valor) {
            return rotacaoDireita(noAtual);
        }

        // Caso RR (rotação simples à esquerda)
        if (fatorBalanceamento < -1 && valor > noAtual.filhoDireito.valor) {
            return rotacaoEsquerda(noAtual);
        }

        // Caso LR (rotação dupla esquerda-direita)
        if (fatorBalanceamento > 1 && valor > noAtual.filhoEsquerdo.valor) {
            noAtual.filhoEsquerdo = rotacaoEsquerda(noAtual.filhoEsquerdo);
            return rotacaoDireita(noAtual);
        }

        // Caso RL (rotação dupla direita-esquerda)
        if (fatorBalanceamento < -1 && valor < noAtual.filhoDireito.valor) {
            noAtual.filhoDireito = rotacaoDireita(noAtual.filhoDireito);
            return rotacaoEsquerda(noAtual);
        }

        return noAtual;
    }

    private No rotacaoDireita(No noDesbalanceado) {
        No filhoEsquerdo = noDesbalanceado.filhoEsquerdo;
        No subArvoreDireitaDoFilho = filhoEsquerdo.filhoDireito;

        // Rotação
        filhoEsquerdo.filhoDireito = noDesbalanceado;
        noDesbalanceado.filhoEsquerdo = subArvoreDireitaDoFilho;

        // Ajusta pais
        if (subArvoreDireitaDoFilho != null)
            subArvoreDireitaDoFilho.pai = noDesbalanceado;

        filhoEsquerdo.pai = noDesbalanceado.pai;
        noDesbalanceado.pai = filhoEsquerdo;

        // Recalcula alturas
        noDesbalanceado.calcularAltura();
        filhoEsquerdo.calcularAltura();

        return filhoEsquerdo; // nova raiz da subárvore
    }

    private No rotacaoEsquerda(No noDesbalanceado) {
        No filhoDireito = noDesbalanceado.filhoDireito;
        No subArvoreEsquerdaDoFilho = filhoDireito.filhoEsquerdo;

        // Rotação
        filhoDireito.filhoEsquerdo = noDesbalanceado;
        noDesbalanceado.filhoDireito = subArvoreEsquerdaDoFilho;

        // Ajusta pais
        if (subArvoreEsquerdaDoFilho != null)
            subArvoreEsquerdaDoFilho.pai = noDesbalanceado;

        filhoDireito.pai = noDesbalanceado.pai;
        noDesbalanceado.pai = filhoDireito;

        // Recalcula alturas
        noDesbalanceado.calcularAltura();
        filhoDireito.calcularAltura();

        return filhoDireito; // nova raiz da subárvore
    }

    public void imprimirEmOrdem(No noAtual) {
        if (noAtual != null) {
            imprimirEmOrdem(noAtual.filhoEsquerdo);
            System.out.print(noAtual.valor + " ");
            imprimirEmOrdem(noAtual.filhoDireito);
        }
    }

    public void imprimirArvore() {
        imprimirEmOrdem(raiz);
        System.out.println();
    }
}
