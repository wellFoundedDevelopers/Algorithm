def solution(s):
    answer = 0
    s = list(s)
    for i in range(len(s)):
        if isCorrect(s):
            answer += 1
        tmp = s[0]
        s.pop(0)
        s.append(tmp)

    return answer


def isCorrect(s):
    stack = []
    idx = 0
    isCorrect = True
    start = ['(', '[', '{']
    end = [')', ']', '}']
    while idx < len(s) and isCorrect:
        if s[idx] in start:
            stack.append(s[idx])
        elif s[idx] in end:
            if not stack:
                isCorrect = False
            else:
                for i in range(3):
                    if stack[-1] == start[i]:
                        if s[idx] == end[i]:
                            stack.pop()
                        else:
                            isCorrect = False
                        break
        idx += 1

    if stack:
        isCorrect = False

    return isCorrect