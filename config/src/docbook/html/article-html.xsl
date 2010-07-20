<?xml version='1.0'?>

<!--
  - Copyright 2008-2010 Vladimir Ritz Bossicard
  -
  - This file is part of WorkingOnIt.
  -
  - WorkingOnIt is free software: you can redistribute it and/or modify it
  - under the terms of the GNU Lesser General Public License as published by
  - the Free Software Foundation, either version 3 of the License, or (at your
  - option) any later version.
  -
  - This program is distributed in the hope that it will be useful, but WITHOUT
  - ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
  - FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
  - for more details.
  -
  - You should have received a copy of the GNU Lesser General Public License
  - along with this program. If not, see <http://www.gnu.org/licenses/>.
  -->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        version="1.0"
        xmlns="http://www.w3.org/TR/xhtml1/transitional"
        xmlns:fo="http://www.w3.org/1999/XSL/Format"
        exclude-result-prefixes="#default">

    <xsl:import href="@DOCBOOK.HOME@/html/docbook.xsl"/>

    <xsl:param name="css.decoration" select="1"/>
    <xsl:param name="html.stylesheet.type">text/css</xsl:param>
    <xsl:param name="html.stylesheet">workingonit.css</xsl:param>
 
</xsl:stylesheet>