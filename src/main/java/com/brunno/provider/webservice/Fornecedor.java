package com.brunno.provider.webservice;

public class Fornecedor {
	private int idFornec;
	private String nameFornec;
	private String emailFornec;
	private String comment;
	private String cnpjFornec;
	
	public int getIdFornec() {
		return idFornec;
	}
	
	public void setIdFornec(int idFornec) {
		this.idFornec = idFornec;
	}
	
	public String getNameFornec() {
		return nameFornec;
	}
	
	public void setNameFornec(String nameFornec) {
		this.nameFornec = nameFornec;
	}
	
	public String getEmailFornec() {
		return emailFornec;
	}
	
	public void setEmailFornec(String emailFornec) {
		this.emailFornec = emailFornec;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getCnpjFornec() {
		return cnpjFornec;
	}
	
	public void setCnpjFornec(String cnpjFornec) {
		this.cnpjFornec = cnpjFornec;
	}
	
}