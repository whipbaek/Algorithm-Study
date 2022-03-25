#include <bits/stdc++.h>
using namespace std;

void printv(vector <pair<int, int>> v, int idx) {
	cout << "\n idx : " << idx << '\n';
	cout << "출력\n";

	for (auto val : v) {
		cout << val.first << "(" << val.second << ") ";
	} cout << "\n\n";
}

int main(void) {
	
	int t;
	cin >> t;

	while (t--) {
		int size, idx, val;
		cin >> size >> idx;

		vector<pair<int,int>> v(size);
		
		for (int i = 0; i < size; i++) {
			int temp;
			cin >> temp;
			v[i] = make_pair(temp, i); //temp는 값, i는 idx
		}
	
		int i = 0;

		while (i<size) {
			int cnt = 0;
			for (int j = i + 1; j < size; j++) {
				if (v[i].first < v[j].first) {
					cnt++;
					v.push_back(make_pair(v[i].first, v[i].second));
					v.erase(v.begin()+(i));
					break;
				}
			}
			if (cnt == 0) {
				i++;
			}

		}

		for (int i = 0; i < size; i++) {
			if (v[i].second == idx) {
				cout << i + 1 << '\n';
				break;
			}
		}

	}

	return 0;
}