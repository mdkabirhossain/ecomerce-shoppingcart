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
 * This class is a wrapper for {@link Countries}.
 * </p>
 *
 * @author Abhishek
 * @see Countries
 * @generated
 */
public class CountriesWrapper implements Countries, ModelWrapper<Countries> {
	public CountriesWrapper(Countries countries) {
		_countries = countries;
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

	/**
	* Returns the primary key of this countries.
	*
	* @return the primary key of this countries
	*/
	@Override
	public long getPrimaryKey() {
		return _countries.getPrimaryKey();
	}

	/**
	* Sets the primary key of this countries.
	*
	* @param primaryKey the primary key of this countries
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_countries.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the country ID of this countries.
	*
	* @return the country ID of this countries
	*/
	@Override
	public long getCountryId() {
		return _countries.getCountryId();
	}

	/**
	* Sets the country ID of this countries.
	*
	* @param countryId the country ID of this countries
	*/
	@Override
	public void setCountryId(long countryId) {
		_countries.setCountryId(countryId);
	}

	/**
	* Returns the shortname of this countries.
	*
	* @return the shortname of this countries
	*/
	@Override
	public java.lang.String getShortname() {
		return _countries.getShortname();
	}

	/**
	* Sets the shortname of this countries.
	*
	* @param shortname the shortname of this countries
	*/
	@Override
	public void setShortname(java.lang.String shortname) {
		_countries.setShortname(shortname);
	}

	/**
	* Returns the country name of this countries.
	*
	* @return the country name of this countries
	*/
	@Override
	public java.lang.String getCountryName() {
		return _countries.getCountryName();
	}

	/**
	* Sets the country name of this countries.
	*
	* @param countryName the country name of this countries
	*/
	@Override
	public void setCountryName(java.lang.String countryName) {
		_countries.setCountryName(countryName);
	}

	@Override
	public boolean isNew() {
		return _countries.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_countries.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _countries.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_countries.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _countries.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _countries.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_countries.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _countries.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_countries.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_countries.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_countries.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new CountriesWrapper((Countries)_countries.clone());
	}

	@Override
	public int compareTo(com.dynamic.model.Countries countries) {
		return _countries.compareTo(countries);
	}

	@Override
	public int hashCode() {
		return _countries.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.dynamic.model.Countries> toCacheModel() {
		return _countries.toCacheModel();
	}

	@Override
	public com.dynamic.model.Countries toEscapedModel() {
		return new CountriesWrapper(_countries.toEscapedModel());
	}

	@Override
	public com.dynamic.model.Countries toUnescapedModel() {
		return new CountriesWrapper(_countries.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _countries.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _countries.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_countries.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CountriesWrapper)) {
			return false;
		}

		CountriesWrapper countriesWrapper = (CountriesWrapper)obj;

		if (Validator.equals(_countries, countriesWrapper._countries)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Countries getWrappedCountries() {
		return _countries;
	}

	@Override
	public Countries getWrappedModel() {
		return _countries;
	}

	@Override
	public void resetOriginalValues() {
		_countries.resetOriginalValues();
	}

	private Countries _countries;
}