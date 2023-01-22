Feature: Transaction API
  As a fintech developer
  I want to test the Transaction API
  So that I can ensure it is working correctly

Scenario: Transfer funds
  Given a wallet with ID 1 and name "My Wallet" and balance 100
  And a wallet with ID 2 and name "Other Wallet" and balance 50
  When I POST a transaction with wallet ID 1 and amount 50 and type "transfer" to the "/transactions" endpoint
  Then the response status should be 201
  And the wallet with ID 1 should have a balance of 50
  And the wallet with ID 2 should have a balance of 100

Scenario: Deposit funds
  Given a wallet with ID 1 and name "My Wallet" and balance 100
  When I POST a transaction with wallet ID 1 and amount 50 and type "deposit" to the "/transactions" endpoint
  Then the response status should be 201
  And the wallet with ID 1 should have a balance of 150

Scenario: Withdraw funds
  Given a wallet with ID 1 and name "My Wallet" and balance 100
  When I POST a transaction with wallet ID 1 and amount 50 and type "withdraw" to the "/transactions" endpoint
  Then the response status should be 201
  And the wallet with ID 1 should have a balance of 50