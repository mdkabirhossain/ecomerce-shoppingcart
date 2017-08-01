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

package com.dynamic.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StateLocalService}.
 *
 * @author Abhishek
 * @see StateLocalService
 * @generated
 */
public class StateLocalServiceWrapper implements StateLocalService,
	ServiceWrapper<StateLocalService> {
	public StateLocalServiceWrapper(StateLocalService stateLocalService) {
		_stateLocalService = stateLocalService;
	}

	/**
	* Adds the state to the database. Also notifies the appropriate model listeners.
	*
	* @param state the state
	* @return the state that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.dynamic.model.State addState(com.dynamic.model.State state)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _stateLocalService.addState(state);
	}

	/**
	* Creates a new state with the primary key. Does not add the state to the database.
	*
	* @param stateId the primary key for the new state
	* @return the new state
	*/
	@Override
	public com.dynamic.model.State createState(long stateId) {
		return _stateLocalService.createState(stateId);
	}

	/**
	* Deletes the state with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stateId the primary key of the state
	* @return the state that was removed
	* @throws PortalException if a state with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.dynamic.model.State deleteState(long stateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _stateLocalService.deleteState(stateId);
	}

	/**
	* Deletes the state from the database. Also notifies the appropriate model listeners.
	*
	* @param state the state
	* @return the state that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.dynamic.model.State deleteState(com.dynamic.model.State state)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _stateLocalService.deleteState(state);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stateLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _stateLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.dynamic.model.impl.StateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _stateLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.dynamic.model.impl.StateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _stateLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _stateLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _stateLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.dynamic.model.State fetchState(long stateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _stateLocalService.fetchState(stateId);
	}

	/**
	* Returns the state with the primary key.
	*
	* @param stateId the primary key of the state
	* @return the state
	* @throws PortalException if a state with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.dynamic.model.State getState(long stateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _stateLocalService.getState(stateId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _stateLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the states.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.dynamic.model.impl.StateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of states
	* @param end the upper bound of the range of states (not inclusive)
	* @return the range of states
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.dynamic.model.State> getStates(int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _stateLocalService.getStates(start, end);
	}

	/**
	* Returns the number of states.
	*
	* @return the number of states
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getStatesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _stateLocalService.getStatesCount();
	}

	/**
	* Updates the state in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param state the state
	* @return the state that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.dynamic.model.State updateState(com.dynamic.model.State state)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _stateLocalService.updateState(state);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _stateLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_stateLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _stateLocalService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public StateLocalService getWrappedStateLocalService() {
		return _stateLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedStateLocalService(StateLocalService stateLocalService) {
		_stateLocalService = stateLocalService;
	}

	@Override
	public StateLocalService getWrappedService() {
		return _stateLocalService;
	}

	@Override
	public void setWrappedService(StateLocalService stateLocalService) {
		_stateLocalService = stateLocalService;
	}

	private StateLocalService _stateLocalService;
}