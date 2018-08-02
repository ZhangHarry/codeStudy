package CAS;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Zhanghr on 2016/4/13.
 */
public class ConcurrentStack<E> {
    AtomicReference<Node<E>> head = new AtomicReference<Node<E>>();
    public void push(E item){
        Node<E> newHead = new Node<>(item);
        Node<E> oldHead;
        do {
            oldHead = head.get();
            newHead.next = oldHead;
        }while (!head.compareAndSet(oldHead, newHead));
    }
    public E pop(){
        Node<E> newHead ;
        Node<E> oldHead;
        do {
            oldHead = head.get();
            if (oldHead == null)
                return null;
            newHead= oldHead.next;
        }while (!head.compareAndSet(oldHead,newHead));
        return oldHead.item;
    }
    static class Node<E>{
        final E item;
        Node<E> next;
        public Node(E item){
            this.item = item;
        }
    }
}
