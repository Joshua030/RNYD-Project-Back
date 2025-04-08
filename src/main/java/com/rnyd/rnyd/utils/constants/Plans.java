package com.rnyd.rnyd.utils.constants;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Plans {
    NONE(0L),
    NUTRITION(35L),
    TRAINING(50L),
    COMPLETE(90L);

    private final Long price;

    Plans(Long price){
        this.price = price;
    }

    public static Plans getPlanByName(String name){
        Plans plans = Arrays.stream(Plans.values())
                .filter(plan -> plan.name().equals(name))
                .findFirst().orElse(null);
        return plans;
    }

}
