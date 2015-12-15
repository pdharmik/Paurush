-- SR Locale Administration P1 Change

-- add PARTNER_TYPE into table SIEBEL_LOCALIZATION
alter table SIEBEL_LOCALIZATION add PARTNER_TYPE VARCHAR2(20);
-- add check constraint PARTNER_TYPE_CK into table SIEBEL_LOCALIZATION
alter table SIEBEL_LOCALIZATION add constraint PARTNER_TYPE_CK check (PARTNER_TYPE IN (NULL, 'DIRECT', 'INDIRECT', 'BOTH'));