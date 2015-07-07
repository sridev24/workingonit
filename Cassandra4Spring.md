## Introduction ##

The library offers some classes to work with Cassandra from a Spring application. The implementation reuses terms that are well known by Spring developers (like _CassandraTemplate_ or _ColumnRowMapper_) so that using Cassandra is not too much different than using a standard JDBC database.

## Example ##

The configuration is very similar to

```
<bean id="datasource" class="org.workingonit.cassandra.CassandraDataSource"
    p:url="localhost"
    p:port="9160"
    p:keyspace="Keyspace1"/>

<bean id="cassandraTemplate" class="org.workingonit.cassandra.CassandraTemplate"
    p:dataSource-ref="datasource"/>
```

the Spring code then looks like

```
String key = "102";

template.insert(key, new SimpleColumnPath("Standard1", "name"), "John Dow".getBytes("UTF-8"));
template.insert(key, new SimpleColumnPath("Standard1", "age"), "39".getBytes("UTF-8"));
template.insert(key, new SimpleColumnPath("Standard1", "address"), "nowhere".getBytes("UTF-8"));

template.query(key, new SimpleColumnParent("Standard1"), new SimpleSlicePredicate(), 
    new ColumnCallbackHandler() {
	public void processColumn(Column column, int pos) throws Exception {
	    System.out.println(pos + ") " + new String(column.name, "UTF-8") + " -> " + new String(column.value, "UTF-8"));
	}
});
```

would then output:

```
0) address -> nowhere
1) age -> 39
2) name -> John Dow
```