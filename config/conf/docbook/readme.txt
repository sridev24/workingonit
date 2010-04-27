Docbook usage
-------------

If you want to generate Docbook documents, you'll have to modify your Ant build
script as follow:

* define the xml namespace

    <project [...] xmlns:docbook="antlib:org.workingonit.docbook">

* import the docbook-antlib.xml file

    <typedef file="conf/ant/docbook-antlib.xml" uri="antlib:org.workingonit.docbook"/>

* download the various java projects used for the generation of the document

    <project:init ivyfile="conf/docbook/ivy.xml"/>

* (optional) generate various assets according to the project name

    <docbook:init project.name="[project.name]"/>

* generate the document

    <docbook:pdf.generate file="[filename]" draft="yes"/>




