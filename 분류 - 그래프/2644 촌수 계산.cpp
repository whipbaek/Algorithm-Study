#include <bits/stdc++.h>
using namespace std;

int n, m, start, des;
int answer;
bool possible;

bool check[101];
vector<int> v[101];

void dfs(int x, int val) {
	check[x] = true; //정점을 방문함
	for (int i = 0; i < v[x].size(); i++) {
		if (possible) return; //값을 찾았으니 더 이상 탐색할 필요 없음

		int next = v[x][i];
		if (next == des) {
			possible = true;
			answer = val + 1;
			return;
		}


		if (!check[next]) { //아직 방문하지 않은 정점이라면
			dfs(next,val+1);
		}
	}
}

int main(void) {

	cin >> n;
	cin >> des >> start;
	cin >> m;

	for (int i = 0; i < m; i++) {
		int from, to;
		cin >> from >> to;
		v[from].push_back(to);
		v[to].push_back(from);
	}
	dfs(start, 0);
	if (!possible) answer = -1;
	cout << answer << '\n';

}