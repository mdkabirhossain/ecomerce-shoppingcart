/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.dynamic.model;

import com.dynamic.service.CityLocalServiceUtil;
import com.dynamic.service.ClpSerializer;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Abhishek
 */
public class CityClp extends BaseModelImpl<City> implements City {
	public CityClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return City.class;
	}

	@Override
	public String getModelClassName() {
		return City.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _cityId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCityId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cityId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("stateId", getStateId());
		attributes.put("cityId", getCityId());
		attributes.put("cityName", getCityName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long stateId = (Long)attributes.get("stateId");

		if (stateId != null) {
			setStateId(stateId);
		}

		Long cityId = (Long)attributes.get("cityId");

		if (cityId != null) {
			setCityId(cityId);
		}

		String cityName = (String)attributes.get("cityName");

		if (cityName != null) {
			setCityName(cityName);
		}
	}

	@Override
	public long getStateId() {
		return _stateId;
	}

	@Override
	public void setStateId(long stateId) {
		_stateId = stateId;

		if (_cityRemoteModel != null) {
			try {
				Class<?> clazz = _cityRemoteModel.getClass();

				Method method = clazz.getMethod("setStateId", long.class);

				method.invoke(_cityRemoteModel, stateId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCityId() {
		return _cityId;
	}

	@Override
	public void setCityId(long cityId) {
		_cityId = cityId;

		if (_cityRemoteModel != null) {
			try {
				Class<?> clazz = _cityRemoteModel.getClass();

				Method method = clazz.getMethod("setCityId", long.class);

				method.invoke(_cityRemoteModel, cityId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getCityName() {
		return _cityName;
	}

	@Override
	public void setCityName(String cityName) {
		_cityName = cityName;

		if (_cityRemoteModel != null) {
			try {
				Class<?> clazz = _cityRemoteModel.getClass();

				Method method = clazz.getMethod("setCityName", String.class);

				method.invoke(_cityRemoteModel, cityName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getCityRemoteModel() {
		return _cityRemoteModel;
	}

	public void setCityRemoteModel(BaseModel<?> cityRemoteModel) {
		_cityRemoteModel = cityRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _cityRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_cityRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			CityLocalServiceUtil.addCity(this);
		}
		else {
			CityLocalServiceUtil.updateCity(this);
		}
	}

	@Override
	public City toEscapedModel() {
		return (City)ProxyUtil.newProxyInstance(City.class.getClassLoader(),
			new Class[] { City.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		CityClp clone = new CityClp();

		clone.setStateId(getStateId());
		clone.setCityId(getCityId());
		clone.setCityName(getCityName());

		return clone;
	}

	@Override
	public int compareTo(City city) {
		long primaryKey = city.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CityClp)) {
			return false;
		}

		CityClp city = (CityClp)obj;

		long primaryKey = city.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{stateId=");
		sb.append(getStateId());
		sb.append(", cityId=");
		sb.append(getCityId());
		sb.append(", cityName=");
		sb.append(getCityName());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.dynamic.model.City");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>stateId</column-name><column-value><![CDATA[");
		sb.append(getStateId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>cityId</column-name><column-value><![CDATA[");
		sb.append(getCityId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>cityName</column-name><column-value><![CDATA[");
		sb.append(getCityName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _stateId;
	private long _cityId;
	private String _cityName;
	private BaseModel<?> _cityRemoteModel;
	private Class<?> _clpSerializerClass = com.dynamic.service.ClpSerializer.class;
}