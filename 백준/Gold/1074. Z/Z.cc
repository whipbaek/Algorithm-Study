#include <bits/stdc++.h>
using namespace std;

int n, r, c;
int ans;

void solve(int x, int y, int n)  {
    if (x == r && y == c) {
        cout << ans << '\n';
        return;
    }

    int m = n / 2;

    //사분면 안에 존재한다면 나누면서 탐색해준다. 
    if (r < x + n && r >= x && c < y + n && c >= y) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                solve(x + m * i, y + m * j, m);
            }
        }
    }

    //존재하지 않을경우 값을 더해주고 넘어간다. 
    else ans += n * n;
    
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> r >> c;
    
    solve(0, 0, (1<<n)); //parameter : x행, y열, 2^n

    return 0;
}