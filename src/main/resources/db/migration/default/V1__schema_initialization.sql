create table tenants (
    uuid varchar(255) primary key,
    tenant_name varchar(255),
    schema_name varchar(255),
    created_at timestamp,
    updated_at timestamp,
    constraint UC_TENANT_NAME unique (tenant_name) ,
    constraint UC_SCHEMA_NAME unique (schema_name)
);
