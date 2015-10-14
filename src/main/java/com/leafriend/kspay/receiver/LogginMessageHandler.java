package com.leafriend.kspay.receiver;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogginMessageHandler implements MessageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogginMessageHandler.class);

    private final Map<Class<?>, List<Field>> classFields = new HashMap<Class<?>, List<Field>>();

    @Override
    public boolean isHandleable(Message message) {
        return true;
    }

    @Override
    public void handle(Message message) {
        Class<? extends Message> messageClass = message.getClass();
        LOGGER.debug("Handle message of {}", messageClass.getName());
        for (Field field : getClassFields(messageClass)) {
            LOGGER.debug("  {} = {{}}", field.getName(), getValue(message, field));
        }
        Class<?> targetClass = messageClass.getSuperclass();
        while (!Object.class.equals(targetClass)) {
            LOGGER.debug("Inherited fields from: {}", targetClass.getName());
            for (Field field : getClassFields(targetClass)) {
                LOGGER.debug("  {} = {{}}", field.getName(), getValue(message, field));
            }
            targetClass = targetClass.getSuperclass();
        }
    }

    private Object getValue(Message message, Field field) {
        try {
            return field.get(message);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Failed to field value " + field.getDeclaringClass().getName() + "." + field.getName(), e);
            return null;
        } catch (IllegalAccessException e) {
            LOGGER.warn("Failed to field value " + field.getDeclaringClass().getName() + "." + field.getName(), e);
            return null;
        }
    }

    public List<Field> getClassFields(Class<?> targetClass) {
        if (!classFields.containsKey(targetClass)) {
            List<Field> list = new ArrayList<Field>();
            for (Field field : targetClass.getDeclaredFields()) {
                field.setAccessible(true);
                list.add(field);
            }
            classFields.put(targetClass, list);
        }
        List<Field> fields = classFields.get(targetClass);
        return fields;
    }

}
