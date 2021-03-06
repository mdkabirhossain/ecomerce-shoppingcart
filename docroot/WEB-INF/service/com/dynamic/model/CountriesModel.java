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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the Countries service. Represents a row in the &quot;z_Countries&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.dynamic.model.impl.CountriesModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.dynamic.model.impl.CountriesImpl}.
 * </p>
 *
 * @author Abhishek
 * @see Countries
 * @see com.dynamic.model.impl.CountriesImpl
 * @see com.dynamic.model.impl.CountriesModelImpl
 * @generated
 */
public interface CountriesModel extends BaseModel<Countries> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a countries model instance should use the {@link Countries} interface instead.
	 */

	/**
	 * Returns the primary key of this countries.
	 *
	 * @return the primary key of this countries
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this countries.
	 *
	 * @param primaryKey the primary key of this countries
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the country ID of this countries.
	 *
	 * @return the country ID of this countries
	 */
	public long getCountryId();

	/**
	 * Sets the country ID of this countries.
	 *
	 * @param countryId the country ID of this countries
	 */
	public void setCountryId(long countryId);

	/**
	 * Returns the shortname of this countries.
	 *
	 * @return the shortname of this countries
	 */
	@AutoEscape
	public String getShortname();

	/**
	 * Sets the shortname of this countries.
	 *
	 * @param shortname the shortname of this countries
	 */
	public void setShortname(String shortname);

	/**
	 * Returns the country name of this countries.
	 *
	 * @return the country name of this countries
	 */
	@AutoEscape
	public String getCountryName();

	/**
	 * Sets the country name of this countries.
	 *
	 * @param countryName the country name of this countries
	 */
	public void setCountryName(String countryName);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(com.dynamic.model.Countries countries);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.dynamic.model.Countries> toCacheModel();

	@Override
	public com.dynamic.model.Countries toEscapedModel();

	@Override
	public com.dynamic.model.Countries toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}