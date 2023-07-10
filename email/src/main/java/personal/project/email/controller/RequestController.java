package personal.project.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import personal.project.email.model.Request;
import personal.project.email.service.RequestService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/requests")
public class RequestController {

    @Autowired
    RequestService service;

    @PostMapping(value = "/add-request")
    public Mono<Request> addRequest(Request request) {
        return service.addRequest(request);
    }
}
