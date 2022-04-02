#include <bits/stdc++.h>
using namespace std;

int main(void) {

	vector<pair<int,bool>> v;
	vector<int> answer;

	int n, k;
	cin >> n >> k;

	for (int i = 1; i <= n; i++) v.push_back(make_pair(i,false));
	
	int idx = 0;
	int cnt = 0;
	int noft = 0;
	while (1) {
		if (noft == n) break;

		if (v[idx].second == false) cnt++;
		
		if (v[idx].second == false && cnt == k) {
			cnt = 0;
			v[idx].second = true;
			noft++;
			answer.push_back(v[idx].first);
		}

		idx = (idx == n-1) ? 0 : idx + 1;
	}
	cout << "<";
	for (int i = 0; i < answer.size(); i++) {
		if (i == answer.size() - 1) cout << answer[i];
		else cout << answer[i] << ", ";
	}
	cout << "> \n";
	return 0;
}