import sys
from collections import deque


def check_students(first, students, visited):
    queue = deque(students[first])
    visited[first] = 1

    while queue:
        now = queue.popleft()
        visited[now] = 1

        for student in students[now]:
            queue.append(student)


n, m = map(int, sys.stdin.readline().split())
students = [[] for _ in range(n + 1)]
reverse_students = [[] for _ in range(n + 1)]
result = 0

for _ in range(m):
    a, b = map(int, sys.stdin.readline().split())
    students[a].append(b)
    reverse_students[b].append(a)

for i in range(1, n + 1):
    visited = [0 for _ in range(n + 1)]
    visited[0] = 1
    check_students(i, students, visited)
    check_students(i, reverse_students, visited)

    if all(visited):
        result += 1

print(result)