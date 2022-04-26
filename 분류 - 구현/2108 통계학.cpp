#include <bits/stdc++.h>
#define pp pair<int,int> 

using namespace std;

bool compare(pp a, pp b) {
	if (a.second == b.second) return a.first < b.first;
	return a.second > b.second;
}

map<int, int> m;

int main(void) {
	int n;
	cin >> n;
	vector<int> v;

	double sum = 0;
	int temp;

	for (int i = 0; i < n; i++) {
		cin >> temp;
		sum += temp;
		v.push_back(temp);
		m[temp]++;
	}
	sort(v.begin(), v.end());

	vector<pp> vp(m.begin(), m.end()); //from map to vector
	sort(vp.begin(), vp.end(), compare); // sorting by second elements 

	sum = floor((sum / n) + 0.5); // 산술평균

	int mid = v[n / 2]; //중간값
	int fre = 0; 
	int ran = v[n - 1] - v[0]; //최대값 - 최소값

	if (vp.size() > 2) {
		if (vp[0].second == vp[1].second) fre = vp[1].first;
		else fre = vp[0].first;
	}
	else fre = vp[0].first;


	cout << sum << '\n';
	cout << mid << '\n';
	cout << fre << '\n';
	cout << ran << '\n';

	return 0;
}