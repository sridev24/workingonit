/*
 * Copyright 2008-2009 Vladimir Ritz Bossicard
 * 
 * This file is part of WorkingOnIt.
 *
 * WorkingOnIt is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * Version     : $Revision: 308 $
 * Last edit   : $Date: 2009-12-16 20:10:54 +0100 (Wed, 16 Dec 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.clusterus.security;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.Authentication;
import org.springframework.security.BadCredentialsException;
import org.springframework.security.providers.AuthenticationProvider;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * @author Vladimir Ritz Bossicard
 */
@ContextConfiguration (
    locations = "classpath:org/workingonit/clusterus/security/context-test.xml"
)
@Test
public class HeadlessAuthenticationProviderTest extends AbstractTestNGSpringContextTests {

    @Autowired
    @Qualifier("authenticationProvider")
    AuthenticationProvider provider;
    
    public void valid_login() {
        Authentication auth = provider.authenticate(new UsernamePasswordAuthenticationToken("scott", "tiger"));
        assertNotNull(auth);
        UserDetails principal = (UserDetails) auth.getPrincipal();
        assertEquals(principal.getClass(), SessionContext.class);
        assertEquals(principal.getUsername(), "scott");
    }
    
    @Test(expectedExceptions = BadCredentialsException.class)
    public void invalid_login() {
        provider.authenticate(new UsernamePasswordAuthenticationToken("scott", "lion"));
    }
    
}
