#include <bits/stdc++.h>
using namespace std;

int main(void) {

	vector<int> ans;
	string str;
	cin >> str;

	for (char i = 'a'; i <= 'z'; i++) {
		if (str.find(i) != string::npos)
			ans.push_back(str.find(i));
		else
			ans.push_back(-1);
	}

	for (int i = 0; i < ans.size(); i++)
		cout << ans[i] << " ";

	return 0;
}
