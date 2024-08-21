import java.util.*;
import java.io.*;

public class Main {

    public static boolean isPossible = false;
    public static StringBuffer currentString;

    public void process(StringBuffer targetString) {
        if(isPossible) return;
        if(targetString.length() < currentString.length()) return;
        if(targetString.toString().equals(currentString.toString())){
            isPossible = true;
            return;
        }

        if(targetString.charAt(0) == 'B') {
            StringBuffer temp = new StringBuffer(targetString.toString());
            temp.reverse();
            temp.deleteCharAt(temp.length()-1);
            process(temp);
        }
        if(targetString.charAt(targetString.length()-1) == 'A') {
            StringBuffer temp = new StringBuffer(targetString.toString());
            temp.deleteCharAt(temp.length()-1);
            process(temp);
        }
    }

    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        currentString = new StringBuffer(br.readLine());

        process(new StringBuffer(br.readLine()));

        if(isPossible){
            System.out.println(1);
            return;
        }

        System.out.println(0);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }


}
