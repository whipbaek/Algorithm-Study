#include <bits/stdc++.h>
using namespace std;

/*

	[0][0] * [0][0] + [0][1] * [1][0]
	[0][0] * [0][1] + [0][1] * [1][1]
	[0][0] * [0][2] + [0][1] * [1][2]

	[1][0] * [0][0] + [1][1] * [1][0]
	[1][0] * [0][1] + [1][1] * [1][1]
	[1][0] * [0][2] + [1][1] * [1][2]

	[2][0] * [0][0] + [2][1] * [1][0]
	[2][0] * [0][1] + [2][1] * [1][1]
	[2][0] * [0][2] + [2][1] * [1][2]

*/

int main(void) {
	
	int n, m, k, t;

	cin >> n >> m;

	vector<vector<int>> v1, v2, res;
	
	for (int i = 0; i < n; i++) {
		vector<int> temp;
		for (int j = 0; j < m; j++) {
			cin >> t;
			temp.push_back(t);
		}
		v1.push_back(temp);
	}

	cin >> m >> k;

	for (int i = 0; i < m; i++) {
		vector<int> temp;
		for (int j = 0; j < k; j++) {
			cin >> t;
			temp.push_back(t);
		}
		v2.push_back(temp);
	}
	
	for (int i = 0; i < n; i++) {
		vector<int> temp;
		for (int j = 0; j < k; j++) {
			int val = 0;
			for (int l = 0; l < m; l++) {
				val += v1[i][l] * v2[l][j];
			}
			temp.push_back(val);
		}
		res.push_back(temp);
	}
	
	for (vector<int> v : res) {
		for (int val : v) {
			cout << val << " ";
		}
		cout << '\n';
	}

	return 0;
}