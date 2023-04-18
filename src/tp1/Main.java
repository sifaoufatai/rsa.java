package tp1;

import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Générer les nombres premiers p et q
        rsa r1 = new rsa();
        rsa pq = r1.calculerPQ(16);


        // Calculer les clés publiques et privées
       rsa r2 = new rsa(pq.p, pq.q);

        // Chiffrer et déchiffrer un message
       int message = 123;
        int messageChiffre = r2.chiffrer(message);
        int messageDechiffre = r2.dechiffrer(messageChiffre);

        System.out.println("Message : " + message);
        System.out.println("Message chiffré : " + messageChiffre);
        System.out.println("Message déchiffré : " + messageDechiffre);
        ///partie 2 test
        // question a et b ;
       Test r =new Test();
        System.out.println(r.Calculerq(13289));
        System.out.println(r.calculerp(32887847, r.Calculerq(13289)));
        rsa rv; rv =r.dechiffrer2(26300813,32887847, 9197);
        System.out.println(rv.toString());
        // question c
        rsa r3= r.dechiffrer2(163119273,755918011,671828605 );
        System.out.println(r3.toString());
        // la question c aussi marche a merbveille

        //
        Codage  codage = new Codage( "CBAEFABCFFEB", 377);

          ;
           // int a= codage.tailleBlock(codage.n, BigInteger.valueOf(377));
      //  System.out.println(c);System.out.println(k);
       // System.out.println("lavaleur de bolc est "+a);;
        String tab[];
        String m ="CBAEFABCFFEB";
       // tab = codage.encodage(m,6,BigInteger.valueOf(377));
       // System.out.println(Arrays.toString(tab));

        int []tab1 =codage.cryptage(codage.Message,codage.nbrc,BigInteger.valueOf(codage.n));
       // System.out.println(Arrays.toString(tab1)+"je suis la valeur de sortie  ");
      //   codage.encodagemessagechiffre(m,6,BigInteger.valueOf(377));
       //  String tab2 = codage.encodagemessagechiffre(m,6,BigInteger.valueOf(377));
      //  System.out.println(tab2);
         codage.decryptage();

    }
}

