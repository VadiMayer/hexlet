package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

import java.io.IOException;

// BEGIN
@Component
public class ProductSpecification {
    public Specification<Product> build(ProductParamsDTO paramsDTO) {
        return withCategoryId(paramsDTO.getCategoryId())
                .and(withPriceGt(paramsDTO.getPriceGt()));
    }

    private Specification<Product> withCategoryId(Long categoryId) {
        return ((root, query, criteriaBuilder) ->
                categoryId == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("categoryId"), categoryId));
    }

    private Specification<Product> withPriceGt(Integer priceGt) {
        return ((root, query, criteriaBuilder) ->
                priceGt == null ? criteriaBuilder.conjunction() : criteriaBuilder.greaterThan(root.get("priceGt"), priceGt));
    }

    
}

// END
