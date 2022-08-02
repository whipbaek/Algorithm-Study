#include <bits/stdc++.h>
using namespace std;

int main(void) {

	int N, M;

	cin >> N >> M;

	map<string, int> m;

	for (int i = 0; i < N; i++) {
		string str;
		cin >> str;
		m.insert({ str, 0 });
	}

	for (int i = 0; i < M; i++) {
		string str; 
		cin >> str;
		if (m.find(str) != m.end()) {
			m[str]++;
		}
	}

	int res = 0;

	for (auto val : m) res += val.second;
	
	cout << res << '\n';

	return 0;
}