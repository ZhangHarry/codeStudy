package CAS;

import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Zhanghr on 2016/4/14.
 */
public class LinkedQueue<E> {
    private static class Node <E> {
        final E item;
        final AtomicReference<Node<E>> next;
        Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<Node<E>>(next);
        }
    }
    private AtomicReference<Node<E>> head
            = new AtomicReference<Node<E>>(new Node<E>(null, null));
    private AtomicReference<Node<E>> tail = head;
    public boolean put(E item) {
        Node<E> node = new Node<>(item, null);
        while (true) {
            Node<E> curTail = tail.get();
            Node<E> residue = curTail.next.get();
            if (curTail == tail.get()) {
                if (residue == null) {
                    if (curTail.next.compareAndSet(null, node)) { //保证正确修改curTail.next，并且不会冲突修改，下面设置tail才能成立
                        tail.compareAndSet(curTail, node);
                        return true;
                    }
                }else {//帮助未完成的修改，因为所有线程中只有一个可以修改curTail.next，所以所有线程都可以完成第二步，
                // 避免线程完成第一步后失败引起其他进程饥饿，或者原线程第一步后切换cpu，其他线程空转自己的时间片等待原线程继续
                    tail.compareAndSet(curTail, node);
                }
            }
        }
    }
    public static void main(String[] args) {
        String path = "D:\\cmd.txt";
        Runtime run = Runtime.getRuntime();
        try {
            Process process = run.exec("cmd.exe /c start " + path);

            //将调用结果打印到控制台上
            InputStream in = process.getInputStream();
            while (in.read() != -1) {
                System.out.println(in.read());
            }
            in.close();
            process.waitFor();
            process.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
