package jimin.week52;

import java.util.Arrays;

class 택배_배달과_수거하기 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int dLast = n - 1;
        int pLast = n - 1;

        dLast = updateIndex(dLast, deliveries);
        pLast = updateIndex(pLast, pickups);

        int nowD = cap;
        int nowP = cap;

        while (dLast >= 0 || pLast >= 0) {
            answer += (Math.max(dLast, pLast) + 1) * 2;

            for(int i = dLast; i >= 0; i--) {
                if (deliveries[i] > 0) {
                    int newD = Math.max(nowD - deliveries[i], 0);
                    int newDelivery = Math.max(deliveries[i] - nowD, 0);

                    nowD = newD;
                    deliveries[i]  = newDelivery;
                    dLast = i;
                }
                if (nowD == 0) {
                    break;
                }
            }

            for(int i = pLast; i >= 0; i--) {
                if (pickups[i] > 0) {
                    int newP = Math.max(nowP - pickups[i], 0);
                    int newPickup = Math.max(pickups[i] - nowP, 0);

                    nowP = newP;
                    pickups[i]  = newPickup;
                    pLast = i;
                }
                if (nowP == 0) {
                    break;
                }
            }

            nowD = cap;
            nowP = cap;

            dLast = updateIndex(dLast, deliveries);
            pLast = updateIndex(pLast, pickups);
        }
        return answer;
    }

    public int updateIndex(int last, int[] items) {
        boolean isOver = true;

        for(int i = last; i >= 0; i--) {
            if (items[i] != 0) {
                last = i;
                isOver = false;
                break;
            }
        }

        if (isOver) {
            return -1;
        } else {
            return last;
        }
    }
}