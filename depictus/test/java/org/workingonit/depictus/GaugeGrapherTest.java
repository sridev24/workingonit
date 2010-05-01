/*
 * Copyright 2008-2010 Vladimir Ritz Bossicard
 *
 * This file is part of WorkingOnIt Depictus.
 *
 * WorkingOnIt Depictus is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.workingonit.depictus;

import java.awt.Graphics2D;

import org.testng.annotations.Test;
import org.workingonit.test.utils.Graphics2DFileOutputterTemplate;

/**
 * @author Vladimir Ritz Bossicard
 */
public class GaugeGrapherTest {

    private void generatePercentile(final float percentile, final String filename) throws Exception {
        new Graphics2DFileOutputterTemplate() {
            @Override
            protected void doWithGraphics(final Graphics2D graphics) {
                graphics.translate(50, 100);

                int radius = 190;

                new GaugeGrapher().graph(graphics, radius, radius, percentile, "Hello");
            }
        }.process("target/test/" + filename);

    }

    @Test
    public void generate() throws Exception {
        generatePercentile(0, "gauge-grapher-000pc.png");
        generatePercentile(.25f, "gauge-grapher-025pc.png");
        generatePercentile(.30f, "gauge-grapher-030pc.png");
        generatePercentile(.5f, "gauge-grapher-050pc.png");
        generatePercentile(.60f, "gauge-grapher-060pc.png");
        generatePercentile(.75f, "gauge-grapher-075pc.png");
        generatePercentile(1, "gauge-grapher-100pc.png");
    }

}