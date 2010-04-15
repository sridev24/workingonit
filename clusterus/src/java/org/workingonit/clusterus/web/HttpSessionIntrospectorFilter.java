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
package org.workingonit.clusterus.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * This class evaluated the user's session size and writes it into the defined
 * log4j logger.<p>
 * 
 * <b>Dependencies</b>
 * <ul>
 *     <li>Spring Framework (> 2.5)</li>
 * </ul>
 * 
 * <b>Usage</b><p>
 * 
 * <ul>
 *    <li>copy <code>clusterus.jar</code> under the <code>WEB-INF/lib</code> directory</li>
 *    <li>define the following filter in the <code>web.xml</code> file:
 * <pre>
 *  &lt;filter>
 *      &lt;filter-name>SessionIntrospectorFilter&lt;/filter-name>
 *      &lt;filter-class>org.workingonit.clusterus.security.SessionIntrospectorFilter&lt;/filter-class>
 *  &lt;/filter>
 * 
 *  &lt;filter-mapping>
 *      &lt;filter-name>SessionIntrospectorFilter&lt;/filter-name>
 *      &lt;url-pattern>/*&lt;/url-pattern>
 *  &lt;/filter-mapping>
 * </pre>
 *     <li>set the log level for <code>org.workingonit.http</code> to <code>INFO</code></li>
 * </ul>
 *
 * @author Vladimir Ritz Bossicard
 */
public class HttpSessionIntrospectorFilter extends OncePerRequestFilter {

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        if (request instanceof HttpServletRequest) {
            HttpSession session = request.getSession();
            estimateSessionSize(session);
        }
        chain.doFilter(request, response);
    }

    @SuppressWarnings("unchecked")
    public void estimateSessionSize(HttpSession session) throws IOException {
        long total = 0;

        for (Enumeration<String> enumeration = session.getAttributeNames(); enumeration.hasMoreElements(); ) {
            String name = enumeration.nextElement();
            try {
                Object obj = session.getAttribute(name);
                
                long size = evaluateObjectSize(obj);
                outputObjectSize(name, size);
                
                total += size;
            } catch (NotSerializableException ex) {
                logger.warn("    session object not serializable: " + name, ex);
            } catch (Throwable t) {
                logger.warn("    error while evaluating " + name, t);
            }
        }

        if (logger.isInfoEnabled()) {
            logger.info("Estimated session size : " + total + " [" + session.getId() + "]");
        }
    }

    protected long evaluateObjectSize(Object obj) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(stream);
        try {
            os.writeObject(obj);
        } finally {
            os.flush();
            os.close();
        }
        return stream.size();
    }

    protected void outputObjectSize(String name, long size) {
        if (logger.isDebugEnabled()) {
            logger.debug("    session object/size: " + name + "/" + size);
        }
    }

}
