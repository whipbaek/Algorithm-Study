import java.util.*;

class Solution {
    
        static class Genre {
        public String genreName;
        public int totalPlayCnt;

        public Genre(String genreName, int totalPlayCnt) {
            this.genreName = genreName;
            this.totalPlayCnt = totalPlayCnt;
        }
    }

    static class Music  {
        public int personalNum;
        public int playCnt;

        public Music(int personalNum, int playCnt) {
            this.personalNum = personalNum;
            this.playCnt = playCnt;
        }
    }
    
        public List<Integer> solution(String[] genres, int[] plays) throws Exception {

        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> genreMap = new HashMap<>();     // 장르별 총 재생 횟수 Map
        Map<String, List<Music>> musicMap = new HashMap<>(); // 장르내 음악들의 고유번호와 재생 횟수 Map

        for (int i = 0; i < genres.length; i++) {

            String genreName = genres[i];
            int playCnt = plays[i];

            if(genreMap.containsKey(genreName)){
                genreMap.put(genreName, genreMap.get(genreName) + playCnt);
                musicMap.get(genreName).add(new Music(i, playCnt));
            } else{
                genreMap.put(genreName, playCnt);

                List<Music> list = new ArrayList<>();
                list.add(new Music(i, playCnt));
                musicMap.put(genreName, list);
            }
        }

        // Map 을 pq 로 변환
        PriorityQueue<Genre> pq = new PriorityQueue<>(((o1, o2) -> o2.totalPlayCnt - o1.totalPlayCnt));

        for (String s : genreMap.keySet()) {
            pq.add(new Genre(s, genreMap.get(s)));
        }


        // pq 에서 값이 큰 것부터 꺼낸다.
        while(!pq.isEmpty()){
            Genre poll = pq.poll();
            String genreName = poll.genreName;

            List<Music> musicList = musicMap.get(genreName);

            musicList.sort((o1, o2) -> {
                if(o1.playCnt == o2.playCnt){
                    return o1.personalNum - o2.personalNum;
                }
                return  o2.playCnt - o1.playCnt;
            });

            if(musicList.size() < 2){
                answer.add(musicList.get(0).personalNum);
                continue;
            }

            answer.add(musicList.get(0).personalNum);
            answer.add(musicList.get(1).personalNum);
        }

        return answer;

    }
}