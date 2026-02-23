CREATE DATABASE push_down_ceni;

CREATE USER ceni_db_manager WITH PASSWORD '123456';

GRANT CONNECT ON DATABASE push_down_ceni TO ceni_db_manager;

\c push_down_ceni

GRANT SELECT ON candidate TO ceni_db_manager;
GRANT SELECT ON voter TO ceni_db_manager;
GRANT SELECT ON vote TO ceni_db_manager;