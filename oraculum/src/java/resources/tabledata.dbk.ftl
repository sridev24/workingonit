<#if table.comments?has_content>
<para>
<para>${table.comments}</para>
	
</#if>
<table xmlns="http://docbook.org/ns/docbook"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://docbook.org/ns/docbook http://www.docbook.org/xml/5.0/xsd/docbook.xsd"
        frame="all">
    <title>Table '${table.name}'</title>

    <tgroup cols='4' align='left' colsep='1' rowsep='1'>
        <colspec colname='name' colwidth='2*'/>
        <colspec colname='type' colwidth='1*'/>
        <colspec colname='flags' align="center" colwidth='0.5*'/>
        <colspec colname='comments' colwidth='4*'/>
        <thead>
            <row>
                <entry>Name</entry>
                <entry>Type</entry>
                <entry>Flags</entry>
                <entry>Comments</entry>
            </row>
        </thead>
<#if table.columns?has_content>
        <tbody>
<#list table.columns as column>
<#if column.ignored == false>
            <row>
                <entry>${column.name}</entry>
                <entry>${column.type}</entry>
                <entry>${column.flags}</entry>
                <entry>${column.comments}</entry>
            </row>
</#if>
</#list>
        </tbody>
</#if>
    </tgroup>
</table>
<#if table.comments?has_content>
</para>
</#if>