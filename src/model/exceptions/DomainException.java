package model.exceptions;

public class DomainException extends RuntimeException {
	//pode ser só extends Exception , mas dai sempre teria
	// que tratar nos metodos com throws DomainExcpetion
	// usando o RuntimeException eu não sou obrigado a tratar nd
	private static final long serialVersionUID = 1L;
	
	public DomainException(String msg) {
		super(msg);
	}

}
