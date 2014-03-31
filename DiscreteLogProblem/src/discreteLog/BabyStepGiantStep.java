package discreteLog;

import java.math.BigInteger;
import java.util.HashMap;

public class BabyStepGiantStep {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BigInteger y = new BigInteger("8414508");
		BigInteger g = new BigInteger("2744");
		BigInteger p = new BigInteger("24852977");
		long currentTime = System.currentTimeMillis();
		BigInteger x = computeDiscreteLog(y, g, p);
		System.out.println("x = " + x);
		System.out.println(g.modPow(x, p));
		System.out.println("Time took "+(System.currentTimeMillis() - currentTime));
		
	}
	
	public static BigInteger computeDiscreteLog(BigInteger y,BigInteger g, BigInteger p){
		System.out.println(p);
		BigInteger m = sqrt(p).add(BigInteger.ONE);
		System.out.println("m = " + m);
		BigInteger j = BigInteger.ZERO;
		
		HashMap<BigInteger, BigInteger> pairMap =  new HashMap<>();
		//compute g^j and store key value pair j g^j 
		for(; j.compareTo(m) < 0 ;j = j.add(BigInteger.ONE)){
			BigInteger gPowJ = g.modPow(j, p); 
			pairMap.put(gPowJ, j);
		}
		// Compute g^-m
		BigInteger gPowMinusM = g.modPow(m,p).modInverse(p);
		//
		BigInteger k = y;
		
		//
		 
		for(BigInteger i = new BigInteger("0"); i.compareTo(m) < 0 ; i = i.add(BigInteger.ONE)){
			
			BigInteger pairMapRes = pairMap.get(k);
			if(pairMapRes != null){
				System.out.println("i = " +i);
				 return m.multiply(i).add(pairMapRes); 
			}
			else{
				k = k.multiply(gPowMinusM).mod(p);
			}
		}
		
		return y;
	}
	
	public static BigInteger sqrt(BigInteger n) {
		  BigInteger a = BigInteger.ONE;
		  BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8")).toString());
		  while(b.compareTo(a) >= 0) {
		    BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
		    if(mid.multiply(mid).compareTo(n) > 0) b = mid.subtract(BigInteger.ONE);
		    else a = mid.add(BigInteger.ONE);
		  }
		  return a.subtract(BigInteger.ONE);
		}

}
