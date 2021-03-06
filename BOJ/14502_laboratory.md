# 백준 알고리즘 14502번, ['연구소'](https://www.acmicpc.net/problem/14502)

## 문제

인체에 치명적인 바이러스를 연구하던 연구소에서 바이러스가 유출되었다. 다행히 바이러스는 아직 퍼지지 않았고, 바이러스의 확산을 막기 위해서 연구소에 벽을 세우려고 한다.

연구소는 크기가 N×M인 직사각형으로 나타낼 수 있으며, 직사각형은 1×1 크기의 정사각형으로 나누어져 있다. 연구소는 빈 칸, 벽으로 이루어져 있으며, 벽은 칸 하나를 가득 차지한다. 

일부 칸은 바이러스가 존재하며, 이 바이러스는 상하좌우로 인접한 빈 칸으로 모두 퍼져나갈 수 있다. 새로 세울 수 있는 벽의 개수는 3개이며, 꼭 3개를 세워야 한다.

> 입력

첫째 줄에 지도의 세로 크기 N과 가로 크기 M이 주어진다. (3 ≤ N, M ≤ 8)

둘째 줄부터 N개의 줄에 지도의 모양이 주어진다. 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 위치이다. 2의 개수는 2보다 크거나 같고, 10보다 작거나 같은 자연수이다.

빈 칸의 개수는 3개 이상이다.

> 출력

첫째 줄에 얻을 수 있는 안전 영역의 최대 크기를 출력한다.

## 풀이



## 소스

```java
import java.util.Scanner;
 
public class Test14502 {
  static int N, M;
  static int totalCNT;
  static int[] dx = {0, -1, 0, 1};
  static int[] dy = {-1, 0, 1, 0};
  static int[][] adMatrix, visit;
   
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    M = sc.nextInt();
    adMatrix = new int[N][M];
    visit = new int[N][M];
    for(int i=0; i<N; i++) {
      for(int j=0; j<M; j++) {
        adMatrix[i][j] = sc.nextInt();
      }
    }
    //알고리즘 풀이
    for(int i=0; i<N*M; i++) {
      int row = i/M;
      int col = i%M;
      if(adMatrix[row][col]==0) {
        adMatrix[row][col]=1;
        DFS(i, 1);
        adMatrix[row][col]=0;
      }
    }
    System.out.println(totalCNT);
  }
  //벽 3개 세우기
  static void DFS(int v, int cnt) {
    int row = v/M;
    int col = v%M;
    if(cnt==3) {
      for(int i=0; i<N; i++) {
        for(int j=0; j<M; j++) {
          visit[i][j]=adMatrix[i][j];
        }
      }
      for(int i=0; i<N; i++) {
        for(int j=0; j<M; j++) {
          if(visit[i][j]==2) {
            spreadVirus(i, j);
          }
        }
      }
      safetyArea();
    }else {
      for(int i=v+1; i<N*M; i++) {
        int row2 = i/M;
        int col2 = i%M;
        if(adMatrix[row2][col2]==0) {
          adMatrix[row2][col2]=1;
          DFS(i, cnt+1);
        }
      }
    }
    adMatrix[row][col]=0;
  }
  //바이러스 확산 메소드
  static void spreadVirus(int row, int col) {
    for(int i=0; i<4; i++) {
      int nextRow = dx[i]+row;
      int nextCol = dy[i]+col;
      if(nextRow>=0 && nextRow<N && nextCol>=0 && nextCol<M) {
        if(visit[nextRow][nextCol]==0) {
          visit[nextRow][nextCol]=2;
          spreadVirus(nextRow, nextCol);
        }
      }
    }
  }
  //안정 영역 크기 구하기
  static void safetyArea() {
    int cnt=0;
    for(int i=0; i<N; i++) {
      for(int j=0; j<M; j++) {
        if(visit[i][j]==0) {
          cnt++;
        }
      }
    }
    if(totalCNT<cnt) {
      totalCNT = cnt;
    }
  }
}

// 출처 : https://zoonvivor.tistory.com/125
```

