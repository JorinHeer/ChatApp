package ch.bbw.pr.sospri.member;

import ch.bbw.pr.sospri.constraint.FieldMatch;
import net.bytebuddy.implementation.bind.annotation.FieldProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Constraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.security.SecureRandom;

/**
 * To regist a new Member
 * @author peter.rutschmann
 * @version 27.04.2020
 */

public class RegisterMember {
	@NotEmpty(message = "prename may not be empty" )
	@Size(min=2, max=24, message="Die Länge des Vornamens: 2 bis 25 Zeichen")
	private String prename;


	@NotEmpty (message = "lastname may not be empty" )
	@Size(min=2, max=24, message="Die Länge des Nachnamens: 2 bis 25 Zeichen")
	private String lastname;


	@NotEmpty
	//@Pattern(regexp = "^(?=.[a-z])(?=.[A-Z])(?=.\\d)[a-zA-Z\\d]{8,}$", message = "Passwort muss midestens. 1 Grossbuchstabe, Kleinbuchstabe, eine Zahl und 8 Zeichen enthalten!")
	private String password;
	@NotEmpty
	//@Pattern(regexp = "^(?=.[a-z])(?=.[A-Z])(?=.\\d)[a-zA-Z\\d]{8,}$", message = "\nPasswort muss mindestens 1 Grossbuchstabe, Kleinbuchstabe, eine Zahl und 8 Zeichen enthalten!")
	private String confirmation;

	private String message;

	
	public String getPrename() {
		return prename;
	}
	public void setPrename(String prename) {
		this.prename = prename;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmation() {
		return confirmation;
	}
	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Member toMember() {
		Member member = new Member();
		member.setPrename(prename);
		member.setLastname(lastname);
		member.setAuthority("member");
		member.setUsername(prename.toLowerCase()+"."+lastname.toLowerCase());
		member.setPassword(encode(password));

		return member;
	}
	public String encode(String passw){
		int strenght = 10;
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strenght, new SecureRandom());
		String encodedPassw = bCryptPasswordEncoder.encode(passw);
		return encodedPassw;
	}

	@Override
	public String toString() {
		return "RegisterMember [prename=" + prename + ", lastname=" + lastname + ", password=" + password
				+ ", confirmation=" + confirmation + "]";
	}
}
