CREATE DATABASE push_down_ceni;

CREATE USER ceni_db_manager WITH PASSWORD '123456';

GRANT CONNECT ON DATABASE push_down_ceni TO ceni_db_manager;

\c push_down_ceni

GRANT CREATE ON SCHEMA public TO ceni_db_manager;


GRANT SELECT ON invoice TO ceni_db_manager;
GRANT SELECT ON invoice_line TO ceni_db_manager;
GRANT SELECT ON tax_config TO ceni_db_manager;