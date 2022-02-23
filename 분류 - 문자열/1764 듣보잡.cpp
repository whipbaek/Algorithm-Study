#include <bits/stdc++.h>
using namespace std;

int main(void) {

    int n, m;
    string str;
    set<string> s;
    vector<string> v;

    cin >> n >> m;

    while (n--) {
        cin >> str;
        s.insert(str);
    }

    while (m--) {
        cin >> str;
        if (s.find(str) != s.end())
            v.push_back(str);
    }
    sort(v.begin(), v.end());

    cout << v.size() << '\n';
    for (string ans : v) cout << ans << "\n";

    return 0;
}