```java
static int[][] map, tempMap, copyMap;
static int n, m;
static int[] dx = { -1, 0, 1, 0 };
static int[] dy = { 0, -1, 0, 1 };
static int ans;
 
private void solve() {
    n = sc.nextInt();
    m = sc.nextInt();
    map = new int[n][m];
    tempMap = new int[n][m];
 
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            tempMap[i][j] = map[i][j] = sc.nextInt();
        }
    }
 
    for (int i = 0; i < n * m; i++) {
        int c = (int) (i / (m * 1.0));
        int r = i % m;
 
        if (tempMap[c][r] == 0) {
            tempMap[c][r] = 1;
            dfs(i, 1);
            tempMap[c][r] = 0;
        }
    }
    System.out.println(ans);
}
// 벽 3개 배치 
public static void dfs(int v, int cnt) {
    int c = (int) (v / (m * 1.0));
    int r = v % m;
 
    if (cnt == 3) {
        copyMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copyMap[i][j] = tempMap[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyMap[i][j] == 2) {
                    paint(i, j);
                }
            }
        }
        cal();
    } else {
        for (int i = v + 1; i < n * m; i++) {
            int cc = (int) (i / (m * 1.0));
            int rr = i % m;
 
            if (tempMap[cc][rr] == 0) {
                tempMap[cc][rr] = 1;
                dfs(i, cnt + 1);
            }
        }
    }
    // backtracking
    tempMap[c][r] = 0;
    --cnt;
}
// 바이러스 퍼트리기
public static void paint(int y, int x) {
    for (int i = 0; i < 4; i++) {
        int nx = dx[i] + x;
        int ny = dy[i] + y;
 
        if (0 <= ny && ny < n && 0 <= nx && nx < m) {
            if (copyMap[ny][nx] == 0) {
                copyMap[ny][nx] = 3;
                paint(ny, nx);
            }
        }
    }
}
// 안전 영역 구하기
public static void cal() {
    int cnt = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (copyMap[i][j] == 0) {
                ++cnt;
            }
        }
    }
    if (cnt > ans) {
        ans = cnt;
    }
}


// 출처: http://mygumi.tistory.com/227
```

<br>

# 개념

## 그래프

> 그래프의 개념

단순히 노드(N, node)와 그 노드를 연결하는 간선(E, edge)을 하나로 모아 놓은 자료 구조

`G = (V, E)` 정점과 간선으로 이루어진 자료구조의 일종

- 즉, 연결되어 있는 객체 간의 관계를 표현할 수 있는 자료구조이다.

  - Ex) 지도, 지하철 노선도의 최단 경로, 전기 회로의 소자들, 도로(교차점과 일방 통행길), 선수 과목 등

- 그래프는 여러 개의 고립된 부분 그래프(Isolated Subgraphs)로 구성될 수 있다.


> 그래프 탐색 

하나의 정점으로부터 시작하여 차례대로 모든 정점들을 **한 번씩** 방문하는 것

> 그래프와 트리의 차이

||그래프|트리|
|:---:|---|---|
|정의|노드(node)와 그 노드를 연결하는<br>간선(edge)을 하나로 모아 놓은 자료구조|그래프의 한 종류<br>DAG(Directed Acyclic Graph, 방향성이 있는 비순환 그래프)의 한 종류
|방향성|방향 그래프(Directed)<br>무방향 그래프(Undirected) 모두 존재|방향 그래프(Directed Graph)|
|사이클|사이클 가능<br>자체 간선(self-loop)도 가능<br>순환 그래프, 비순환 그래프 모두 존재|사이클 불가능<br>자체 간선(self-loop)도 불가능<br>비순환 그래프(Acyclic Graph)|
|루트 노드|루트 노드의 개념이 없음|한 개의 루트 노드만이 존재<br>모든 자식 노드는 한 개의 부모 노드 만을 가짐|
|부모-자식|부모-자식의 개념이 없음|부모-자식 관계<br>top-bottom 또는 bottom-top으로 이루어짐|
|모델|네트워크 모델|계층 모델|
|순회|DFS, BFS|DFS, BFS 안의 Pre-, In-, Post-order|
|간선의 수|그래프에 따라 간선의 수가 다름<br>간선이 없을 수도 있음|노드가 N인 트리는 항상 N-1의 간선을 가짐|
|경로|-|임의의 두 노드 간의 경료는 유일|
|예시 및 종류|지도, 지하철 노선도의 최단 경로, 전기 회로의 소자들<br>도로(교차점과 일방 통행길), 선수 과목|이진 트리, 이진 탐색 트리<br>균형 트리(AVL 트리, red-black 트리)<br>이진 힙(최대힙, 최소힙) 등|

