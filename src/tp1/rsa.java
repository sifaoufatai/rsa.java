package tp1;

import java.math.BigInteger;
import java.util.Random;

public class rsa {
    public int p;
    public int q;
    public int n;
    public int phi;
    public int e;
    public int d;
    public int  c;
    public int dc;

    public rsa() {
    }

    public rsa(int p, int q) {
        this.p = p;
        this.q = q;
        this.n = p * q;
        this.phi = (p - 1) * (q - 1);
        this.e = genererE();
        this.d = modInverse(e, phi);
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getPhi() {
        return phi;
    }

    public void setPhi(int phi) {
        this.phi = phi;
    }

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int pgcd(int a, int b) {
        if (a > b) {
            int c = b;
            b = a;
            a = c;
        }

        if (a == 0) return b;

        return pgcd(a, b % a);
    }

    public boolean premier(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "rsa{" +
                "p=" + p +
                ", q=" + q +
                ", n=" + n +
                ", phi=" + phi +
                ", e=" + e +
                ", d=" + d +
                ", c=" + c +
                ", dc=" + dc +
                '}';
    }

    public rsa calculerPQ(int l) {
        rsa r1 = new rsa();

        int m;
        int limite = (int) Math.pow(2, l / 2);
        Random random = new Random();

        m = (random.nextInt(limite) + 1);

        while (!premier(m)) {
            m = (random.nextInt(limite) + 1);
        }

        r1.p = m;

        while (true) {
            m = (random.nextInt(limite) + 1);

            if (premier(m) && (m != r1.p)) {
                r1.q = m;
                break;
            }
        }

        return r1;
    }

    public int genererE() {
        int e = 2;
        while (pgcd(e, phi) != 1) {
            e++;
        }
        return e;
    }

    public int modInverse(int a, int m) {
        int m0 = m;
        int y = 0, x = 1;

        if (m == 1)
            return 0;

        while (a > 1) {
            int q = a / m;
            int t = m;

            m = a % m;
            a = t;
            t = y;

            y = x - q * y;
            x = t;
        }

        if (x < 0)
            x += m0;

        return x;
    }


    public int chiffrer(int message, int e , int n ) {
        BigInteger M = BigInteger.valueOf(message);
        BigInteger C = M.modPow(BigInteger.valueOf(e), BigInteger.valueOf(n));

        return C.intValue();
    }
    public int chiffrer(int message ) {
        BigInteger M = BigInteger.valueOf(message);
        BigInteger C = M.modPow(BigInteger.valueOf(e), BigInteger.valueOf(n));

        return C.intValue();
    }


    public int dechiffrer(int messageChiffre ) {
        BigInteger C = BigInteger.valueOf(messageChiffre);
        BigInteger D = BigInteger.valueOf(d);
        BigInteger N= BigInteger.valueOf(n);
        System.out.println(N);
        // System.out.println(D);
        // System.out.println(BigInteger.valueOf(d));
        BigInteger M = C.modPow(D, N);

        return M.intValue();
    }
    // test 2









}
