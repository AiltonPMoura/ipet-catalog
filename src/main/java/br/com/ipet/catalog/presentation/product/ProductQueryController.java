package br.com.ipet.catalog.presentation.product;

import br.com.ipet.catalog.application.product.query.ProductOutput;
import br.com.ipet.catalog.application.product.query.ProductQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductQueryController {

    private final ProductQueryService productQueryService;

    @GetMapping("/{productId}")
    public ProductOutput findById(@PathVariable UUID productId) {
        return productQueryService.findById(productId);
    }

}
