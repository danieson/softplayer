package com.softplayer.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
	private static final int[] pesoCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

	public static boolean email(String email) throws Exception {
	
		
		if ((email == null) || (email.trim().length() == 0)){
			throw new Exception("E-mail inválido");
		}

		String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*\\.{0,1}@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
		Pattern pattern = Pattern.compile(emailPattern,
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()){
			throw new Exception("E-mail inválido");
		}
		
		return true;
	}
	
	
	public static boolean cpf(String cpf) throws Exception {
		if ((cpf == null) || (cpf.length() != 11)) {
			throw new Exception("CPF inválido");
		}
		
		if (cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444")
				|| cpf.equals("55555555555") || cpf.equals("66666666666")
				|| cpf.equals("77777777777") || cpf.equals("88888888888")
				|| cpf.equals("99999999999")) {

			throw new Exception("CPF inválido");
		}
		

		Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);

		if (!cpf.equals(cpf.substring(0, 9) + digito1.toString()
				+ digito2.toString())) {
			throw new Exception("CPF inválido");
		}
		
		return true;
	}

	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;

		return soma > 9 ? 0 : soma;
	}
}
