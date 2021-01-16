# 배열

## 정의와 성질

> 배열은 메모리 상에 원소를 연속하게 배치한 자료구조

- O(1)에 k번째 원소를 확인/변경 가능
- 추가적으로 소모되는 메모리의 양 <sup>overhead</sup>가 거의 없음
- Cache hit rate가 높음
- 메모리 상에 연속한 구간을 잡아야해서 할당에 제약이 걸림

## 기능과 구현

- 임의의 위치에 있는 원소를 확인/변경 → `O(1)`
- 원소를 끝에 추가 → `O(1)`
- 마지막 원소를 제거 → `O(1)`
- 임의의 위치에 원소를 추가/제거 → **`O(N)`**

> 배열에 요소 추가와 제거 구현

```c++ 
void insert(int idx, int num, int arr[], int& len) {
	for(int i = len; i > idx; i--) {
		arr[i] = arr[i - 1];
	}
	arr[idx] = num;
	len++;
}

void erase(int idx, int arr[], int& len) {
	len--;
	for(int i = idx; i < len; i++) {
		arr[i] = arr[i + 1];
	}
}
```

> C++에서 배열 전체를 특정값으로 초기화시키는 방법들

```c++
int a[21];
int b[21][21];

// 1. memset
//    0 이나 -1 이 아닌 다른 값을 넣으면 오동작하거나,
//    2차원 이상의 배열을 함수 인자로 넘겨 그곳에서 memset을 하면 잘못 들어간다 하는 문제점이 있으므로
//    비추천!!
memset(a, 0, sizeof a);
memset(b, 0, sizeof b);

// 2. for
for(int i = 0; i < 21; i++)
  a[i] = 0;
for(int i = 0; i < 21; i++)
  for(int j = 0; j <21; j++)
    b[i][j] = 0;

// 3. fill - algorithm 헤더의 fill 함수
fill(a, a+21, 0);
for(int i = 0; i < 21; i++)
  fill(b[i], b[i]+21, 0)
```

## STL vector

> [레퍼런스 사이트](http://www.cplusplus.com/reference/vector/vector/)

```c++
#include <bits/stdc++.h>
using namespace std;

int main(void) {
  vector<int> v1(3, 5); // {5,5,5};
  cout << v1.size() << '\n'; // 3
  v1.push_back(7); // {5,5,5,7};

  vector<int> v2(2); // {0,0};
  v2.insert(v2.begin()+1, 3); // {0,3,0};

  vector<int> v3 = {1,2,3,4}; // {1,2,3,4}
  v3.erase(v3.begin()+2); // {1,2,4};

  vector<int> v4; // {}
  v4 = v3; // {1,2,4}
  cout << v4[0] << v4[1] << v4[2] << '\n';
  v4.pop_back(); // {1,2}
  v4.clear(); // {}
}
```

### vector 내 순회

```c++
vector<int> v1 = {1, 2, 3, 4, 5, 6};

// 1. range-based for loop (since C++11)
for(int e : v1)
  cout << e << ' ';

// 2. not bad
for(int i = 0; i < v1.size(); i++)
  cout << v1[i] << ' ';

// 3. ***WRONG***
//     size 메소드의 반환값이 unsigned int이기 때문에 v1이 빈 vector일 때,
//     v1.size - 1 이 unsiged int 0에서 int 1을 빼는 식이 되어
//     -1 이 아닌 unsigned int overflow로 인해 4294967295와 같은 이상한 값이 나온다.
for(int i = 0; i <= v1.size-1; i++)
  cout << v1[i] << ' ';
```

## 연습문제

### BOJ 10808번: 알파벳 갯수

```c++  
#include <bits/stdc++.h>
using namespace std;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	string s;
	cin >> s;
	for (char a = 'a'; a <= 'z'; a++) {
		int cnt = 0;
		for (auto c : s)
			if (a == c) cnt++;
		cout << cnt << ' ';
	}
}
```

```c++
#include <bits/stdc++.h>
using namespace std;

int freq[26];
int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	string s;
	cin >> s;
	for (auto c : s)
		freq[c - 'a']++;
	for (int i = 0; i < 26; i++)
		cout << freq[i] << ' ';
}
```

### 주어진 길이 N의 int 배열 arr에서 합이 100인 서로 다른 위치의 두 원소가 존재하는지 확인하는 함수

```c++
#include <bits/stdc++.h>
using namespace std;

int func2(int arr[], int N) {
	int exist[101] = {};
	for (int i = 0; i < N; i++) {
		if (exist[100 - arr[i]] == 1)
			return 1;
		exist[arr[i]] = 1;
	}
	return 0;
}

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	int arr[5] = { 1, 23, 53, 77, 60 };
	cout << func2(arr, 5);
}
```