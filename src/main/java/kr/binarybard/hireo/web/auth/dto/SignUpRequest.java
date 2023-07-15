package kr.binarybard.hireo.web.auth.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import kr.binarybard.hireo.common.validation.constraints.FieldMatch;
import kr.binarybard.hireo.web.account.domain.AccountType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@FieldMatch(first = "password", second = "passwordConfirm")
public class SignUpRequest {

	@NotBlank
	@Email(message = "{invalid.email}", regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
	@Length(max = 32)
	private String email;

	@NotBlank
	@Length(min = 10, max = 20)
	private String password;

	private String passwordConfirm;

	private String name;
	private AccountType type;
}
