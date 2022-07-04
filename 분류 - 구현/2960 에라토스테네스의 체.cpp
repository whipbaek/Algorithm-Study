#include <bits/stdc++.h>
using namespace std;

bool possible[1001];

int main(void) {

	int n, k;
	cin >> n >> k;

	possible[0] = true;
	possible[1] = true;

	int cnt = 0;

	for (int i = 2; i <= n; i++) {
		if (!possible[i]) { // 소수, P값이면
			possible[i] = true;
			cnt++;
			if (cnt == k) {
				cout << i << '\n';
				return 0;
			}
			for (int j = i * i; j <= n; j += i) {

				if (!possible[j]) { //지워지지 않은 배수라면
					possible[j] = true;
					cnt++;
					if (cnt == k) {
						cout << j << '\n';
						return 0;
					}
				}
			}
		}
	}

	return 0;
}