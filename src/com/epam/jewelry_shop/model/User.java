package com.epam.jewelry_shop.model;

/**
 * A class that stores user information with properties <b>id</b>,
 * <b>firstName</b>, <b>lastName</b>, <b>email</b>, <b>password</b>,
 * <b>mailingsIds</b>.
 * 
 * @autor Oleh Bober
 */
public class User {

	private int idUser;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String avatar;
	private String role;

	/**
	 * Constructor - creating a new object
	 * 
	 * @see User#User(long,String,String,String,String,String)
	 */
	public User() {
	}

	/**
	 * Constructor - creating a new object with specific values
	 *
	 * @param firstName - user's firstName
	 * @param lastName  - user's last name
	 * @param email     - user's email
	 * @param password  - password
	 * 
	 * @see Product#Product()
	 */
	public User(String firstName, String lastName, String email, String password) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;

	}

	/**
	 * Constructor- creating a new object with specific values**
	 * 
	 * @param id        - user's id
	 * @param firstName - user's firstName
	 * @param lastName  - user's last name
	 * @param email     - user's email
	 * @param password  - password
	 * 
	 * @see Product#Product()
	 */

	public User(int idUser, String firstName, String lastName, String email, String password) {

		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;

	}

	/**
	 * Constructor- creating a new object with specific values**
	 * 
	 * @param id        - user's id
	 * @param firstName - user's firstName
	 * @param lastName  - user's last name
	 * @param email     - user's email
	 * @param password  - password
	 * @see Product#Product()
	 */

	public User(int idUser, String firstName, String lastName, String email, String password, String avatar) {

		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.avatar = avatar;
	}

	public User(int idUser, String firstName, String lastName, String email, String password, String avatar,
			String role) {
		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.avatar = avatar;
		this.role = role;
	}

	/**
	 * method for obtaining the value of the {@link User # id} field
	 *
	 * @return returns user's id
	 */
	public long getIdUser() {
		return idUser;
	}

	/**
	 * method for assigning a value to the {@link User # id} field
	 *
	 * @param id - user's id
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	/**
	 * method for obtaining the value of the {@link User # id} field
	 *
	 * @return returns the firstName of the user
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * method for assigning a value to the {@link User # id} field
	 *
	 * @param firstName - user's firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * method for obtaining the value of the {@link User # id} field
	 *
	 * @return returns the lastName of the user
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * method for assigning a value to the {@link User # id} field
	 *
	 * @param lastName - user's last name
	 */

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * method for obtaining the value of the {@link User # id} field
	 *
	 * @return returns the user's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * method for assigning a value to the {@link User # id} field
	 *
	 * @param email - user's email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * method for getting the value of the {@link User # id} field
	 *
	 * @return returns the user's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * method for assigning a value to the {@link User # id} field
	 *
	 * @param password - user password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User{" + "idUser=" + idUser + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
				+ ", email='" + email + '\'' + ", password='" + password + '\'' + '}';
	}
}
