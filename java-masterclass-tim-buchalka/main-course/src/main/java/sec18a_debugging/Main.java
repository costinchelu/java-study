package sec18a_debugging;

public class Main {

    public static void main(String[] args) {
        StringUtilities utils = new StringUtilities();
        StringBuilder sb = new StringBuilder();
        while (utils.getsBuilder().length() < 10) {
            utils.addChar(utils.getsBuilder(), 'a');
            sb.append(utils.getsBuilder());
        }
        System.out.println(sb);

        String str = "abcdef";
        String result = utils.upperAndPrefix(utils.addSuffix(str));
        System.out.println(result);
    }
}
