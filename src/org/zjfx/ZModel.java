package org.zjfx;

import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.property.adapter.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.HashMap;
import java.util.Map;

public class ZModel<T> implements ObservableValue<T> {

    private T model;
    private Map<String, Property> propertyMap;

    public ZModel(T model) {
        this.model = model;
        this.propertyMap = new HashMap<String, Property>();
    }

    public static <T> ZModel<T> wrap(T model) {
        return new ZModel<T>(model);
    }

    public boolean getBoolean(String name) {
        return getBooleanProperty(name).get();
    }

    public void setBoolean(String name, boolean value) {
        getBooleanProperty(name).set(value);
    }

    public BooleanProperty getBooleanProperty(String name) {
        Property property = propertyMap.get(name);
        if (property == null)
            try {
                property = propertyMap.put(name,
                        JavaBeanBooleanPropertyBuilder.create().bean(model).name(name).beanClass(model.getClass()).build());
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        return (BooleanProperty) property;
    }

    public String getString(String name) {
        return getStringProperty(name).get();
    }

    public void setString(String name, String value) {
        getStringProperty(name).set(value);
    }

    public StringProperty getStringProperty(String name) {
        Property property = propertyMap.get(name);
        if (property == null)
            try {
                property = JavaBeanStringPropertyBuilder.create().bean(model).name(name).beanClass(model.getClass()).build();
                propertyMap.put(name, property);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        return (StringProperty) property;
    }

    public int getInteger(String name) {
        return getIntegerProperty(name).get();
    }

    public void setInteger(String name, int value) {
        getIntegerProperty(name).set(value);
    }

    public IntegerProperty getIntegerProperty(String name) {
        Property property = propertyMap.get(name);
        if (property == null)
            try {
                property = JavaBeanIntegerPropertyBuilder.create().bean(model).name(name).beanClass(model.getClass()).build();
                propertyMap.put(name, property);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        return (IntegerProperty) property;
    }

    public long getLong(String name) {
        return getLongProperty(name).get();
    }

    public void setLong(String name, long value) {
        getLongProperty(name).set(value);
    }

    private LongProperty getLongProperty(String name) {
        Property property = propertyMap.get(name);
        if (property == null)
            try {
                property = JavaBeanLongPropertyBuilder.create().bean(model).name(name).beanClass(model.getClass()).build();
                propertyMap.put(name, property);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        return (LongProperty) property;
    }

    public double getDouble(String name) {
        return getDoubleProperty(name).get();
    }

    public void setDouble(String name, double value) {
        getDoubleProperty(name).set(value);
    }

    private DoubleProperty getDoubleProperty(String name) {
        Property property = propertyMap.get(name);
        if (property == null)
            try {
                property = JavaBeanDoublePropertyBuilder.create().bean(model).name(name).beanClass(model.getClass()).build();
                propertyMap.put(name, property);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        return (DoubleProperty) property;
    }

    public float getFloat(String name) {
        return getFloatProperty(name).get();
    }

    public void setFLoat(String name, float value) {
        getFloatProperty(name).set(value);
    }

    private FloatProperty getFloatProperty(String name) {
        Property property = propertyMap.get(name);
        if (property == null)
            try {
                property = JavaBeanFloatPropertyBuilder.create().bean(model).name(name).beanClass(model.getClass()).build();
                propertyMap.put(name, property);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        return (FloatProperty) property;
    }

    public Object getObject(String name) {
        return getObjectProperty(name).get();
    }

    public void setObject(String name, Object value) {
        getObjectProperty(name).set(value);
    }

    private ObjectProperty getObjectProperty(String name) {
        Property property = propertyMap.get(name);
        if (property == null)
            try {
                property = JavaBeanObjectPropertyBuilder.create().bean(model).name(name).beanClass(model.getClass()).build();
                propertyMap.put(name, property);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        return (ObjectProperty) property;
    }

    public Property<?> getProperty(String name) {
        Property<?> property = propertyMap.get(name);
        if (property == null) {
            try {
                Class<?> type = model.getClass().getDeclaredField(name).getType();
                if (Boolean.class.isAssignableFrom(type) || boolean.class.isAssignableFrom(type))
                    return getBooleanProperty(name);
                if (String.class.isAssignableFrom(type))
                    return getStringProperty(name);
                if (Integer.class.isAssignableFrom(type) || int.class.isAssignableFrom(type))
                    return getIntegerProperty(name);
                if (Long.class.isAssignableFrom(type) || long.class.isAssignableFrom(type))
                    return getLongProperty(name);
                if (Double.class.isAssignableFrom(type) || double.class.isAssignableFrom(type))
                    return getDoubleProperty(name);
                if (Float.class.isAssignableFrom(type) || float.class.isAssignableFrom(type))
                    return getFloatProperty(name);
                return getObjectProperty(name);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
        return property;
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addListener(ChangeListener<? super T> changeListener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeListener(ChangeListener<? super T> changeListener) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public T getValue() {
        return model;
    }
}
