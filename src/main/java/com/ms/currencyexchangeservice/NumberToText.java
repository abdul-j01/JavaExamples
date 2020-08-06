package com.ms.currencyexchangeservice;

import org.springframework.stereotype.Component;

@Component
public class NumberToText {
	
	static String units[]= {"","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten",
			"Eleven","Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
	static String tens[]= {"","","Twenty","Thirty","Fourty","Fifty","Sixty","Seventy","Eighty","Ninety"};
	
	public static String convert(int n){
		
		if(n<0){
			return "Minus " + convert(-n);
		}else if(n < 20) {
			return units[n];
		}else if(n < 100) {
			return tens[n/10] +((n%10 !=0) ? " ":"")+units[n%10];
		}else if(n < 1000) {
			return units[n/100] +" Hundred" +((n%100 !=0) ? " ":"")+convert(n%100);
		}else if(n < 100000) {
			return convert(n/1000) +" Thousand" +((n%10000 !=0) ? " ":"")+convert(n%1000);
		}else if(n < 10000000) {
			return convert(n/100000) +" Lakh" +((n%1000000 !=0) ? " ":"")+convert(n%100000);
		}else {
			return convert(n/10000000) +" Crore" +((n%10000000 !=0) ? " ":"")+convert(n%10000000);
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(convert(1222567293));
	}

}
