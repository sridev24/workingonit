# Introduction #

Commutus is a standalone Java application that recursively migrate JUnit tests from 3.x to 4.x syntax.

<table width='100%' border='0'><tr><td align='center'>
<img src='http://wiki.workingonit.googlecode.com/hg/commutus-sample.png' />
</td></tr></table>

The following syntax will be migrated:

| **JUnit 3 Syntax**      | **JUnit 4 Syntax**            |
|:------------------------|:------------------------------|
| public void testAbc()   | @Test public void abc()       |
| public void setUp()     | @Before public void setUp()   |