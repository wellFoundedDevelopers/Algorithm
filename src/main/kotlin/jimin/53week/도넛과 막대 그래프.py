import itertools
from collections import deque


def solution(edges):
    edge_size = max(list(itertools.chain(*edges)))
    edge_info = [[] for _ in range(edge_size + 1)]
    edge_get = [0 for _ in range(edge_size + 1)]
    for a, b in edges:
        edge_info[a].append(b)
        edge_get[b] += 1

    candidates = [[i, len(edge_info[i])] for i in range(1, edge_size + 1)]
    candidates.sort(key=lambda x: x[1], reverse=True)
    visited = [False for _ in range(edge_size + 1)]
    visited[0] = True
    point = -1

    result = [0, 0, 0, 0]

    for candidate, _ in candidates:
        if edge_get[candidate] == 0:
            point = candidate
            break

    result[0] = point

    for edge in edge_info[point]:
        edge_get[edge] -= 1

    for i in range(1, edge_size + 1):
        if i == point or visited[i] == True:
            continue

        type = check_graph(i, edge_info, visited, point)
        if type != 4:
            result[type] += 1

    return result


def check_graph(first, edge_info, visited, point):
    type = -1

    queue = deque([])
    visited[first] = True
    if (len(edge_info[first]) == 2):
        type = 3

    for e in edge_info[first]:
        if visited[e] == False:
            queue.append(e)
        else:
            if e == first:
                type = 1
            else:
                type = 4

    while queue:
        now = queue.popleft()
        visited[now] = True

        if (len(edge_info[now]) == 2):
            type = 3

        if type != 3 and now == first:
            type = 1

        for e in edge_info[now]:
            if visited[e] == False:
                queue.append(e)
            else:
                if type != 3:
                    if e == first:
                        type = 1
                    else:
                        type = 4

    if type == -1:
        type = 2

    return type


