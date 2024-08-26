import java.util.*;
import java.io.*;

public class Main {

    public static List<ArrayList<Integer>> tree = new ArrayList<>();
    public static List<Integer> removedNodes = new ArrayList<>();
    public static int rootNode = -1;
    public void solution() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Integer numOfNodes = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Integer removeNode = Integer.parseInt(br.readLine());

        for (int i = 0; i < numOfNodes; i++) {
            tree.add(new ArrayList<>());
        }

        // 2 차원 리스트로 트리를 구성한다.
        for (int i = 0; i < numOfNodes; i++) {
            int node = Integer.parseInt(st.nextToken());
            // root node 일 경우
            if(node == -1) {
                rootNode = i;
                continue;
            }

            // 그 외의 노드일 경우 -> 부모에게 귀속된다.
            tree.get(node).add(i);
        }

        // 지울 노드들을 체크한다.
        removedNodes.add(removeNode);
        removeBfs(removeNode);
//        System.out.println("removedLeafs = " + removedNodes.size());

        System.out.println(bfs(rootNode));
    }

    public void removeBfs(int startNode) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(startNode);

        while(!queue.isEmpty()){
            int node = queue.pollFirst();

            // 자식의 개수를 체크한다. -> 자식 노드들이 지워진 노드에 없는지 체크한다.
            int numOfChild = tree.get(node).size();

            // 해당 tree 가 leaf 노드일때.
            if(numOfChild == 0) {
                removedNodes.add(node);
                continue;
            }

            for(int i=0; i<numOfChild; i++){
                queue.addLast(tree.get(node).get(i));
            }
        }
    }

    public int bfs(int startNode) {
        if(removedNodes.contains(rootNode)) return 0;

        int leafs = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(startNode);

        while(!queue.isEmpty()){
            int node = queue.pollFirst();
            List<Integer> existChild = new ArrayList<>();
            int numOfChild = 0;

            // 실질적인 자식의 개수를 체크한다. -> 자식 노드들이 지워진 노드에 없는지 체크한다.
            for(int i=0; i<tree.get(node).size(); i++){
                int childNode = tree.get(node).get(i);
                if(!removedNodes.contains(childNode)){
                    numOfChild++;
                    existChild.add(childNode);
                }
            }

            // 해당 tree 가 leaf 노드일때.
            if(numOfChild == 0) {
                leafs++;
            }

            for (Integer childNode : existChild) {
                queue.addLast(childNode);
            }
        }

        return leafs;
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }

}