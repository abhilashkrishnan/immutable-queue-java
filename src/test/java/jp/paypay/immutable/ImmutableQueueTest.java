package jp.paypay.immutable;

import jp.paypay.immutable.exception.EmptyQueueException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class ImmutableQueueTest {

    @Test
    public void testEnqueue() throws Exception {
        Queue<String> queue = ImmutableQueue.getEmptyQueue();
        queue = queue.enQueue("A");
        assertNotNull(queue);
        assertEquals("A", queue.head());
        print(queue);
        queue = queue.enQueue("B");
        assertNotNull(queue);
        print(queue);
        assertEquals("A", queue.head());
        queue = queue.enQueue("C");
        assertNotNull(queue);
        assertEquals("A", queue.head());
        print(queue);
    }

    @Test
    public void testDequeue() throws Exception {
        Queue<String> queue = ImmutableQueue.getEmptyQueue();
        queue = queue.enQueue("A");
        queue = queue.enQueue("B");
        queue = queue.enQueue("C");
        print(queue);
        queue = queue.deQueue();
        assertNotNull(queue);
        assertEquals("B", queue.head());
        print(queue);
        queue = queue.deQueue();
        assertNotNull(queue);
        assertEquals("C", queue.head());
        print(queue);
    }

    @Test
    public void testEmpty() {
            assertThrows(EmptyQueueException.class, () -> {
            Queue<String> queue = ImmutableQueue.getEmptyQueue();
            queue = queue.enQueue("A");
            queue = queue.deQueue();
            queue.deQueue();
        });
    }

    public void print(Queue<String> queue) throws Exception {
        while(queue != null && !queue.isEmpty()) {
            System.out.print(queue.head() + "->");
            queue = queue.deQueue();
        }

        System.out.println();
    }
}
