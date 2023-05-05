# Consistent-Hashing-Implementation
This is a simple implementation of Consistent Hashing.



This code is a simple implementation to illustrate the functioning of a data store which is based on the technique of Consistent Hashing.
This video has a great explanation of what ‘Consistent Hashing’ is.

Below are some points to note -
Same hash function is used to hash the server name(node name) as well as the key which is being stored.
The class DataNode mimics a real node which will save the key value data.

To understand these tests, the below mapping of Strings and their hash value will help. 
"cloudJunky" - 14 

"codeCreature" - 38 

"dataMonster" - 4 

"sangam" - 31 

"xangam" - 36


Run the tests to see these transactions in action.
