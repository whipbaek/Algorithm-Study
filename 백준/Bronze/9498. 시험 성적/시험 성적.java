import java.util.*;
import java.io.*;

public class Main {

    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer score = Integer.parseInt(br.readLine());

        if(score >= 90){
            System.out.println("A");
            return;
        }

        if(score >= 80){
            System.out.println("B");
            return;
        }

        if(score >= 70){
            System.out.println("C");
            return;
        }

        if(score >= 60){
            System.out.println("D");
            return;
        }

        System.out.println("F");

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
