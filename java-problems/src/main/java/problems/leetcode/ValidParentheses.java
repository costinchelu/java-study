package problems.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
<p>
 An input string is valid if:
 <p>
 - Open brackets must be closed by the same type of brackets.<p>
 - Open brackets must be closed in the correct order.<p>
 - Every close bracket has a corresponding open bracket of the same type.
 <p>
 Constraints:
 <p>
 1 <= s.length <= 10^4 <p>
 s consists of parentheses only '()[]{}'
 */
public class ValidParentheses {

    public static void main(String[] args) {
        ValidParentheses instance = new ValidParentheses();
        System.out.println(instance.isValid("()[]"));
        System.out.println(instance.isValid("()"));
        System.out.println(instance.isValid("({})[]"));
        System.out.println(instance.isValid("({([[{()}]])})[]"));
        System.out.println(instance.isValid("[({(())}[()])]"));
        System.out.println(instance.isValid("{[]}"));

        System.out.println(instance.isValid("(]"));
        System.out.println(instance.isValid("()("));
        System.out.println(instance.isValid("("));
        System.out.println(instance.isValid(")("));
    }

    // v.3
    private boolean isValid(String s) {
        if (s.isBlank() | s.length() % 2 != 0) return false;
        if (s.startsWith(")") | s.startsWith("]") | s.startsWith("}")) return false;

        boolean check;
        do {
            s = s.replaceAll("\\(\\)", "");
            s = s.replaceAll("\\[]", "");
            s = s.replaceAll("\\{}", "");
            check = s.contains("()") || s.contains("[]") || s.contains("{}");
        } while (check);
        return s.isBlank();
    }

    // LC more runtime efficient solution
    public boolean isValidMoreEfficient(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == ')' || ch == ']' || ch == '}') {
                if (deque.isEmpty())
                    return false;

                if (ch == ')' && deque.peek() == '(')
                    deque.pop();
                else if (ch == ']' && deque.peek() == '[')
                    deque.pop();
                else if (ch == '}' && deque.peek() == '{')
                    deque.pop();
                else
                    return false;
            } else {
                deque.push(ch);
            }
        }
        return deque.isEmpty();
    }

    // v.2 initial method - was not covering all cases
    private boolean notCoveringAllCases(String s) {
        if (s.isBlank() | s.length() % 2 != 0) return false;
        if (s.startsWith(")") | s.startsWith("]") | s.startsWith("}")) return false;

        Stack<String> stack = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            if (!(s.startsWith("()", i) | s.startsWith("[]", i) | s.startsWith("{}", i))) {
                stack.push(s.substring(i, i + 1));
                i++;
            } else {
                i = i + 2;
            }
        }

        for (int j = 0; j < stack.size(); j++) {
            String pop = stack.pop();
            if (!(stack.elementAt(j).concat(pop).equals("()") |
                    stack.elementAt(j).concat(pop).equals("[]") |
                    stack.elementAt(j).concat(pop).equals("{}"))) {
                return false;
            }
        }
        return true;
    }

    // v.1 only for cases like [] () {} ()[]{} etc
    private boolean isValidSimpleCase(String s) {
        String[] split = s.split("");
        if (split.length % 2 != 0) return false;
        for (int i = 0; i < split.length; i += 2) {
            if (!(split[i].equals("(") | split[i].equals("[") | split[i].equals("{"))) {
                return false;
            }
            if (split[i].equals("(") && !split[i + 1].equals(")")) {
                return false;
            } else if (split[i].equals("[") && !split[i + 1].equals("]")) {
                return false;
            } else if (split[i].equals("{") && !split[i + 1].equals("}")) {
                return false;
            }
        }
        return true;
    }

    // this regex only works for some cases
    private boolean isValidRegex(String s) {
        return s.matches("^(\\([^()]*\\)|\\{[^{}]*}|\\[[^\\[\\]]*])*$");
    }
}
