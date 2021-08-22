package com.au.alexanderdavies.graphqlnetflixwebfluxpoc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Collections;
import java.util.LinkedHashMap;

import com.netflix.graphql.dgs.DgsQueryExecutor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GraphqlNetflixWebfluxPocApplicationTests {

	@Autowired
	DgsQueryExecutor dgsQueryExecutor;

	@Test
	void testGetAccountsQuery() {

		String accountsQuery = "query($customerId: String!) { accounts(customerId: $customerId) {accountId accountName balance customerId} }";
		String customerId = "hvQ3b0uom9H6LdgO90Q12345asdf1";

		LinkedHashMap<String, Object> data = dgsQueryExecutor.executeAndExtractJsonPath(accountsQuery, "data",
				Collections.singletonMap("customerId", customerId));

		assertNotNull(data.get("accounts"));
		assertNull(data.get("customer"));
	}

	@Test
	void testGetCustomerQuery() {

		String customerQuery = "query($customerId: String!) { customer(customerId: $customerId) {customerId firstName surname} }";
		String customerId = "hvQ3b0uom9H6LdgO90Q12345asdf1";
 
		LinkedHashMap<String, Object> data = dgsQueryExecutor.executeAndExtractJsonPath(customerQuery, "data",
				Collections.singletonMap("customerId", customerId));

		assertNotNull(data.get("customer"));
		assertNull(data.get("accounts"));
	}

	@Test
	void testGetCustomerAccountsAndTransactionsQuery() {

		String customerQuery = "query($customerId: String!) { customer(customerId: $customerId) {customerId firstName surname} accounts(customerId: $customerId) {accountId accountName balance customerId transactions {transactionId amount date accountId}} }";
		String customerId = "hvQ3b0uom9H6LdgO90Q12345asdf1";
 
		LinkedHashMap<String, Object> data = dgsQueryExecutor.executeAndExtractJsonPath(customerQuery, "data",
				Collections.singletonMap("customerId", customerId));

		assertNotNull(data.get("customer"));
		assertNotNull(data.get("accounts"));

	}

}
