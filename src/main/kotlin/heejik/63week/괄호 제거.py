exp = input()
answers = []


def dfs(start_idx, now_exp):
    if now_exp not in answers:
        answers.append(now_exp)
    # start_idx 포함 이후부터 ( 이게 없으면 그냥 리턴하는 것도 고려
    for i in range(start_idx, len(now_exp)):
        if now_exp[i] == '(':
            # 여기에 맞는 닫는 괄호를 찾아야 한다
            close_idx = find_idx(i, now_exp)
            dfs(start_idx=i, now_exp=now_exp[:i] + now_exp[i + 1:close_idx] + now_exp[close_idx + 1:])


def find_idx(i, find_exp):
    cnt = 1
    for idx, char in enumerate(find_exp[i + 1:]):
        if char == ')':
            cnt -= 1
            if cnt == 0:
                return i + 1 + idx
        if char == '(':
            cnt += 1


if __name__ == '__main__':
    dfs(start_idx=0, now_exp=exp)
    answers.sort()
    answers.remove(exp)
    for answer in answers:
        print(answer)
