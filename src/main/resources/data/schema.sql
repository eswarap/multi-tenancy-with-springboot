CREATE DATABASE testdb;
//Create multiple schemas as per your requirement
CREATE SCHEMA tenanta;
CREATE SCHEMA tenantb;

GRANT USAGE ON SCHEMA tenanta TO your_username;
GRANT USAGE ON SCHEMA tenantb TO your_username;