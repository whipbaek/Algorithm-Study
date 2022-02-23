#include <bits/stdc++.h>
using namespace std;

int main(void) {
	 
	string s;
	cin >> s;

	int cnt0, cnt1;
	cnt0 = cnt1 = 0;

	for (int i = 0; i < s.size(); i++) {
		if (s[i] == '0') {
			while (s[i] == '0') {
				i++;
			}
			cnt0++;
		}
	}

	for (int i = 0; i < s.size(); i++) {
		if (s[i] == '1') {
			while (s[i] == '1') {
				i++;
			}
			cnt1++;
		}
	}

	if (cnt0 < cnt1) cout << cnt0 << '\n';
	else cout << cnt1 << '\n';


	return 0;
}