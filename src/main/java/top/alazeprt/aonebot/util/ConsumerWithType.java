package top.alazeprt.aonebot.util;

import com.google.gson.JsonObject;

import java.lang.reflect.Method;
import java.util.function.Consumer;

public class ConsumerWithType<T> {
    private final Method method;
    private final Consumer<T> consumer;

    public ConsumerWithType(Class<T> clazz, Consumer<T> consumer) {
        this.consumer = consumer;
        try {
            this.method = clazz.getMethod("fromJson", JsonObject.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public void accept(JsonObject jsonObject) {
        try {
            consumer.accept((T) method.invoke(null, jsonObject));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
