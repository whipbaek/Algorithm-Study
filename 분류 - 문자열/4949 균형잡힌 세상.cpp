#include <bits/stdc++.h>
using namespace std;

int main(void) {

    while (1) {
        
        stack<char> s;
        bool check = true;
        
        string str;
        getline(cin, str);

        if (str[0] == '.') break;

        for (int i = 0; i < str.size(); i++) {
            if (str[i] == '(' || str[i] == '[') s.push(str[i]);
            else if (str[i] == ')') {
                if (!s.empty() && s.top() == '(') s.pop();
                else {
                    check = false;
                    break;
                }
            }
            else if (str[i] == ']') {
                if (!s.empty() && s.top() == '[') s.pop();
                else {
                    check = false;
                    break;
                }

            }
        }
        if (!s.empty()) check = false;

        if (check) cout << "yes\n";
        else cout << "no\n";
    }
    return 0;
}