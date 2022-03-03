#include <bits/stdc++.h>
using namespace std;

const int SIZE = 10;
bool possible;

struct TrieNode {
	TrieNode* Node[SIZE];
	bool isEnd;
	
	TrieNode() {
		isEnd = false;
		for (int i = 0; i < SIZE; i++) Node[i] = nullptr;
	}

	void Insert(const string& key, int index) {

		if (index == key.length()) {
			isEnd = true;
			return;
		}

		if (isEnd) {
			possible = false;
		}

		else {
			int next = key[index] - '0';
			if (Node[next] == nullptr) {
				Node[next] = new TrieNode();
			}
			Node[next]->Insert(key, index + 1);

		}
	}
};


int main(void) {

	int t;
	cin >> t;

	while(t--) {
		int n;
		string str;
		TrieNode root;
		vector<string> vs;

		possible = true;

		cin >> n;

		for (int i= 0; i < n; i++) {
			cin >> str;
			vs.push_back(str);
		}
		sort(vs.begin(), vs.end());

		for (auto val : vs) {
			root.Insert(val,0);
		}

		if (possible) cout << "YES\n"; //일관성 o
		else cout << "NO\n"; //일관성 x
	}

	return 0;
}