> ※ 오일러 경로(Eulerian tour)

- 그래프에 존재하는 모든 간선(edge)을 한 번만 통과하면서 처음 정점(vertex)으로 되돌아오는 경로를 말한다.

- 그래프의 모든 정점에 연결된 가넌의 개수가 짝수일 때만 오일러 경로가 존재한다.

> 그래프와 관련된 용어

- 정점(vertex) : 위치라는 개념 (`동의어` node)

- 간선(edge) : 위치 간의 관계. 즉, 노드를 연결하는 선 (`동의어` link, branch)

- 인접 정점(adjacent vertex) : 간선에 의해 직접 연결된 정점

- 정점의 차수(degree) : 무방향 그래프에서 하나의 정점에 인접한 정점의 수

  - 무방향 그래프에 존재하는 정점의 모든 차수의 합 = 그래프의 간선 수의 2배
  
- 진입 차수(in-degree) : 방향 그래프에서 외부에서 오는 간선의 수 (`동의어` 내차수)

- 진출 차수(out-degree) : 방향 그래프에서 외부로 향하는 간선의 수 (`동의어` 외차수)

  - 방향 그래프에 있는 정점의 진입 차수 또는 진출 차수의 합 = 방향 그래프의 간선의 수 (`내차수 + 외차수`)

- 경로 길이(path length) : 경로를 구성하는데 사용된 간선의 수

- 단순 경로(simple path) : 경로 중에서 반복되는 정점이 없는 경우

- 사이클(cycle) : 단순 경로의 시작 정점과 종료 정점이 동일한 경우

![차수의 이해](https://julismail.staff.telkomuniversity.ac.id/files/2015/09/digraph.png)

```
상단의 방향 그래프(Directed Graph)를 G1이라 하자.

정점 : V(G1) = {A, B, C, D, E, F}

간선 : E(G1) = {(A,B), (B,C), (C,E), (D,B), (E,D), (E,F)}

상단의 방향 그래프 G1에서 정점 B의 차수는 3이다.

정점 B의 진입 차수(내차수)는 2, 진출 차수(외차수)는 1이다.

정점 B의 차수는 진입 차수와 진출 차수의 합인 3이다.

```

> 그래프의 특징

- 그래프는 **네트워크 모델** 이다.

- 2개 이상의 경로가 가능하다.

  -  즉, 노드들 사이에 무방향/방향에서 양방향 경로를 가질 수 있다.
  
- self-loop 뿐 아니라 loop/circuit 모두 가능하다.

- 루트 노드라는 개념이 없다.

- 부모-자식 관계라는 개념이 없다.

- 순회는 DFS나 BFS로 이루어진다.

- 그래프는 순환(Cyclic) 혹은 비순환(Acyclic)이다.

- 그래프는 크게 방향 그래프와 무방향 그래프가 있다.

- 간선의 유무는 그래프에 따라 다르다.

> 그래프의 종류

***1. 무방향 그래프 & 방향 그래프***

- 무방향 그래프(Undirected Graph)

  - 무방향 그래프의 간선은 간선을 통해서 양방향으로 갈 수 있다.
  
  - 정점 A와 정점 B를 연결하는 간선은 (A, B)와 같이 정점의 쌍으로 표현한다.
  
    - (A,B)와 (B,A) 동일
    
  - Ex) 양방향 통행 도로

