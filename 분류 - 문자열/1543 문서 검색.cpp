#include <bits/stdc++.h>
using namespace std;

int main(void) {

	string doc, dupl;

	getline(cin, doc);
	getline(cin, dupl);

	int idx = 0;
	int ans = 0;
	while ( doc.find(dupl, idx) != string::npos) {
		idx = doc.find(dupl, idx) + dupl.size();
		ans++;
	}

	cout << ans << '\n';

	return 0;
}