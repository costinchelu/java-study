package problems.luxcity;


import java.util.Stack;

/**
 * Assume "?" is like a backspace in string.
 * This means that string "j?dew?u?v" actually is "dev"
 *
 * Your task is to process a string with "?" symbols.
 *
 * In the input:    s - string
 *  At the output:  string
 *
 *  Example:
 * 1.       "jn?y??bd" -->  "bd"
 * 2.       "jkn????h??" -->  ""
 * 3.       "" -->  ""
 */
class BackspaceInString {

    public static String applyBackspaces(String s) {
        String[] split = s.split("");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < split.length; i++) {
            stack.push(split[i]);
            if (split[i].equals("?")) {
                stack.pop();
                if (!stack.empty()) {
                    stack.pop();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String ss: stack) {
            sb.append(ss);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(applyBackspaces("jn?y??bd"));
        System.out.println(applyBackspaces("jkn????h??"));
        System.out.println(applyBackspaces(""));
    }
}
