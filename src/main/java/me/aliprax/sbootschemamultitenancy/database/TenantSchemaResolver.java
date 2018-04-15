package me.aliprax.sbootschemamultitenancy.database;

import me.aliprax.sbootschemamultitenancy.database.repositories.TenantRepository;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class TenantSchemaResolver implements CurrentTenantIdentifierResolver {

	private String defaultTenant ="DEFAULT_SCHEMA";

    @Override
	public String resolveCurrentTenantIdentifier() {
		String t =  TenantContext.getTenantSchema();
		if(t!=null){
			return t;
		} else {
			return defaultTenant;
		}
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}

}