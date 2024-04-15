round = 1


def solve(_prices):
    money = 0

    all_day = len(_prices) - 1
    day = 0

    prices = _prices
    while day < all_day:
        max_price = max(prices)
        max_index = prices.index(max_price)
        # 산 가격 합
        buy = sum(prices[:max_index])
        # 팔기
        money += (max_price * max_index) - buy
        # 날짜는 모두 판 다음 날
        day += max_index + 1
        # 가격도 다음 배열로
        prices = prices[max_index + 1:]

    print("#" + str(round), money)


if __name__ == '__main__':
    t = int(input())
    for _ in range(t):
        n = int(input())
        input_prices = list(map(int, input().split()))
        solve(input_prices)
        round += 1
