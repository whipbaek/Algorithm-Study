#include <bits/stdc++.h>
using namespace std;

int main(void) {

	string s1, s2; //len.s1 < len.s2
	cin >> s1 >> s2;

	int ans = 51;

	for (int i = 0; i <= s2.size() - s1.size(); i++) {
		int idx = 0;
		int temp = 0;

		for (int j = i; j < i + s1.size(); j++ ) {
			if (s2[j] != s1[idx++])
				temp++;
		}
		ans = min(temp, ans);
	}

	cout << ans << '\n';

	return 0;
}