#include <bits/stdc++.h>
using namespace std;

bool self[100001];

int func(int n) {
	
	int sum = n;

	while (n != 0) {
		sum += n % 10;
		n /= 10;
	}

	self[sum] = true;

	return sum;
}


int main(void) {

	for (int i = 1; i <= 10000; i++) {
		func(i);
	}

	for (int i = 1; i <= 10000; i++) {
		if (!self[i]) cout << i << '\n';
	}

	return 0;
}