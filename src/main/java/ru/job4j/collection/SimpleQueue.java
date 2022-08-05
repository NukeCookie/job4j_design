package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private int inNodes;
    private int outNodes;
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() throws NoSuchElementException {
        if (outNodes == 0 && inNodes == 0) {
            throw new NoSuchElementException();
        }
        if (outNodes == 0) {
            while (inNodes != 0) {
                out.push(in.pop());
                outNodes++;
                inNodes--;
            }
            outNodes--;
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        inNodes++;
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}