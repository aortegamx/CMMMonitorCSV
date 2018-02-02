/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmm.cvs2xml.paypoint.bean;

/**
 *
 * @author DXN
 */
public class AtributosPayPoint {
  
	//nodo BulkPaymentFile
	private String type;
	private String appID;
	
	private String customData;
	//nodo MakePaymentRequest
	private String ID;
	
	private String paymentAmount;
	
	private String reference;
	
	private String paymentMedium;
	
	private String cardNumber;
	
	private String expirationDate;
	
	private String cardStatusFlag;
	
	private String installment;
	
	private String installmentSequence;
	
	private String purchaseID;

	private String billingAddress;
	
	private String applicationID;
	
	private String securityKey;
	
	private String paymentChannel;
        
        private String nameFull;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public String getCustomData() {
		return customData;
	}

	public void setCustomData(String customData) {
		this.customData = customData;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getPaymentMedium() {
		return paymentMedium;
	}

	public void setPaymentMedium(String paymentMedium) {
		this.paymentMedium = paymentMedium;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCardStatusFlag() {
		return cardStatusFlag;
	}

	public void setCardStatusFlag(String cardStatusFlag) {
		this.cardStatusFlag = cardStatusFlag;
	}

	public String getInstallment() {
		return installment;
	}

	public void setInstallment(String installment) {
		this.installment = installment;
	}

	public String getInstallmentSequence() {
		return installmentSequence;
	}

	public void setInstallmentSequence(String installmentSequence) {
		this.installmentSequence = installmentSequence;
	}

	public String getPurchaseID() {
		return purchaseID;
	}

	public void setPurchaseID(String purchaseID) {
		this.purchaseID = purchaseID;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}

	public String getSecurityKey() {
		return securityKey;
	}

	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}

	public String getPaymentChannel() {
		return paymentChannel;
	}

	public void setPaymentChannel(String paymentChannel) {
		this.paymentChannel = paymentChannel;
	} 

    public String getNameFull() {
        return nameFull;
    }

    public void setNameFull(String nameFull) {
        this.nameFull = nameFull;
    }
        
        
}
