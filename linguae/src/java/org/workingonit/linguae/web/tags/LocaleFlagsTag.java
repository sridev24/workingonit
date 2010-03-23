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
 * Version     : $Revision: 267 $
 * Last edit   : $Date: 2009-08-21 08:09:55 +0200 (Fri, 21 Aug 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.linguae.web.tags;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.springframework.web.servlet.tags.RequestContextAwareTag;
import org.workingonit.linguae.LocaleSource;

/**
 * Looks for a bean named <code>localeSource</code> that must implement the
 * {@link LocaleSource} interface.
 *
 * @author Vladimir Ritz Bossicard
 */
public class LocaleFlagsTag extends RequestContextAwareTag {

    private static final long serialVersionUID = 1L;

    @Override
    protected int doStartTagInternal() throws Exception {
        List<Locale> locales = getLocaleSource().getLocales();

        for (Locale locale : locales) {
            writeMessage("<a href=\"linguae.htm?lang=" + locale.getLanguage() + "\"><img border=\"0\"src=\"" + getRequestContext().getThemeMessage("flag_" + locale.getLanguage()) + "\"></a>&nbsp;" );
        }

        return EVAL_BODY_INCLUDE;
    }

    /**
     * Use the current RequestContext's application context to retrieve the
     * {@link LocaleSource} bean.
     */
    protected LocaleSource getLocaleSource() {
        Object source = getRequestContext().getWebApplicationContext().getBean("messageSource");
        if (source instanceof LocaleSource) {
            return (LocaleSource) source;
        }
        throw new IllegalStateException("the bean 'messageSource' must implement the interface org.workingonit.linguae.LocaleSource " + source.getClass());
    }

    protected void writeMessage(final String msg) throws IOException {
        this.pageContext.getOut().write(String.valueOf(msg));
    }

}
