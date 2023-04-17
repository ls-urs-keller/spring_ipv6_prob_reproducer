package com.example.ipv6.reproducer.ipv6reproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@SpringBootApplication
public class Ipv6reproducerApplication implements WebGraphQlInterceptor {

    public static void main(String[] args) {
        SpringApplication.run(Ipv6reproducerApplication.class, args);
    }

    @QueryMapping
    public Mono<String> uri() {
        return Mono.deferContextual(ctx -> Mono.just(ctx.get("URI")));
    }

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        return chain.next(request)
                .contextWrite(ctx -> ctx.put("URI", request.getUri().toUri().toASCIIString()));
    }

}
