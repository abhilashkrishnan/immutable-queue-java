package jp.paypay.immutable;

import jp.paypay.immutable.exception.EmptyStackException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ImmutableStackTest {

    @Test
    public void testPush() throws Exception {
        Stack<String> stack = ImmutableStack.getEmptyStack();
        stack = stack.push("A");
        assertNotNull(stack);
        assertEquals("A", stack.head());
        print(stack);
        stack = stack.push("B");
        assertNotNull(stack);
        assertEquals("B", stack.head());
        print(stack);
        stack = stack.push("C");
        assertNotNull(stack);
        assertEquals("C", stack.head());
        print(stack);
    }

    @Test
    public void testPop() throws Exception {
        Stack<String> stack = ImmutableStack.getEmptyStack();
        stack = stack.push("A");
        assertNotNull(stack);
        assertEquals("A", stack.head());
        print(stack);
        stack = stack.push("B");
        assertNotNull(stack);
        assertEquals("B", stack.head());
        print(stack);

    }

    @Test
    public void testEmpty() {

            assertThrows(EmptyStackException.class, () -> {
            Stack<String> stack = ImmutableStack.getEmptyStack();
            stack = stack.push("A");
            stack = stack.pop();
            stack.pop();
        });
    }

    private static void print(Stack<String> s) throws Exception{
        while(s != null && !s.isEmpty()){
            System.out.print(s.head() + " -> ");
            s = s.pop();
        }
        System.out.println();
    }
}
