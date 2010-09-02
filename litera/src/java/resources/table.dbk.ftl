<?xml version="1.0"?>

<table xmlns="http://docbook.org/ns/docbook"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://docbook.org/ns/docbook http://www.docbook.org/xml/5.0/xsd/docbook.xsd"
        frame="all">

<#if title?has_content>
    <title>${title}</title>

</#if>
    <tgroup cols='${header?size}' align='left' colsep='1' rowsep='1'>
<#list header as head>
        <colspec colname='${head}' colwidth='1*'/>
</#list>
        <thead>
            <row>
<#list header as head>
                <entry>${head}</entry>
</#list>
            </row>
        </thead>
<#if rows?has_content>
        <tbody>
<#list rows as row>
            <row>
<#list row as entry>
                <entry><para>${entry}</para></entry>
</#list>
            </row>
</#list>
        </tbody>
</#if>
    </tgroup>
</table>
