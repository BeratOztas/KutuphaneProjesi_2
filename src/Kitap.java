
public class Kitap{
    private int id;
    private String ad;
    private String yazarAdi;
    private String yayinEvi;
    private String kitapTuru;
    private int sayfaSayisi;
    private int stok;

    public Kitap(int id, String ad, String yazarAdi, String yayinEvi, String kitapTuru, int sayfaSayisi, int stok) {
        this.id = id;
        this.ad = ad;
        this.yazarAdi = yazarAdi;
        this.yayinEvi = yayinEvi;
        this.kitapTuru = kitapTuru;
        this.sayfaSayisi = sayfaSayisi;
        this.stok = stok;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getYazarAdi() {
        return yazarAdi;
    }

    public void setYazarAdi(String yazarAdi) {
        this.yazarAdi = yazarAdi;
    }

    public String getYayinEvi() {
        return yayinEvi;
    }

    public void setYayinEvi(String yayinEvi) {
        this.yayinEvi = yayinEvi;
    }

    public String getKitapTuru() {
        return kitapTuru;
    }

    public void setKitapTuru(String kitapTuru) {
        this.kitapTuru = kitapTuru;
    }

    public int getSayfaSayisi() {
        return sayfaSayisi;
    }

    public void setSayfaSayisi(int sayfaSayisi) {
        this.sayfaSayisi = sayfaSayisi;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
    
    
    
}
