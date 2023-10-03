from collections import deque
import math

dx,dy = [0,0,1,-1],[1,-1,0,0]

def solution(board):
    def bfs(startX,startY,startC,startD):
        n = len(board)
        result = [[math.inf for _ in range(n)] for _ in range(n)]
        q = deque()
        q.append((startX,startY,startC,startD))
        result[startX][startY] = 0
        while q:
            x,y,cost,direction = q.popleft()
            for i in range(4):
                nx,ny,ncost = x + dx[i], y + dy[i], cost+600 if i != direction else cost+100
                if 0 <= nx < n and 0 <= ny < n and board[nx][ny] == 0 and result[nx][ny] > ncost:
                    result[nx][ny] = ncost
                    q.append((nx,ny,ncost,i))
        return result[-1][-1]
    return min(bfs(0,0,0,0),bfs(0,0,0,2))