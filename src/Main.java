public class Main {
    static Menu menu = new Menu();

    public static void main(String[] args) {
        init();
        menu.displayLoginPage();
    }
    private static void init() {
        ConsomRepository conRep = new ConsomRepository();
        ResidentRepository resRep = new ResidentRepository();
        UserRepository useRep = new UserRepository();
        useRep.add(new User("1","Yacine","pwd",true));
        useRep.add(new User("2","Mahmoud","pwd",true));
        useRep.add(new User("3","Mouhib","pwd",true));
        useRep.add(new User("4","Louis","pwd",true));
        resRep.add(new Resident("1","Yacine","Mkhinini","@","000","11 Avenue Linton"));
        resRep.add(new Resident("2","Mahmoud","Labidi","@","000","12 Avenue Linton"));
        resRep.add(new Resident("3","Mouhib","Aydi","@","000","13 Avenue Linton"));
        resRep.add(new Resident("4","Louis-Edouard","Lafontant","@","000",
                "14 Avenue Linton"));
        Bac b1 = new Bac("A","bacA","Recyclage");
        Bac b2 = new Bac("B","bacB","Compostage");
        Bac b3 = new Bac("C","bacC","Ordures");
        Bac b4 = new Bac("D","bacD","Recyclage");
        Bac b5 = new Bac("E","bacE","Compostage");
        Bac b6 = new Bac("F","bacF","Ordures");

        Resident r = (Resident) resRep.get("1");
        r.addBac(b1);
        r.addBac(b2);
        r.addBac(b3);

        r = (Resident) resRep.get("2");
        r.addBac(b1);
        r.addBac(b5);
        r.addBac(b6);

        r = (Resident) resRep.get("3");
        r.addBac(b4);
        r.addBac(b5);
        r.addBac(b6);

        r = (Resident) resRep.get("4");
        r.addBac(b4);
        r.addBac(b2);
        r.addBac(b3);

        User ucon1 = new User("13", "cons1", "123", false);
        Consommateur con1 = new Consommateur("13", "213", "cons1", "conso1@yahoo.fr",
                "123-546-789", "5450 blabla , Montreal", "Recyclage,Ordure", 550,"" +
                "cartons, brulerie d'ordures" );
        User ucon2 = new User("14", "cons2", "123", false);
        Consommateur con2 = new Consommateur("14", "216", "cons2", "conso2@yahoo.fr",
                "153-546-000", "5451 blabla , Montreal","Recyclage,Compostage,Ordures", 900,
                "bouteilles en verre,engrais de plante,export d'ordures" );
        User ucon3 = new User("5", "cons3", "123", false);
        Consommateur con3 = new Consommateur("5", "21", "cons3", "conso3@yahoo.fr",
                "163-006-789", "5456 blabla , Montreal",  "Recyclage",520,"fabrication de " +
                "cahiers et livres");
        User ucon4 = new User("6", "cons4", "123", false);
        Consommateur con4 = new Consommateur("6", "13", "cons4", "conso4@yahoo.fr",
                "123-540-789", "5440 blabla , Montreal", "Compostage", 600, "extraction de" +
                "calcium et fers");
        User ucon5 = new User("7", "cons5", "123", false);
        Consommateur con5 = new Consommateur("7", "2163", "cons5", "conso5@yahoo.fr",
                "123-506-789", "5490 blabla , Montreal", "Recyclage", 500,"sac en plastique," +
                "bouteilles en plastiques");
        User ucon6 = new User("8", "cons6", "123", false);
        Consommateur con6 = new Consommateur("8", "21003", "cons6", "conso6@yahoo.fr",
                "123-546-780", "8450 blabla , Montreal", "Compostage,Ordure",454,"engrais" +
                "pour plantes,brulerie d'ordures" );
        User ucon7 = new User("9", "cons7", "123", false);
        Consommateur con7 = new Consommateur("9", "23413", "cons7", "conso7@yahoo.fr",
                "123-546-709", "6910 blabla , Montreal", "Ordure",456,"brulerie d'ordures" );
        User ucon8 = new User("10", "cons8", "123", false);
        Consommateur con8 = new Consommateur("10", "2163", "cons8", "conso8@yahoo.fr",
                "123-545-729", "5400 blabla , Montreal", "Compostage",456,"engrais pour plantes");
        User ucon9 = new User("11", "cons9", "123", false);
        Consommateur con9 = new Consommateur("11", "213410", "cons9", "conso9@yahoo.fr",
                "123-547-789", "5004 blabla , Montreal", "Recyclage,Ordure",456,"barre de" +
                "metal,brulerie d'ordures");
        User ucon10 = new User("12", "cons1", "123", false);
        Consommateur con10 = new Consommateur("12", "3", "cons10", "conso10@yahoo.fr",
                "103-546-789", "5455 blabla , Montreal",  "Recyclage,Ordure",6,"bouteilles" +
                " en verres, cartons");

        conRep.add(con1);
        useRep.add(ucon1);
        conRep.add(con2);
        useRep.add(ucon2);
        conRep.add(con3);
        useRep.add(ucon3);
        conRep.add(con4);
        useRep.add(ucon4);
        conRep.add(con5);
        useRep.add(ucon5);
        conRep.add(con6);
        useRep.add(ucon6);
        conRep.add(con7);
        useRep.add(ucon7);
        conRep.add(con8);
        useRep.add(ucon8);
        conRep.add(con9);
        useRep.add(ucon9);
        conRep.add(con10);
        useRep.add(ucon10);
    }
}