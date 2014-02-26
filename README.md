Author: Yingchi Liu

*What is it?

This Java program finds n gifts matches a target price closely.

*What files does it include?

1 GiftSetSuggestion.java
	contains main methods
2 SearchAndSuggest.java
	Retrieve product information and call methods in XSumClosest.java
	It print out the results(all the sets of gifts)
3 XSumClosest.java
	It implemented the algorithm for of finding sum of the x (or n) 
	products' price that matches the target price closest.
*Dependency：

json-simple-1.1.1.jar

the program also requires the key from zappos. To change the key please edit the urls in SearchAndSuggest.java

*Important Instructions for testing
1 great a package named as "zappos"
2 import files 
	1)GiftSetSuggestion.java
	2)SearchAndSuggest.java
	3)XSumClosest.java
3 If you don't creat packag named as "zappos", you need to change the package name in each of the 3 files above.
4 If you want to use your own key from Zappos, please edit the urls in SearchAndSuggest.java by changing the key.

*Input format

Please enter number of gift you are looking for:
3 //enter a number
Please enter a target total price you want to spend: 
10 //enter a price
Please enter a category you want to shop: 
boots //enter a category

*When input in IDE console, must move the cursor to the next line after hint line 
such as “Please enter number of gift you are looking for:”

*output format
 
Each line of output contains products of one set. Products are seperated by two TAB spaces. 
The product information are formatted as "productName(productID):price"

Sample output

Tot Bowl  for Big Kids(8314215):$3.99		Gypsy Liners(8101781):$4.99

*Number of requests Limit

1 As the number of request send to the API is limited. Sometime it will results in error messages such as "
401 for URL: http://api.zappos.com/Search?limit=100&term=&sort={"price":"asc"}&page=1&key=52ddafbe3ee659bad97fcce7c53592916a6bfd73". 

It will return {"statusCode":"401","error":"Zappos API requests are being throttled, see Http Response Header for more info"} by the API, if enter this to the browser.

2 Another limit is 2 call/sec. As I did not have problem while testing. 
So, I assume current request is not faster than 2 call/sec. 
Therefore, I did not include sleep() function,
but that is something could be done to comply with the rate limit set by API.