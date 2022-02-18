/*
처음에는 reverse함수 사용 -> 시간 초과
vector에서 erase 사용 -> 시간 초과
자료구조를 vector에서 deque로 바꾸고 pop으로 교체 -> 성공
*/

#include <bits/stdc++.h>
using namespace std;

int main(void) {

	int t, n;
	string func;
	string arr;


	int s_num;
	char s_ch;

	cin >> t;

	while (t--) {

		deque<int> d;
		bool error = false;
		bool isreverse = false;

		cin >> func;
		cin >> n;
		cin >> arr;
		stringstream ss(arr);

		s_ch = ss.get();

		
		while (n!=0) {
			if (s_ch == ']') break;
			ss >> s_num;
			d.push_back(s_num);
			ss >> s_ch;
		}


		for (char ch : func) {
			if (ch == 'R') {
				isreverse = !isreverse;
			}

			else if (ch == 'D') {
				if (d.empty()) {
					error = true;
					break;
				}
				else {
					if (isreverse) d.pop_back();
					else d.pop_front();
				}
			}
		}

		if (error) cout << "error\n";
		else {
			cout << "[";
			if (isreverse) {
				for (int i = d.size() - 1; i >= 0; i--) {
					cout << d[i];
					if (i != 0) cout << ',';
				}
			}

			else {
				for (int i = 0; i < d.size(); i++) {
					cout << d[i];
					if (i != d.size() - 1) cout << ',';
				}
			}
			cout << "]\n";
		}
	}
	return 0;
}