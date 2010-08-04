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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * @author Vladimir Ritz Bossicard
 */
@ContextConfiguration(locations={
  "classpath:test-platform.xml",
  "classpath:META-INF/spring/com.acme.kitchensink.platform.xml",
  "classpath:META-INF/spring/com.acme.kitchensink.sink.main.xml"
})
@Test
public class SinkRepositoryTest extends AbstractTestNGSpringContextTests {

  @Autowired
  private SinkRepository repository;

  public void insertion() {
    repository.save(new Sink(1l, "type A"), true);
    repository.save(new Sink(2l, "type B"), true);    

    Collection<Sink> sinks = repository.findAll();
    assertEquals(sinks.size(), 2);
    for (Sink sink : sinks) {
      System.out.println(sink);
    }
  }

}
