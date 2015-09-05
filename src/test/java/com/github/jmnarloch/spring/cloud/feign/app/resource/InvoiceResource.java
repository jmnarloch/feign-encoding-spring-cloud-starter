package com.github.jmnarloch.spring.cloud.feign.app.resource;

import com.github.jmnarloch.spring.cloud.feign.app.domain.Invoice;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 */
@RestController
public class InvoiceResource {

    @RequestMapping(value = "invoices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Invoice>> getInvoices() {

        return ResponseEntity.ok(createInvoiceList(100));
    }

    private List<Invoice> createInvoiceList(int count) {
        final List<Invoice> invoices = new ArrayList<>();
        for (int ind = 0; ind < count; ind++) {
            final Invoice invoice = new Invoice();
            invoice.setTitle("Invoice " + (ind + 1));
            invoice.setAmount(new BigDecimal(String.format(Locale.US, "%.2f", (Math.random() * 1000))));
            invoices.add(invoice);
        }
        return invoices;
    }
}
