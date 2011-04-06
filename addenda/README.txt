Workingonit Addenda
===================

Maven Installation
------------------

The following libraries must be individually installed on your local maven repository (i.e. ~/.m2/repository):

   * Mylyn library (http://www.eclipse.org/mylyn/downloads/)
      - download and unzip the WikiText standalone library then execute (change the version as appropriate)

mvn install:install-file -Dfile=org.eclipse.mylyn.wikitext.confluence.core_1.4.0.I20110104-0100-e3x.jar -DgroupId=org.eclipse.mylyn.wikitext -DartifactId=wikitext-confluence -Dversion=3.5 -Dpackaging=jar
mvn install:install-file -Dfile=org.eclipse.mylyn.wikitext.core_1.4.0.I20110104-0100-e3x.jar -DgroupId=org.eclipse.mylyn.wikitext -DartifactId=wikitext-core -Dversion=3.5 -Dpackaging=jar
mvn install:install-file -Dfile=org.eclipse.mylyn.wikitext.mediawiki.core_1.4.0.I20110104-0100-e3x.jar -DgroupId=org.eclipse.mylyn.wikitext -DartifactId=wikitext-mediawiki -Dversion=3.5 -Dpackaging=jar
mvn install:install-file -Dfile=org.eclipse.mylyn.wikitext.textile.core_1.4.0.I20110104-0100-e3x.jar -DgroupId=org.eclipse.mylyn.wikitext -DartifactId=wikitext-textile -Dversion=3.5 -Dpackaging=jar
mvn install:install-file -Dfile=org.eclipse.mylyn.wikitext.tracwiki.core_1.4.0.I20110104-0100-e3x.jar -DgroupId=org.eclipse.mylyn.wikitext -DartifactId=wikitext-tracwiki -Dversion=3.5 -Dpackaging=jar
mvn install:install-file -Dfile=org.eclipse.mylyn.wikitext.twiki.core_1.4.0.I20110104-0100-e3x.jar -DgroupId=org.eclipse.mylyn.wikitext -DartifactId=wikitext-twiki -Dversion=3.5 -Dpackaging=jar


