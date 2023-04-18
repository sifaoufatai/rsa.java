package tp1;

import java.math.BigInteger;

public class Test extends  rsa {

  /*  public int p;
    public int q;
    public int n;
    public int phi;
    public int e;
    public int d;
    public int  c;
    public int dc;*/

    public Test() {
        super();
    }

    public Test(int p, int q) {
        super(p, q);
    }

    public int dechiffrer(int messageChiffre, int d , int n  ) {
        BigInteger C = BigInteger.valueOf(messageChiffre);
        BigInteger D = BigInteger.valueOf(d);
        BigInteger N= BigInteger.valueOf(n);
       // System.out.println(N);

        BigInteger M = C.modPow(D, N);

        return M.intValue();
    }

    public int chiffrer(int message, int e , int n ) {
        BigInteger M = BigInteger.valueOf(message);
        BigInteger C = M.modPow(BigInteger.valueOf(e), BigInteger.valueOf(n));

        return C.intValue();
    }

   public rsa dechiffrer2(int e ,int n, int m) {
        rsa r0;
        Test r = new Test();
        r.n= n;
        r.e = e;
        r.q = Calculerq(n);
        r.p = calculerp(n, r.q);
        r.phi = (r.p - 1) * (r.q - 1);
        r.d = modInverse(e, r.phi);
      //  System.out.println(r.d);
     //   System.out.println(r.e);
        r.c = m;
        r.dc = dechiffrer(m,r.d, n);
        int f= r.chiffrer(r.dc,r.e, n );
        // f cest le message chifreer je verifie les donner ici
       // System.out.println(f +"==>>"+ m);
        //je retounr un objet dans le quels jai tout la cle public , privé , le message chiffrer
        return r;
    }
    //question c  la complexité de mon algo est de taille
    // ici la factoriasation est au plus raccine de n ;
    int Calculerq(int n ){

        int q =2;
        while (!premier(q)|| n%q !=0){
            q++;
        }
        return q;
    }
    int calculerp(int n , int q){
        p=n/q;
        return p;
    }





}
