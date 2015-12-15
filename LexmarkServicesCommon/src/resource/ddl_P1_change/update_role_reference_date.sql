-- Document/Report Category Display P1 Change

-- update reference data of role.
update ROLE set TARGET_PORTAL = 'Customer', ROLE_TYPE = 'External' where NAME in ('Billing', 'Standard Access', 'Project Management', 'Service and Support', 'Account Management', 'Account Administrator', 'Analyst');
update ROLE set TARGET_PORTAL = 'Customer', ROLE_TYPE = 'Internal' where NAME in ('Publishing', 'Services Portal Administrator');
insert into ROLE (ROLE_ID, NAME, TARGET_PORTAL, ROLE_TYPE) values (10, 'Service Technician', 'Partner', 'External');
insert into ROLE (ROLE_ID, NAME, TARGET_PORTAL, ROLE_TYPE) values (11, 'Service Manager', 'Partner', 'External');
insert into ROLE (ROLE_ID, NAME, TARGET_PORTAL, ROLE_TYPE) values (12, 'Accounts Receivable', 'Partner', 'External');
insert into ROLE (ROLE_ID, NAME, TARGET_PORTAL, ROLE_TYPE) values (13, 'Service Administrator', 'Partner', 'External');
insert into ROLE (ROLE_ID, NAME, TARGET_PORTAL, ROLE_TYPE) values (14, 'Partner Administrator', 'Partner', 'External');
insert into ROLE (ROLE_ID, NAME, TARGET_PORTAL, ROLE_TYPE) values (15, 'Analyst', 'Partner', 'Internal');
insert into ROLE (ROLE_ID, NAME, TARGET_PORTAL, ROLE_TYPE) values (16, 'Publishing', 'Partner', 'Internal');
insert into ROLE (ROLE_ID, NAME, TARGET_PORTAL, ROLE_TYPE) values (17, 'Services Portal Administrator', 'Partner', 'Internal');