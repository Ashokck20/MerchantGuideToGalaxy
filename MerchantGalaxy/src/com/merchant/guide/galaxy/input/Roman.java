package com.merchant.guide.galaxy.input;

public enum Roman{
    
	i("i", 1), v("v", 5), x("x", 10), l("l", 50), c("c", 100), d("d", 500), m("m", 1000);
	
	private int value;
    private String symbol;
    
    Roman(String s, int value){
        this.symbol = s;
        this.value = value;
    }
	public int getValue() {
		return value;
	}
	public String getSymbol() {
		return symbol;
	}

}
