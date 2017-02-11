package com.pyt.util;

import java.util.Random;

public class CodeUtils {

	private CodeUtils() {
	}

	public static String generatePin(int digit) {
		int[] sizeTable = { 0, 9, 99, 999, 9999, 99999, 999999, Integer.MAX_VALUE };

		if (digit == 0 || digit > 7) {
			digit = 7;
		}

		Random random = new Random();

		int highest = sizeTable[digit] + 1;
		int lowest = sizeTable[digit - 1] + 1;
		int generated = 0;
		while (generated < lowest) {
			generated = random.nextInt(highest);
		}

		return String.valueOf(generated);
	}

	public static String generaCodeLite() {
		return generaCode(8);
	}

	public static String generaCode() {
		return generaCode(15);
	}

	public static String generaCode(int length) {

		Random random = new Random();
		String buf = "23456789QWERTYUPLKJHGFDSAZXCVBNM";
		StringBuilder out = new StringBuilder(length);

		for (int i = 0; i < length; i++)
			out.append(buf.charAt(random.nextInt(buf.length())));

		return out.toString();

	}

	/**
	 * @param pattern
	 *            rappresenta il modello del codice da generare
	 * @return ritorna una stringa come il pattern sostituendo le A con lettere
	 *         e gli 0 con numeri
	 */

	public static String generaCode(String pattern) {

		if (pattern == null)
			return generaCode();

		Random random = new Random();
		String charBuf = "QWERTYUPLKJHGFDSAZXCVBNM";
		String intBuf = "23456789";
		String buf = charBuf + intBuf;

		String out = "";

		for (int i = 0; i < pattern.length(); i++) {

			switch (pattern.charAt(i)) {
			case '@':
				out += charBuf.charAt(random.nextInt(charBuf.length()));
				break;

			case '0':
				out += intBuf.charAt(random.nextInt(intBuf.length()));
				break;
			case '*':
				out += buf.charAt(random.nextInt(buf.length()));
				break;
			default:
				out += pattern.charAt(i);
				break;
			}

		}

		return out;

	}

	/**
	 * @param length
	 *            lunghezza della password generata
	 * @return ritorna una stringa random contenente almeno un carattere
	 *         numerico
	 */

	public static String generatePassword(int length) {
		String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		Random rnd = new Random(System.currentTimeMillis());
		boolean hasNumber = false;

		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			char randChar = alphabet.charAt(rnd.nextInt(alphabet.length()));
			if (!hasNumber && Character.isDigit(randChar))
				hasNumber = true;
			sb.append(randChar);
		}

		if (!hasNumber)
			sb.setCharAt(rnd.nextInt(length), Character.forDigit(rnd.nextInt(10), 10));

		return sb.toString();
	}
}
