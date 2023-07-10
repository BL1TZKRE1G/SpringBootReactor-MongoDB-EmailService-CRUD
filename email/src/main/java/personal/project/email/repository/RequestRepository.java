package personal.project.email.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import personal.project.email.model.Request;

public interface RequestRepository extends ReactiveMongoRepository<Request, String> {

}
