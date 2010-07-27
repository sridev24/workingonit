<?xml version='1.0'?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        version="1.0"
        xmlns="http://www.w3.org/TR/xhtml1/transitional"
        xmlns:fo="http://www.w3.org/1999/XSL/Format"
        exclude-result-prefixes="#default">

    <xsl:import href="../../docbook-xsl/eclipse/eclipse.xsl"/>
    <xsl:import href="book-titlepage-eclipse.xsl"/>

    <!-- management of the HTML head -->
    <xsl:param name="html.stylesheet">workingonit.css</xsl:param>

    <!-- management of the TOC -->
    <xsl:template match="preface|chapter|appendix|article" mode="toc">
        <xsl:param name="toc-context" select="."/>

        <xsl:choose>
            <xsl:when test="local-name($toc-context) = 'book'">
                <xsl:call-template name="subtoc">
                    <xsl:with-param name="toc-context" select="$toc-context"/>
                    <xsl:with-param name="nodes" select="foo"/>
                </xsl:call-template>
            </xsl:when>
            <xsl:otherwise>
                <xsl:call-template name="subtoc">
                    <xsl:with-param name="toc-context" select="$toc-context"/>
                    <xsl:with-param name="nodes"
                    select="section|sect1|glossary|bibliography|index
                        |bridgehead[$bridgehead.in.toc != 0]"/>
                </xsl:call-template>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <!-- removing navigational header/footer -->
    <xsl:template name="header.navigation"/>
    <xsl:template name="footer.navigation"/>

    <xsl:param name="chapter.autolabel">0</xsl:param>
    <xsl:param name="section.autolabel">0</xsl:param>

 </xsl:stylesheet>
