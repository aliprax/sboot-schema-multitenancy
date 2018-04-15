package me.aliprax.sbootschemamultitenancy.controllers;

import me.aliprax.sbootschemamultitenancy.database.entities.Tenant;
import me.aliprax.sbootschemamultitenancy.database.repositories.TenantRepository;
import org.flywaydb.core.Flyway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

@RestController
@RequestMapping(value = "/tenants")
public class TenantController {

    private TenantRepository repository;

    private DataSource dataSource;

    public TenantController(TenantRepository repository, DataSource dataSource) {
        this.repository = repository;
        this.dataSource = dataSource;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Tenant createTenant(@RequestBody Tenant tenant){
        tenant = repository.save(tenant);
        String schema = tenant.getSchemaName();
        if(schema==null) throw new RuntimeException("schema is null");
        Flyway flyway = new Flyway();
        flyway.setLocations("db/migration/tenants");
        flyway.setDataSource(dataSource);
        flyway.setSchemas(schema);
        flyway.migrate();
        return tenant;
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTenant(@RequestParam String uuid){
        repository.deleteById(uuid);
    }

    @GetMapping
    public Page<Tenant> getTenants(Pageable pageable){
        return repository.findAll(pageable);
    }

}
