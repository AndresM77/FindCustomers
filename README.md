# FindCustomers

## The problem

We have some customer records in a text file (customers.txt) -- one customer per line, JSON lines formatted. We want to invite any customer within 100km of our Dublin office for some food and drinks on us. Write a program that will read the full list of customers and output the names and user ids of matching customers (within 100km), sorted by User ID (ascending).

- You must use the first formula from this Wikipedia article to calculate distance. Don't forget, you'll need to convert degrees to radians.

- The GPS coordinates for our SF office are 37.788802,-122.4025067.

- You can find the Customer list attached. 
We're looking for you to produce working code, with enough room to demonstrate how to structure components in a small program. Good submissions are well composed. Calculating distances and reading from a file are separate concerns. Classes or functions have clearly defined responsibilities. Poor submissions will be in the form of one big function. It’s impossible to test anything smaller than the entire operation of the program, including reading from the input file.

Here are some guidelines when approaching the submission:

- Submit code as if you intended to ship it to production.

- Use whatever language you feel strongest in. It doesn’t have to be one we use.

Each submission must meet the following requirements:

- Code must be tested. Test cases cover likely problems.

- Please include the output of your program with your submission in a separate file, e.g., output.txt.

- A file explaining how to install, how to execute the code and how to run tests. We may not be familiar with the language/framework you used and this helps us to evaluate it.

## How to install, execute code, and run tests


## Notes
I ended up writing my own parser, reader, and writer as conventional libararies in Java did not have the functionality which I was looking for.
