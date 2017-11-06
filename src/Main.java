import java.util.Scanner;

/**
 * @author jianjunhuang.me@foxmail.com
 * @since 2017/11/5
 */
public class Main {
    public static void main(String args[]) {
        System.out.println("输入 q 退出");
        Scanner scanner = new Scanner(System.in);
        LexicalAnalysis lexicalAnalysis = new LexicalAnalysis();
        String lexical = "";
        while (!"q".equals(lexical = scanner.nextLine())) {
            lexicalAnalysis.analysis(lexical);
        }
    }
}
