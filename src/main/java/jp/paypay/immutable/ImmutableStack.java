package jp.paypay.immutable;

import jp.paypay.immutable.exception.EmptyStackException;

/**
 * The ImmutableStack class represents a last-in-first-out (LIFO) stack of objects. The usual push and pop operations are provided,
 * as well as a method to peek at the top item on the stack, a method to test for whether the stack is empty.
 * An ImmutableStack object is initially constructed using {@link EmptyStack} which is a singleton instance since every empty stack is the same.
 * To operations performed on the ImmutableStack will essentially create a new ImmutableStack. The original ImmutableStack will remain the same.
 * @param <E> The element to be pushed and popped
 */
public class ImmutableStack<E> implements Stack<E> {

    private final E head;
    private final Stack<E> tail;

    /**
     * This constructor shouldn't be invoked from external sources.
     * @param head Latest element pushed into the stack
     * @param tail ImmutableStack of oldest elements pushed into the stack
     */
    private ImmutableStack(E head, Stack<E> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public final Stack<E> push(E t) {
        return new ImmutableStack<E>(t, this);
    }

    @Override
    public final Stack<E> pop() {
        return tail;
    }

    @Override
    public final  E head() {
        return head;
    }

    @Override
    public final boolean isEmpty() {
        return false;
    }

    /**
     * Construct a singelton instance of {@link EmptyStack}
     * @return {@link EmptyStack}
     */
    public final static Stack getEmptyStack(){
        return EmptyStack.getInstance();
    }


    private static final class EmptyStack<E> implements Stack<E> {

        private static final EmptyStack emptyStack = new EmptyStack();

        /**
         * Singleton instance of empty stack
         * @return {@link EmptyStack}
         */
        public static final EmptyStack getInstance() {
            return emptyStack;
        }

        @Override
        public final Stack<E> push(E t) {
            return new ImmutableStack<E>(t, this);
        }

        @Override
        public final Stack<E> pop() {
            throw new EmptyStackException("Stack is empty");
        }

        @Override
        public final E head() {
            throw new EmptyStackException("Stack is empty");
        }

        @Override
        public final boolean isEmpty() {
            return true;
        }

    }
}
