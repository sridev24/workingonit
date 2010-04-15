/*
 * This file is generated, do not edit directly!
 */
CREATE OR REPLACE PACKAGE errnums
IS 

<#list infos as info>
    exc_${info.name} EXCEPTION;
    en_${info.name} CONSTANT INTEGER := ${info.code?string.computer};
    PRAGMA EXCEPTION_INIT (exc_${info.name}, ${info.code?string.computer});

</#list>
END errnums;