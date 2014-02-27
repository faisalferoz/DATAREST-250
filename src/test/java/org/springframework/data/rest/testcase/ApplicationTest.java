
package org.springframework.data.rest.testcase;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.jayway.jsonpath.JsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationTest {

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Before
    public void before() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void shouldRenderManagedEmbeddedEntitiesAsLinks() throws Exception {
        mockMvc.perform(get("/products/{id}", -1L)) //
        .andExpect(status().isOk()) //
        .andExpect(jsonPath("$.name", is("test product 1"))) //
        .andExpect(jsonPath("$._links.productAttributes", not(nullValue()))) //
        .andExpect(
                jsonPath("$._links.productAttributes.href", not(isEmptyOrNullString())));
    }

    @Test
    public void shouldRenderManagedEmbeddedEntitiesFromEmbeddedLinks() throws Exception {
        final MockHttpServletResponse response = mockMvc.perform(
                get("/products/{id}", -1L)) //
        .andExpect(status().isOk()) //
        .andReturn().getResponse();

        final String productAttributesLink = JsonPath.read(response.getContentAsString(),
                "$._links.productAttributes.href");

        mockMvc.perform(get(productAttributesLink)) //
        .andExpect(status().isOk()) //
        .andExpect(jsonPath("$._embedded.productAttributes[*]", hasSize(1))) //
        .andExpect(
                jsonPath("$._embedded.productAttributes[0].name", is("test attribute 1")));
    }

}
