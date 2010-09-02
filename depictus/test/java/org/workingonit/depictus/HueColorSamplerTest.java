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

import java.awt.Color;
import java.awt.Graphics2D;

import org.testng.annotations.Test;
import org.workingonit.test.utils.Graphics2DFileOutputterTemplate;

/**
 * @author Vladimir Ritz Bossicard
 */
public class HueColorSamplerTest {

    @Test
    public void generateSampling() throws Exception {
        new Graphics2DFileOutputterTemplate() {

            @Override
            protected void doWithGraphics(final Graphics2D graphics) {
                Color[] colors = new HueColorSampler().interpolate(Color.GREEN, Color.RED, 20);

                graphics.translate(20, 20);
                for (int i = 0; i < colors.length; i++) {
                    graphics.setPaint(colors[i]);
                    graphics.fillRect(0, i * 10, 10, 10);
                }
            }
        }.process("target/test/colors.png");
    }

}
