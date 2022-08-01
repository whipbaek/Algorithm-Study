#include <bits/stdc++.h>
#define INF 10e9
#define MAXVAL 1001
using namespace std;

int N, M;
vector<pair<int, int>> graph[MAXVAL];
int result[MAXVAL];

void dijkstra(int start) {
    priority_queue<pair<int, int>> pq; // {가중치, 정점}
    result[start] = 0; //시작점에서는 가중치 0
    pq.push({ 0, start });

    while (!pq.empty()) {
        int distance = -pq.top().first;
        int node = pq.top().second;
        pq.pop();

        if (result[node] < distance) continue;

        for (int i = 0; i < graph[node].size(); i++) {
            int nextDistance = distance + graph[node][i].second;
            int nextNode = graph[node][i].first;

            if (nextDistance < result[nextNode]) {
                result[nextNode] = nextDistance;
                pq.push({ -nextDistance, nextNode });
            }
        }
    }

}

int main(void) {

    cin >> N >> M; // N: 도시의 개수, M: 버스의 개수 

    for (int i = 1; i <= M; i++) {
        int start, to, val;

        cin >> start >> to >> val;

        graph[start].push_back({ to,val }); //start 정점에서 to로 가는데 가중치는 val 이다.
    }

    int start, des;
    cin >> start >> des;

    for (int i = 0; i <= N; i++) {
        result[i] = INF;
    }

    dijkstra(start);

    cout << result[des] << '\n';

    return 0;
}