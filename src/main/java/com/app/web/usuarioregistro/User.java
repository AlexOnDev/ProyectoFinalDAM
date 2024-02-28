package com.app.web.usuarioregistro;

import java.util.List;

import com.app.web.entidad.ObjetoSkinArma;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

/**
 * Esta clase define el objeto que hace referencia al Usuario.
 * Las cuales ya estan metidas en la base de datos de manera automatica.
 * 
 * @author: Oskar Stankevicius
 * @version: 1.0.6v
 */
@Entity(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", nullable=false, unique= true)
	private String name;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_authority",
			  joinColumns = @JoinColumn(
					  	name = "user_id", referencedColumnName = "id"),
			  inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id")
					  )
	private List<Authority> authorities;

	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "urlPerfil")
	private String urlPerfil;

	@Column(name = "puntos")
	private int puntos;
	
	/*@Column(name = "cantidadarmas") //No hace falta ya que usamos DAO. inventario.size();
	private int cantidadArmas;*/
	
	@Column(name = "cajasAbiertas")
	private int cajasAbiertas;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true )
	List<ObjetoSkinArma> inventario;
		
	/**
	 * Crea un nuevo objeto User con el nombre, la contraseña y las autoridades especificadas.
	 *
	 * @param name         Nombre del usuario.
	 * @param password     Contraseña del usuario.
	 * @param authorities  Lista de autoridades asignadas al usuario.
	 */
	public User(String name, String password, List<Authority> authorities) {
	    super();
	    this.name = name;
	    this.password = password;
	    this.authorities = authorities;
	    this.cajasAbiertas = 0;
	    this.puntos=100;
	}

	/**
	 * Crea un nuevo objeto User con el nombre, la contraseña, las autoridades, el correo electrónico
	 * y la URL del perfil especificados.
	 *
	 * @param name         Nombre del usuario.
	 * @param password     Contraseña del usuario.
	 * @param authorities  Lista de autoridades asignadas al usuario.
	 * @param email        Correo electrónico del usuario.
	 * @param urlPerfil    URL del perfil del usuario.
	 */
	public User(String name, String password, List<Authority> authorities, String email, String urlPerfil) {
	    super();
	    this.name = name;
	    this.password = password;
	    this.authorities = authorities;
	    this.email = email;
	    this.urlPerfil = urlPerfil;
	    this.puntos = 100;
	    this.cajasAbiertas = 0;
	}
	
	/**
	 * Crea un nuevo objeto User con el nombre, la contraseña, las autoridades, el correo electrónico
	 * y la URL del perfil especificados.
	 *
	 * @param name         Nombre del usuario.
	 * @param password     Contraseña del usuario.
	 * @param email        Correo electrónico del usuario.
	 */

	public User(String name, String password, String email) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.puntos=100;
	}
	

	public User(String name, String password, String email, List<Authority> authorities) {
		super();
		this.name = name;
		this.password = password;
		this.authorities = authorities;
		this.email = email;
	}

	/**
	 * Crea un nuevo objeto User sin inicializar los atributos.
	 */
	public User() {
	}

	/**
	 * Devuelve el ID del usuario.
	 *
	 * @return El ID del usuario.
	 */
	public long getId() {
	    return id;
	}

	/**
	 * Establece el ID del usuario. Pero es automatico.
	 *
	 * @param id El ID del usuario.
	 */
	public void setId(long id) {
	    this.id = id;
	}

	/**
	 * Devuelve el nombre del usuario.
	 *
	 * @return El nombre del usuario.
	 */
	public String getName() {
	    return name;
	}

	/**
	 * Establece el nombre del usuario.
	 *
	 * @param name El nombre del usuario.
	 */
	public void setName(String name) {
	    this.name = name;
	}

	/**
	 * Devuelve la contraseña del usuario.
	 *
	 * @return La contraseña del usuario.
	 */
	public String getPassword() {
	    return password;
	}

	/**
	 * Establece la contraseña del usuario.
	 *
	 * @param password La contraseña del usuario.
	 */
	public void setPassword(String password) {
	    this.password = password;
	}

	/**
	 * Devuelve la lista de autoridades asignadas al usuario.
	 *
	 * @return La lista de autoridades asignadas al usuario.
	 */
	public List<Authority> getAuthorities() {
	    return authorities;
	}

	/**
	 * Establece la lista de autoridades asignadas al usuario.
	 *
	 * @param authorities La lista de autoridades asignadas al usuario.
	 */
	public void setAuthorities(List<Authority> authorities) {
	    this.authorities = authorities;
	}

	/**
	 * Obtiene el correo electrónico del usuario.
	 *
	 * @return el correo electrónico del usuario.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Establece el correo electrónico del usuario.
	 *
	 * @param email el correo electrónico a establecer.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtiene el inventario de objetos de skins y armas del usuario.
	 *
	 * @return el inventario de objetos de skins y armas del usuario.
	 */
	public List<ObjetoSkinArma> getInventario() {
		return inventario;
	}

	/**
	 * Establece el inventario de objetos de skins y armas del usuario.
	 *
	 * @param inventario el inventario de objetos de skins y armas a establecer.
	 */
	public void setInventario(List<ObjetoSkinArma> inventario) {
		this.inventario = inventario;
	}
	
	//COMENTAR
	
	public void sumarCajaAbierta() {
		this.cajasAbiertas++;
	}

	public String getUrlPerfil() {
		return urlPerfil;
	}

	public void setUrlPerfil(String urlPerfil) {
		this.urlPerfil = urlPerfil;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getCajasAbiertas() {
		return cajasAbiertas;
	}

	public void setCajasAbiertas(int cajasAbiertas) {
		this.cajasAbiertas = cajasAbiertas;
	}
	
	
	

}
