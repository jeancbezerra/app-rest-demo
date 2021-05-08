package br.com.yaman.rest.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "cards", schema = "portal")
public class CardEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id	
	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "nickname")
	private String nickname;
	
	@Column(name = "number")
	private String number;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "membersince")
	private Date memberSince;
	
	@Column(name = "flag")
	private String flag;
	
	@Column(name = "localphonesac")
	private String localPhoneSAC;
	
	@Column(name = "internationphonesac")
	private String internationPhoneSAC;
	
	@Column(name = "emailsac")
	private String emailSAC;
	
	@Column(name = "bankcard")
	private boolean bankCard;
	
	@Column(name = "bankname")
	private String bankName;
	
	@Column(name = "user_uuid")
	private String userUUID;

	public CardEntity() {
	}

	public CardEntity(String uuid, String nickname, String number, Date memberSince, String flag, String localPhoneSAC,
			String internationPhoneSAC, String emailSAC, boolean bankCard, String bankName, String userUUID) {
		super();
		this.uuid = uuid;
		this.nickname = nickname;
		this.number = number;
		this.memberSince = memberSince;
		this.flag = flag;
		this.localPhoneSAC = localPhoneSAC;
		this.internationPhoneSAC = internationPhoneSAC;
		this.emailSAC = emailSAC;
		this.bankCard = bankCard;
		this.bankName = bankName;
		this.userUUID = userUUID;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getMemberSince() {
		return memberSince;
	}

	public void setMemberSince(Date memberSince) {
		this.memberSince = memberSince;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getLocalPhoneSAC() {
		return localPhoneSAC;
	}

	public void setLocalPhoneSAC(String localPhoneSAC) {
		this.localPhoneSAC = localPhoneSAC;
	}

	public String getInternationPhoneSAC() {
		return internationPhoneSAC;
	}

	public void setInternationPhoneSAC(String internationPhoneSAC) {
		this.internationPhoneSAC = internationPhoneSAC;
	}

	public String getEmailSAC() {
		return emailSAC;
	}

	public void setEmailSAC(String emailSAC) {
		this.emailSAC = emailSAC;
	}

	public boolean isBankCard() {
		return bankCard;
	}

	public void setBankCard(boolean bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getUserUUID() {
		return userUUID;
	}

	public void setUserUUID(String userUUID) {
		this.userUUID = userUUID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CreditCardEntity [uuid=" + uuid + ", nickname=" + nickname + ", number=" + number + ", memberSince="
				+ memberSince + ", flag=" + flag + ", localPhoneSAC=" + localPhoneSAC + ", internationPhoneSAC="
				+ internationPhoneSAC + ", emailSAC=" + emailSAC + ", bankCard=" + bankCard + ", bankName=" + bankName
				+ ", userUUID=" + userUUID + "]";
	}

}