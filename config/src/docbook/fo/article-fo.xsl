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

    <xsl:import href="../../docbook-xsl/fo/docbook.xsl"/>
    <xsl:import href="article-titlepage-fo.xsl"/>

    <!-- remove TOC for articles -->
    <xsl:param name="generate.toc" select="'nop'"/>

<!--###################################################
                   Paper & Page Size
    ################################################### -->

    <xsl:param name="paper.type" select="'A4'"/>

    <xsl:param name="draft.mode">yes</xsl:param>
    <xsl:param name="draft.watermark.image" select="'images/draft.png'"/>

<!--###################################################
                   Title Page
    ################################################### -->

    <xsl:template match="email">
        <xsl:call-template name="inline.charseq">
            <xsl:with-param name="content">
                <fo:inline keep-together.within-line="always" hyphenate="false">
                    <xsl:text>(</xsl:text>
                    <xsl:apply-templates/>
                    <xsl:text>)</xsl:text>
                </fo:inline>
            </xsl:with-param>
        </xsl:call-template>
    </xsl:template>

<!--###################################################
                   Fonts & Styles
    ################################################### -->

    <!-- Left aligned text and no hyphenation -->
    <xsl:param name="alignment">left</xsl:param>
    <xsl:param name="hyphenate">false</xsl:param>

    <!-- Default Font size -->
    <xsl:param name="body.font.family">sans-serif</xsl:param>
    <xsl:param name="body.font.master">10</xsl:param>
    <xsl:param name="body.font.small">8</xsl:param>
    <xsl:param name="body.start.indent">0pt</xsl:param>

    <xsl:param name="title.font.master">10</xsl:param>

    <!-- Line height in body text -->
    <xsl:param name="line-height">1.4</xsl:param>

    <xsl:attribute-set name="component.title.properties">
        <xsl:attribute name="text-align">left</xsl:attribute>
    </xsl:attribute-set>

    <xsl:attribute-set name="section.title.level1.properties">
        <xsl:attribute name="space-before.optimum">0.8em</xsl:attribute>
        <xsl:attribute name="space-before.minimum">0.8em</xsl:attribute>
        <xsl:attribute name="space-before.maximum">0.8em</xsl:attribute>
        <xsl:attribute name="font-size">
            <xsl:value-of select="$body.font.master * 1.2"/>
            <xsl:text>pt</xsl:text>
        </xsl:attribute>
        <xsl:attribute name="space-after.optimum">0.1em</xsl:attribute>
        <xsl:attribute name="space-after.minimum">0.1em</xsl:attribute>
        <xsl:attribute name="space-after.maximum">0.1em</xsl:attribute>
    </xsl:attribute-set>

    <xsl:attribute-set name="section.title.level2.properties">
        <xsl:attribute name="space-before.optimum">0.8em</xsl:attribute>
        <xsl:attribute name="space-before.minimum">0.8em</xsl:attribute>
        <xsl:attribute name="space-before.maximum">0.8em</xsl:attribute>
        <xsl:attribute name="font-weight">normal</xsl:attribute>
        <xsl:attribute name="font-style">italic</xsl:attribute>
        <xsl:attribute name="font-size">
            <xsl:value-of select="$body.font.master * 1.2"/>
            <xsl:text>pt</xsl:text>
        </xsl:attribute>
        <xsl:attribute name="space-after.optimum">0.1em</xsl:attribute>
        <xsl:attribute name="space-after.minimum">0.1em</xsl:attribute>
        <xsl:attribute name="space-after.maximum">0.1em</xsl:attribute>
    </xsl:attribute-set>

    <xsl:attribute-set name="section.title.level3.properties">
        <xsl:attribute name="space-before.optimum">0.8em</xsl:attribute>
        <xsl:attribute name="space-before.minimum">0.8em</xsl:attribute>
        <xsl:attribute name="space-before.maximum">0.8em</xsl:attribute>
        <xsl:attribute name="font-weight">normal</xsl:attribute>
        <xsl:attribute name="font-style">italic</xsl:attribute>
        <xsl:attribute name="font-size">
            <xsl:value-of select="$body.font.master"/>
            <xsl:text>pt</xsl:text>
        </xsl:attribute>
        <xsl:attribute name="space-after.optimum">0.1em</xsl:attribute>
        <xsl:attribute name="space-after.minimum">0.1em</xsl:attribute>
        <xsl:attribute name="space-after.maximum">0.1em</xsl:attribute>
    </xsl:attribute-set>

    <!-- Titles of formal objects (tables, examples, ...) -->
    <xsl:attribute-set name="formal.title.properties" use-attribute-sets="normal.para.spacing">
        <xsl:attribute name="font-weight">bold</xsl:attribute>
        <xsl:attribute name="font-size">
            <xsl:value-of select="$body.font.master"/>
            <xsl:text>pt</xsl:text>
        </xsl:attribute>
        <xsl:attribute name="hyphenate">false</xsl:attribute>
        <xsl:attribute name="space-after.minimum">0.4em</xsl:attribute>
        <xsl:attribute name="space-after.optimum">0.6em</xsl:attribute>
        <xsl:attribute name="space-after.maximum">0.8em</xsl:attribute>
    </xsl:attribute-set>

    <!-- Abstract section -->

    <xsl:attribute-set name="abstract.properties">
        <xsl:attribute name="font-style">italic</xsl:attribute>
        <xsl:attribute name="text-align">justify</xsl:attribute>
    </xsl:attribute-set>

<!--###################################################
                      Programlistings
    ################################################### -->

    <!-- Verbatim text formatting (programlistings) -->
    <xsl:attribute-set name="monospace.verbatim.properties">
        <xsl:attribute name="font-size">
            <xsl:value-of select="$body.font.small * 1.0"/>
            <xsl:text>pt</xsl:text>
        </xsl:attribute>
    </xsl:attribute-set>

    <xsl:attribute-set name="verbatim.properties">
        <xsl:attribute name="space-before.minimum">1em</xsl:attribute>
        <xsl:attribute name="space-before.optimum">1em</xsl:attribute>
        <xsl:attribute name="space-before.maximum">1em</xsl:attribute>
        <xsl:attribute name="border-color">#444444</xsl:attribute>
        <xsl:attribute name="border-style">solid</xsl:attribute>
        <xsl:attribute name="border-width">0.1pt</xsl:attribute>
        <xsl:attribute name="padding-top">0.5em</xsl:attribute>
        <xsl:attribute name="padding-left">0.5em</xsl:attribute>
        <xsl:attribute name="padding-right">0.5em</xsl:attribute>
        <xsl:attribute name="padding-bottom">0.5em</xsl:attribute>
        <xsl:attribute name="margin-left">0.5em</xsl:attribute>
        <xsl:attribute name="margin-right">0.5em</xsl:attribute>
    </xsl:attribute-set>

    <!-- Shade (background) programlistings -->
    <xsl:param name="shade.verbatim">1</xsl:param>
    <xsl:attribute-set name="shade.verbatim.style">
        <xsl:attribute name="background-color">#F0F0F0</xsl:attribute>
    </xsl:attribute-set>

<!--###################################################
                          Misc
    ################################################### -->

    <!-- Placement of titles -->
    <xsl:param name="formal.title.placement">
        figure before
        example before
        equation before
        table before
        procedure before
    </xsl:param>

</xsl:stylesheet>
