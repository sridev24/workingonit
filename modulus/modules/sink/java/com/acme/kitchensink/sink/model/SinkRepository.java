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
package com.acme.kitchensink.sink.model;

import java.util.Collection;

import com.acme.kitchensink.hibernate3.AbstractRepositorySupport;

/**
 * @author Vladimir Ritz Bossicard
 */
public class SinkRepository extends AbstractRepositorySupport<Sink, Long> {

  public SinkRepository() {
    super(Sink.class);
  }

  @SuppressWarnings("unchecked")
  public Collection<Sink> findAll() {
    return getSession().getNamedQuery(Sink.FIND_ALL).list();
  }

}
