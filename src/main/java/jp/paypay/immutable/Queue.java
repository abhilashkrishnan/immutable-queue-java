package jp.paypay.immutable;

import jp.paypay.immutable.exception.EmptyQueueException;

public interface Queue<E> {
    /**
     * Enqueue the element into the backwards stack.
     * The enQueue() operation runs in constant, i.e. O(1) time.
     * @param e Element to be enqueued
     * @return The Queue instance
     */
    Queue<E> enQueue(E e);

    /**
     * Dequeue the element from the forwards stack.
     * The deQueue() operation of ImmutableQueue runs in O(n) time in worst case and O(1) time in best case.
     * The worst case O(n) time arises since that could involve reversing of the full forwards stack.
     * @return The Queue instance
     */
    Queue<E> deQueue();

    /**
     * Head operation will peek the first element enqueue into the forwards stack without removing it.
     * @return First element enqueued
     */
    E head();

    /**
     * Checks whether queue is empty
     * @return is empty
     */
    boolean isEmpty();
}
