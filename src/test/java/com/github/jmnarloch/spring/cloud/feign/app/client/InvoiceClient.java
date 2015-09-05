package com.github.jmnarloch.spring.cloud.feign.app.client;

import com.github.jmnarloch.spring.cloud.feign.app.domain.Invoice;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 *
 */
@FeignClient("local")
public interface InvoiceClient {

    @RequestMapping(value = "invoices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Invoice>> getInvoices();
}
