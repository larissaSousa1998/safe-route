package br.com.polaris.safe.route.domain;

public class PilhaObj<T> {
    private int topo;
    private T[] pilha;

    public PilhaObj(int capacidade) {
        topo = -1;
        pilha = (T[]) new Object[capacidade];
    }

    public T[] getPilha() {
        return pilha;
    }

    public boolean isEmpty() {
        return topo == -1;

    }

    public boolean isFull() {
        return topo == pilha.length - 1;
    }


    public void push(T info) {
        if (!isFull()) {
            pilha[++topo] = info;
        }
        else {
            System.out.println("Pilha cheia");
        }
    }

    public T pop() {
        if (!isEmpty()) {
            return pilha[topo--];
        }
        return (T) new Integer("-1");
    }

    public T peek() {
        if(!isEmpty()) {
            return pilha[topo];
        }
        return (T) new Integer("-1");
    }

    public void exibe() {
        if(isEmpty()) {
            System.out.println("Pilha vazia");
        }
        else {
            for(int i = 0; i <= topo; i++) {
                System.out.println(pilha[i]);
            }
        }

    }



}
