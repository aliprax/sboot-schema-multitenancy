package me.aliprax.sbootschemamultitenancy.database.repositories;

import me.aliprax.sbootschemamultitenancy.database.entities.Tenant;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TenantRepository extends PagingAndSortingRepository<Tenant,String>{

}
