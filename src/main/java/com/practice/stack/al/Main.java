package main.java.com.practice.stack.al;

public class Main {
    public static void main(String[] args) {
        String myString = "hello";
        String reversedString = reverseString(myString);
        System.out.println(reversedString);

        testAndPrint("()", true);
        testAndPrint("()()", true);
        testAndPrint("(())", true);
        testAndPrint("()()()", true);
        testAndPrint("(()())", true);
        testAndPrint(")()(", false);
        testAndPrint(")(", false);
        testAndPrint("(()", false);
        testAndPrint("))", false);
        testAndPrint("(", false);
        testAndPrint(")", false);

    }

    public static String reverseString(String string){
        Stack<Character> stack = new Stack<>();
        String res = "";
        for (char c : string.toCharArray()){
            stack.push(c);
        }

       while(!stack.isEmpty()){
           res+= stack.pop();
       }
        return res;
    }

    public static void testAndPrint(String testStr, boolean expected) {
        // Run the test and store the result
        boolean result = isBalancedParentheses(testStr);

        // Print the test string, expected result, and actual result
        System.out.println("Test String: " + testStr);
        System.out.println("EXPECTED: " + expected);
        System.out.println("RESULT: " + result);

        // Check if the test passed or failed
        if (result == expected) {
            System.out.println("STATUS: PASS");
        } else {
            System.out.println("STATUS: FAIL");
        }

        // Print a separator for better readability
        System.out.println("--------------");
    }

    public static boolean isBalancedParentheses(String str){
        Stack<Character> stack = new Stack<>();

        for(char c: str.toCharArray()){
            if(c == '('){
                stack.push(c);
            }else if (c ==')'){
                if(stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void sortStack(Stack<Integer> stack) {
        Stack<Integer> additionalStack = new Stack<>();

        while(!stack.isEmpty()){
            Integer tmp = stack.pop();

            while(!additionalStack.isEmpty() && additionalStack.peek() > tmp){
                stack.push(additionalStack.pop());
            }

            additionalStack.push(tmp);
        }

        while(!additionalStack.isEmpty()){
            stack.push(additionalStack.pop());
        }
    }




















}
