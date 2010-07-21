package org.workingonit.litera.docbook;

import org.testng.annotations.Test;

/**
 * @author Vladimir Ritz Bossicard
 */
@Test
public class TableGeneratorTest {

    public void generateTabs() throws Exception {
        String input = "abc\tdef\n1\t2\n3\t4\n";

        System.out.println(new TableGenerator().generate("Dummy test", input));
    }

    public void generateSemicolon() throws Exception {
        String input = "hij;klm\n1;2\n3;4\n";

        System.out.println(new TableGenerator().generate("Dummy test", input));
    }

}
