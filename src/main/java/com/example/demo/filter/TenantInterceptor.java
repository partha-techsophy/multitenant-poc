package com.example.demo.filter;

import com.example.demo.Exception.TenantAliasNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@Component
public class TenantInterceptor implements WebFilter {

    private static final String TENANT_HEADER = "X-Tenant";


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        //Ignore if path is actuator
        if (ignoreIfHelthcheckRequest(exchange)) {
            return chain.filter(exchange);
        }

        try {
            setTenantContext(exchange);
        } catch (TenantAliasNotFoundException ex) {
            return Mono.error(
                    new TenantAliasNotFoundException("Tenant header not found.")
            );
        }

        return chain.filter(exchange);
    }

    private boolean ignoreIfHelthcheckRequest(ServerWebExchange exchange) {
        PathPattern pathPattern = new PathPatternParser().parse("/actuator/health");

        if (pathPattern.matches(exchange.getRequest().getPath().pathWithinApplication())) {
            return true;
        } else {
            return false;
        }
    }

    private void setTenantContext(ServerWebExchange exchange) throws TenantAliasNotFoundException {

        if (Objects.nonNull(exchange.getRequest()
                .getHeaders().get(TENANT_HEADER).get(0))) {
            TenantContext.setTenantId(exchange.getRequest()
                    .getHeaders().get(TENANT_HEADER).get(0));
        } else {
            log.error("Tenant header not found.");
            throw new TenantAliasNotFoundException("Tenant header not found.");
        }

    }
}
