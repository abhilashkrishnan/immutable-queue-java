package jp.paypay.immutable;

import jp.paypay.immutable.exception.EmptyQueueException;

/**
 * The ImmutableQueue class represents a first-in-first-out (FIFO) queue of objects.
 * The ImmutableQueue is constructed using forwards and backwards stacks to keep track of the elements.
 * The usual enqueue and dequeue operations are provided, as well as a method to peek at the top item on the forwards stack, a method to test for whether the stack is empty.
 * An ImmutableQueue object is initially constructed using {@link EmptyQueue} which is a singleton instance since every empty queue is the same.
 * The enQueue and deQueue operations performed on the ImmutableQueue will essentially create a new instance of ImmutableQueue. The original instance of ImmutableQueue will remain the same.
 * @param <E> The element to be enQueued and deQueued.
 */
public class ImmutableQueue<E> implements Queue<E> {

    /**
     * The forwards stack keeps track of the elements being deQueued.
     */
    private final Stack<E> forwards;
    /**
     * The backwards stack keeps track of the elements being enQueued (since a queue is like a reverse stack).
     */
    private final Stack<E> backwards;

    /**
     * This constructor shouldn't be invoked from external sources.
     * @param forwards Forwards stack to keep track of elements to be deQueued
     * @param backwards Backwards stack to keep track of elements to be enQueued
     */
    private  ImmutableQueue(Stack<E> forwards, Stack<E> backwards) {
        this.forwards = forwards;
        this.backwards = backwards;
    }

    /**
     * Reverse the stack.
     * The use case of this operation is when the forwards stack is empty
     * the backwards stack is reversed and pushed into the forwards stack.
     * @param stack The stack to be reversed
     * @param <E> Elements inside the stack to be reversed
     * @return The reversed stack
     */
    public static final <E> Stack reverseStack(Stack<E> stack) {
        Stack<E> r = ImmutableStack.getEmptyStack();
        while(!stack.isEmpty()) {
            r = r.push(stack.head());
            stack = stack.pop();
        }

        return r;
    }

    /**
     * Cosntruct the singelton instance of {@link EmptyQueue}
     * @return {@link EmptyQueue}
     */
    public static final Queue getEmptyQueue() {
        return EmptyQueue.getInstance();
    }

    @Override
    public final Queue<E> enQueue(E e) {

        Stack b = backwards.push(e);
        return new ImmutableQueue<E>(forwards, b);
    }

    @Override
    public final Queue<E> deQueue() throws EmptyQueueException {
        Stack<E> f = forwards.pop();

        if (!f.isEmpty()) {
            return new ImmutableQueue<E>(f, backwards);
        } else if (backwards.isEmpty()) {
            return ImmutableQueue.getEmptyQueue();
        } else {
            return new ImmutableQueue<E>(ImmutableQueue.reverseStack(backwards), ImmutableStack.getEmptyStack());
        }
    }

    @Override
    public final E head() throws EmptyQueueException {
        return forwards.head();
    }

    @Override
    public final boolean isEmpty() {
        return false;
    }

    private static final class EmptyQueue<E> implements Queue<E> {
        private static final EmptyQueue emptyQueue = new EmptyQueue();

        /**
         * Singleton instance of empty Queue
         * @return {@link EmptyQueue}
         */
        public static final EmptyQueue getInstance() {
            return emptyQueue;
        }

        @Override
        public final Queue<E> enQueue(E e) {
            return new ImmutableQueue<E>(ImmutableStack.getEmptyStack().push(e), ImmutableStack.getEmptyStack());
        }

        @Override
        public final Queue<E> deQueue() {
            throw new EmptyQueueException("Queue is empty.");
        }

        @Override
        public final E head() {
            throw new EmptyQueueException("Queue is empty.");
        }
        /**
          * Empty queue always return true
          * @return is empty
          */
        @Override
        public final boolean isEmpty() {
            return true;
        }
    }
}
