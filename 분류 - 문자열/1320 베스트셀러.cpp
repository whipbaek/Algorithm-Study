#include <bits/stdc++.h>
#define pp pair<string,int>
using namespace std;

bool compare(pp a, pp b) {
	if (a.second == b.second) return a.first < b.first;
	return a.second > b.second;
}

int main(void) {

	map<string, int> m;

	int t;
	string str;

	cin >> t;

	while (t--) {
		cin >> str;
		m[str] += 1;
	}

	vector<pp> v(m.begin(), m.end());
	sort(v.begin(), v.end(),compare);

	cout << v[0].first << '\n';

	return 0;
}