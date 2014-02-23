
package org.springframework.data.rest.testcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.testcase.domain.Product;

public interface ProdcutRepository extends JpaRepository<Product, Long> {

}
