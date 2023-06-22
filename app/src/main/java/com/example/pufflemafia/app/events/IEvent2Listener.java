package com.example.pufflemafia.app.events;

public interface IEvent2Listener<T,S> {
    void Response(T t, S s);
}
