-- Document/Report Category Display P1 Change

-- add TARGET_PORTAL, and ROLE_TYPE into table ROLE
alter table ROLE add (TARGET_PORTAL VARCHAR2(20), ROLE_TYPE VARCHAR2(20));