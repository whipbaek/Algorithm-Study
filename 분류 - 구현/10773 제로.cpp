#include <bits/stdc++.h>
using namespace std;

int main(void) {
	
	int k, temp;
	int sum = 0;
	cin >> k;

	vector<int> vec;

	while (k--) {
		cin >> temp;
		if (!vec.empty() && temp == 0) 	vec.erase(vec.begin() + vec.size() - 1);
		else vec.push_back(temp);
	}

	for (int val : vec) sum += val;
	cout << sum << '\n';
		
	return 0;
}