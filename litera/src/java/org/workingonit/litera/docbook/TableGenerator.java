package org.workingonit.litera.docbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.workingonit.addenda.freemarker.FreemarkerOutputTemplate;
import org.workingonit.addenda.freemarker.FreemarkerUtils;

import freemarker.template.Configuration;

/**
 * Generates a Docbook <code>table</code>.
 * 
 * @author Vladimir Ritz Bossicard
 */
public class TableGenerator {

    private Configuration cfg = FreemarkerUtils.createClassloaderConfiguration(getClass());

    public String generate(String title, String input) throws Exception {
        String[] lines = StringUtils.split(input.trim(), "\n");
        String[] header = StringUtils.split(lines[0].trim(), "\t;");
        header = cleanup(header);
        List<String[]> rows = new ArrayList<String[]>(lines.length-1);
        for (int i=1; i<lines.length; i++) {
            rows.add(StringUtils.split(lines[i].trim(), "\t;"));
        }

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("title", title);
        model.put("header", header);
        model.put("rows", rows);

        return new FreemarkerOutputTemplate(this.cfg).writeToString(model, "table.dbk.ftl");
    }

    private String[] cleanup(String[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = input[i].toLowerCase().replace(' ', '_');
        }
        return input;
    }

}
