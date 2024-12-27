import java.util.*;

class Solution {
        static class Node {
        public int idx;
        public int val;

        public Node(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }

    public List<Integer> solution(String[] operations) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> answer = new ArrayList<>();

        PriorityQueue<Node> minQueue = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        PriorityQueue<Node> maxQueue = new PriorityQueue<>((o1, o2) -> o2.val - o1.val);

        Map<Integer, Set<Integer>> map = new HashMap<>();
        int idx = 1;
        for (String operation : operations) {
            String[] splitValue = operation.split(" ");
            String operator = splitValue[0];
            int value = Integer.parseInt(splitValue[1]);

            // 데이터 큐에 삽입
            if (operator.equals("I")) {

                // queue에 동시에 데이터 삽입한다.
                minQueue.add(new Node(idx, value));
                maxQueue.add(new Node(idx, value));

                // map key 값이 없는 경우
                if(!map.containsKey(value)){

                    Set<Integer> set = new HashSet<>();
                    set.add(idx);
                    map.put(value, set);

                    idx++;
                    continue;
                }

                // map key 값이 있는 경우 idx를 세팅해준다.
                map.get(value).add(idx);
                idx++;
            }

            // 연산일경우 처리
            if(operator.equals("D")){

                // 최대값 삭제
                if(value == 1){
                    while(!maxQueue.isEmpty()){
                        Node peek = maxQueue.peek();

                        // 해당값의 idx가 없는경우 -> 이미 minQueue 에서 삭제 된 값.
                        if (!map.get(peek.val).contains(peek.idx)) {
                            maxQueue.poll();
                            continue;
                        }

                        // map내 set에서 없앤다.
                        map.get(peek.val).remove(peek.idx);
                        maxQueue.poll();
                        break;
                    }
                }

                // 최소값 삭제
                if(value == -1){
                    while(!minQueue.isEmpty()){
                        Node peek = minQueue.peek();

                        // 해당값의 idx가 없는경우 -> 이미 maxQueue 에서 삭제 된 값.
                        if (!map.get(peek.val).contains(peek.idx)) {
                            minQueue.poll();
                            continue;
                        }

                        // map내 set에서 없앤다.
                        map.get(peek.val).remove(peek.idx);
                        minQueue.poll();
                        break;
                    }
                }
            }
        }

        if(maxQueue.isEmpty() || minQueue.isEmpty()){
            answer.add(0);
            answer.add(0);
            return answer;
        }

        while(true){
            if(!map.get(maxQueue.peek().val).contains(maxQueue.peek().idx)){
                maxQueue.poll();
                continue;
            }
            answer.add(maxQueue.poll().val);
            break;
        }

        while(true){
            if(!map.get(minQueue.peek().val).contains(minQueue.peek().idx)){
                minQueue.poll();
                continue;
            }
            answer.add(minQueue.poll().val);
            break;
        }


        return answer;
    }
}