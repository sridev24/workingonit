<?xml version="1.0" encoding="UTF-8"?>

<plugin>

    <classpath>
        <include name="pdk/lib/mx4j"/>
    </classpath>

    <server name="${appname} Server" version="1.x">
        <property name="jmx.url" value="${connection}"/>

        <config include="jmx"/>

        <plugin type="autoinventory" class="org.hyperic.hq.product.jmx.MxServerDetector"/>
        <plugin type="measurement" class="org.hyperic.hq.product.jmx.MxMeasurementPlugin"/>

        <metric name="Availability"
                template="${prefix}:name=PlatformMBean:Availability"
                category="AVAILABILITY"
                indicator="true"/>
<#list groups as group>

        <service name="${group.name}">
            <property name="OBJECT_NAME" value="${group.jmxName}"/>
    
            <plugin type="autoinventory"/>
            <plugin type="measurement" class="org.hyperic.hq.product.jmx.MxMeasurementPlugin"/>

            <metric name="Availability"
                    template="${"$"}{OBJECT_NAME}:Availability"
                    category="AVAILABILITY"
                    indicator="true"/>
        </service>
</#list>
    </server>

</plugin>