import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class MainCitizen {

    public static void main(String[] args){

    try{
            BufferedReader br = new BufferedReader(new FileReader("CitizenList.txt"));
            LinkedList<Citizen> citizenList = new LinkedList<>();
            
            Citizen citizen;
            String indata = null;

            while((indata = br.readLine())!=null){
                
                StringTokenizer st = new StringTokenizer(indata, ";");
                
                String sName = st.nextToken();
                String sIc = st.nextToken();
                String sState = st.nextToken();
                int iAge = Integer.parseInt(st.nextToken());
                String sCategory = st.nextToken();
                String sFirstDoseStat = st.nextToken();
                String sSecondDoseStat = st.nextToken();
                String sCertificateVac = st.nextToken();

                citizen = new Citizen(sName, sIc, sState, iAge, sCategory, sFirstDoseStat, sSecondDoseStat, sCertificateVac);

                citizenList.add(citizen);
            } 
            br.close();

        // if age is 18 to 30 then push to stCenter1 using while loop        

        Stack<Citizen> stCenter1 = new Stack<>();
        Stack<Citizen> stCenter2 = new Stack<>();
        Stack<Citizen> stCenter3 = new Stack<>();

        while (!citizenList.isEmpty()) {
            citizen = citizenList.removeLast();
            if (citizen.getAge() >= 18 && citizen.getAge() <= 30) {
                stCenter1.push(citizen);
            }
            else if (citizen.getAge() >= 31 && citizen.getAge() < 50) {
                stCenter2.push(citizen);
            }
            else {
                stCenter3.push(citizen);
            }
        }


        // declare a queue named qCenter1
        Queue<Citizen> qCenter1 = new LinkedList<>();
        // declare a queue named qCenter2
        Queue<Citizen> qCenter2 = new LinkedList<>();
        // declare a queue named qCenter3
        Queue<Citizen> qCenter3 = new LinkedList<>();

        // create a linkedlist named completeList
        LinkedList<Citizen> completeList = new LinkedList<>();

   int menu = 0;
   do{

   menu = Integer.parseInt(JOptionPane.showInputDialog("Please choose the following menu  "+
                                                            "\n Menu 1 - add new recipient" +
                                                            "\n Menu 2 - remove existing recipient" + 
                                                            "\n Menu 3 - Update 1st dose status" +
                                                            "\n Menu 4 - Update 2nd dose status" +
                                                            "\n Menu 5 - Display"));

   //add new recipient
   if (menu == 1) {

        String sName = JOptionPane.showInputDialog("Enter recipient name ");
        String sIc = JOptionPane.showInputDialog("Enter recipient IC ");
        String sState = JOptionPane.showInputDialog("Enter recipient state ");
        int iAge = Integer.parseInt(JOptionPane.showInputDialog("Enter recipient age "));
        String sCategory = JOptionPane.showInputDialog("Enter recipient category ");
        String sFirstDoseStat = JOptionPane.showInputDialog("Enter recipient first dose status ");
        String sSecondDoseStat = JOptionPane.showInputDialog("Enter recipient second dose status ");
        String sCertificateVac = JOptionPane.showInputDialog("Enter recipient certificate of vaccination status ");

        citizen = new Citizen(sName, sIc, sState, iAge, sCategory, sFirstDoseStat, sSecondDoseStat, sCertificateVac);

        citizenList.add(citizen);
   }

   // delete existing recipient
    else if (menu == 2){
    
        String recipientIC = JOptionPane.showInputDialog( "Enter recipient IC");
    
        for (int i = 0; i <citizenList.size(); i++) {
    
            citizen = citizenList.get(i);
    
            if (citizen.getIc().equals(recipientIC)) {
                
                citizenList.remove(i);
                JOptionPane.showMessageDialog(null, "Recipient deleted");
            }
        }
    }

    // first does
    else if (menu == 3){

        // update sFirstDoseStat to "COMPLETED" from stCenter1 then push to qCenter1

        while(!stCenter1.isEmpty() ){
            citizen = stCenter1.pop();
            if(citizen.getFirstDoseStat().equalsIgnoreCase("NULL")){
                citizen.setFirstDoseStat("COMLETED");
                qCenter1.add(citizen);
            }
        }
        
        while(!stCenter2.isEmpty() ){
            citizen = stCenter2.pop();
            if(citizen.getFirstDoseStat().equalsIgnoreCase("NULL")){
                citizen.setFirstDoseStat("COMLETED");
                qCenter2.add(citizen);
            }
        }

        while(!stCenter3.isEmpty() ){
            citizen = stCenter3.pop();
            if(citizen.getFirstDoseStat().equalsIgnoreCase("NULL")){
                citizen.setFirstDoseStat("COMLETED");
                qCenter3.add(citizen);
            }
        }
    }

    // 2nd dose
    else if (menu == 4){

        // if sFirstDoseStat is "NULL" then show warning message
        if (qCenter1.isEmpty() && qCenter2.isEmpty() && qCenter3.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Recipient has not received first dose", "Missing Input" , JOptionPane.ERROR_MESSAGE);
        } else {

            // update sSecondDoseStat to "COMPLETED" and set sCertificateVac to "COMPLETED" and remove object from qCenter1 and store it in completedList

            while(!qCenter1.isEmpty()){
                citizen = qCenter1.remove();
                citizen.setSecondDoseStat("COMPLETED");
                citizen.setcertificateVac("COMPLETED");
                completeList.add(citizen);
            }

            while(!qCenter2.isEmpty()) {
                citizen = qCenter2.remove();
                citizen.setSecondDoseStat("COMPLETED");
                citizen.setcertificateVac("COMPLETED");
                completeList.add(citizen);
            }

            while(!qCenter3.isEmpty()) {
                citizen = qCenter3.remove();
                citizen.setSecondDoseStat("COMPLETED");
                citizen.setcertificateVac("COMPLETED");
                completeList.add(citizen);
            }

        }

    }

    //DISPLAY
    else if (menu == 5){

        int submenu = 0;

        do {

            submenu = Integer.parseInt(JOptionPane.showInputDialog("please choose what you want to display" + 
                                                                        "\n 1 - Display first dose recipient" + 
                                                                        "\n 2 - Complete dose recipient" + 
                                                                        "\n 3 - Exit"));

            // display 1st dose recipient in command line
            if (submenu == 1) {

                System.out.println((String.format("\t_____________________________________________________________________________________________________________________________________________________________________")));
                System.out.printf((String.format("\t| %-30s | %-18s | %-28s | %-5s | %-10s | %-15s | %-15s | %-19s | %n " , "Name", "IC", "State" , "Age" , "Category" , "1st Dose Status" , "2nd Dose Status" , "Vaccine Certificate")));
                System.out.print(qCenter1.toString().replace("[","").replace("]","").replace(",", ""));
                System.out.println((String.format("\t|___________________________________________________________________________________________________________________________________________________________________|" + "\n")));

                System.out.println((String.format("\t_____________________________________________________________________________________________________________________________________________________________________")));
                System.out.printf((String.format("\t| %-30s | %-18s | %-28s | %-5s | %-10s | %-15s | %-15s | %-19s | %n " , "Name", "IC", "State" , "Age" , "Category" , "1st Dose Status" , "2nd Dose Status" , "Vaccine Certificate")));
                System.out.print(qCenter2.toString().replace("[","").replace("]","").replace(",", ""));
                System.out.println((String.format("\t|___________________________________________________________________________________________________________________________________________________________________|" + "\n")));

                System.out.println((String.format("\t_____________________________________________________________________________________________________________________________________________________________________")));
                System.out.printf((String.format("\t| %-30s | %-18s | %-28s | %-5s | %-10s | %-15s | %-15s | %-19s | %n " , "Name", "IC", "State" , "Age" , "Category" , "1st Dose Status" , "2nd Dose Status" , "Vaccine Certificate")));
                System.out.print(qCenter3.toString().replace("[","").replace("]","").replace(",", ""));
                System.out.println((String.format("\t|___________________________________________________________________________________________________________________________________________________________________|" + "\n")));
            }
            else if (submenu == 2){
                //display 2nd dose recipient in command line
                System.out.println((String.format("\t_____________________________________________________________________________________________________________________________________________________________________")));
                System.out.printf((String.format("\t| %-30s | %-18s | %-28s | %-5s | %-10s | %-15s | %-15s | %-19s | %n " , "Name", "IC", "State" , "Age" , "Category" , "1st Dose Status" , "2nd Dose Status" , "Vaccine Certificate")));
                System.out.print(completeList.toString().replace("[","").replace("]","").replace(",", ""));
                System.out.println((String.format("\t|___________________________________________________________________________________________________________________________________________________________________|" + "\n")));
            }
            
        } while (submenu != 3);
    }
   
}while (menu != 6);

    }
        catch(FileNotFoundException fnf) {
            System.out.println("File not found");
        }
        catch(EOFException eof) {
            System.out.println("End of file error");
        }
        catch(IOException io) {
            System.out.println("wrong input!!!");
        }
        catch(NullPointerException npe) {
            System.out.println("null string");
        }
        catch(NumberFormatException nfe) {
            System.out.println("wrong input!!!");
        }
        finally {
            System.out.println("\nSystem end here.Bye!");
        }

    }
}   