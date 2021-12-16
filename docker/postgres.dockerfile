FROM postgres:14.1-alpine

COPY cars-bootstrap.sql /docker-entrypoint-initdb.d/

EXPOSE 5432