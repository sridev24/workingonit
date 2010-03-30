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
package org.workingonit.linguae;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jmx.export.annotation.ManagedOperation;

/**
 * @author Vladimir Ritz Bossicard
 */
public abstract class AbstractDiscoveryMessageSource extends ReloadableResourceBundleMessageSource
        implements LocaleSource, InitializingBean {

    protected final Log log = LogFactory.getLog(getClass());

    protected String checkMessage = "org.wkg.linguae.locale";

    /** Cache to hold already loaded locale */
    private final List<Locale> cachedLocale = new ArrayList<Locale>();

    public void setCheckMessage(final String checkMessage) {
        this.checkMessage = checkMessage;
    }

    public List<Locale> getLocales() {
        return Collections.unmodifiableList(this.cachedLocale);
    }

    @ManagedOperation
    public String[] getLocalesAsString() {
        List<String> res = new ArrayList<String>();
        for (Locale locale : getLocales()) {
            res.add(locale.toString());
        }
        return (String[]) res.toArray();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        loadMessages();
    }

    @Override
    @ManagedOperation(description="forces a reload of the messages")
    public void clearCache() {
        super.clearCache();
        loadMessages();
    }

    private void loadMessages() {
        synchronized (this.cachedLocale) {
            this.cachedLocale.clear();
            for (Locale locale : getAvailableLocales()) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug("checking for locale: " + locale);
                }
                loadMessages(locale);
                String msg = "";
                try {
                    msg = getMessage(this.checkMessage, null, "", locale);
                } catch (NoSuchMessageException ex) {
                    // noop
                }
                if (locale.toString().equals(msg)) {
                    this.cachedLocale.add(locale);
                    if (this.log.isInfoEnabled()) {
                        this.log.info("locale '" + locale + "' was found");
                    }
                }
            }
        }
    }

    /**
     * Callback method that let implementing classes load the messages for the
     * given {@link Locale}. This should be used if the messages are not
     * available when the application is started.
     * 
     * @param locale the {@link Locale} the prepare the messages for.
     */
    protected abstract void loadMessages(Locale locale);

    protected abstract Locale[] getAvailableLocales();

}