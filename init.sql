CREATE DATABASE transaction_service;
CREATE USER transaction_service WITH ENCRYPTED PASSWORD 'transaction_service';
GRANT ALL PRIVILEGES ON DATABASE transaction_service TO transaction_service;

CREATE DATABASE balance_service;
CREATE USER balance_service WITH ENCRYPTED PASSWORD 'balance_service';
GRANT ALL PRIVILEGES ON DATABASE balance_service TO balance_service;