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
 * This class is a wrapper for {@link City}.
 * </p>
 *
 * @author Abhishek
 * @see City
 * @generated
 */
public class CityWrapper implements City, ModelWrapper<City> {
	public CityWrapper(City city) {
		_city = city;
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

	/**
	* Returns the primary key of this city.
	*
	* @return the primary key of this city
	*/
	@Override
	public long getPrimaryKey() {
		return _city.getPrimaryKey();
	}

	/**
	* Sets the primary key of this city.
	*
	* @param primaryKey the primary key of this city
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_city.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the state ID of this city.
	*
	* @return the state ID of this city
	*/
	@Override
	public long getStateId() {
		return _city.getStateId();
	}

	/**
	* Sets the state ID of this city.
	*
	* @param stateId the state ID of this city
	*/
	@Override
	public void setStateId(long stateId) {
		_city.setStateId(stateId);
	}

	/**
	* Returns the city ID of this city.
	*
	* @return the city ID of this city
	*/
	@Override
	public long getCityId() {
		return _city.getCityId();
	}

	/**
	* Sets the city ID of this city.
	*
	* @param cityId the city ID of this city
	*/
	@Override
	public void setCityId(long cityId) {
		_city.setCityId(cityId);
	}

	/**
	* Returns the city name of this city.
	*
	* @return the city name of this city
	*/
	@Override
	public java.lang.String getCityName() {
		return _city.getCityName();
	}

	/**
	* Sets the city name of this city.
	*
	* @param cityName the city name of this city
	*/
	@Override
	public void setCityName(java.lang.String cityName) {
		_city.setCityName(cityName);
	}

	@Override
	public boolean isNew() {
		return _city.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_city.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _city.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_city.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _city.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _city.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_city.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _city.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_city.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_city.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_city.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new CityWrapper((City)_city.clone());
	}

	@Override
	public int compareTo(com.dynamic.model.City city) {
		return _city.compareTo(city);
	}

	@Override
	public int hashCode() {
		return _city.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.dynamic.model.City> toCacheModel() {
		return _city.toCacheModel();
	}

	@Override
	public com.dynamic.model.City toEscapedModel() {
		return new CityWrapper(_city.toEscapedModel());
	}

	@Override
	public com.dynamic.model.City toUnescapedModel() {
		return new CityWrapper(_city.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _city.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _city.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_city.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CityWrapper)) {
			return false;
		}

		CityWrapper cityWrapper = (CityWrapper)obj;

		if (Validator.equals(_city, cityWrapper._city)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public City getWrappedCity() {
		return _city;
	}

	@Override
	public City getWrappedModel() {
		return _city;
	}

	@Override
	public void resetOriginalValues() {
		_city.resetOriginalValues();
	}

	private City _city;
}