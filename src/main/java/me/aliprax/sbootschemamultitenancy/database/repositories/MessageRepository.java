package me.aliprax.sbootschemamultitenancy.database.repositories;

import me.aliprax.sbootschemamultitenancy.database.entities.Message;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MessageRepository extends PagingAndSortingRepository<Message,String>{

}
