package zappos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


 
public class SearchAndSuggest {
 
	private final String USER_AGENT = "Mozilla/5.0";

	/**
	 * findset search through API to get all the products page by page
	 * until the first item on the page exceed the taget totalprice
	 * @param n total number of gifts
	 * @param totalprice target price
	 * @param category product category to search
	 * @throws Exception
	 */
	public void findSets(int n, double totalprice, String category) throws Exception {
		int pageNum=1;
		double tmpprice=0;
		int i;
		ArrayList<JSONObject> products=new ArrayList<JSONObject>(); // the ArrayList to store products found
		
		//search the product page by page, 100 items per page
		System.out.println("Retrieving products information...");
		while(tmpprice<=totalprice){ // stop searching next page when first item on the page is exceeding target price
			String url;
			if (category.equals("all"))
				url = "http://api.zappos.com/Search?limit=100&sort={\"price\":\"asc\"}&page="+pageNum+"&key=52ddafbe3ee659bad97fcce7c53592916a6bfd73";
			else
				url = "http://api.zappos.com/Search?limit=100&term="+category+"&sort={\"price\":\"asc\"}&page="+pageNum+"&key=52ddafbe3ee659bad97fcce7c53592916a6bfd73";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);
 
			//int responseCode = con.getResponseCode();
			//System.out.println("\nSending 'GET' request to URL : " + url);
			//System.out.println("Response Code : " + responseCode);
 
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		
			JSONParser parser = new JSONParser();
		
			try {
			 
				Object obj2 = parser.parse(response.toString());
	 
				JSONObject jsonObject = (JSONObject) obj2;
	 
			
				JSONArray productList = (JSONArray) jsonObject.get("results");
			
				if (((String) jsonObject.get("currentResultCount")).equals('0'))
					break;
				//get first item price on each page
				tmpprice=Double.parseDouble(((String) ((JSONObject) productList.get(0)).get("price")).substring(1));
				for (i=0;i<productList.size();i++){
					products.add((JSONObject) productList.get(i)); //add all the products to the ArrayList
				//System.out.println(((JSONObject) productList.get(i)).get("price"));
				}
			
		 
			}  catch (ParseException e) {
				e.printStackTrace();
			}
			pageNum++;
			}//end-while
			
			// find n items whose total price matches the totalprice closest
			System.out.println("Matching products...");// start to calculate the combinations
			System.out.println();
			
			XSumClosest xSum=new XSumClosest();
			ArrayList<ArrayList<JSONObject>> giftsSuggestions=xSum.findSumClosest(products,n,totalprice);
			//print gift set suggestion one (set) by one (set)
			for(i=0;i<giftsSuggestions.size();i++){
				for (int j=0;j<giftsSuggestions.get(i).size();j++){
					System.out.print((String) giftsSuggestions.get(i).get(j).get("productName")+'('+(String) giftsSuggestions.get(i).get(j).get("productId")+"):"+(String) giftsSuggestions.get(i).get(j).get("price"));
					System.out.print('\t');
					System.out.print('\t');
				}
				System.out.println();
			}
		}
}
