import java.util.*;
import java.io.*;

public class Main {


    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split("");
            LinkedList<String> linkedList = new LinkedList<>();
            ListIterator<String> iterator = linkedList.listIterator();


            for (String val : line) {
                if(val.equals("<")){
                    if(iterator.hasPrevious()){
                        iterator.previous();
                    }
                    continue;
                }

                if(val.equals(">")){
                    if(iterator.hasNext()){
                        iterator.next();
                    }
                    continue;
                }

                if(val.equals("-")){
                    if(iterator.hasPrevious()){
                        iterator.previous();
                        iterator.remove();
                    }
                    continue;
                }

                iterator.add(val);
            }

            StringBuilder sb = new StringBuilder();
            for (String s : linkedList) {
                sb.append(s);
            }

            System.out.println(sb);
        }

    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
