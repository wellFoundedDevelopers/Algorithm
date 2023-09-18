from heapq import heappush, heappop, heapify


def solution(jobs):
    length = len(jobs)
    heapify(jobs)
    start, time = heappop(jobs)
    end = start + time
    waiting = time

    while jobs:
        others = []
        while jobs and jobs[0][0] <= end:
            ns, nt = heappop(jobs)
            heappush(others, [nt, ns])

        if others:
            time, start = heappop(others)
            waiting += end - start + time
            end += time
        else:
            start, time = heappop(jobs)
            waiting += time
            end = start + time

        while others:
            nt, ns = heappop(others)
            heappush(jobs, [ns, nt])

    return waiting // length