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

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * @author Vladimir Ritz Bossicard
 */
@Entity
@Table(name = "SINK")
@NamedQueries( { 
  @NamedQuery(name = Sink.FIND_ALL, query = "from Sink s") })
public class Sink implements Serializable {

  private static final long serialVersionUID = 1L;

  public final static String FIND_ALL = "findAll";
  
  @Id
  @Column(name = "ID")
  private Long id;

  @Column(name = "TYPE")
  private String type;

  public Sink() {
    // required by Hibernate
  }

  public Sink(Long id, String type) {
    this.id = id;
    this.type = type;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

}