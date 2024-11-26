import java.util.*;
import java.io.*;

public class Main {

    public void solution() throws IOException {

          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          int n = Integer.parseInt(br.readLine());

          int[] arr = new int[10001];

          for(int i=0; i<=10000; i++) arr[i] = 1;
          for(int i=2; i<=10000; i++) arr[i] += arr[i-2];
          for(int i=3; i<=10000; i++) arr[i] += arr[i-3];

          for(int i=0; i<n; i++){
              int target = Integer.parseInt(br.readLine());
              System.out.println(arr[target]);
          }

    }


    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}


