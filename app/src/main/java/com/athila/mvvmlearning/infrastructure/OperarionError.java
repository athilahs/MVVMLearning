package com.athila.mvvmlearning.infrastructure;

/**
 * Created by athila on 09/08/15.
 */
public class OperarionError {
    private int code;

    public OperarionError(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static final int INVALID_CREDENTIALS = -7000;
}
