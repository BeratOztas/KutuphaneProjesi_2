
import java.util.Date;


public class EmanetKitap {
    private int id;
    private String uyeAdi;
    private String uyeSoyadi;
    private String kitapAdi;
    private String kitapYazari;
    private String uyeEmail;
    private String uyeTelefon;
    private java.util.Date basTarihi;
    private java.util.Date bitTarihi;

    public EmanetKitap(int id, String uyeAdi, String uyeSoyadi, String kitapAdi,String kitapYazari, String uyeEmail, String uyeTelefon, Date basTarihi, Date bitTarihi ) {
        this.id = id;
        this.uyeAdi = uyeAdi;
        this.uyeSoyadi = uyeSoyadi;
        this.kitapAdi = kitapAdi;
        this.kitapYazari=kitapYazari;
        this.uyeEmail = uyeEmail;
        this.uyeTelefon = uyeTelefon;
        this.basTarihi = basTarihi;
        this.bitTarihi = bitTarihi;
    }

    public String getKitapYazari() {
        return kitapYazari;
    }

    public void setKitapYazari(String kitapYazari) {
        this.kitapYazari = kitapYazari;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUyeAdi() {
        return uyeAdi;
    }

    public void setUyeAdi(String uyeAdi) {
        this.uyeAdi = uyeAdi;
    }

    public String getUyeSoyadi() {
        return uyeSoyadi;
    }

    public void setUyeSoyadi(String uyeSoyadi) {
        this.uyeSoyadi = uyeSoyadi;
    }

    public String getKitapAdi() {
        return kitapAdi;
    }

    public void setKitapAdi(String kitapAdi) {
        this.kitapAdi = kitapAdi;
    }

    public String getUyeEmail() {
        return uyeEmail;
    }

    public void setUyeEmail(String uyeEmail) {
        this.uyeEmail = uyeEmail;
    }

    public String getUyeTelefon() {
        return uyeTelefon;
    }

    public void setUyeTelefon(String uyeTelefon) {
        this.uyeTelefon = uyeTelefon;
    }

    public Date getBasTarihi() {
        return basTarihi;
    }

    public void setBasTarihi(Date basTarihi) {
        this.basTarihi = basTarihi;
    }

    public Date getBitTarihi() {
        return bitTarihi;
    }

    public void setBitTarihi(Date bitTarihi) {
        this.bitTarihi = bitTarihi;
    }
    
    
    
    
}
