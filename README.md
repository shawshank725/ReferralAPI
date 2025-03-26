# ReferralAPI
 
## How it works
* First user enters a email id which is required. Name and referral code are not required.
* A new referral code is generated randomly for every user.
* If a user enters a referral code, and that code is found in the database then the referral is considered successful.
* It uses two services - one for referral tale and other for users table.
* There are two different repositories for referral and user tables.

* There is an sql script to create the tables in mysql server.
