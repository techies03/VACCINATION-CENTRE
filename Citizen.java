public class Citizen {
	
	String name;
    String ic;
	String state;
	int age;
	String category;
	String FirstDoseStat;
	String SecondDoseStat;
	String certificateVac;

    //create constructor
    public Citizen(String name, String ic, String state, int age, String category, String FirstDoseStat, String SecondDoseStat, String certificateVac){
        this.name = name;
        this.ic = ic;
        this.state = state;
        this.age = age;
        this.category = category;
        this.FirstDoseStat = FirstDoseStat;
        this.SecondDoseStat = SecondDoseStat;
        this.certificateVac = certificateVac;
    }

    //create default constructor
    public Citizen(){
        this.name = "";
        this.ic = "";
        this.state = "";
        this.age = 0;
        this.category = "";
        this.FirstDoseStat = "";
        this.SecondDoseStat = "";
        this.certificateVac = "";
    }

    //create mutator
    public void setName(String name){
        this.name = name;
    }

    public void setIc(String ic){
        this.ic = ic;
    }

    public void setState(String state){
        this.state = state;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setFirstDoseStat(String FirstDoseStat){
        this.FirstDoseStat = FirstDoseStat;
    }

    public void setSecondDoseStat(String SecondDoseStat){
        this.SecondDoseStat = SecondDoseStat;
    }

    public void setcertificateVac(String certificateVac){
        this.certificateVac = certificateVac;
    }

    //create accessor
    public String getName(){
        return name;
    }

    public String getIc(){
        return ic;
    }

    public String getState(){
        return state;
    }

    public int getAge(){
        return age;
    }

    public String getCategory(){
        return category;
    }

    public String getFirstDoseStat(){
        return FirstDoseStat;
    }

    public String getSecondDoseStat(){
        return SecondDoseStat;
    }

    public String getcertificateVac(){
        return certificateVac;
    }

    // create to string
    public String toString(){
        return String.format("\t| %-30s | %-18s | %-29s | %-5s | %-10s | %-15s | %-15s | %-19s | %n ", name, ic, state, age, category, FirstDoseStat, SecondDoseStat, certificateVac);
    }

    
}
