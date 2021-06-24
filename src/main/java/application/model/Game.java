package application.model;

import java.io.Serializable;
import java.sql.Date;

import javafx.scene.image.Image;

public class Game implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -1999546130738992055L;
	/**
		 * 
		 */

	private String nome;
	private String produttore;
	private String genere;
	private String descrizione;
	private byte[] img;
	private Date data;
	private float prezzo;
	private int preferenze;
	private int download;
	private int eta_minima;
	private String urlDownload;
	public Game(String nome, String produttore, String genere, String descrizione, byte[] img, Date data,float prezzo) {
		super();
		this.nome = nome;
		this.produttore = produttore;
		this.genere = genere;
		this.descrizione = descrizione;
		this.img = img;
		this.data = data;
		this.prezzo=prezzo;
	}
	public String getNome() {
		return nome;
	}
	@Override
	public int hashCode() {
		return (nome+produttore).hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game game = (Game) obj;
		return (game.nome.equals(this.nome) && game.produttore.equals(this.produttore));
	}
	public String getProduttore() {
		return produttore;
	}
	public void setProduttore(String produttore) {
		this.produttore = produttore;
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public String toString() {
		return nome +' '+produttore;
		
	}
	public void setPreferenze(int p) {
		this.preferenze=p;
		
	}
	public void setDownload(int download) {
		this.download=download;
	}
	public void setEtàMin(int eta_min) {
		this.eta_minima=eta_min;
		
	}
	public int getPreferenze() {
		return preferenze;
	}
	public String getUrlDownload() {
		return urlDownload;
		
	}
public void setUrlDownload(String url) {
		this.urlDownload=url;
		
	}
	}
