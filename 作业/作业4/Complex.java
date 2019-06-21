//Complex.java
public class Complex{
    private double re, im;
	Complex(){
	    re = 0;
		im = 0;
	}
	Complex(double r, double i){
	    re = r;
		im = i;
	}
	Complex(Complex ref){
	    re = ref.re;
		im = ref.im;
	}
	double getReal(){
	    return re;
	}
	double getImage(){
	    return im;
	}
	Complex add(Complex ref){
	    Complex c = new Complex();
		c.re = this.re+ref.re;
		c.im = this.im+ref.im;
		return c;
	}
	public static void main(String[] args){
	    Complex c1 = new Complex();
		Complex c2 = new Complex(1, 2);
		Complex c3 = new Complex(c2);
		Complex c4 = new Complex(3,4);
		c4 = c4.add(c3);
		System.out.println(c1.getReal()+"+"+c1.getImage()+"i");
		System.out.println(c2.getReal()+"+"+c2.getImage()+"i");
		System.out.println(c3.getReal()+"+"+c3.getImage()+"i");
		System.out.println(c4.getReal()+"+"+c4.getImage()+"i");
	}
}