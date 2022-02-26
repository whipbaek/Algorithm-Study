#include <bits/stdc++.h>
using namespace std;

int main(void) {

	string s,t,str;
	cin >> s;
	cin >> t;
	
	for (int i = 0; i < s.size(); i++) {
		bool boom = true;

		str += s[i];
		if (str.size() >= t.size()) {
			int k = 0;
			for (int j = str.size() - t.size(); j < str.size(); j++) {
				if (str[j] != t[k++]) boom = false;
			}

			if (boom) { //폭팔!
				for (int j = 0; j < t.size(); j++) {
					str.pop_back();
				}
			}
		}
	}

	if (str.empty()) cout << "FRULA\n";
	else cout << str << '\n';

}