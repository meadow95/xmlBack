//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.02 at 10:16:31 PM CEST 
//


package post;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for post complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="post">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="likes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dislikes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lokacija" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="opis" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="slike" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="korisnik" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="komentari" type="{smestaj}komentar" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "post", propOrder = {
    "id",
    "likes",
    "dislikes",
    "lokacija",
    "opis",
    "slike",
    "korisnik",
    "komentari"
})
public class Post {

    @XmlElement(required = true)
    protected String id;
    protected int likes;
    protected int dislikes;
    protected String lokacija;
    @XmlElement(required = true)
    protected String opis;
    @XmlElement(required = true)
    protected List<String> slike;
    @XmlElement(required = true)
    protected String korisnik;
    protected List<Komentar> komentari;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the likes property.
     * 
     */
    public int getLikes() {
        return likes;
    }

    /**
     * Sets the value of the likes property.
     * 
     */
    public void setLikes(int value) {
        this.likes = value;
    }

    /**
     * Gets the value of the dislikes property.
     * 
     */
    public int getDislikes() {
        return dislikes;
    }

    /**
     * Sets the value of the dislikes property.
     * 
     */
    public void setDislikes(int value) {
        this.dislikes = value;
    }

    /**
     * Gets the value of the lokacija property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLokacija() {
        return lokacija;
    }

    /**
     * Sets the value of the lokacija property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLokacija(String value) {
        this.lokacija = value;
    }

    /**
     * Gets the value of the opis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Sets the value of the opis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpis(String value) {
        this.opis = value;
    }

    /**
     * Gets the value of the slike property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the slike property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSlike().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSlike() {
        if (slike == null) {
            slike = new ArrayList<String>();
        }
        return this.slike;
    }

    /**
     * Gets the value of the korisnik property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKorisnik() {
        return korisnik;
    }

    /**
     * Sets the value of the korisnik property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKorisnik(String value) {
        this.korisnik = value;
    }

    /**
     * Gets the value of the komentari property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the komentari property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKomentari().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Komentar }
     * 
     * 
     */
    public List<Komentar> getKomentari() {
        if (komentari == null) {
            komentari = new ArrayList<Komentar>();
        }
        return this.komentari;
    }

}
