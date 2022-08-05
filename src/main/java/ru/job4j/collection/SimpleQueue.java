package ru.job4j.collection;

public class SimpleQueue<T> {

    int inNodes;
    int outNodes;
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
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