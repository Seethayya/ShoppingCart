/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.seethayya.shoppingcart.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.*;
import org.hibernate.usertype.EnhancedUserType;
import org.hibernate.usertype.ParameterizedType;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Implements a generic enum user type identified / represented by a single identifier / column.
 * <p/>
 * <p/>
 * <ul> <li>The enum type being represented by the certain user type must be set by using the
 * 'enumClass' property.</li> <li>The identifier representing a enum value is retrieved by the
 * identifierMethod. The name of the identifier method can be specified by the 'identifierMethod'
 * property and by default the name() method is used.</li> <li>The identifier type is automatically
 * determined by the return-type of the identifierMethod.</li> <li>The valueOfMethod is the name of
 * the static factory method returning the enumeration object being represented by the given
 * indentifier. The valueOfMethod's name can be specified by setting the 'valueOfMethod' property.
 * The default valueOfMethod's name is 'valueOf'.</li> </ul> </p> <p>Example of an enum type
 * represented by an int value:
 * <pre><code>
 * public enum SimpleNumber {
 *     Unknown(-1), Zero(0), One(1), Two(2), Three(3);
 * <p/>
 *     public int toInt() { return value; }
 * <p/>
 *     public SimpleNumber fromInt(int value) {
 *         switch(value) {
 *             case 0: return Zero;
 *             case 1: return One;
 *             case 2: return Two;
 *             case 3: return Three;
 *             default: return Unknown;
 *         }
 *     }
 * }</code></pre>
 * <p/>
 * <p>The Mapping would look like this:
 * <pre><code>
 * &lt;typedef name=&quot;SimpleNumber&quot; class=&quot;GenericEnumUserType&quot;&gt;
 * &lt;param name="enumClass">SimpleNumber&lt;/param&gt;
 * &lt;param name="identifierMethod">toInt&lt;/param&gt;
 * &lt;param name="valueOfMethod">fromInt&lt;/param&gt;
 * &lt;/typedef&gt;
 * &lt;class ...&gt;
 * ...
 * &lt;property name="number" column="number" type="SimpleNumber"/&gt;
 * &lt;/class&gt;</code></pre>
 *
 * @author Martin Kersten
 * @author Les Hazlewood
 * @since 05.05.2005
 */
@SuppressWarnings(value = "unchecked")
public class GenericEnumUserType implements EnhancedUserType, ParameterizedType {
    private static final long serialVersionUID = -1854479466843620961L;
    private static final String DEFAULT_IDENTIFIER_METHOD_NAME = "name";
    private static final String DEFAULT_VALUE_OF_METHOD_NAME = "valueOf";
    private Class<? extends Enum> enumClass;
    private Class<?> identifierType;
    private Method identifierMethod;
    private Method valueOfMethod;
    private AbstractStandardBasicType<? extends Object> type;
    private int[] sqlTypes;

    public GenericEnumUserType() {

    }

    @Override
    public void setParameterValues(Properties parameters) {
        String enumClassName = parameters.getProperty("enumClass");

        try {
            enumClass = Class.forName(enumClassName).asSubclass(Enum.class);
        } catch (ClassNotFoundException cfne) {
            throw new HibernateException("Enum class not found", cfne);
        }

        String identifierMethodName = parameters.getProperty("identifierMethod", DEFAULT_IDENTIFIER_METHOD_NAME);

        try {
            identifierMethod = enumClass.getMethod(identifierMethodName, new Class[0]);
            identifierType = identifierMethod.getReturnType();
        } catch (Exception e) {
            throw new HibernateException("Failed to obtain identifier method", e);
        }

        type = (AbstractSingleColumnStandardBasicType<? extends Object>) new TypeResolver().heuristicType(identifierType.getName(), parameters);

        if (type == null) {
            throw new HibernateException("Unsupported identifier type " + identifierType.getName());
        }

        sqlTypes = new int[]{((AbstractSingleColumnStandardBasicType<?>) type).sqlType()};

        String valueOfMethodName = parameters.getProperty("valueOfMethod", DEFAULT_VALUE_OF_METHOD_NAME);

        try {
            valueOfMethod = enumClass.getMethod(valueOfMethodName, new Class[]{identifierType});
        } catch (Exception e) {
            throw new HibernateException("Failed to obtain valueOf method", e);
        }
    }

    @Override
    public Class<? extends Enum> returnedClass() {
        return enumClass;
    }

    @Override
    public int[] sqlTypes() {
        return sqlTypes;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return x == y;
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
        Object identifier = type.get(rs, names[0], session);
        if (rs.wasNull()) {
            return null;
        }

        try {
            return valueOfMethod.invoke(enumClass, new Object[]{identifier});
        } catch (Exception e) {
            throw new HibernateException("Exception while invoking valueOf method '" + valueOfMethod.getName() + "' of "
                    + "enumeration class '" + enumClass + "'", e);
        }

    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
        try {
            if (value == null) {
                st.setNull(index, ((AbstractSingleColumnStandardBasicType<?>) type).sqlType());
            } else {
                Object identifier = identifierMethod.invoke(value, new Object[0]);
                type.nullSafeSet(st, identifier, index, session);
            }
        } catch (Exception e) {
            throw new HibernateException("Exception while invoking identifierMethod '" + identifierMethod.getName() + "' of "
                    + "enumeration class '" + enumClass + "'", e);
        }
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

    @Override
    public String objectToSQLString(Object value) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String toXMLString(Object value) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object fromXMLString(String xmlValue) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}