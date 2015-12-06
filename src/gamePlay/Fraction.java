package gamePlay;

public class Fraction {
	private int numerator;
	private int denominator;
	
	public Fraction(){
		this.numerator = -1;
		this.denominator = -1;
	}
	public Fraction(int num, int denom){
		this.numerator = num;
		this.denominator = denom;
	}
	
	public Fraction(Fraction other){
		this.numerator = other.numerator;
		this.denominator = other.denominator;
	}

	public int getNumerator(){
		return numerator;
	}
	public void setNumerator(int numerator){
		this.numerator = numerator;
	}
	public int getDenominator(){
		return denominator;
	}
	public void setDenominator(int denominator){
		this.denominator = denominator;
	}
	public void reduce(){
		
	}	
	public boolean equals(Fraction other){
		return numerator == other.numerator && denominator == other.denominator;
	}
	public String toString(){
		return Integer.toString(numerator)+"/"+Integer.toString(denominator);
	}
}
