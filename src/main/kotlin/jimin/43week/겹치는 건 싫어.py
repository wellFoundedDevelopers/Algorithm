'''
문제를 잘 못 이해했다.
https://khu98.tistory.com/187 보고 깨달았다.
'''

n, k = map(int, input().split())
nums = list(map(int, input().split()))
nums_info = [0 for _ in range(max(nums) + 1)]
start = 0
end = 0
maxi = 0

while start < n and end < n:
    if nums_info[nums[start]] < k:
        nums_info[nums[start]] += 1
        start += 1
    elif nums_info[nums[start]] == k:
        nums_info[nums[end]] -= 1
        maxi = max(maxi, start - end)
        end += 1


maxi = max(maxi, n - end)
print(maxi)