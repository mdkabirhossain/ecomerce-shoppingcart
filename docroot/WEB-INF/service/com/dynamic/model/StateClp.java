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
import com.dynamic.service.StateLocalServiceUtil;

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
public class StateClp extends BaseModelImpl<State> implements State {
	public StateClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return State.class;
	}

	@Override
	public String getModelClassName() {
		return State.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _stateId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setStateId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _stateId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("countryId", getCountryId());
		attributes.put("stateId", getStateId());
		attributes.put("stateName", getStateName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long countryId = (Long)attributes.get("countryId");

		if (countryId != null) {
			setCountryId(countryId);
		}

		Long stateId = (Long)attributes.get("stateId");

		if (stateId != null) {
			setStateId(stateId);
		}

		String stateName = (String)attributes.get("stateName");

		if (stateName != null) {
			setStateName(stateName);
		}
	}

	@Override
	public long getCountryId() {
		return _countryId;
	}

	@Override
	public void setCountryId(long countryId) {
		_countryId = countryId;

		if (_stateRemoteModel != null) {
			try {
				Class<?> clazz = _stateRemoteModel.getClass();

				Method method = clazz.getMethod("setCountryId", long.class);

				method.invoke(_stateRemoteModel, countryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getStateId() {
		return _stateId;
	}

	@Override
	public void setStateId(long stateId) {
		_stateId = stateId;

		if (_stateRemoteModel != null) {
			try {
				Class<?> clazz = _stateRemoteModel.getClass();

				Method method = clazz.getMethod("setStateId", long.class);

				method.invoke(_stateRemoteModel, stateId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getStateName() {
		return _stateName;
	}

	@Override
	public void setStateName(String stateName) {
		_stateName = stateName;

		if (_stateRemoteModel != null) {
			try {
				Class<?> clazz = _stateRemoteModel.getClass();

				Method method = clazz.getMethod("setStateName", String.class);

				method.invoke(_stateRemoteModel, stateName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getStateRemoteModel() {
		return _stateRemoteModel;
	}

	public void setStateRemoteModel(BaseModel<?> stateRemoteModel) {
		_stateRemoteModel = stateRemoteModel;
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

		Class<?> remoteModelClass = _stateRemoteModel.getClass();

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

		Object returnValue = method.invoke(_stateRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			StateLocalServiceUtil.addState(this);
		}
		else {
			StateLocalServiceUtil.updateState(this);
		}
	}

	@Override
	public State toEscapedModel() {
		return (State)ProxyUtil.newProxyInstance(State.class.getClassLoader(),
			new Class[] { State.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		StateClp clone = new StateClp();

		clone.setCountryId(getCountryId());
		clone.setStateId(getStateId());
		clone.setStateName(getStateName());

		return clone;
	}

	@Override
	public int compareTo(State state) {
		long primaryKey = state.getPrimaryKey();

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

		if (!(obj instanceof StateClp)) {
			return false;
		}

		StateClp state = (StateClp)obj;

		long primaryKey = state.getPrimaryKey();

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
		sb.append(", stateId=");
		sb.append(getStateId());
		sb.append(", stateName=");
		sb.append(getStateName());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("com.dynamic.model.State");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>countryId</column-name><column-value><![CDATA[");
		sb.append(getCountryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>stateId</column-name><column-value><![CDATA[");
		sb.append(getStateId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>stateName</column-name><column-value><![CDATA[");
		sb.append(getStateName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _countryId;
	private long _stateId;
	private String _stateName;
	private BaseModel<?> _stateRemoteModel;
	private Class<?> _clpSerializerClass = com.dynamic.service.ClpSerializer.class;
}