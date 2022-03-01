#include <bits/stdc++.h>
using namespace std;

int main(void) {

	string s;
	getline(cin, s);
	string::iterator it_end;
	for (string::iterator it = s.begin(); it != s.end(); it++){
		bool in = false;
		if (*it == '<') {
			while (*it != '>') {
				it++;
			}
		}

		else {
			it_end = it;

			while (1) {
				if (it_end == s.end()) break;
				if (*it_end == ' ')break;
				if (*it_end == '<') {
					in = true;
					break;
				}
				it_end++;
			}
			reverse(it, it_end);	
			if (it_end == s.end()) break;
			
			if (in)	it = it_end - 1;
			else it = it_end;
		}
	}
	

	cout << s << '\n';
	

	return 0;
}