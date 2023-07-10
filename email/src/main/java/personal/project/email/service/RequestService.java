package personal.project.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import personal.project.email.model.Request;
import personal.project.email.repository.RequestRepository;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class RequestService {

    @Autowired
    RequestRepository repository;

    public Mono<Request> addRequest(Request request) {
        log.info("Saving " + request.getClass() + " Request!");
        return repository.save(request);
    }

    public Mono<Request> getRequest(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.defer(() -> {
                    throw new RuntimeException(id + " is not available in the Database!");
                })).flatMap(request -> {
                    log.info("Found: {}", request);
                    return Mono.just(request);
                });
    }
}
