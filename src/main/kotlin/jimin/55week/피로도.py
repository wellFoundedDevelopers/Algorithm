maxi = 0


def solution(k, dungeons):
    global maxi
    for i in range(len(dungeons)):
        if dungeons[i][0] <= k:
            visited = [False for _ in range(len(dungeons))]
            visited[i] = True
            dfs(k, dungeons, visited, i, 1)
    return maxi


def dfs(k, dungeons, visited, now, count):
    global maxi
    maxi = max(count, maxi)

    for i in range(len(dungeons)):
        if not visited[i] and dungeons[i][0] <= k - dungeons[now][1]:
            visited[i] = True
            dfs(k - dungeons[now][1], dungeons, visited, i, count + 1)
            visited[i] = False
