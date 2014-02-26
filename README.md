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

the program also requires the key from zappos. To change the key please edit the url in SearchAndSuggest.java

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
