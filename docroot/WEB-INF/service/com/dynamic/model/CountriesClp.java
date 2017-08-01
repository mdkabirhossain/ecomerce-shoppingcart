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

import com.dynamic.service.ClpSerializer;
import com.dynamic.service.CountriesLocalServiceUtil;

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
public class CountriesClp extends BaseModelImpl<Countries> implements Countries {
	public CountriesClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Countries.class;
	}

	@Override
	public String getModelClassName() {
		return Countries.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _countryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCountryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _countryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("countryId", getCountryId());
		attributes.put("shortname", getShortname());
		attributes.put("countryName", getCountryName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long countryId = (Long)attributes.get("countryId");

		if (countryId != null) {
			setCountryId(countryId);
		}

		String shortname = (String)attributes.get("shortname");

		if (shortname != null) {
			setShortname(shortname);
		}

		String countryName = (String)attributes.get("countryName");

		if (countryName != null) {
			setCountryName(countryName);
		}
	}

	@Override
	public long getCountryId() {
		return _countryId;
	}

	@Override
	public void setCountryId(long countryId) {
		_countryId = countryId;

		if (_countriesRemoteModel != null) {
			try {
				Class<?> clazz = _countriesRemoteModel.getClass();

				Method method = clazz.getMethod("setCountryId", long.class);

				method.invoke(_countriesRemoteModel, countryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getShortname() {
		return _shortname;
	}

	@Override
	public void setShortname(String shortname) {
		_shortname = shortname;

		if (_countriesRemoteModel != null) {
			try {
				Class<?> clazz = _countriesRemoteModel.getClass();

				Method method = clazz.getMethod("setShortname", String.class);

				method.invoke(_countriesRemoteModel, shortname);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getCountryName() {
		return _countryName;
	}

	@Override
	public void setCountryName(String countryName) {
		_countryName = countryName;

		if (_countriesRemoteModel != null) {
			try {
				Class<?> clazz = _countriesRemoteModel.getClass();

				Method method = clazz.getMethod("setCountryName", String.class);

				method.invoke(_countriesRemoteModel, countryName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getCountriesRemoteModel() {
		return _countriesRemoteModel;
	}

	public void setCountriesRemoteModel(BaseModel<?> countriesRemoteModel) {
		_countriesRemoteModel = countriesRemoteModel;
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

		Class<?> remoteModelClass = _countriesRemoteModel.getClass();

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

		Object returnValue = method.invoke(_countriesRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			CountriesLocalServiceUtil.addCountries(this);
		}
		else {
			CountriesLocalServiceUtil.updateCountries(this);
		}
	}

	@Override
	public Countries toEscapedModel() {
		return (Countries)ProxyUtil.newProxyInstance(Countries.class.getClassLoader(),
			new Class[] { Countries.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		CountriesClp clone = new CountriesClp();

		clone.setCountryId(getCountryId());
		clone.setShortname(getShortname());
		clone.setCountryName(getCountryName());

		return clone;
	}

	@Override
	public int compareTo(Countries countries) {
		long primaryKey = countries.getPrimaryKey();

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

		if (!(obj instanceof CountriesClp)) {
			return false;
		}

		CountriesClp countries = (CountriesClp)obj;

		long primaryKey = countries.getPrimaryKey();

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

		sb.append("{countryId=");
		sb.append(getCountryId());
		sb.append(", shortname=");
		sb.append(getShortname());
		sb.append(", countryName=");
		sb.append(getCountryName());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.dynamic.model.Countries");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>countryId</column-name><column-value><![CDATA[");
		sb.append(getCountryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>shortname</column-name><column-value><![CDATA[");
		sb.append(getShortname());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>countryName</column-name><column-value><![CDATA[");
		sb.append(getCountryName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _countryId;
	private String _shortname;
	private String _countryName;
	private BaseModel<?> _countriesRemoteModel;
	private Class<?> _clpSerializerClass = com.dynamic.service.ClpSerializer.class;
}