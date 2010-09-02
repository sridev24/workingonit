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

        generate(150, 150, 0.75f);
    }

    public void problematic_scaling() throws Exception {
        // here I have a problem when the height and width are not the same.
        // Ideally, and since there is enough place, the graph should take 200
        // as it's maximum size but it takes 100 as the minimum. Ideally the
        // algorithm should be smart enough to figure out that even if the
        // height is 100, there is still enough place to draw the full graph.
        generate(200, 150);
        generate(150, 200);

        generate(300, 200);
        generate(200, 300);
    }
    
}
