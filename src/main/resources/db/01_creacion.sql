CREATE DATABASE academia;
GO

USE academia;
GO

CREATE LOGIN matricula_user WITH PASSWORD = 'Matricula123!';
GO

CREATE USER matricula_user FOR LOGIN matricula_user;
GO

ALTER ROLE db_owner ADD MEMBER matricula_user;
GO
