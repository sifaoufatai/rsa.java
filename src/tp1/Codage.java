package tp1;

import java.math.BigInteger;
import java.util.Arrays;

public class Codage {

    public int n ;
    public   int nbrc =6;
   private  int tailleInitial;
  public   String Message =new String();
   private  rsa r = new rsa();
   private  Test t = new Test() ;

    private  int e ;
    private int d ;
    private int p ;
    private int q ;
    int Taille ;
    public Codage (String Message ,int n ){
       rsa r0 = new rsa();
     r=  r0.calculerPQ(12);
     this.p= r.p ;
     this.q=r.q ;
     this.tailleInitial=tailleBlock(nbrc, BigInteger.valueOf(n));
     this.nbrc=6;
    this.n = n;
    this.Message = Message;
    }

    public String codage(String message){


        var k = tailleInitial;




      //  String message = "HELLO_WORLD?";
        int[] messageChiffre = new int[message.length()];
        int  valeurdecimal=0;
        int i1 = 0;

// Table de correspondance pour l'alphabet choisi
        int[] tableCorrespondance = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39};

// Parcourir chaque caractère du message et le transformer en chiffre
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);

            int index = -1;
            if (c >= 'A' && c <= 'Z') { // Lettre majuscule
                index = c - 'A';


               System.out.println(index+ String.valueOf(c));
            } else if (c >= 'a' && c <= 'z') { // Lettre minuscule
                index = c - 'a';
            } else if (c >= '0' && c <= '9') { // Chiffre
                index = c - '0' + 26;
            } else if (c == '_') {
                index = 36;
            } else if (c == '.') {
                index = 37;
            } else if (c == '?') {
                index = 38;
            } else if (c == '€') {
                index = 39;
            }
            // Ajouter le chiffre correspondant au tableau de chiffres
            messageChiffre[i] = tableCorrespondance[index];

            valeurdecimal+= (int) (messageChiffre[i]*(Math.pow(nbrc, k-1)));
            k=k-1;



        }

        // le message  coder concatener
        String m= new String();
        for(int i =0 ; i< message.length(); i++){
            m = m.concat(String.valueOf(messageChiffre[i]));
        }

      //  System.out.println(Arrays.toString(messageChiffre).concat("ceci est  encodé"));
        System.out.println(message +" la valeur decimal correspondant ====>> "+valeurdecimal);
        return  String.valueOf(valeurdecimal);


    }


    public int  tailleBlock(int nbreAaplabet, BigInteger modulo ){
            BigInteger max= BigInteger.valueOf(0);
            int i =1 ;

        while(true){
            max=BigInteger.valueOf((long) Math.pow(nbreAaplabet,i));
            int cmp = max.compareTo(modulo);
            if(cmp>0){
                break;
            }
            i++;

        }
        Taille=i-1;

        return i-1;



    }
    public String[] decoupeMessage (String message , int nbrcarracter , BigInteger Modulo ) {
        int k= Taille;

        String[] souchaintab = new String[(int)Math.ceil((double)message.length() / k)];
        String souschaine;
        int j = 0;
        for (int i = 0; i < message.length(); i += k) {
            souschaine = message.substring(i, Math.min(i + k, message.length()));

            souchaintab[j] = souschaine;
            j=j+1;

        }
return souchaintab;
    }


    // je recupere tout les bloc encode et je les concatene pour avoir une chaine
public String[] encodage(String message , int nbrcarracter , BigInteger Modulo ) {
    String[] tab = decoupeMessage(message, nbrcarracter, Modulo);
    String[] chaineencode = new String[tab.length];
    for (int i = 0; i < tab.length; i++) {
        chaineencode[i] = (codage(tab[i]));

    }
    return chaineencode;
}
//generation cle privé , public
    public Test generationcle( ){

        Test r =new Test();

        r.calculerp(n, r.Calculerq(n));
        Test r2 = new Test(r.Calculerq(n), r.calculerp(n, r.Calculerq(n)));
        System.out.println(r2.toString());
        t= r2;
        return r2;

    }
