package com.athila.mvvmlearning.infrastructure;

/**
 * Created by athila on 09/08/15.
 */
public abstract class OperationListener<T> {
    public void onSuccess(T... result) {}
    public void onError(OperarionError error) {}
}
