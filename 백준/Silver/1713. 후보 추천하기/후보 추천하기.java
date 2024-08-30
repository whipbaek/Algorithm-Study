import java.util.*;
import java.io.*;

public class Main {

    public class Photo{
        public int count;
        public int sequence;

        public Photo(int count, int sequence) {
            this.count = count;
            this.sequence = sequence;
        }
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int globalSequence = 0;
        int frameCount = Integer.parseInt(br.readLine());
        int recommendCount = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<Integer, Photo> frames = new HashMap<>();

        for (int i = 0; i < recommendCount; i++, globalSequence++) {
            int candidate = Integer.parseInt(st.nextToken());

            // 프레임이 모두 채워졌을 때
            if(frames.size() == frameCount && !frames.containsKey(candidate)){
                int targetCandidate = -1;
                int minimumCount = Integer.MAX_VALUE;
                int oldSequence = Integer.MAX_VALUE;

                for (Map.Entry<Integer, Photo> integerPhotoEntry : frames.entrySet()) {
                    int currentCandidate = integerPhotoEntry.getKey();
                    int currentCount = integerPhotoEntry.getValue().count;
                    int currentSequence = integerPhotoEntry.getValue().sequence;

                    // 추천수를 비교한다.
                    if(currentCount <= minimumCount){
                        if (currentCount == minimumCount) { // 만약 추천수가 동일할 경우
                            if(currentSequence < oldSequence) { // 더욱 오래된거 -> sequence 값이 작은 거
                                targetCandidate = currentCandidate;
                                oldSequence = currentSequence;
                            }
                            continue;
                        }

                        // 추천수가 더 적을경우
                        targetCandidate = currentCandidate;
                        minimumCount = currentCount;
                        oldSequence = currentSequence;
                    }
                }
                frames.remove(targetCandidate);
                frames.put(candidate, new Photo(1, globalSequence));
                continue;
            }

            // 프레임에 여유가 있는 경우

            if(frames.containsKey(candidate)){ //이미 프레임에 걸려있다면
                Photo photo = frames.get(candidate);
                photo.count +=1;
                frames.put(candidate, photo);
                continue;
            }

            frames.put(candidate, new Photo(1, globalSequence));
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.addAll(frames.keySet());

        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
    }


    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
