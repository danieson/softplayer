package com.softplayer.validate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
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
	public static boolean validateDate(Object object, boolean obrigatorio,
			boolean dataEHora) throws Exception {

		// Verifica se o objeto passado é string ou java.util.Date
		// Preenche a string a ser usada na validação
		String dataString = null;
		if (object instanceof String) {
			dataString = object.toString();
		} else if (object instanceof Date) {
			SimpleDateFormat out = null;
			if (dataEHora) {
				out = new SimpleDateFormat("ddMMyyyyHHmmss");
				dataString = out.format(object);
			} else {
				out = new SimpleDateFormat("ddMMyyyy");
				dataString = out.format(object);
			}
		}

		// Verifica se é obrigatório e o campo não foi preenchido
		if (obrigatorio && isEmpty(dataString)) {
			throw new Exception("Data inválida");
		} else
		// Verifica se não for obrigatório e o campo não foi preenchido
		if (!obrigatorio && isEmpty(dataString)) {
			return true;
		}

		// Retira todos os caracteres que não forem numéricos
		dataString = dataString.replaceAll("[^\\d]", "");

		// Se campo possui hora, deve possuir pelo menos 14 dígitos
		if (dataEHora && dataString.length() < 14) {
			throw new Exception("Data inválida");
		}

		// Se campo não possui hora, deve possuir pelo menos 8 digitos
		if (!dataEHora && dataString.length() < 8) {
			throw new Exception("Data inválida");
		}

		// Pega os valores do dia, mês e ano
		Long dia = new Long(dataString.substring(0, 2));
		Long mes = new Long(dataString.substring(2, 4));
		Long ano = new Long(dataString.substring(4, 8));

		Long hora = null;
		Long minuto = null;
		Long segundo = null;

		// Se data tem hora pega os valores de horas, minutos e segundos.
		if (dataEHora) {
			hora = new Long(dataString.substring(8, 10));
			minuto = new Long(dataString.substring(10, 12));
			segundo = new Long(dataString.substring(12, 14));
		}

		try {
			// Preenche um objeto GregorianCalendar onde a validação acontece
			// Inclusive sobre ano bisexto, dia inválido, hora inválida
			GregorianCalendar data = new GregorianCalendar();
			data.setLenient(false);
			data.set(GregorianCalendar.YEAR, ano.intValue());
			data.set(GregorianCalendar.MONTH, mes.intValue() - 1);
			data.set(GregorianCalendar.DATE, dia.intValue());

			if (dataEHora) {
				data.set(GregorianCalendar.HOUR_OF_DAY, hora.intValue());
				data.set(GregorianCalendar.MINUTE, minuto.intValue());
				data.set(GregorianCalendar.SECOND, segundo.intValue());
			}

			// A validação da data ocorre aqui
			// Caso tenha alguma coisa errada com a data o sistema lança exceção
			// Capturada pelo catch abaixo e retorna false
			data.getTime();
		} catch (Exception e) {
			throw new Exception("Data inválida");
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
	
    private static boolean isEmpty(String s) {
        return ((s == null) || ("".equals(s.trim())));
    }
    
}
