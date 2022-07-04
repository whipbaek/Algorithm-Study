#include <bits/stdc++.h>
using namespace std;

int solution(string s) {
	int answer = 0;
	int temp = 1;
	stack<int> st;
	bool possible = true;
	
	for (int i = 0; i < s.size(); i++) {
		if (s[i] == '(') {
			temp *= 2;
			st.push(s[i]);
		}
		else if (s[i] == '[') {
			temp *= 3;
			st.push(s[i]);
		}

		else if (s[i] == ')' && (st.empty() || st.top() != '(')) {
			possible = false;
			break;
		}

		else if (s[i] == ']' && (st.empty() || st.top() != '[')) {
			possible = false;
			break;
		}

		else if (s[i] == ')') {

			if (s[i - 1] == '(')
				answer += temp;
			st.pop();
			temp /= 2;
		}

		else if (s[i] == ']') {
			if (s[i - 1] == '[')
				answer += temp;
			st.pop();
			temp /= 3;
		}
	}
	
	if (!possible || !st.empty()) answer = 0;

	return answer;
}


int main(void) {

	string s;
	cin >> s;

	cout << solution(s) << '\n';

	return 0;
}