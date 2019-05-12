package jp.paypay.immutable;

import jp.paypay.immutable.exception.EmptyStackException;

public interface Stack<E> {

    /**
     * Push operation will construct a new stack with the latest element as head and oldest elements as tail.
     * @param e Element to be pushed
     * @return Immutable stack
     */
    Stack<E> push(E e);

    /**
     * Pop operation will return stack of oldest elements pushed into the tail.
     * @return Stack of oldest elements
     * @throws {@link EmptyStackException}
     */
    Stack<E> pop();

    /**
     * Head operation will peek the latest element pushed into the stack without removing it.
     * @return Latest element
     * @throws {@link EmptyStackException}
     */
    E head();

    /**
     * Checks whether stack is empty
     * @return is empty
     */
    boolean isEmpty();

}
