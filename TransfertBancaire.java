public class TransfertBancaire {


    public void transfert(String login, String password, Double somme){

        public static Logger LOGGER = Logger.getLogger(TransfertBancaire.class);
        String select = "select * from utilisateur where login = " + login ;
        select += " and password = " + password;

        Utilisateur toto;
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo","root","root");  
            
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery(select);  

            while(rs.next()){
                toto = new Utilisateur();
                toto.setNom(rs.getString("nom"));
                toto.setPassword(rs.getString("password"));
            }
        } catch(Exception ex){
            LOGGER.error("Probleme d'identification");
            return;
        }

        if(toto != null){
            try{
                Class.forName("com.mysql.jdbc.Driver");  
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo","root","root");

                String select = "select * from compte where proprietaire = " + login  
                select += " and principal = true";
                Compte compteCourant = new Compte();
                while(rs.next()){
                    compteCourant = new Compte();
                    compteCourant.setNumero(rs.getString("identifiant"));
                    compteCourant.setSolde(rs.getDouble("solde"));
                }

                String select = "select * from compte where proprietaire = " + login  
                select += " and principal = false";
                Compte compteEpargne = new Compte();

                while(rs.next()){
                    compteEpargne = new Compte();
                    compteEpargne.setNumero(rs.getString("identifiant"));
                    compteEpargne.setSolde(rs.getDouble("solde"));
                }


                String update1 = "update compte set solde = " compteCourant - somme;
                update += "where proprietaire =  + login and principal = true";

                Statement stmt=con.createStatement();  
                stmt.executeQuery(update1);  

                String update2 = "update compte set solde = " compteCourant - somme;
                update += "where proprietaire =  + login and principal = true";

                stmt.executeQuery(update2);  

            } catch (Exception ex) {
                LOGGER.error("Probleme de transfert vers compte epargne");
                return;
            }
        }
    }
}

