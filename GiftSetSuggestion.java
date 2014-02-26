package zappos;

import java.io.BufferedReader;
import java.io.InputStreamReader;



public class GiftSetSuggestion {
	public static void main(String[] args) throws Exception {
		int n = 0; //number of items
		double totalprice=0; //total money
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter number of gift you are looking for:");
        String num_gifts = br.readLine();
        System.out.println("Please enter a target total price you want to spend:");
        String total_price = br.readLine();
        System.out.println("Please enter a category you want to shop:");
        String category = br.readLine();
        
        try{
            n = Integer.parseInt(num_gifts);
            //System.out.println(n);
            totalprice = Double.parseDouble(total_price);
            if (category.equals(""))
            	category="all"; //default value
            //System.out.println(totalprice);
        }catch(NumberFormatException nfe){
            System.err.println("Please enter valid number format!");
        }

		if (n>0 && totalprice>0){
			SearchAndSuggest ss = new SearchAndSuggest();
			ss.findSets(n,totalprice,category);
		}else{
			System.out.println("please give n>0 and totalprice>0");
		}
 
	}
}
