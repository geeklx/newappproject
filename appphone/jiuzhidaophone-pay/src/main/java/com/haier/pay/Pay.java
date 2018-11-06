package com.haier.pay;

public interface Pay<T> {
    void pay();

    void success(T t);

    void error(String s);
}