- 방향 그래프(Directed Graph)
  
  - 간선에 방향성이 존재하는 그래프
  
  - A → B 로만 갈 수 있는 간선은 <A,B>로 표시한다.
    
    - <A, B>와 <B, A>는 다름
    
  - Ex) 일방 통행
  
***2. 가중치 그래프(Weighted Graph)***

![가중치 그래프](https://d2vlcm61l7u1fs.cloudfront.net/media%2Fbdd%2Fbdd8745f-583a-4851-bd09-104c78fe7afc%2FphpCNXNbc.png)

- 간선에 비용이나 가중치가 할당된 그래프

- **'네트워크(Network)'** 라고도 한다.

  - Ex) 도시-도시의 연결, 도로의 길이, 회로 소자의 용량, 통신망의 사용료 등
  
***3. 연결 그래프 & 비연결 그래프***

- 연결 그래프(Connected Graph)
  
  - 무방향 그래프에 있는 모든 정점쌍에 대해서 항상 경로가 존재하는 경우
  
  - Ex) 트리(Tree) : 사이클을 가지지 않는 연결 그래프

- 비연결 그래프(Disconnected Graph)
  
  - 무방향 그래프에서 특점 정점쌍 사이에 경로가 존재하지 않는 경우
  
***4. 사이클 & 비순환 그래프***

- 사이클 그래프(Cycle Graph)
  
  - 단순 경로의 시작 정점과 종료 정점이 동일한 경우
  
    - 단순 경로(Simple Path) : 경로 중에서 반복되는 정점이 없는 경우
    
  - 비순환 그래프(Acyclic Graph)
  
    - 사이클이 없는 그래프
    
 ***5. 완전 그래프(Complete Graph)***
 
 - 그래프에 속해 있는 모든 정점이 서로 연결되어 있는 그래프
 
 - 무방향 완전 그래프 : 정점의 수가 `n` 이면 간선의 수는 `n*(n-1)/2`

* * *

## DFS와 BFS

