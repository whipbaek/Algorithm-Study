import java.util.*;
import java.io.*;

public class Main {

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Set<Character> alpha = new HashSet<>();

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            String[] lines = line.split(" ");

            boolean option = false;

            // 우선 첫번째 단어를 확인한다.
            for (int j = 0; j < lines.length; j++) {
                if(alpha.contains(Character.toUpperCase(lines[j].charAt(0)))){
                    continue;
                }
                // 알파벳을 추가한다.
                option = true;
                alpha.add(Character.toUpperCase(lines[j].charAt(0)));
                lines[j] = "[" + lines[j].charAt(0) + "]" + lines[j].substring(1);
                break;
            }

            if(!option) {
                for (int j = 0; j < lines.length; j++) {
                    for (int k = 0; k < lines[j].length(); k++) {
                        if (alpha.contains(Character.toUpperCase(lines[j].charAt(k)))) {
                            continue;
                        }

                        alpha.add(Character.toUpperCase(lines[j].charAt(k)));
                        lines[j] = lines[j].substring(0, k) + "[" + lines[j].charAt(k) + "]" + lines[j].substring(k + 1);
                        option = true;
                        break;
                    }
                    if (option) break;
                }
            }

            // 결과 출력
            for(int j=0; j< lines.length; j++){
                System.out.print(lines[j] + " ");
            }
            System.out.println();
        }

    }


    public static void main(String[] args) throws Exception {
        new Main().solution();
    }


}
