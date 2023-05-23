package com.example.pufflemafia.app;

public interface IListener<T> {
    void Response();
    void Response(T t);
}
