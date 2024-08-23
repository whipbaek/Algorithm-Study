import java.util.*;
import java.io.*;

public class Main {

    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Integer dnaLen = Integer.parseInt(st.nextToken());
        Integer partialLen = Integer.parseInt(st.nextToken());
        Integer answer = 0;
        String dnaString = br.readLine();
        st = new StringTokenizer(br.readLine());

        Integer minimumA = Integer.parseInt(st.nextToken());
        Integer minimumC = Integer.parseInt(st.nextToken());
        Integer minimumG = Integer.parseInt(st.nextToken());
        Integer minimumT = Integer.parseInt(st.nextToken());

        int currentAlphas[] = new int[100];

        // 알파벳 별로 숫잘르 센다.
        for (int i = 0; i < partialLen; i++) {
            currentAlphas[dnaString.charAt(i)]++;
        }

        if ( currentAlphas['A'] >= minimumA && currentAlphas['C'] >= minimumC &&
             currentAlphas['G'] >= minimumG && currentAlphas['T'] >= minimumT) {
            answer++;
        }

        int frontIdx = 1;
        int backIdx = partialLen;

        while (true) {
            if (backIdx == dnaLen) break;

            currentAlphas[dnaString.charAt(frontIdx-1)]--;
            currentAlphas[dnaString.charAt(backIdx)]++;

            frontIdx++;
            backIdx++;

            if ( currentAlphas['A'] >= minimumA && currentAlphas['C'] >= minimumC &&
                 currentAlphas['G'] >= minimumG && currentAlphas['T'] >= minimumT) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }


}
