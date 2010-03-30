comment on table msg_info is 'MSG_INFO stores the list of mappings between Oracle and application exceptions ids and names.';

comment on column msg_info.msgcode is 'Exception number (can be an Oracle reserved number or not)';
comment on column msg_info.msgtype is 
'Type of the ''''''message''''''. Currently supported values are:
* EXCEPTION';
comment on column msg_info.msgmodule is 
'Name of the module (or packages group) declaring this exception. The possible values are:
* Oracle
* any other application name';
comment on column msg_info.msgtext is 'Short text used as describing the ''''exception''''';
comment on column msg_info.msgname is 'Identifier of the exception. This value will be referenced by PL/SQL modules';
comment on column msg_info.description is 
'Optional description of the exception

This is optional but should ideally be intelligently filled!';