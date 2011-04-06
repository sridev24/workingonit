/*
 * Copyright 2009 Vladimir Ritz Bossicard
 *
 * This file is part of WorkingOnIt.
 *
 * WorkingOnIt is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) 
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * Version     : $Revision: 269 $
 * Last edit   : $Date: 2009-08-27 13:11:39 +0200 (Thu, 27 Aug 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.addenda.http;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.NameValuePair;
import org.testng.annotations.Test;

/**
 * Tests for the {@link QueryStringBuilder} class.
 *
 * @author Vladimir Ritz Bossicard
 */
@Test
public class QueryStringBuilderTest {

    /* ----- testing the url's base ----- */

    public void emptyBaseUrl() {
        QueryStringBuilder builder = new QueryStringBuilder();

        assertEquals("", builder.encode());
    }

    public void nullBaseUrl() {
        QueryStringBuilder builder = new QueryStringBuilder(null);

        assertEquals("", builder.encode());
    }

    public void blankBaseUrl() {
        QueryStringBuilder builder = new QueryStringBuilder("  ");

        assertEquals("", builder.encode());
    }

    public void initializedBaseUrl() {
        QueryStringBuilder builder = new QueryStringBuilder("/test.jsp?hello=world&bonjour=monde");

        assertEquals("/test.jsp", builder.getBase());

        assertTrue(builder.containsParameter("hello"));
        assertEquals("world", builder.getQueryParameter("hello").getValue());

        assertTrue(builder.containsParameter("bonjour"));
        assertEquals("monde", builder.getQueryParameter("bonjour").getValue());
    }

    public void initializedTrimmedBaseUrl() {
        QueryStringBuilder builder = new QueryStringBuilder("/test.jsp?hello=world   ");

        assertEquals("/test.jsp", builder.getBase());

        assertTrue(builder.containsParameter("hello"));
        assertEquals("world", builder.getQueryParameter("hello").getValue());
    }

    public void initializedDecodedBaseUrl() {
        QueryStringBuilder builder = new QueryStringBuilder("/test.jsp?hello=big+world");

        assertEquals("/test.jsp", builder.getBase());

        assertTrue(builder.containsParameter("hello"));
        assertEquals("big world", builder.getQueryParameter("hello").getValue());
    }

    public void baseUrl() {
        QueryStringBuilder builder = new QueryStringBuilder("/test.jsp");

        assertEquals("/test.jsp", builder.encode());
    }

    /* ----- testing NameValuePair parameters ----- */

    public void valuePairParameter() {
        QueryStringBuilder builder = new QueryStringBuilder("/test.jsp")
            .addQueryParameter(new NameValuePair("hello", "world"));

        assertEquals("/test.jsp?hello=world", builder.encode());
    }

    public void nullValuePairParameter() {
        QueryStringBuilder builder = new QueryStringBuilder("/test.jsp")
            .addQueryParameter(null);

        assertEquals("/test.jsp", builder.encode());
    }

    public void valuePairNullValueParameter() {
        QueryStringBuilder builder = new QueryStringBuilder("/test.jsp")
            .addQueryParameter(new NameValuePair("hello", null));

        assertEquals("/test.jsp", builder.encode());
    }

    /* ----- testing string parameters ----- */

    public void singleParameter() {
        QueryStringBuilder builder = new QueryStringBuilder("/test.jsp")
            .addQueryParameter("hello", "world");

        assertEquals("/test.jsp?hello=world", builder.encode());
    }

    public void trimSingleParameter() {
        QueryStringBuilder builder = new QueryStringBuilder("/test.jsp")
            .addQueryParameter("hello", "world  ");

        assertEquals("/test.jsp?hello=world", builder.encode());
    }

    public void nullParameter() {
        QueryStringBuilder builder = new QueryStringBuilder("/test.jsp")
            .addQueryParameter("hello", null);

        assertEquals("/test.jsp", builder.encode());
    }

    public void nullDefaultParameter() {
        QueryStringBuilder builder = new QueryStringBuilder("/test.jsp")
            .addQueryParameter("hello", null, "NULL");

        assertEquals("/test.jsp?hello=NULL", builder.encode());
    }

    public void multipleParameter() {
        QueryStringBuilder builder = new QueryStringBuilder("/test.jsp")
            .addQueryParameter("hello", "world")
            .addQueryParameter("bonjour", "monde");

        assertEquals("/test.jsp?hello=world&bonjour=monde", builder.encode());
    }

    public void overwriteParameter() {
        QueryStringBuilder builder = new QueryStringBuilder("/test.jsp")
            .addQueryParameter("hello", "world")
            .addQueryParameter("hello", "bigworld");

        assertEquals("/test.jsp?hello=bigworld", builder.encode());
    }

    public void noOverwriteParameter() {
        QueryStringBuilder builder = new QueryStringBuilder("/test.jsp", false)
            .addQueryParameter("hello", "world")
            .addQueryParameter("hello", "bigworld");

        assertEquals("/test.jsp?hello=world", builder.encode());
    }

    public void encodedParameters() {
        QueryStringBuilder builder = new QueryStringBuilder("/test.jsp")
            .addQueryParameter("hello", "big world")
            .addQueryParameter("bonjour", "le/monde");

        assertEquals("/test.jsp?hello=big+world&bonjour=le%2Fmonde", builder.encode());
    }

    public void utf8EncodedParameters() throws UnsupportedEncodingException {
        QueryStringBuilder builder = new QueryStringBuilder("/test.jsp")
            .addQueryParameter("hello", "big world");

        assertEquals("/test.jsp?hello=big+world", builder.encode("UTF-8"));
    }

    /* ----- testing boolean parameters ----- */

    public void booleanParameters() {
        QueryStringBuilder builder = new QueryStringBuilder("/test.jsp")
            .addQueryParameter("hello", true)
            .addQueryParameter("bonjour", false);

        assertEquals("/test.jsp?hello=true&bonjour=false", builder.encode());
    }

    /* ----- testing session id ----- */
    
    public void sessionid_without_parameter() {
        QueryStringBuilder builder = new QueryStringBuilder("/test.jsp")
            .addSessionId("123456");
        
        assertEquals("/test.jsp;jsessionid=123456", builder.encode());
    }

    public void sessionid_with_parameter() {
        QueryStringBuilder builder = new QueryStringBuilder("/test.jsp")
            .addQueryParameter("hello", true)
            .addSessionId("123456");
        
        assertEquals("/test.jsp;jsessionid=123456?hello=true", builder.encode());
    }

    public void sessionid_with_base() {
        QueryStringBuilder builder = new QueryStringBuilder("/test.jsp?hello=world&bonjour=monde")
            .addSessionId("123456");
        
        assertEquals("/test.jsp;jsessionid=123456?hello=world&bonjour=monde", builder.encode());
    }

}