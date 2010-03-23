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

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.workingonit.addenda.freemarker.FreemarkerOutputTemplate;
import org.workingonit.addenda.freemarker.FreemarkerUtils;
import org.workingonit.linguae.dao.MessageDao;
import org.workingonit.linguae.dao.MessageTO;

import freemarker.template.Configuration;

/**
 * @author Vladimir Ritz Bossicard
 */
@ManagedResource(objectName="linguae:name=MessageSource",
    description="Responsible for loading and storing languages")
public class DatabaseDiscoveryMessageSource extends AbstractDiscoveryMessageSource
    implements ApplicationContextAware {

    private String basename;
    private ApplicationContext context;
    private MessageDao dao;
    protected Configuration cfg = FreemarkerUtils.createClassloaderConfiguration(getClass());

    public void setMessageDao(final MessageDao dao) {
        this.dao = dao;
    }

    @Override
    public void setApplicationContext(final ApplicationContext context)
            throws BeansException {
        this.context = context;
    }

    /*
     * The method is overridden because there is no method to retrieve the
     * basename from the ReloadableResourceBundleMessageSource class.
     */
    @Override
    public void setBasename(final String basename) {
        this.basename = basename;
        super.setBasename(basename);
    }

    @Override
    protected Locale[] getAvailableLocales() {
        try {
            List<Locale> res = this.dao.getAvailableLocales();
            return res == null ? null : res.toArray(new Locale[res.size()]);
        } catch (Exception ex) {
            this.log.warn("error while loading the messages", ex);
            return null;
        }
    }

    @Override
    protected void loadMessages(final Locale locale) {
        Resource rs = this.context.getResource(this.basename);
        try {
            File outFile = new File(rs.getFile().getAbsolutePath() + "_" + locale.getLanguage() + ".properties");
            FileUtils.forceMkdir(outFile.getParentFile());

            if (this.log.isInfoEnabled()) {
                this.log.info("loading messages for locale '" + locale + "' into " + outFile.getAbsolutePath());
            }

            List<MessageTO> messages = this.dao.getMessages(locale.getLanguage());

            Map<String, Object> model = new HashMap<String, Object>();
            model.put("language", locale.getLanguage());
            model.put("checkMessage", this.checkMessage);
            model.put("messages", messages);
            new FreemarkerOutputTemplate(this.cfg).writeToFile(model, "labels.properties.ftl", outFile);
        } catch (Exception ex) {
            this.log.warn("error while generating the messages", ex);
        }
    }

}
