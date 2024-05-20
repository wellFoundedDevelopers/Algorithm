n, m = map(int, input().split())
all_cost = 0
minimum_cost = 0
edges = []
parents = [i for i in range(n + 1)]

for _ in range(m):
    a, b, cost = map(int, input().split())
    all_cost += cost
    edges.append([a, b, cost])

edges.sort(key=lambda x: x[2])

for a, b, cost in edges:
    parentA = a
    parentB = b

    while parentA != parents[parentA]:
        parentA = parents[parentA]

    while parentB != parents[parentB]:
        parentB = parents[parentB]

    if parentA == parentB:
        continue

    if parentA < parentB:
        parents[parentB] = parentA
    else:
        parents[parentA] = parentB

    minimum_cost += cost

count = 0
for i in range(1, n + 1):
    if i == parents[i]:
        count += 1
if count > 1:
    print(-1)
else:
    print(all_cost - minimum_cost)
