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

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.testng.annotations.Test;

/**
 * @author Vladimir Ritz Bossicard
 */
@Test
public class GaugeGrapherExporterTest {

    private void generate(final int width, final int height) throws Exception {
        generate(width, height, 0.5f);
    }

    private void generate(final int width, final int height, final float percentile) throws Exception {
        String label = "Hello";

        BufferedImage image = new GaugeGrapherExporter().export(width, height, percentile, label);

        ImageIO.write(image, "png", new File("target/test/depictus-" + width + "x" + height + ".png"));
    }

    public void generate() throws Exception {
        generate(100, 100);
        generate(200, 200);
        generate(300, 300);

        generate(200, 200, 0.75f);
    }

}
