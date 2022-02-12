#include <bits/stdc++.h>
using namespace std; 

int main(void) {
	vector<string> chro = { "c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=" };
	string str;
	cin >> str;

	for (int i = 0; i < chro.size(); i++) {
		while (1) {
			int idx = str.find(chro[i]);
			if (idx == string::npos) break;

			str.replace(idx, chro[i].size(), "#");
		}
	}
	cout << str.size() << '\n';

	return 0;
}