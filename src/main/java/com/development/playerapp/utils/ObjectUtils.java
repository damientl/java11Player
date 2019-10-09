package com.development.playerapp.utils;

import java.util.Arrays;
import java.util.Objects;

public class ObjectUtils {
    @SafeVarargs
    public static <T> Boolean hasAnyNull(T... values) {
        if (values != null) {
            return Arrays.stream(values).anyMatch(Objects::isNull);
        }
        return false;
    }
}
