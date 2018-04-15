package me.aliprax.sbootschemamultitenancy;

import me.aliprax.sbootschemamultitenancy.database.entities.Tenant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TenantControllerIT {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void testTenantCreation(){
        Tenant tenant = new Tenant();
        tenant.setSchemaName("TENANT_TEST_POST");
        tenant.setTenantName("testTenantCreation");
        ResponseEntity<Tenant> response = restTemplate.postForEntity("/tenants",tenant,Tenant.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.CREATED);
        assertThat(response.getBody()).hasFieldOrProperty("uuid");
        restTemplate.delete("/tenant/{uuid}",response.getBody().getUuid());
    }


}
