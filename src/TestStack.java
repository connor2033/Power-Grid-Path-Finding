public class TestStack {
    public static void main(String[] args) {
        boolean testPassed;
        Integer val = null;

        ArrayStack<Integer> stack = new ArrayStack<Integer>(30);

        // Test 1. Test push and peek on a stack with a few elements

        // adds numbers 0-10 onto the stack
        try {
            for (int i = 0; i < 11; i++) {
                stack.push(Integer.valueOf(i));
            }

            // checks the top value of the stack, it should be 10
            val = stack.peek();

            // checks the size of the stack, it should be 11
            if ((val.intValue() == 10) && (stack.size() == 11)) {
                testPassed = true;
            } else {
                testPassed = false;
            }

        } catch (Exception e) {
            testPassed = false;
        }

        if (testPassed) {
            System.out.println("Test 1 passed, expected val = 10, stack.size = 11, got val = " + val.intValue()
                    + ", stack.size = " + stack.size());
        } else {
            System.out.println("Test 1 failed, expected val = 10, stack.size = 11, got val = " + val.intValue()
                    + ", stack.size = " + stack.size());
        }

        // Test 2. Test pop on a stack with a few elements
        try {
            testPassed = true;

            // pop 4 values off of the stack
            for (int i = 0; i < 5; i++) {
                val = stack.pop();
            }

            // checks if the value last popped is 6 and that the stack is not empty
            if ((val.intValue() == 6) && !stack.isEmpty()) {
                testPassed = true;
            } else {
                testPassed = false;
            }
        } catch (Exception e) {
            testPassed = false;
        }

        if (testPassed) {
            System.out.println("Test 2 passed, expected val = 6, recieved val = " +  val.intValue());
        } else {
            System.out.println("Test 2 failed, expected val = 6, recieved val = " + val.intValue());
        }

        // Test 3. Pop on an empty stack

        // tests if it's possible to pop 6 more values from the stack
        try {
            for (int i = 0; i < 7; i++) {
                val = stack.pop();
            }
            testPassed = false;

            // SYSTEM OUT TO DETERMINE IF IT IS EMPTY -- TO STRING
            System.out.println(stack.toString());

        } catch (EmptyStackException e) {
            testPassed = true;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(stack.toString());
            testPassed = false;
        }

        if (testPassed) {
            System.out.println("Test 3 passed, expected to throw EmptyStackException");
        } else {
            System.out.println("Test 3 failed, expected to throw EmptyStackException");
        }

        // Test 4. Test that size of a stack is increased correctly
        stack = new ArrayStack<Integer>(25);

        try {
            testPassed = true;

            for (int i = 0; i < 101; i++) {
                stack.push(Integer.valueOf(i));

                // should be 50 after 25 pushes
                if ((i == 25) && (stack.length() != 50)) {

                    // print size of stack
                    testPassed = false;
                    break;
                }

                // should be 100 after 50 pushes
                else if ((i == 50) && (stack.length() != 100)) {
                    // print size of stack

                    testPassed = false;
                    break;
                }

                // should be 150 after after 100 pushes
                else if ((i == 100) && (stack.length() != 150)) {
                    // print size of stack

                    testPassed = false;
                    break;
                }
            }
        }  catch (Exception e) {
            testPassed = false;
        }

        if (testPassed) {
            System.out.println("Test 4 passed, expect stack length to increase as specified in the assignment 2" + stack.length());
        } else {
            System.out.println("Test 4 failed, expect stack length to increase as specified in assignment 2");
        }

        // Test 5. Test that the size of the stack is decreased correctly.
        stack = new ArrayStack<Integer>(60);

        int result;

        try {
            testPassed = true;

            // push values 0 to 20 to the stack
            for (int i = 0; i <= 20; i++) {
                stack.push(Integer.valueOf(i));
            }

            // pop 16 values to the stack
            for (int i = 20; i > 5; i--) {
                result = stack.pop();

                // once a value is popped, the stack should shrink to 30 like in assignment 2 (divide by 2)
                if ((i == 19) && (stack.length() != 30)) {
                    System.out.println("i = 19, stack size = " + stack.length());
                    testPassed = false;
                    break;
                }

                else if ((i == 9) && (stack.length() != 15)) {
                    System.out.println("i = 9, stack size = " + stack.length());
                    testPassed = false;
                    break;
                }
            }
            if (stack.length() != 15) {
                testPassed = false;
            }
        } catch (Exception e) {
            testPassed = false;
        }

        if (!testPassed) {
            System.out.println("Test 5 failed, expected stack length to be 15, got " + stack.length());
        } else {
            System.out.println("Test 5 passed, expected stack length to be 15, got " + stack.length());
        }

        // Test 6. Test push, pop, size

        testPassed = true;
        try {
            stack = new ArrayStack<Integer>();

            for (int i = 0; i < 990; i++) {
                stack.push(Integer.valueOf(i));
            }

            // checks to see if it's the correct size
            if (stack.size() != 990) {
                testPassed = false;
            }

            // checks to see if the values being popped are the same
            for (int i = 989; i >= 0; i--) {
                val = stack.pop();
                if (val.intValue() != i) {
                    testPassed = false;
                    break;
                }
            }

        } catch (Exception e) {
            testPassed = false;
        }

        if (testPassed) {
            System.out.println("Test 6 passed");
        } else {
            System.out.println("Test 6 failed, expected to test stack first in last out structure");
        }

        // Test 7. Test toString
        testPassed = true;
        try {
            stack = new ArrayStack<Integer>(3);
            for (int i = 0; i < 3; i++) {
                stack.push(Integer.valueOf(i));
            }

            String out = stack.toString();

            if (out.equals("Stack: 0, 1, 2")) {
                testPassed = true;
            } else {
                testPassed = false;
            }
        } catch (Exception e) {
            testPassed = false;
        }

        if (testPassed) {
            System.out.println("Test 7 passed - expected: \"Stack: 0, 1, 2\", recieved: " + stack.toString());
        } else {
            System.out.println("Test 7 failed - expected: \"Stack: 0, 1, 2 \", recieved: " + stack.toString());
        }
    }
}
