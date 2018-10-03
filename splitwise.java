import java.io.*;
import java.util.*;

public class splitwise{
	static class pair{
		String nm;
		double am;
		pair(String nm, double am){
          this.am = am;
          this.nm = nm;
		}
	}

	private static class Sort implements Comparator<pair> {
		public int compare(pair a, pair b){
           int cmp = Double.compare(a.am,b.am);
           return cmp;
		}
	}
	static ArrayList<pair> aa = new ArrayList<>();
	static TreeMap<String,Double> tm = new TreeMap<String,Double>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //ArrayList<pair> aa = new ArrayList<pair>();
        //TreeMap<String,Double> tm = new TreeMap<String,Double>();
      while(true){
       try{
      	System.out.println("Want to enter transactions press 'y' else 'n': ");
      	String stop = br.readLine();
      	if(stop.equalsIgnoreCase("n")){
          break;
      	}
    
        System.out.println("Enter the number of people involved: ");
        int num = Integer.parseInt(br.readLine());

        
        System.out.println("Enter the name of the person who paid: ");
        String name = br.readLine();
      
        System.out.println("Enter the total amount paid: ");
        double amount = Double.parseDouble(br.readLine());
       // aa.add(new pair(name,-amount));
        
        System.out.println("Split--> e, i");
        String typ = br.readLine();
       
        if(typ.equals("e")){
        	if(num<=1){
        		System.out.println("There should be atleast two people involved");
              return;
        	}
        	System.out.println("Enter name of remaining people involved: ");
            double split = amount/num;
          for(int i=0;i<num-1;i++){
             String nm = br.readLine();
            // aa.add(new pair(nm,split));
            if(tm.containsKey(nm)){
              double cm = tm.get(nm);
              cm+=split;
              tm.put(nm,cm);
            }
            else{
               tm.put(nm,split);
            }
          }
        }
        else{
           for(int i=0;i<num;i++){

        	System.out.println("Enter the name of "+(i+1)+"st person involved : ");
       
            String nm = br.readLine();
            System.out.println("Enter the money borrowed by "+nm);
        	//aa.add(new pair(nm,amount));
        	double amm = Double.parseDouble(br.readLine());
        	if(amm>amount){
              System.out.println("Borrowed amount cannot be greater than total amount");
              return;
        	}
        	if(tm.containsKey(nm)){
              double cm = tm.get(nm);
              cm+=amm;
              tm.put(nm,cm);
        	}
        	else{
        		tm.put(nm,amm);
        	}
          }
        }
        if(tm.containsKey(name)){
          double cm = tm.get(name);
          cm+=-amount;
          tm.put(name,cm);
        }
        else{
          tm.put(name,-amount);
        }
       }
       catch(NumberFormatException e){
         System.out.println("Bammmm!! You entered something other than a number");
         return;
       }      
      }
      
      for(Map.Entry<String,Double>m:tm.entrySet()){
           aa.add(new pair(m.getKey(),m.getValue()));
      }
      Collections.sort(aa, new Sort());
     try{
       while(aa.size()!=0){
          pair pp= aa.remove(0);
          pair rr = aa.remove(aa.size()-1);
          double poor = pp.am;
          String nmp = pp.nm;
          double rich = rr.am;
          String nmr = rr.nm;
 
          double min = Math.min(-poor,rich);
          System.out.println(nmr+" has to pay "+rich+" to "+nmp);

          poor += min;
          rich -= min;

          if(poor!=0.0){
            aa.add(new pair(pp.nm,poor));
          }
          if(rich!=0.0){
            aa.add(new pair(rr.nm,rich));
          }
          Collections.sort(aa,new Sort());
        }
      }
      catch(NumberFormatException e){
      	 System.out.println("Bammm!!!! Entered something other than digits???");
         return;
      }
      catch(ArrayIndexOutOfBoundsException e){
      	//
      }
      finally{
      	System.out.println("Hope u r settled!!");
      }
	}
}