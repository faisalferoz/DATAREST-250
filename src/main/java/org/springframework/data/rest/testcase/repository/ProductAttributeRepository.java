
package org.springframework.data.rest.testcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.testcase.domain.ProductAttribute;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Long> {

}
