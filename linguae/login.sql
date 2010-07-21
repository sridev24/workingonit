REM ----------------------------------------------------
REM login script executed by sqlplus
REM ----------------------------------------------------

set pagesize 999

set linesize 132

set serveroutput on size 1000000 format wrapped

column segment_name format A30 word_wrap
column object_name format A30 word_wrap
