#include <bits/stdc++.h>

using namespace std;
    
int main(void) {
    
    int n;
    cin >> n;
    
    vector<int> arr;
    while (n > 0) {
        arr.push_back(n % 10);
        n /= 10;
    }

    sort(arr.begin(), arr.end(),greater<>());

    for (int num : arr) cout << num;
    return 0;
}