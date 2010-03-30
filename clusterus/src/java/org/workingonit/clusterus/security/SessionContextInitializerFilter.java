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

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.context.HttpSessionContextIntegrationFilter;
import org.springframework.security.context.SecurityContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 
 * <b>Dependencies</b>
 * <ul>
 *     <li>Spring Framework (> 2.5)</li>
 * </ul>
 * 
 * @author Vladimir Ritz Bossicard
 */
public class SessionContextInitializerFilter extends OncePerRequestFilter {

    protected final Log logger = LogFactory.getLog(getClass());
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        if (request instanceof HttpServletRequest) {
            HttpSession session = request.getSession();
            initializeSessionObjects(session);
        }
        chain.doFilter(request, response);
    }

    private void initializeSessionObjects(HttpSession session) {
        /*
         * if the session doesn't contain any reference to an ayuthenticated
         * user, there's no point in trying to update the UserContext values.
         */
        if (!isAuthenticated(session)) {
            return;
        }
        
        SessionContext sessionContext = getSessionContext(session);
        if (!sessionContext.isInitialized()) {
            WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
            
            SessionContextInitializer initializer = (SessionContextInitializer) wac.getBean("sessionInitializer", SessionContextInitializer.class);
            initializer.initialize(sessionContext);
        }
    }

    private boolean isAuthenticated(HttpSession session) {
        SecurityContext context = (SecurityContext) session.getAttribute(HttpSessionContextIntegrationFilter.SPRING_SECURITY_CONTEXT_KEY);
        return context != null && context.getAuthentication().isAuthenticated();
    }

    private SessionContext getSessionContext(HttpSession session) {
        SecurityContext context = (SecurityContext) session.getAttribute(HttpSessionContextIntegrationFilter.SPRING_SECURITY_CONTEXT_KEY);
        return (SessionContext) context.getAuthentication().getPrincipal();
    }

}