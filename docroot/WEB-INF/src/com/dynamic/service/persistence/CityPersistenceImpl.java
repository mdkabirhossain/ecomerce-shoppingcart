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

package com.dynamic.service.persistence;

import com.dynamic.NoSuchCityException;

import com.dynamic.model.City;
import com.dynamic.model.impl.CityImpl;
import com.dynamic.model.impl.CityModelImpl;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the city service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Abhishek
 * @see CityPersistence
 * @see CityUtil
 * @generated
 */
public class CityPersistenceImpl extends BasePersistenceImpl<City>
	implements CityPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CityUtil} to access the city persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CityImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CityModelImpl.ENTITY_CACHE_ENABLED,
			CityModelImpl.FINDER_CACHE_ENABLED, CityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CityModelImpl.ENTITY_CACHE_ENABLED,
			CityModelImpl.FINDER_CACHE_ENABLED, CityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CityModelImpl.ENTITY_CACHE_ENABLED,
			CityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_CITYNAME = new FinderPath(CityModelImpl.ENTITY_CACHE_ENABLED,
			CityModelImpl.FINDER_CACHE_ENABLED, CityImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchBycityName",
			new String[] { String.class.getName() },
			CityModelImpl.CITYNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CITYNAME = new FinderPath(CityModelImpl.ENTITY_CACHE_ENABLED,
			CityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBycityName",
			new String[] { String.class.getName() });

	/**
	 * Returns the city where cityName = &#63; or throws a {@link com.dynamic.NoSuchCityException} if it could not be found.
	 *
	 * @param cityName the city name
	 * @return the matching city
	 * @throws com.dynamic.NoSuchCityException if a matching city could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public City findBycityName(String cityName)
		throws NoSuchCityException, SystemException {
		City city = fetchBycityName(cityName);

		if (city == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("cityName=");
			msg.append(cityName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchCityException(msg.toString());
		}

		return city;
	}

	/**
	 * Returns the city where cityName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param cityName the city name
	 * @return the matching city, or <code>null</code> if a matching city could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public City fetchBycityName(String cityName) throws SystemException {
		return fetchBycityName(cityName, true);
	}

	/**
	 * Returns the city where cityName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param cityName the city name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching city, or <code>null</code> if a matching city could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public City fetchBycityName(String cityName, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { cityName };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CITYNAME,
					finderArgs, this);
		}

		if (result instanceof City) {
			City city = (City)result;

			if (!Validator.equals(cityName, city.getCityName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_CITY_WHERE);

			boolean bindCityName = false;

			if (cityName == null) {
				query.append(_FINDER_COLUMN_CITYNAME_CITYNAME_1);
			}
			else if (cityName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CITYNAME_CITYNAME_3);
			}
			else {
				bindCityName = true;

				query.append(_FINDER_COLUMN_CITYNAME_CITYNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCityName) {
					qPos.add(cityName);
				}

				List<City> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CITYNAME,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"CityPersistenceImpl.fetchBycityName(String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					City city = list.get(0);

					result = city;

					cacheResult(city);

					if ((city.getCityName() == null) ||
							!city.getCityName().equals(cityName)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CITYNAME,
							finderArgs, city);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CITYNAME,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (City)result;
		}
	}

	/**
	 * Removes the city where cityName = &#63; from the database.
	 *
	 * @param cityName the city name
	 * @return the city that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public City removeBycityName(String cityName)
		throws NoSuchCityException, SystemException {
		City city = findBycityName(cityName);

		return remove(city);
	}

	/**
	 * Returns the number of cities where cityName = &#63;.
	 *
	 * @param cityName the city name
	 * @return the number of matching cities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBycityName(String cityName) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CITYNAME;

		Object[] finderArgs = new Object[] { cityName };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CITY_WHERE);

			boolean bindCityName = false;

			if (cityName == null) {
				query.append(_FINDER_COLUMN_CITYNAME_CITYNAME_1);
			}
			else if (cityName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CITYNAME_CITYNAME_3);
			}
			else {
				bindCityName = true;

				query.append(_FINDER_COLUMN_CITYNAME_CITYNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindCityName) {
					qPos.add(cityName);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CITYNAME_CITYNAME_1 = "city.cityName IS NULL";
	private static final String _FINDER_COLUMN_CITYNAME_CITYNAME_2 = "city.cityName = ?";
	private static final String _FINDER_COLUMN_CITYNAME_CITYNAME_3 = "(city.cityName IS NULL OR city.cityName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_STATEID = new FinderPath(CityModelImpl.ENTITY_CACHE_ENABLED,
			CityModelImpl.FINDER_CACHE_ENABLED, CityImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByStateId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATEID =
		new FinderPath(CityModelImpl.ENTITY_CACHE_ENABLED,
			CityModelImpl.FINDER_CACHE_ENABLED, CityImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByStateId",
			new String[] { Long.class.getName() },
			CityModelImpl.STATEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_STATEID = new FinderPath(CityModelImpl.ENTITY_CACHE_ENABLED,
			CityModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByStateId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the cities where stateId = &#63;.
	 *
	 * @param stateId the state ID
	 * @return the matching cities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<City> findByStateId(long stateId) throws SystemException {
		return findByStateId(stateId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cities where stateId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.dynamic.model.impl.CityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param stateId the state ID
	 * @param start the lower bound of the range of cities
	 * @param end the upper bound of the range of cities (not inclusive)
	 * @return the range of matching cities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<City> findByStateId(long stateId, int start, int end)
		throws SystemException {
		return findByStateId(stateId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the cities where stateId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.dynamic.model.impl.CityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param stateId the state ID
	 * @param start the lower bound of the range of cities
	 * @param end the upper bound of the range of cities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<City> findByStateId(long stateId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATEID;
			finderArgs = new Object[] { stateId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_STATEID;
			finderArgs = new Object[] { stateId, start, end, orderByComparator };
		}

		List<City> list = (List<City>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (City city : list) {
				if ((stateId != city.getStateId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CITY_WHERE);

			query.append(_FINDER_COLUMN_STATEID_STATEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(CityModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(stateId);

				if (!pagination) {
					list = (List<City>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<City>(list);
				}
				else {
					list = (List<City>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first city in the ordered set where stateId = &#63;.
	 *
	 * @param stateId the state ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching city
	 * @throws com.dynamic.NoSuchCityException if a matching city could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public City findByStateId_First(long stateId,
		OrderByComparator orderByComparator)
		throws NoSuchCityException, SystemException {
		City city = fetchByStateId_First(stateId, orderByComparator);

		if (city != null) {
			return city;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("stateId=");
		msg.append(stateId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCityException(msg.toString());
	}

	/**
	 * Returns the first city in the ordered set where stateId = &#63;.
	 *
	 * @param stateId the state ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching city, or <code>null</code> if a matching city could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public City fetchByStateId_First(long stateId,
		OrderByComparator orderByComparator) throws SystemException {
		List<City> list = findByStateId(stateId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last city in the ordered set where stateId = &#63;.
	 *
	 * @param stateId the state ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching city
	 * @throws com.dynamic.NoSuchCityException if a matching city could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public City findByStateId_Last(long stateId,
		OrderByComparator orderByComparator)
		throws NoSuchCityException, SystemException {
		City city = fetchByStateId_Last(stateId, orderByComparator);

		if (city != null) {
			return city;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("stateId=");
		msg.append(stateId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchCityException(msg.toString());
	}

	/**
	 * Returns the last city in the ordered set where stateId = &#63;.
	 *
	 * @param stateId the state ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching city, or <code>null</code> if a matching city could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public City fetchByStateId_Last(long stateId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByStateId(stateId);

		if (count == 0) {
			return null;
		}

		List<City> list = findByStateId(stateId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the cities before and after the current city in the ordered set where stateId = &#63;.
	 *
	 * @param cityId the primary key of the current city
	 * @param stateId the state ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next city
	 * @throws com.dynamic.NoSuchCityException if a city with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public City[] findByStateId_PrevAndNext(long cityId, long stateId,
		OrderByComparator orderByComparator)
		throws NoSuchCityException, SystemException {
		City city = findByPrimaryKey(cityId);

		Session session = null;

		try {
			session = openSession();

			City[] array = new CityImpl[3];

			array[0] = getByStateId_PrevAndNext(session, city, stateId,
					orderByComparator, true);

			array[1] = city;

			array[2] = getByStateId_PrevAndNext(session, city, stateId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected City getByStateId_PrevAndNext(Session session, City city,
		long stateId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CITY_WHERE);

		query.append(_FINDER_COLUMN_STATEID_STATEID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(CityModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(stateId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(city);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<City> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the cities where stateId = &#63; from the database.
	 *
	 * @param stateId the state ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByStateId(long stateId) throws SystemException {
		for (City city : findByStateId(stateId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(city);
		}
	}

	/**
	 * Returns the number of cities where stateId = &#63;.
	 *
	 * @param stateId the state ID
	 * @return the number of matching cities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByStateId(long stateId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_STATEID;

		Object[] finderArgs = new Object[] { stateId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CITY_WHERE);

			query.append(_FINDER_COLUMN_STATEID_STATEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(stateId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_STATEID_STATEID_2 = "city.stateId = ?";

	public CityPersistenceImpl() {
		setModelClass(City.class);
	}

	/**
	 * Caches the city in the entity cache if it is enabled.
	 *
	 * @param city the city
	 */
	@Override
	public void cacheResult(City city) {
		EntityCacheUtil.putResult(CityModelImpl.ENTITY_CACHE_ENABLED,
			CityImpl.class, city.getPrimaryKey(), city);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CITYNAME,
			new Object[] { city.getCityName() }, city);

		city.resetOriginalValues();
	}

	/**
	 * Caches the cities in the entity cache if it is enabled.
	 *
	 * @param cities the cities
	 */
	@Override
	public void cacheResult(List<City> cities) {
		for (City city : cities) {
			if (EntityCacheUtil.getResult(CityModelImpl.ENTITY_CACHE_ENABLED,
						CityImpl.class, city.getPrimaryKey()) == null) {
				cacheResult(city);
			}
			else {
				city.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cities.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(CityImpl.class.getName());
		}

		EntityCacheUtil.clearCache(CityImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the city.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(City city) {
		EntityCacheUtil.removeResult(CityModelImpl.ENTITY_CACHE_ENABLED,
			CityImpl.class, city.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(city);
	}

	@Override
	public void clearCache(List<City> cities) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (City city : cities) {
			EntityCacheUtil.removeResult(CityModelImpl.ENTITY_CACHE_ENABLED,
				CityImpl.class, city.getPrimaryKey());

			clearUniqueFindersCache(city);
		}
	}

	protected void cacheUniqueFindersCache(City city) {
		if (city.isNew()) {
			Object[] args = new Object[] { city.getCityName() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CITYNAME, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CITYNAME, args, city);
		}
		else {
			CityModelImpl cityModelImpl = (CityModelImpl)city;

			if ((cityModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_CITYNAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { city.getCityName() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CITYNAME, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CITYNAME, args,
					city);
			}
		}
	}

	protected void clearUniqueFindersCache(City city) {
		CityModelImpl cityModelImpl = (CityModelImpl)city;

		Object[] args = new Object[] { city.getCityName() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CITYNAME, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CITYNAME, args);

		if ((cityModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_CITYNAME.getColumnBitmask()) != 0) {
			args = new Object[] { cityModelImpl.getOriginalCityName() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CITYNAME, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CITYNAME, args);
		}
	}

	/**
	 * Creates a new city with the primary key. Does not add the city to the database.
	 *
	 * @param cityId the primary key for the new city
	 * @return the new city
	 */
	@Override
	public City create(long cityId) {
		City city = new CityImpl();

		city.setNew(true);
		city.setPrimaryKey(cityId);

		return city;
	}

	/**
	 * Removes the city with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cityId the primary key of the city
	 * @return the city that was removed
	 * @throws com.dynamic.NoSuchCityException if a city with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public City remove(long cityId) throws NoSuchCityException, SystemException {
		return remove((Serializable)cityId);
	}

	/**
	 * Removes the city with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the city
	 * @return the city that was removed
	 * @throws com.dynamic.NoSuchCityException if a city with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public City remove(Serializable primaryKey)
		throws NoSuchCityException, SystemException {
		Session session = null;

		try {
			session = openSession();

			City city = (City)session.get(CityImpl.class, primaryKey);

			if (city == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(city);
		}
		catch (NoSuchCityException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected City removeImpl(City city) throws SystemException {
		city = toUnwrappedModel(city);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(city)) {
				city = (City)session.get(CityImpl.class, city.getPrimaryKeyObj());
			}

			if (city != null) {
				session.delete(city);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (city != null) {
			clearCache(city);
		}

		return city;
	}

	@Override
	public City updateImpl(com.dynamic.model.City city)
		throws SystemException {
		city = toUnwrappedModel(city);

		boolean isNew = city.isNew();

		CityModelImpl cityModelImpl = (CityModelImpl)city;

		Session session = null;

		try {
			session = openSession();

			if (city.isNew()) {
				session.save(city);

				city.setNew(false);
			}
			else {
				session.merge(city);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !CityModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((cityModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { cityModelImpl.getOriginalStateId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STATEID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATEID,
					args);

				args = new Object[] { cityModelImpl.getStateId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_STATEID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_STATEID,
					args);
			}
		}

		EntityCacheUtil.putResult(CityModelImpl.ENTITY_CACHE_ENABLED,
			CityImpl.class, city.getPrimaryKey(), city);

		clearUniqueFindersCache(city);
		cacheUniqueFindersCache(city);

		return city;
	}

	protected City toUnwrappedModel(City city) {
		if (city instanceof CityImpl) {
			return city;
		}

		CityImpl cityImpl = new CityImpl();

		cityImpl.setNew(city.isNew());
		cityImpl.setPrimaryKey(city.getPrimaryKey());

		cityImpl.setStateId(city.getStateId());
		cityImpl.setCityId(city.getCityId());
		cityImpl.setCityName(city.getCityName());

		return cityImpl;
	}

	/**
	 * Returns the city with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the city
	 * @return the city
	 * @throws com.dynamic.NoSuchCityException if a city with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public City findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCityException, SystemException {
		City city = fetchByPrimaryKey(primaryKey);

		if (city == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCityException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return city;
	}

	/**
	 * Returns the city with the primary key or throws a {@link com.dynamic.NoSuchCityException} if it could not be found.
	 *
	 * @param cityId the primary key of the city
	 * @return the city
	 * @throws com.dynamic.NoSuchCityException if a city with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public City findByPrimaryKey(long cityId)
		throws NoSuchCityException, SystemException {
		return findByPrimaryKey((Serializable)cityId);
	}

	/**
	 * Returns the city with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the city
	 * @return the city, or <code>null</code> if a city with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public City fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		City city = (City)EntityCacheUtil.getResult(CityModelImpl.ENTITY_CACHE_ENABLED,
				CityImpl.class, primaryKey);

		if (city == _nullCity) {
			return null;
		}

		if (city == null) {
			Session session = null;

			try {
				session = openSession();

				city = (City)session.get(CityImpl.class, primaryKey);

				if (city != null) {
					cacheResult(city);
				}
				else {
					EntityCacheUtil.putResult(CityModelImpl.ENTITY_CACHE_ENABLED,
						CityImpl.class, primaryKey, _nullCity);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(CityModelImpl.ENTITY_CACHE_ENABLED,
					CityImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return city;
	}

	/**
	 * Returns the city with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cityId the primary key of the city
	 * @return the city, or <code>null</code> if a city with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public City fetchByPrimaryKey(long cityId) throws SystemException {
		return fetchByPrimaryKey((Serializable)cityId);
	}

	/**
	 * Returns all the cities.
	 *
	 * @return the cities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<City> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.dynamic.model.impl.CityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cities
	 * @param end the upper bound of the range of cities (not inclusive)
	 * @return the range of cities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<City> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cities.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.dynamic.model.impl.CityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cities
	 * @param end the upper bound of the range of cities (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<City> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<City> list = (List<City>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CITY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CITY;

				if (pagination) {
					sql = sql.concat(CityModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<City>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = new UnmodifiableList<City>(list);
				}
				else {
					list = (List<City>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the cities from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (City city : findAll()) {
			remove(city);
		}
	}

	/**
	 * Returns the number of cities.
	 *
	 * @return the number of cities
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CITY);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the city persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.dynamic.model.City")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<City>> listenersList = new ArrayList<ModelListener<City>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<City>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(CityImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_CITY = "SELECT city FROM City city";
	private static final String _SQL_SELECT_CITY_WHERE = "SELECT city FROM City city WHERE ";
	private static final String _SQL_COUNT_CITY = "SELECT COUNT(city) FROM City city";
	private static final String _SQL_COUNT_CITY_WHERE = "SELECT COUNT(city) FROM City city WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "city.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No City exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No City exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(CityPersistenceImpl.class);
	private static City _nullCity = new CityImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<City> toCacheModel() {
				return _nullCityCacheModel;
			}
		};

	private static CacheModel<City> _nullCityCacheModel = new CacheModel<City>() {
			@Override
			public City toEntityModel() {
				return _nullCity;
			}
		};
}