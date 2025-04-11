package com.rcd.rcdapi.utils;

import java.util.List;
import java.util.Random;

/**
 * 랜덤 유틸
 */
public class RandomUtil {

    private static final Random random = new Random();

    /**
     * 전달받은 id들 중 하나를 랜덤하게 뽑는다.
     * @param ids
     * @return
     */
    public static Long pickRandomId(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return null;
        }
        int randomIndex = random.nextInt(ids.size());
        return ids.get(randomIndex);
    }
}
