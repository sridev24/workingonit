/*
 * Copyright 2010 Vladimir Ritz Bossicard
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.acme.kitchensink.hibernate3;

import java.io.Serializable;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Vladimir Ritz Bossicard
 */
public abstract class AbstractRepositorySupport<T, ID extends Serializable> 
    extends HibernateDaoSupport implements RepositoryOperations<T, ID> {

  private Class<T> entityClass;

  public AbstractRepositorySupport(Class<T> target) {
    super();
    this.entityClass = target;
  }

  public T load(ID id) {
    return getHibernateTemplate().load(this.entityClass, id);
  }

  public T get(ID id) {
    return getHibernateTemplate().get(this.entityClass, id);
  }

  public void save(T obj, boolean flush) {
    getHibernateTemplate().saveOrUpdate(obj);
    if (flush) {
      getHibernateTemplate().flush();
    }
  }

  public void save(T obj) {
    save(obj, false);
  }

  public void persist(T obj, boolean flush) {
    getHibernateTemplate().persist(obj);
    if (flush) {
      getHibernateTemplate().flush();
    }
  }

  public void persist(T obj) {
    save(obj, false);
  }

}