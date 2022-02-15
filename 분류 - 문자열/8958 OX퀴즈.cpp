#include <bits/stdc++.h>
using namespace std; 

int main(void) {

	int t;
	cin >> t;
	string str;

	while (t--) {
		cin >> str;
		int cnt = 1;
		int answer = 0;
		for (int i = 0; i < str.size(); i++) {
			if (str[i] == 'O') answer += cnt++;
			else cnt = 1;
		}
		cout << answer << "\n";
	}

	return 0;
}