
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UyeIslemleri {
    
    private Connection con=null;
    
    private Statement statement=null;
    private PreparedStatement preparedStatement=null;
    private PreparedStatement preparedStatement2=null;
    public UyeIslemleri(){
         String url="jdbc:mysql://"+Database.host+":"+Database.port+"/"+Database.db_ismi;
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Bulunamadı...");
        }
        try {
            con=DriverManager.getConnection(url, Database.kullanici_adi, Database.parola);
            System.out.println("Bağlantı Başarılı..");
        } catch (SQLException ex) {
            System.out.println("Bağlantı Başarısız...");
        }
    }
    
    public void emanetKitapEkle(String ad,String soyad,String kitap_adi,String kitap_yazari,String email,String telefon,java.util.Date basTarihi,java.util.Date bitTarihi){
        
        try {
            String sorgu="Insert into emanetkitaplar(uyeAdi,uyeSoyadi,kitapAdi,kitapYazari,uyeEmail,uyeTelefon,basTarihi,bitTarihi)VALUES(?,?,?,?,?,?,?,?)";
            String sorgu2="Update kitaplar Set stok=stok-1 where ad=?";
            preparedStatement2=con.prepareStatement(sorgu2);
            preparedStatement2.setString(1, kitap_adi);
            preparedStatement2.executeUpdate();
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1, ad);
            preparedStatement.setString(2, soyad);
            preparedStatement.setString(3, kitap_adi);
            preparedStatement.setString(4, kitap_yazari);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, telefon);
            preparedStatement.setDate(7, new java.sql.Date(basTarihi.getTime()));
            preparedStatement.setDate(8, new java.sql.Date(bitTarihi.getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UyeIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public boolean girisYap(String tc,String parola){
        
        try {
            String sorgu="Select * from uyeler where tcKimlikNo=? and  password=?";
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1, tc);
            preparedStatement.setString(2, parola);
            ResultSet rs=preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UyeIslemleri.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public void kayitOl(String tc,String ad,String soyad,String email,String telefon,String password){
       
        try {
             String sorgu="Insert into  uyeler(tcKimlikNo,ad,soyad,email,telefon,password)VALUES(?,?,?,?,?,?)";
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1, tc);
            preparedStatement.setString(2, ad);
            preparedStatement.setString(3, soyad);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, telefon);
            preparedStatement.setString(6, password);
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UyeIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Kitap> kitaplariGetir(){
        ArrayList<Kitap> cikti=new ArrayList<Kitap>();
        
        try {
            String sorgu="Select *from kitaplar";
            preparedStatement=con.prepareCall(sorgu);
            ResultSet rs=preparedStatement.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                String ad=rs.getString("ad");
                String yazar_adi=rs.getString("yazarAdi");
                String yayin_evi=rs.getString("yayinEvi");
                String kitap_turu=rs.getString("kitapTuru");
                int sayfa_sayisi=rs.getInt("sayfaSayisi");
                int stok=rs.getInt("stok");
                cikti.add(new Kitap(id, ad, yazar_adi, yayin_evi, kitap_turu, sayfa_sayisi, stok));
                
                        
                
            }
            return cikti;
        } catch (SQLException ex) {
            Logger.getLogger(UyeIslemleri.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
    }
    
    
    
    
}
