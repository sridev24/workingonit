-- list of primary keys of a table
select column_name, position 
from all_cons_columns A 
join all_constraints C 
on A.constraint_name = C.constraint_name 
where C.table_name = <table_name> 
and C.constraint_type = 'P';

-- list of foreign keys of a table
select col.column_name as column_name, 
rel.table_name as ref_table, 
rel.column_name as ref_column 
from user_tab_columns col 
join user_cons_columns con on col.table_name = con.table_name and col.column_name = con.column_name 
join user_constraints cc on con.constraint_name = cc.constraint_name 
join user_cons_columns rel on cc.r_constraint_name = rel.constraint_name and con.position = rel.position 
where cc.constraint_type = 'R' 
and cc.table_name = <table_name>;