![DFS & BFS](https://t1.daumcdn.net/cfile/tistory/2254723E588084F830)

### DFS(Depth-First Search, 깊이 우선 탐색)

> 깊이 우선 탐색 이란

루트 노드(혹은 다른 임의의 노드)에서 시작해서 다음 분기(branch)로 넘어가기 전에 해당 분기를 완벽하게 탐색하는 방법

- 미로를 탐색할 때 한 방향으로 갈 수 있을 때까지 계속 가다가 더 이상 갈 수 없게 되면 다시 가장 가까운 갈림길로 돌아와서 이곳으로부터 다른 방향으로 다시 탐색을 진행하는 방법과 유사하다.

- 즉, 넓게(wide) 탐색하기 전에 깊게(deep) 탐색하는 것이다.

- 사용하는 경우: **모든 노드를 방문** 하고자 하는 경우에 이 방법을 선택한다.

- 깊이 우선 탐색(DFS)이 너비 우선 탐색(BFS)보다 좀 더 간단하다.

- 단순 검색 속도 자체는 너비 우선 탐색(BFS)에 비해서 느리다.

<br>

> 깊이 우선 탐색의 특징

- 자기 자신을 호출하는 **순환 알고리즘의 형태** 를 가지고 있다.

- 전위 순회(Pre-Order Traversals)를 포함한 다른 형태의 트리 순회는 모두 DFS의 한 종류이다.

- 이 알고리즘을 구현할 때 가장 큰 차이점은, 그래프 탐색의 경우 **어떤 노드를 방문했었는지 여부를 반드시 검사** 해야 한다는 것이다.

  - 이를 검사하지 않을 경우 무한루프에 빠질 위험이 있다.
  
  <br>

> 깊이 우선 탐색(DFS)의 과정

![Process of DFS](https://gmlwjd9405.github.io/images/algorithm-dfs-vs-bfs/dfs-example.png)

1. a 노드(시작 노드)를 방문한다.

    - 방문한 노드는 방문했다고 표시한다.
  
2. a와 인접한 노드들을 차례로 순회한다.

    - a와 인접한 노드가 없다면 종료한다.
  
3. a와 이웃한 노드 b를 방문했다면, a와 인접한 또 다른 노드를 방문하기 전에 b의 이웃 노드들을 전부 방문해야 한다.

    - b를 시작 정점으로 DFS를 다시 시작하여 b의 이웃 노드들을 방문한다.
  
4. b의 분기를 전부 완벽하게 탐색했다면 다시 a에 인접한 정점들 중에서 아직 방문이 안 된 정점을 찾는다.

    - 즉, b의 분기를 전부 완벽하게 탐색한 뒤에야 a의 다른 이웃 노드를 방문할 수 있다는 뜻이다.
  
    - 아직 방문이 안 된 정점이 없으면 종료한다.
  
   - 아직 방문이 안된 정점이 있으면 다시 그 정점을 시작 정점으로 DFS를 시작한다.
   
   <br>

> 깊이 우선 탐색(DFS)의 구현

1. 순환 호출 이용

- 순환 호출을 이용한 DFS 의사코드(pseudocode)

```
void search(Node root) {
  if (root == null) return;
  // 1. root 노드 방문
  visit(root);
  root.visited = true; // 1-1. 방문한 노드를 표시
  // 2. root 노드와 인접한 정점을 모두 방문
  for each (Node n in root.adjacent) {
    if (n.visited == false) { // 4. 방문하지 않은 정점을 찾는다.
      search(n); // 3. root 노드와 인접한 정점 정점을 시작 정점으로 DFS를 시작
    }
  }
}
```

- 순환 호출을 이용한 DFS 구현 (java 언어)

```java
import java.io.*;
import java.util.*;

/* 인접 리스트를 이용한 방향성 있는 그래프 클래스 */
class Graph {
  private int V;   // 노드의 개수
  private LinkedList<Integer> adj[]; // 인접 리스트

  /** 생성자 */
  Graph(int v) {
      V = v;
      adj = new LinkedList[v];
      for (int i=0; i<v; ++i) // 인접 리스트 초기화
          adj[i] = new LinkedList();
  }

  /** 노드를 연결 v->w */
  void addEdge(int v, int w) { adj[v].add(w); }

  /** DFS에 의해 사용되는 함수 */
  void DFSUtil(int v, boolean visited[]) {
      // 현재 노드를 방문한 것으로 표시하고 값을 출력
      visited[v] = true;
      System.out.print(v + " ");

      // 방문한 노드와 인접한 모든 노드를 가져온다.
      Iterator<Integer> i = adj[v].listIterator();
      while (i.hasNext()) {
          int n = i.next();
          // 방문하지 않은 노드면 해당 노드를 시작 노드로 다시 DFSUtil 호출
          if (!visited[n])
              DFSUtil(n, visited); // 순환 호출
      }
  }

  /** 주어진 노드를 시작 노드로 DFS 탐색 */
  void DFS(int v) {
      // 노드의 방문 여부 판단 (초깃값: false)
      boolean visited[] = new boolean[V];

      // v를 시작 노드로 DFSUtil 순환 호출
      DFSUtil(v, visited);
  }

  /** DFS 탐색 */
  void DFS() {
      // 노드의 방문 여부 판단 (초깃값: false)
      boolean visited[] = new boolean[V];

      // 비연결형 그래프의 경우, 모든 정점을 하나씩 방문
      for (int i=0; i<V; ++i) {
          if (visited[i] == false)
              DFSUtil(i, visited);
      }
  }
}
```

```java
// 사용 방법

public static void main(String args[]) {
    Graph g = new Graph(4);

    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 2);
    g.addEdge(2, 0);
    g.addEdge(2, 3);
    g.addEdge(3, 3);

    g.DFS(2); /* 주어진 노드를 시작 노드로 DFS 탐색 */
    g.DFS(); /* 비연결형 그래프의 경우 */
}
```

2. 명시적인 스택 사용

  - 명시적인 스택을 사용하여 방문한 정점들을 스택에 저장하였다가 다시 꺼내어 작업한다.
  
  <br>

> 깊이 우선 탐색(DFS)의 시간 복잡도

- DFS는 그래프(정점의 수: N, 간선의 수: E)의 모든 간선을 조회한다.
  
  - 인접 리스트로 표현된 그래프: O(N+E)
  
  - 인접 행렬로 표현된 그래프: O(N^2)

- 즉, 그래프 내에 적은 숫자의 간선만을 가지는 **희소 그래프(Sparse Graph)** 의 경우 인접 행렬보다 인접 리스트를 사용하는 것이 유리하다.


### BFS(Breadth-First Search, 너비 우선 탐색)

> 너비 우선 탐색 이란

루트 노드(혹은 다른 임의의 노드)에서 시작해서 인접한 노드를 먼저 탐색하는 방법

  - 시작 정점으로부터 가까운 정점을 먼저 방문하고 멀리 떨어져 있는 정점을 나중에 방문하는 순회 방법이다.
  
  - 즉, 깊게(deep) 탐색하기 전에 넓게(wide) 탐색하는 것이다.
  
  - 사용하는 경우: **두 노드 사이의 최단 경로** 혹은 **임의의 경로를 찾고 싶을 때** 이 방법을 선택한다.
  
    - Ex) 지구상에 존재하는 모든 친구 관계를 그래프로 표현한 후 Ash와 Vanessa 사이에 존재하는 경로를 찾는 경우
    
    - 깊이 우선 탐색의 경우 - 모든 친구 관계를 다 살펴봐야 할지도 모른다.
    
    - 너비 우선 탐색의 경우 - Ash와 가까운 관계부터 탐색
    
  - 너비 우선 탐색(BFS)이 깊이 우선 탐색(DFS)보다 좀 더 복잡하다.

<br>

> 너비 우선 탐색(BFS)의 특징

- 직관적이지 않은 면이 있다.

  - BFS는 시작 노드에서 시작해서 거리에 따라 단계별로 탐색한다고 볼 수 있다.
  
- BFS는 재귀적으로 동작하지 않는다.

- 이 알고리즘을 구현할 때 가장 큰 차이점은, 그래프 탐색의 경우 어떤 노드를 방문했었는지 여부를 반드시 검사 해야 한다는 것이다.

    - 이를 검사하지 않을 경우 무한루프에 빠질 위험이 있다.
    
- BFS는 방문한 노드들을 차례로 저장한 후 꺼낼 수 있는 자료 구조인 큐(Queue)를 사용한다.

    - 즉, 선입선출(FIFO) 원칙으로 탐색
    
    - 일반적으로 큐를 이용해서 반복적 형태로 구현하는 것이 가장 잘 동작한다.
    
- ‘Prim’, ‘Dijkstra’ 알고리즘과 유사하다.

<br>

> 너비 우선 탐색(BFS)의 과정

깊이가 1인 모든 노드를 방문하고 나서 그 다음에는 깊이가 2인 모든 노드를, 그 다음에는 깊이가 3인 모든 노드를 방문하는 식으로 계속 방문하다가 더 이상 방문할 곳이 없으면 탐색을 마친다.

![Process of BFS](https://gmlwjd9405.github.io/images/algorithm-dfs-vs-bfs/bfs-example.png)

1. a 노드(시작 노드)를 방문한다. (방문한 노드 체크)

    - 큐에 방문된 노드를 삽입(enqueue)한다.
    
    - 초기 상태의 큐에는 시작 노드만이 저장
    
      - 즉, a 노드의 이웃 노드를 모두 방문한 다음에 이웃의 이웃들을 방문한다.
      
2. 큐에서 꺼낸 노드과 인접한 노드들을 모두 차례로 방문한다.

    - 큐에서 꺼낸 노드를 방문한다.
    
    - 큐에서 커낸 노드과 인접한 노드들을 모두 방문한다.
    
      - 인접한 노드가 없다면 큐의 앞에서 노드를 꺼낸다(dequeue).
      
    - 큐에 방문된 노드를 삽입(enqueue)한다.
    
3. 큐가 소진될 때까지 계속한다.

<br>

> 너비 우선 탐색(BFS)의 구현

자료구조 **큐(Queue)를 이용**

- BFS 의사코드(pseudocode)
  
```
void search(Node root) {
  Queue queue = new Queue();
  root.marked = true; // (방문한 노드 체크)
  queue.enqueue(root); // 1-1. 큐의 끝에 추가

  // 3. 큐가 소진될 때까지 계속한다.
  while (!queue.isEmpty()) {
    Node r = queue.dequeue(); // 큐의 앞에서 노드 추출
    visit(r); // 2-1. 큐에서 추출한 노드 방문
    // 2-2. 큐에서 꺼낸 노드와 인접한 노드들을 모두 차례로 방문한다.
    foreach (Node n in r.adjacent) {
      if (n.marked == false) {
        n.marked = true; // (방문한 노드 체크)
        queue.enqueue(n); // 2-3. 큐의 끝에 추가
      }
    }
  }
}
```

- BFS 구현(java 언어)

```java
import java.io.*;
import java.util.*;

/* 인접 리스트를 이용한 방향성 있는 그래프 클래스 */
class Graph {
  private int V; // 노드의 개수
  private LinkedList<Integer> adj[]; // 인접 리스트

  /** 생성자 */
  Graph(int v) {
    V = v;
    adj = new LinkedList[v];
    for (int i=0; i<v; ++i) // 인접 리스트 초기화
      adj[i] = new LinkedList();
  }

  /** 노드를 연결 v->w */
  void addEdge(int v, int w) { adj[v].add(w); }

  /** s를 시작 노드으로 한 BFS로 탐색하면서 탐색한 노드들을 출력 */
  void BFS(int s) {
    // 노드의 방문 여부 판단 (초깃값: false)
    boolean visited[] = new boolean[V];
    // BFS 구현을 위한 큐(Queue) 생성
    LinkedList<Integer> queue = new LinkedList<Integer>();

    // 현재 노드를 방문한 것으로 표시하고 큐에 삽입(enqueue)
    visited[s] = true;
    queue.add(s);

    // 큐(Queue)가 빌 때까지 반복
    while (queue.size() != 0) {
      // 방문한 노드를 큐에서 추출(dequeue)하고 값을 출력
      s = queue.poll();
      System.out.print(s + " ");

      // 방문한 노드와 인접한 모든 노드를 가져온다.
      Iterator<Integer> i = adj[s].listIterator();
      while (i.hasNext()) {
        int n = i.next();
        // 방문하지 않은 노드면 방문한 것으로 표시하고 큐에 삽입(enqueue)
        if (!visited[n]) {
          visited[n] = true;
          queue.add(n);
        }
      }
    }
  }
}
```

```java
// 사용 방법

public static void main(String args[]) {
  Graph g = new Graph(4);

  g.addEdge(0, 1);
  g.addEdge(0, 2);
  g.addEdge(1, 2);
  g.addEdge(2, 0);
  g.addEdge(2, 3);
  g.addEdge(3, 3);

  g.BFS(2); /* 주어진 노드를 시작 노드로 BFS 탐색 */
}
```
<br>

> 너비 우선 탐색(BFS)의 시간 복잡도

- 인접 리스트로 표현된 그래프: O(N+E)

- 인접 행렬로 표현된 그래프: O(N^2)

- 깊이 우선 탐색(DFS)과 마찬가지로 그래프 내에 적은 숫자의 간선만을 가지는 희소 그래프(Sparse Graph) 의 경우 인접 행렬보다 인접 리스트를 사용하는 것이 유리하다.

<br>

# 참고

- [그래프란](https://gmlwjd9405.github.io/2018/08/13/data-structure-graph.html)
- [그래프의 종류](https://blog.naver.com/k97b1114/140163248655)
- [DFS](https://gmlwjd9405.github.io/2018/08/14/algorithm-dfs.html)
- [BFS](https://gmlwjd9405.github.io/2018/08/15/algorithm-bfs.html)
