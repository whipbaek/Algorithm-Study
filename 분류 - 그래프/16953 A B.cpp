#include <bits/stdc++.h>
using namespace std;
typedef unsigned long long ll;
bool possible = false;

void solution(ll base, ll target, int cnt) {
	
	if (base == target) {
		cout << cnt + 1 << '\n';
		possible = true;
		return;
	}

	if (base > target) {
		return ;
	}

	solution(base * 2, target, cnt + 1);
	solution(base * 10 + 1, target, cnt + 1);

}

int main(void) {

	ll base, target;
	cin >> base >> target;

	solution(base, target, 0);
	if (!possible) cout << -1 << '\n';

	return 0;
}