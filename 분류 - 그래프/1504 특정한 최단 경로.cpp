#include <bits/stdc++.h>
#define INF 10e6
#define MAXVAL 1000
typedef long long ll;
using namespace std;

int N, E;
vector<pair<int, int>> graph[MAXVAL];
ll result[MAXVAL];

void resetVals() {
    for (int i = 0; i < MAXVAL; i++) result[i] = INF;
}

void dijkstra(int start) {
    priority_queue<pair<int, int>> pq; // {가중치, 정점}
    result[start] = 0; //시작점에서는 가중치 0
    pq.push({ 0, start });

    while (!pq.empty()) {
        ll distance = -pq.top().first;
        int node = pq.top().second;
        pq.pop();

        if (result[node] < distance) continue;

        for (int i = 0; i < graph[node].size(); i++) {
            ll nextDistance = distance + graph[node][i].second;
            int nextNode = graph[node][i].first;

            if (nextDistance < result[nextNode]) {
                result[nextNode] = nextDistance;
                pq.push({ -nextDistance, nextNode });
            }
        }
    }

}

int main(void) {

    cin >> N >> E; // N: 정점의 개수 , E: 간선의 개수 

    for (int i = 1; i <= E; i++) {
        int start, to, val;

        cin >> start >> to >> val;

        graph[start].push_back({ to,val }); //start 정점에서 to로 가는데 가중치는 val 이다.
        graph[to].push_back({ start, val });
    }

    int v1, v2;
    cin >> v1 >> v2; //필히 거쳐야 하는 정점
    resetVals();
    dijkstra(1);
    
    int stov1, stov2, v1tov2, v1toe, v2toe;

    stov1 = result[v1];
    stov2 = result[v2];

    resetVals();
    dijkstra(v1);

    v1tov2 = result[v2];
    v1toe = result[N];

    resetVals();
    dijkstra(v2);

    v2toe = result[N];

    int answer = min((stov1 + v1tov2 + v2toe), (stov2 + v1tov2 + v1toe));
    if (answer >= INF) cout << -1 << '\n';
    else cout << answer << '\n';

    return 0;
}