/*
 * Creates and initializes a table to manage the application defined exceptions.
 * Additional scripts and tools will automatically generate other assets, like 
 * the PL/SQL package and documentation.
 */

-- source: http://www.oracle.com/technology/oramag/oracle/03-jul

DROP TABLE msg_info;

CREATE TABLE msg_info (
    msgcode INTEGER,
    msgtype VARCHAR2(30),
    msgmodule VARCHAR2(30),
    msgtext VARCHAR2(2000),
    msgname VARCHAR2(30),
    description VARCHAR2(2000)
);
