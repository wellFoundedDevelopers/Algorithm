/*
<문제>
[마법사 상어와 비바라기](https://www.acmicpc.net/problem/13904)

<구현 방법>
1 -> 2 -> 3 -> 4 -> 5 을 차례대로 구현했다.

<트러블 슈팅>

*/

n, m = map(int, input().split())

clouds = []
for i in range(n):
    clouds.append(list(map(int, input().split())))

rain_cloud = [[n - 2, 0], [n - 2, 1], [n - 1, 0], [n - 1, 1]]
dx = [0, -1, -1, -1, 0, 1, 1, 1]
dy = [-1, -1, 0, 1, 1, 1, 0, -1]
for mm in range(m):
    move_d, move_s = map(int, input().split())
    move_d -= 1
    # 1번
    for i in range(len(rain_cloud)):
        rain_cloud[i][0] = (rain_cloud[i][0] + dx[move_d] * move_s) % n
        rain_cloud[i][1] = (rain_cloud[i][1] + dy[move_d] * move_s) % n

    # 2번
    for i in range(len(rain_cloud)):
        clouds[rain_cloud[i][0]][rain_cloud[i][1]] += 1

    # 4번
    for i in range(len(rain_cloud)):
        for j in range(1, 8, 2):
            if 0 <= rain_cloud[i][0] + dx[j] < n and 0 <= rain_cloud[i][1] + dy[j] < n and clouds[rain_cloud[i][0] + dx[j]][rain_cloud[i][1] + dy[j]] != 0:
                clouds[rain_cloud[i][0]][rain_cloud[i][1]] += 1

    # 5번
    visited = [[False for _ in range(n)] for _ in range(n)]
    for i in range(len(rain_cloud)):
        visited[rain_cloud[i][0]][rain_cloud[i][1]] = True
    rain_cloud.clear()
    for i in range(n):
        for j in range(n):
            if clouds[i][j] >= 2 and not visited[i][j]:
                clouds[i][j] -= 2
                rain_cloud.append([i, j])
result = 0
for c in clouds:
    result += sum(c)
print(result)