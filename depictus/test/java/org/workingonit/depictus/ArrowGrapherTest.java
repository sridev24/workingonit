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
public class ArrowGrapherTest {

    @Test
    public void generateWithLabel() throws Exception {
        new Graphics2DFileOutputterTemplate() {
            @Override
            protected void doWithGraphics(final Graphics2D graphics) {
                graphics.translate(150, 200);

                int length = 75;
                String label = "Hello";
                double ratio = 1.0d;

                ArrowGrapher grapher = new ArrowGrapher();
                grapher.graph(graphics, 0, length, label, ratio);
                grapher.graph(graphics, 30, length, label, ratio);
                grapher.graph(graphics, 60, length, label, ratio);
                grapher.graph(graphics, 90, length, label, ratio);
                grapher.graph(graphics, 120, length, label, ratio);
                grapher.graph(graphics, 150, length, label, ratio);
                grapher.graph(graphics, 180, length, label, ratio);
            }
        }.process("target/test/arrow-grapher.png");

    }

    @Test
    public void generateNoLabel() throws Exception {
        new Graphics2DFileOutputterTemplate() {
            @Override
            protected void doWithGraphics(final Graphics2D graphics) {
                graphics.translate(150, 200);

                int length = 75;

                ArrowGrapher grapher = new ArrowGrapher();
                grapher.graph(graphics, 0, length);
                grapher.graph(graphics, 30, length);
                grapher.graph(graphics, 60, length);
                grapher.graph(graphics, 90, length);
                grapher.graph(graphics, 120, length);
                grapher.graph(graphics, 150, length);
                grapher.graph(graphics, 180, length);
            }
        }.process("target/test/arrow-nolabel-grapher.png");

    }

}
