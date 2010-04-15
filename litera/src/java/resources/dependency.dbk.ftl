<?xml version="1.0"?>

<table xmlns="http://docbook.org/ns/docbook"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://docbook.org/ns/docbook http://www.docbook.org/xml/5.0/xsd/docbook.xsd"
        frame="all">

    <title>${title}</title>

    <tgroup cols='3' align='left' colsep='1' rowsep='1'>
        <colspec colname='module' colwidth='1*'/>
        <colspec colname='license' colwidth='1.5*'/>
        <colspec colname='homepage' colwidth='2*'/>
        <thead>
            <row>
                <entry>Module, Version</entry>
                <entry>License</entry>
                <entry>Homepage</entry>
            </row>
        </thead>
<#if dependencies?has_content>
        <tbody>
<#list dependencies as dep>
            <row>
                <entry>${dep.name}, ${dep.version}</entry>
                <entry>${dep.license}</entry>
                <entry>${dep.homepage}</entry>
            </row>
</#list>
        </tbody>
</#if>
    </tgroup>
</table>