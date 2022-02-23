#include <bits/stdc++.h>
using namespace std;

int main(void) {

	string s;
	cin >> s;

	vector<string> vs;

	for (int i = 0; i < s.size(); i++) {
		vs.push_back(s.substr(i, s.size()));
	}
	sort(vs.begin(), vs.end());

	for (string val : vs) cout << val << "\n";


	return 0;
}