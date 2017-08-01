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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link State}.
 * </p>
 *
 * @author Abhishek
 * @see State
 * @generated
 */
public class StateWrapper implements State, ModelWrapper<State> {
	public StateWrapper(State state) {
		_state = state;
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

	/**
	* Returns the primary key of this state.
	*
	* @return the primary key of this state
	*/
	@Override
	public long getPrimaryKey() {
		return _state.getPrimaryKey();
	}

	/**
	* Sets the primary key of this state.
	*
	* @param primaryKey the primary key of this state
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_state.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the country ID of this state.
	*
	* @return the country ID of this state
	*/
	@Override
	public long getCountryId() {
		return _state.getCountryId();
	}

	/**
	* Sets the country ID of this state.
	*
	* @param countryId the country ID of this state
	*/
	@Override
	public void setCountryId(long countryId) {
		_state.setCountryId(countryId);
	}

	/**
	* Returns the state ID of this state.
	*
	* @return the state ID of this state
	*/
	@Override
	public long getStateId() {
		return _state.getStateId();
	}

	/**
	* Sets the state ID of this state.
	*
	* @param stateId the state ID of this state
	*/
	@Override
	public void setStateId(long stateId) {
		_state.setStateId(stateId);
	}

	/**
	* Returns the state name of this state.
	*
	* @return the state name of this state
	*/
	@Override
	public java.lang.String getStateName() {
		return _state.getStateName();
	}

	/**
	* Sets the state name of this state.
	*
	* @param stateName the state name of this state
	*/
	@Override
	public void setStateName(java.lang.String stateName) {
		_state.setStateName(stateName);
	}

	@Override
	public boolean isNew() {
		return _state.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_state.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _state.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_state.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _state.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _state.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_state.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _state.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_state.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_state.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_state.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new StateWrapper((State)_state.clone());
	}

	@Override
	public int compareTo(com.dynamic.model.State state) {
		return _state.compareTo(state);
	}

	@Override
	public int hashCode() {
		return _state.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.dynamic.model.State> toCacheModel() {
		return _state.toCacheModel();
	}

	@Override
	public com.dynamic.model.State toEscapedModel() {
		return new StateWrapper(_state.toEscapedModel());
	}

	@Override
	public com.dynamic.model.State toUnescapedModel() {
		return new StateWrapper(_state.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _state.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _state.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_state.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StateWrapper)) {
			return false;
		}

		StateWrapper stateWrapper = (StateWrapper)obj;

		if (Validator.equals(_state, stateWrapper._state)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public State getWrappedState() {
		return _state;
	}

	@Override
	public State getWrappedModel() {
		return _state;
	}

	@Override
	public void resetOriginalValues() {
		_state.resetOriginalValues();
	}

	private State _state;
}