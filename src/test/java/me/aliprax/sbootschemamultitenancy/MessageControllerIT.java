package me.aliprax.sbootschemamultitenancy;


import me.aliprax.sbootschemamultitenancy.database.entities.Message;
import me.aliprax.sbootschemamultitenancy.database.entities.Tenant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MessageControllerIT {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void testMessageCreation(){
        Tenant tenant = new Tenant();
        tenant.setSchemaName("MESSAGE_TEST_POST");
        tenant.setTenantName("testMessageCreation");
        ResponseEntity<Tenant> response = restTemplate.postForEntity("/tenants",tenant,Tenant.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.CREATED);
        assertThat(response.getBody()).hasFieldOrProperty("uuid");

        String tenantUuid = response.getBody().getUuid();
        Message message = new Message();
        message.setMessage("This is a test message");
        ResponseEntity<Message> responsePost = restTemplate.exchange(RequestEntity
                .post(URI.create("/messages"))
                .header("tenant-uuid",tenantUuid)
                .body(message), Message.class);
        assertThat(responsePost.getStatusCode()).isEqualByComparingTo(HttpStatus.CREATED);
        assertThat(responsePost.getBody())
                .hasFieldOrProperty("uuid")
                .hasFieldOrPropertyWithValue("message",message.getMessage());


        restTemplate.delete("/tenant/{uuid}",response.getBody().getUuid());
    }

}