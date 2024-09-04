import java.util.*;
import java.io.*;

public class Main {

    public class Lecture{
        public int startTime;
        public int endTime;

        public Lecture(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int roomCount = 1;
        int lectureCount = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        List<Lecture> lectures = new ArrayList<>();

        for (int i = 0; i < lectureCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int lectureNumber = Integer.parseInt(st.nextToken());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            lectures.add(new Lecture(startTime, endTime));
        }

        //정렬하기
        Collections.sort(lectures, (Lecture l1, Lecture l2) -> {
            if(l1.startTime != l2.startTime){
                return Integer.compare(l1.startTime, l2.startTime);
            } else{
                return Integer.compare(l1.endTime, l2.endTime);
            }
        });


        for (Lecture lecture : lectures) {

            // 시작하는 순서로 정렬을 한다.
            // for 문을 돌면서, pq를 넣어준다.
            // 순서를 넣을때 pq top 이 시작시간보다 작거나 같다면, 강의실 추가 안해줘도 된다.
            // 크다면 강의실 추가 해준다.
            if (pq.isEmpty()) {
                pq.add(lecture.endTime);
                continue;
            }
            // 끝나는 시간 6, 시작시간 5 불가능!
            if(pq.peek() > lecture.startTime){
                pq.add(lecture.endTime);
                roomCount++;
                continue;
            }
            pq.poll();
            pq.add(lecture.endTime);
        }
        System.out.println(roomCount);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

}
