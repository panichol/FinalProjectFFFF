package gamePlay;

public class Fraction {
	private int numerator;
	private int denominator;
	
	public Fraction(){
		this.numerator = 0;
		this.denominator = 0;
	}
	public Fraction(int i, int j){
		this.numerator = i;
		this.denominator = j;
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
}
