
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class YoneticiIslemleri {
    
    private Connection con=null;
    
    private Statement statement=null;
    private PreparedStatement preparedStatement=null;
    private PreparedStatement preparedStatement2=null;
    private PreparedStatement preparedStatement3=null;
    
    public YoneticiIslemleri(){
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
    public boolean girisYap(String kullanici_adi,String parola){
        String sorgu="Select *from yoneticiler where username=? and  password =?";
        try {
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1, kullanici_adi);
            preparedStatement.setString(2,parola);
            ResultSet rs=preparedStatement.executeQuery();
            return rs.next();
            
        } catch (SQLException ex) {
            Logger.getLogger(YoneticiIslemleri.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    public ArrayList<EmanetKitap> emanetKitaplariGetir(){
        ArrayList<EmanetKitap> cikti=new ArrayList<EmanetKitap>();
        
        try {
            String sorgu="Select *from emanetkitaplar";
            statement=con.createStatement();
            ResultSet rs=statement.executeQuery(sorgu);
            while(rs.next()){
                int id=rs.getInt("id");
                String ad=rs.getString("uyeAdi");
                String soyad=rs.getString("uyeSoyadi");
                String kitap_adi=rs.getString("kitapAdi");
                String kitap_yazari=rs.getString("kitapYazari");
                String email=rs.getString("uyeEmail");
                String tel=rs.getString("uyeTelefon");
                java.util.Date basTarihi=rs.getDate("basTarihi");
                java.util.Date bitTarihi=rs.getDate("bitTarihi");
                cikti.add(new EmanetKitap(id, ad, soyad, kitap_adi, kitap_yazari,email, tel, basTarihi, bitTarihi));
            }
            return cikti;
        } catch (SQLException ex) {
            Logger.getLogger(YoneticiIslemleri.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
    }
    public ArrayList<Uye> uyeleriGetir(){
        ArrayList<Uye> cikti=new ArrayList<Uye>();
        
        
        try {
            String sorgu="Select *from uyeler";
            statement=con.createStatement();
            ResultSet rs=statement.executeQuery(sorgu);
            while(rs.next()){
                String tc=rs.getString("tcKimlikNo");
                String ad=rs.getString("ad");
                String soyad=rs.getString("soyad");
                String email=rs.getString("email");
                String tel=rs.getString("telefon");
                String parola=rs.getString("password");
                cikti.add(new Uye(tc, ad, soyad, email, tel,parola));
                
            }
            return  cikti;
            
        } catch (SQLException ex) {
            Logger.getLogger(YoneticiIslemleri.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
    }
    public void uyeSil(String tc){
        String sorgu="Delete from uyeler where tcKimlikNo=?";
        try {
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1, tc);
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(YoneticiIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void uyeGuncelle(String tc,String ad,String soyad,String email,String telefon){
        String sorgu="Update uyeler  Set ad=?,soyad=?,email=?,telefon=? where tcKimlikNo =?";
        try {
            preparedStatement=con.prepareStatement(sorgu);
            
            preparedStatement.setString(1, ad);
            preparedStatement.setString(2, soyad);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4,telefon);
            preparedStatement.setString(5, tc);
            preparedStatement.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(YoneticiIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ArrayList<Kitap> kitaplariGetir(){
        ArrayList<Kitap> cikti=new ArrayList<Kitap>();
        
        try {
            String sorgu="Select *from kitaplar";
            statement=con.createStatement();
            ResultSet rs=statement.executeQuery(sorgu);
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
            Logger.getLogger(YoneticiIslemleri.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    public void kitapEkle(String ad,String yazar_adi,String yayin_evi,String kitap_turu,int sayfa_sayisi,int stok){
        ArrayList<Kitap> cikti=new ArrayList<Kitap>();
        String sorgu="Insert into kitaplar(ad,yazarAdi,yayinEvi,kitapTuru,sayfaSayisi,stok) VALUES(?,?,?,?,?,?)";
        String sorgu2="Update kitaplar Set stok=stok+? where id=?";
        cikti=kitaplariGetir();
        try {
            for(Kitap kitap:cikti){
                if(kitap.getAd().equals(ad)){
                    int id=kitap.getId();
                    preparedStatement2=con.prepareStatement(sorgu2);
                    preparedStatement2.setInt(2, id);
                    preparedStatement2.setInt(1, stok);
                    preparedStatement2.executeUpdate();
                    return;
                }
            }
            
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1, ad);
            preparedStatement.setString(2,yazar_adi);
            preparedStatement.setString(3,yayin_evi);
            preparedStatement.setString(4, kitap_turu);
            preparedStatement.setInt(5, sayfa_sayisi);
            preparedStatement.setInt(6, stok);
            preparedStatement.executeUpdate();
                    
            
        } catch (SQLException ex) {
            Logger.getLogger(YoneticiIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void kitapGuncelle(int id,String ad,String yazar_adi,String yayin_evi,String kitap_turu,int sayfa_sayisi,int stok ){
        
        String sorgu="Update kitaplar Set ad=?,yazarAdi=?,yayinEvi=?,kitapTuru=?,sayfaSayisi=?,stok=? where id=? ";
        try {
            preparedStatement=con.prepareCall(sorgu);
            preparedStatement.setString(1, ad);
            preparedStatement.setString(2,yazar_adi);
            preparedStatement.setString(3,yayin_evi);
            preparedStatement.setString(4, kitap_turu);
            preparedStatement.setInt(5, sayfa_sayisi);
            preparedStatement.setInt(6, stok);
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(YoneticiIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    public void kitapSil(int id,String ad,int stok){
        ArrayList<Kitap> cikti=new ArrayList<Kitap>();
        String sorgu="Delete from kitaplar where id=?";
        String sorgu3="Update kitaplar Set stok=stok-? where id=? ";
        cikti=kitaplariGetir();
        try {
            for(Kitap kitap:cikti){
                if(kitap.getAd().equals(ad)){
                    preparedStatement3=con.prepareStatement(sorgu3);
                    preparedStatement3.setInt(1, stok);
                    preparedStatement3.setInt(2,id);
                    preparedStatement3.executeUpdate();
                    if(kitap.getStok() == 0){
                        preparedStatement=con.prepareStatement(sorgu);
                        preparedStatement.setInt(1, id);
                        preparedStatement.executeUpdate();
                    }
                    return;
                }
            }
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(YoneticiIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
