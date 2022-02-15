#include <bits/stdc++.h>
using namespace std; 

int main(void) {

	vector<int> ans;
	string str; 
	cin >> str;
	char alph = 'a';

	for (int i = 0; i < 26; i++) {
		if (str.find(alph) != string::npos)
			ans.push_back(str.find(alph));
		else
			ans.push_back(-1);

		alph++;
	}

	for (int i = 0; i < ans.size(); i++)
		cout << ans[i] << " ";

	return 0;
}