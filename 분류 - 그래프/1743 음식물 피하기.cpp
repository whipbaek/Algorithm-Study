#include <bits/stdc++.h>
using namespace std;

int arr[101][101];
int check[101][101];
int nx[] = { -1, 0, 1, 0 };
int ny[] = { 0, 1, 0, -1 };
int n, m, k;

int bfs(int x, int y) {
	int val = 1;
	queue<pair<int, int>> q;
	check[x][y] = true;
	q.push(make_pair(x, y));

	while (!q.empty()) {
		x = q.front().first;
		y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int dx = x + nx[i];
			int dy = y + ny[i];

			if (dx >= 0 && dx < n && dy >= 0 && dy < m) {
				if (arr[dx][dy] && !check[dx][dy]) {
					q.push(make_pair(dx, dy));
					check[dx][dy] = true;
					val++;
				}
			}
		}
	}

	return val;
}

int main(void) {
	
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	int ans = 0;

	cin >> n >> m >> k;

	for (int i = 0; i < k; i++) {
		int x, y;
		cin >> x >> y;
		arr[x-1][y-1] = 1;
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (!check[i][j] && arr[i][j]) { //방문하지 않았고, 음식물인 경우
				ans = max(ans,bfs(i, j));
			}
		}
	}

	cout << ans << '\n';

}