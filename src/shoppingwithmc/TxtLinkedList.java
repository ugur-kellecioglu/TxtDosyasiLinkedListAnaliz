
package shoppingwithmc;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class TxtLinkedList {
    
    Node head; //ilk node
    
    public class Node {
    
        //Bir node'un sahip olaca�� de�i�kenler
        String kelime;
        int sayac;
        Node next;
        
        //constructor
        public Node(String kelime){
            this.kelime = kelime;
            sayac = 1;
            next = null;
        }
    
    }
    
    public void listeye_ekle( String kelime){ //parametre olarak girilen kelimeyi listenin en ba��na ekler
    
	Node yeni_node = new Node(kelime); //eklenecek node olu�turulur

	if(head == null){ //hen�z hi�bir node yoksa bu node ilk node olur
	    head = yeni_node;
	}
	else{

	    if(kontrol_et(kelime)) {} //bu kelimeye sahip bir node listede var m� kontrol� kontrolet(kelime) methodu ile sa�lan�r
	    else{ //kontrol et false d�nd�r�rse yani bu kelime listede yoksa yeni olu�turan node listenin head'i olur
		yeni_node.next = head;
		head = yeni_node;
	    }

	}
    }
    
    public boolean kontrol_et(String kelime){ //kelimenin listede olup olmad���n�n kontrol� 
        
        Node temp = head;
        
        while(temp != null){
            
            if(temp.kelime.equals(kelime)) { // e�er kelime listede varsa true de�er d�nd�r�r ve o node'un sayac de�i�kenini 1 artt�r�r
        	temp.sayac++;
                return true;
            }
            
            temp = temp.next;   
        }
        
        //t�m liste kontrol edildikten sonra true return edilmemi�se kelime listede yoktur false d�nd�r�l�r
        return false;
        
    }
  
    public String print_liste(){//listeyi istenilen �ekilde bast�rmak i�in kullan�l�r
	
        Node temp_node = head; //ge�ici bir node belirlenir node null olana kadar her node'un kelime'si ve sayac'i bastirilir
        String result  = "";
        while(temp_node != null){
          
            result += temp_node.kelime + " " + temp_node.sayac + ", ";
            temp_node = temp_node.next;
        }
        
        return result.substring(0, result.length() - 2); 
    }
    
    public int toplam_kelime_sayisi() {//her nodun count'u toplanarak ilerlenir ve return edilir.
	
	Node temp_node = head;
	int count =0;
	while(temp_node != null) {
	    count+= temp_node.sayac;
	    temp_node = temp_node.next;
	    
	}
	
	return count;
    }
    
    public String encok_kelime() {
	
	Node temp_node = head;
	int max = 0;
	int  temp = temp_node.sayac;
	String kelime = "";
	//�nce node'lar�n hepsi ziyaret edilir ve sayac de�i�keni en y�ksek olan bulunur.
	while(temp_node.next != null) {
	     if(temp > max) max = temp;
	     
	     temp_node = temp_node.next;
	     temp = temp_node.sayac;
	}
	
	//sonra hangi node bu de�ere sahipse o node'un kelimesi d�nd�r�l�r
	temp_node = head;
	while(temp_node.next != null) {
	     if(temp_node.sayac == max) {
		 kelime = temp_node.kelime;
		 break;
	     }
	     temp_node = temp_node.next;
	}
	return kelime;
	
    }
    
     public static void main(String[] args) {
        TxtLinkedList llist = new TxtLinkedList();
        Scanner scan = new Scanner(System.in);
        Path currentRelativePath = Paths.get("");
        String sx = currentRelativePath.toAbsolutePath().toString();

        try {
            System.out.print("Dosya ad� giriniz: ");
            String dosya_adi = scan.nextLine();
  
            
            File dosya = new File(sx+"\\"+dosya_adi);
            
            Scanner dosya_scanner = new Scanner(dosya);
            while (dosya_scanner.hasNextLine()) {
        	String data = dosya_scanner.nextLine();
        	String[] words = data.split("\\\\s+|!\\s+|,\\s*|\\.\\s*\\\\\\\\s+| ");
             
                 for(String s: words){
                    llist.listeye_ekle(s);
                 }
             
            }
            
            dosya_scanner.close();
  
        } catch (Exception e) {
            System.out.println("Dosya A��lamad�");
        }
        
        System.out.print("1- listele, 2- toplam kelime sayisi, 3- en cok sayida olan kelime, 0-cikis: ");
        int command = scan.nextInt();
        do {
            switch (command) {
	    case 1: 
		System.out.println(llist.print_liste());
		break;
	    
	    case 2:
		System.out.println(llist.toplam_kelime_sayisi());
		break;
            case 3:
        	System.out.println(llist.encok_kelime());
        	break;
            case 0:
        	
        	break;
        	
            default:
        	System.out.println("Yanl�� komut");
        	break;
        	
            }
            
            System.out.print("1- listele, 2- toplam kelime sayisi, 3- en cok sayida olan kelime, 0-cikis: ");
            command = scan.nextInt();
           
        }
        while(command != 0);
        
        
        
        
        scan.close();
        
    }
}
