<?xml version="1.0"?>

<table xmlns="http://docbook.org/ns/docbook"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://docbook.org/ns/docbook http://www.docbook.org/xml/5.0/xsd/docbook.xsd"
    frame="all">

    <title>PL/SQL errors</title>
    <tgroup cols='3' align='left' colsep='1' rowsep='1'>
        <colspec colname='code'/>
        <colspec colname='name'/>
        <colspec colname='description'/>
        <thead>
            <row>
                <entry>Code</entry>
                <entry>Name</entry>
                <entry>Description</entry>
            </row>
        </thead>
<#if infos?has_content>
        <tbody>
<#list infos as info>
            <row>
                <entry>${info.code?string.computer}</entry>
                <entry>${info.name}</entry>
                <entry>${info.description!''}</entry>
            </row>
</#list>
        </tbody>
</#if>
    </tgroup>
</table>
