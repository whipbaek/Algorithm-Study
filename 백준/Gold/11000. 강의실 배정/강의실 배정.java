import java.util.*;
import java.io.*;

public class Main {

    public class Lesson {
        public int startTime;
        public int endTime;

        public Lesson(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }


    public void solution() throws Exception {
        int answer = 1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer n = Integer.parseInt(br.readLine());
        List<Lesson> lessons = new ArrayList<>();
        // 시작순으로 정렬, 시작시간이 같다면 끝나는 순으로
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lessons.add(new Lesson(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(lessons, ((o1, o2) -> {
            if(o1.startTime > o2.startTime){
                return 1;
            } else if(o1.startTime < o2.startTime){
                return -1;
            } else {
                return o1.endTime - o2.endTime;
            }
        }));

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (Lesson lesson : lessons) {
            if(pq.isEmpty()){
                pq.add(lesson.endTime);
                continue;
            }

            int closeTime = pq.peek();

            if(closeTime <= lesson.startTime){
                pq.poll();
            }

            if(closeTime > lesson.startTime){
                answer++;
            }

            pq.add(lesson.endTime);
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
