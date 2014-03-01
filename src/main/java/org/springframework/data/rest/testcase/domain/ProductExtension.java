
package org.springframework.data.rest.testcase.domain;

/**
 * Work around for DATAREST-254 is to make an extension class which houses the calculated
 * methods (methods not backed by fields)
 *
 * @author fferoz
 *
 */
public class ProductExtension extends Product {

    private static final long serialVersionUID = 7343617188163921023L;

    public String getName2() {
        return getName();
    }

    public void setName2(String name) {
        super.setName(name);
    }

}
