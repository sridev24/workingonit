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
 * Version     : $Revision: 155 $
 * Last edit   : $Date: 2009-05-18 22:53:11 +0200 (Mon, 18 May 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.depictus;

import java.awt.Graphics2D;

import org.testng.annotations.Test;
import org.workingonit.test.utils.Graphics2DFileOutputterTemplate;

/**
 * @author Vladimir Ritz Bossicard
 */
public class ArcGrapherTest {

    @Test
    public void generateArc() throws Exception {
        new Graphics2DFileOutputterTemplate() {
            @Override
            protected void doWithGraphics(final Graphics2D graphics) {
                ArcGrapher grapher = new ArcGrapher();

                graphics.translate(50, 50);
                grapher.setStartAngle(0);
                grapher.graph(graphics);

                graphics.translate(0, 100);
                grapher.setStartAngle(22);
                grapher.graph(graphics);
            }
        }.process("target/test/arc-grapher.png");

    }

}