public int[] cryptage(String message , int nbrcarracter , BigInteger Modulo ){
 t = generationcle();
String chaineecncode[] ;
    String m = new String();

chaineecncode =encodage( message ,  nbrcarracter ,  Modulo );
    int[] chainechiffrer = new int[chaineecncode.length];
    for(int i =0 ; i< chaineecncode.length; i++) {
      //  chainechiffrer[i]
        chainechiffrer[i] = t.chiffrer(Integer.parseInt(chaineecncode[i]), t.e, n);
       // m = m.concat(String.valueOf(chainechiffrer[i]));
      // System.out.println("je suis chaine encode "+chaineecncode[i]+   " et voici mon chiffré=>"+chainechiffrer[i]);
    }
   // System.out.println(m+"je suis le chiffrer ");
    return chainechiffrer;


}



    // on recupere trnsforme le message chiffrer n texte claire
    public  String toBase40(int nombreDecimal ,int Taille){
        String[] symboles = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "_", ".", "?", "€", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuilder nombreBaseCustom = new StringBuilder();
        while (nombreDecimal > 0) {
            int reste = nombreDecimal % nbrc;
            nombreBaseCustom.insert(0, symboles[reste]);
            nombreDecimal /= nbrc;
        }
        if(Taille>tailleInitial) {
            while (nombreBaseCustom.length() < Taille) {
                nombreBaseCustom.insert(0, symboles[0]);
            }
        }
        return nombreBaseCustom.toString();
    }

    public int toBase10(String  message){
        String[] symboles = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "_", ".", "?", "€", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuilder nombreBaseCustom = new StringBuilder();
        int valeurdecimal = 0; int l = Taille-1; ;
        for (int i = 0; i < message.length(); i++) {
            String c = String.valueOf(message.charAt(i));
            for (int j = 0; j < symboles.length; j++) {
                if (c.equals(symboles[j]))  {
                    nombreBaseCustom.append(String.valueOf(j));
                    valeurdecimal += (int) (j*(Math.pow(nbrc, l)));
                   l=l-1;
                }
            }
        }
        System.out.println(valeurdecimal);
        return valeurdecimal;
    }
    //ici on recupere le message echiffre quon transforme en text claire donc conversion chiffre en lettre
public String  encodagemessagechiffre(String message , int nbrcarracter , BigInteger Modulo ){
    int[] chainechiffrer ;
    String [] textclaire ;
    String text = new String();

    chainechiffrer=cryptage(message ,nbrcarracter, Modulo);
    System.out.println(chainechiffrer.length);
    System.out.println("==========>>>>>> je verifie ===="+ Arrays.toString(chainechiffrer));
    textclaire = new String[chainechiffrer.length];
        int m = (int) Math.pow(nbrc, Taille);
        int max=0;
        for(int i=0; i<chainechiffrer.length;i++ ){

            if(max<chainechiffrer[i]) max =chainechiffrer[i];

            else if( max +1> Math.pow(nbrc, tailleInitial )){
                max =chainechiffrer[i];
                Taille +=1;

            }
        }

        for(int j =0 ; j<chainechiffrer.length; j++ ){
            textclaire[j]=toBase40(chainechiffrer[j], tailleInitial+1);
            System.out.println("la chaine chiffrer est "+j+ "je suis" +chainechiffrer[j]+" et le text claire associé est " + textclaire[j]);
            text= text.concat(textclaire[j]);
        }
    System.out.println(text + "jai" +text.length()+ "carractere");
    return text ;
  //  System.out.println(max);
  //  System.out.println("la taille est" + Taille + "et le nbrdec crractere st + nbrc" + "et la valeur"+Math.pow(nbrc, Taille ));

  //  System.out.println("je suis la nouvelle "+Taille+" la taille du message chiffrer ");


        







}
// cete fonction dechiffre le message et renvoi le message initiale
public String decryptage (  ){

    // Taille =   tailleBlock(nbrc, BigInteger.valueOf(n));
    String m1 = encodagemessagechiffre(Message,nbrc,BigInteger.valueOf(n));
    System.out.println(m1);

    String [] tab =decoupeMessage(m1,6,BigInteger.valueOf(377));
   System.out.println("je suis le decouopeé(-è______________________"+ Arrays.toString(tab));
       int[] chainechiffrer  = new int [tab.length];
    for (int i =0 ; i< tab.length ; i++){
        chainechiffrer[i]=toBase10(tab[i]);

    }




    String m = new String();
   int chainedechiffrer[] = new int[chainechiffrer.length];

    for(int i =0 ; i< chainechiffrer.length; i++) {


    t  = (Test) t.dechiffrer2(t.e, n, chainechiffrer[i]);
    chainedechiffrer[i]= t.dc;


      //  System.out.println("je suis chaine encode du message chiffrer  "+chainechiffrer[i]+   " et voici mon dechiffere =>"+chainedechiffrer[i]);
    }
    String text = new String();
    String textclaire []= new String[chainedechiffrer.length];
    for(int i =0 ; i< chainedechiffrer.length; i++) {
        textclaire[i]=toBase40(chainedechiffrer[i], tailleInitial);
        System.out.println("la chaine chiffrer est "+chainedechiffrer[i]+" et le text claire associé est " + textclaire[i]);
        text= text.concat(textclaire[i]);


    }
     System.out.println(text+"je suis le message originale  ");
    System.out.println("je suis loriginal"+Message+" =>>>"+ text + text.equalsIgnoreCase(Message));
    return text ;


}




}


