import java.util.*;
import java.io.*;

public class Main {

    public static Map<Integer, Integer> map = new HashMap<>();

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int count = 0;
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        List<ArrayList<String>> list = new ArrayList<>();
        List<StringBuffer> strs = new ArrayList<>();


        // 문자열 리스트 세팅
        for (int i = 0; i < r; i++) {
            list.add(new ArrayList<>(Arrays.asList(br.readLine().split(""))));
        }

        for (int i = 0; i < c; i++) {
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < r; j++) {
                sb.append(list.get(j).get(i));
            }
            strs.add(sb);
        }


        for (int i=1; i<r; i++) {
            Set<String> set = new HashSet<>();
            for (StringBuffer str : strs) {
                set.add(str.substring(i));
            }
            if(set.size() != c) break;
            count++;
        }

        System.out.println(count);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }


}
