package com.example.pufflemafia;

public interface IListener<T> {
    void Response();
    void Response(T t);
}
