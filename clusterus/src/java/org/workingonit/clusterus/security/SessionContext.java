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

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;

/**
 * @author Vladimir Ritz Bossicard
 */
public class SessionContext implements UserDetails {

    private static final long serialVersionUID = 1L;

    private UserDetails userDetails;

    private Map<String, Object> clustered = new HashMap<String, Object>();
    private transient Map<String, Object> value = new HashMap<String, Object>();

    public SessionContext(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public void addClusteredValue(String key, Object value) {
        this.clustered.put(key, value);
    }

    public void addTransientValue(String key, Object value) {
        this.value.put(key, value);
    }

    public boolean isInitialized() {
        if (this.value == null) {
            this.value = new HashMap<String, Object>();
        }
        return !this.value.isEmpty();
    }

    @Override
    public GrantedAuthority[] getAuthorities() {
        return this.userDetails.getAuthorities();
    }

    @Override
    public String getUsername() {
        return this.userDetails.getUsername();
    }

    @Override
    public String getPassword() {
        return this.userDetails.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.userDetails.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.userDetails.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.userDetails.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.userDetails.isEnabled();
    }

}