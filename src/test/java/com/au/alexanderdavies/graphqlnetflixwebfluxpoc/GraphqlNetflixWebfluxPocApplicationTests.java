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
	void testGetAccountsIntegration() {

		String accountsQuery = "query($accountId: String!) { account(accountId: $accountId) {accountId, accountName balance} }";
		String accountId = "hvQ3b0uom9H6LdgO90Q12345asdfg";

		LinkedHashMap<String, Object> data = dgsQueryExecutor.executeAndExtractJsonPath(accountsQuery, "data",
				Collections.singletonMap("accountId", accountId));

		assertNotNull(data.get("account"));
		assertNull(data.get("transaction"));
	}

	@Test
	void testGetTransactionsIntegration() {

		String accountsQuery = "query($accountId: String!) { transaction(accountId: $accountId) {transactionId, amount, date} }";
		String accountId = "hvQ3b0uom9H6LdgO90Q12345asdfg";

		LinkedHashMap<String, Object> data = dgsQueryExecutor.executeAndExtractJsonPath(accountsQuery, "data",
				Collections.singletonMap("accountId", accountId));

		assertNotNull(data.get("transaction"));
		assertNull(data.get("account"));
	}

	@Test
	void testGetAccountsAndTransactionsIntegration() {

		String accountsQuery = "query($accountId: String!) { account(accountId: $accountId) {accountId, accountName balance} transaction(accountId: $accountId) {transactionId, amount, date} }";
		String accountId = "hvQ3b0uom9H6LdgO90Q12345asdfg";

		LinkedHashMap<String, Object> data = dgsQueryExecutor.executeAndExtractJsonPath(accountsQuery, "data",
				Collections.singletonMap("accountId", accountId));

		assertNotNull(data.get("transaction"));
		assertNotNull(data.get("account"));
	}

}
