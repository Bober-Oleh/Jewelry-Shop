package com.epam.jewelry_shop.model;

/**
 * Class that stores information about mailings with properties
 * <b>mailingId</b>, <b>title</b>, <b>description</b>.
 * 
 * @autor Олег Бобер
 */
public class Mailing {

	private long mailingId;
	private String title;
	private String description;

	/**
	 * Constructor - creating a new object
	 * 
	 * @see User#User(long,String,String)
	 */
	public Mailing() {
	}

	/**
	 * Constructor - creating a new object with specific values
	 * 
	 * @param title       - Mailing title
	 * @param description - Mailing description
	 * @see Product#Product()
	 */
	public Mailing(String title, String description) {
		this.title = title;
		this.description = description;
	}

	/**
	 * Constructor - creating a new object with specific values
	 *
	 * @param mailingId   - Mailing id
	 * @param title       - Mailing title
	 * @param description - Mailing description
	 * @see Product#Product()
	 */
	public Mailing(long mailingId, String title, String description) {
		this.mailingId = mailingId;
		this.title = title;
		this.description = description;
	}

	/**
	 * method for obtaining the value of the {@link User # id} field
	 *
	 * @return returns the user's getMailingId
	 */
	public long getMailingId() {
		return mailingId;
	}

	/**
	 * method for assigning a value to the {@link User # id} field
	 * 
	 * @param MailingId - Mailing id
	 */
	public void setMailingId(long mailingId) {
		this.mailingId = mailingId;
	}

	/**
	 * method for obtaining the value of the {@link User # id} field
	 *
	 * @return returns the title of the user
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * method for assigning a value to the {@link User # id} field
	 * 
	 * @param title - Mailing title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * method for obtaining the value of the {@link User # id} field
	 *
	 * @return returns the user's description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * method for assigning a value to the {@link User # id} field
	 * 
	 * @param description - Mailing description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Mailing{" + "mailingId=" + mailingId + ", title='" + title + '\'' + ", description='" + description
				+ '\'' + '}';
	}
}
