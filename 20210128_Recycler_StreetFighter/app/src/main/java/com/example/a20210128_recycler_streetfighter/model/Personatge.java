package com.example.a20210128_recycler_streetfighter.model;

import com.example.a20210128_recycler_streetfighter.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Personatge implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1486530091436589402L;
	private int id;
	private String nom;
	private String desc;
	private int idRecursImatge;
	private String urlImatge;
	private boolean esDolent =false;
		
	public Personatge(int id, String nom, String desc, int idRecursImatge, boolean esDolent ) {
		super();
		this.id = id;
		this.nom = nom;
		this.desc = desc;
		this.idRecursImatge = idRecursImatge;
		this.esDolent = esDolent;
	}
	//------------------------------------------------------
	// getters i setters
	public int getId() {
		return id;
	}	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getIdRecursImatge() {
		return idRecursImatge;
	}
	public void setIdRecursImatge(int idRecursImatge) {
		this.idRecursImatge = idRecursImatge;
	}
	public boolean esDolent() {
		return esDolent;
	}
	public void setEsDolent(boolean esDolent) {
		this.esDolent = esDolent;
	}
	public String getImatgeUrl(){	return urlImatge;	}
	public String getDesc() {		return desc;	}
	public void setDesc(String desc) {		this.desc = desc;	}
	//------------------------------------------------------
	private static ArrayList<Personatge> personatges;
	public static ArrayList<Personatge> getPersonatges(){
		if(personatges==null) {
			personatges = new ArrayList<Personatge> ();
			personatges.add(new Personatge(0, "Blanca",
					"Also known by his birth name Jimmy (ジミー Jimī?), is a video game character from the Street Fighter series, first appearing in Street Fighter II. He is a feral man from the Brazilian jungle with green skin and the ability to generate electricity. ",  R.drawable.blanca, true
					));
			personatges.add(new Personatge(1, "Chun-li",
					"Chun-Li (春麗 or チュンリー Shunrei or Chunrī?, Simplified Chinese: 春丽) is the main female protagonist of the Street Fighter series, originally debuting in Street Fighter II. The first female fighter in the series, she is an expert martial artist and Interpol officer who relentlessly seeks revenge for the death of her father at the hands of M. Bison. ", R.drawable.chunli, false ));
			personatges.add(new Personatge(2, "Dhalsim", "Dhalsim (ダルシム Darushimu?, Hindi: दाल्सीम, Dālsīma) is a video game character from the Street Fighter series, first appearing in Street Fighter II. He is a Yoga Master who possesses the ability to stretch his body and conjure fire. "
			, R.drawable.dalshim, false ));
			personatges.add(new Personatge(3, "Ken",
					"Ken is Ryu's best friend from his childhood, as the two have trained in the same art of Ansatsuken for a long time. They share a very close bond with each other and will help out each other no matter the cost. Both of them have also trained together under Gouken. One can see that they care for each other and never quarrel. Ken’s able to notice when Ryu isn’t in the right state of mind as shown in the Alpha series, where Ken gave him his red headband in order for Ryu to stay focused and not lose his way. And then in Street Fighter V where he tells Hinagiku that only he will show up alone, and encourages Ryu to continue his training before facing Shadoloo. ",
					R.drawable.ken, false));
			personatges.add(new Personatge(4, "Zangief", "Also known as the \"Red Cyclone\" (赤きサイクロン Akaki Saikuron?), is a video game character from the Street Fighter series, first appearing in Street Fighter II. He is a national Russian hero who is always seen fighting for the glory of his country. ",R.drawable.zangief, true));
			personatges.add(new Personatge(5, "Sagat", "Sagat (サガット Sagatto?, Thai: สกัด) is a video game character from the Street Fighter series, first appearing as the non-playable main antagonist and final boss in the original Street Fighter. He later returned as a boss in Street Fighter II before becoming playable in its update, Street Fighter II: Champion Edition. He is the \"Emperor of Muay Thai\" and a former member of Shadaloo, where he acted as a personal bodyguard for M. Bison. He later changed sides to redeem himself and became one of the protagonists in the series. "
					, R.drawable.sagat, true));
			personatges.add(new Personatge(6, "Bison",
					"M. Bison, known as Vega (ベガ Bega?) in Japan, is a video game character and the primary antagonist of the Street Fighter series, first appearing as a non-playable boss in Street Fighter II before becoming playable in its update, Street Fighter II: Champion Edition. He is a self-imposed dictator and megalomaniac seeking world domination. "
					,R.drawable.bison, true));
		}
		return personatges;
	}
	public static Personatge getPersonatge(int id){
		
		for(Personatge p:personatges){
			if(p.getId()==id) return p;
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personatge other = (Personatge) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
