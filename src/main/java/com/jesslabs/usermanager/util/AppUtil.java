package com.jesslabs.usermanager.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * @author barbo on 22-03-2023
 */
public class AppUtil {

	/**
	 * gets Today's Start Date with 00:00:00 time
	 *
	 * @return
	 */
	public static Date getTodayStartDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * gets Today's End Date with 23:59:59 time
	 *
	 * @return
	 */
	public static Date getTodayEndDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static String getLicenseKey() {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 25;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		generatedString = generatedString.toUpperCase();

		return generatedString;
	}

	public static String getBase64(String value) {
		String encodedString = Base64.getEncoder().encodeToString(value.getBytes());
		return encodedString;
	}

	public static Date convertEpochToDate(Long value) {
		LocalDateTime ldt = Instant.ofEpochMilli(value).atZone(ZoneId.systemDefault()).toLocalDateTime();

		return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * checks whether string is null
	 *
	 * @param value
	 * @return true or false
	 */
	public static boolean isNullString(String value) {
		boolean isNull = false;
		if (value.isEmpty()) {
			isNull = true;
		} else if (value.isBlank()) {
			isNull = true;
		} else if (value.equals(null)) {
			isNull = true;
		} else if (value.equals("null")) {
			isNull = true;
		} else if (value.equals("NULL")) {
			isNull = true;
		} else if (value.equals("Null")) {
			isNull = true;
		}
		return isNull;
	}

	public static Date getCurrentUtcTime(Date utcDate) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
		Date d1 = localDateFormat.parse(simpleDateFormat.format(utcDate));
		return d1;
	}

	public static Date getCurrentGmtDateTime() throws ParseException {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		return new Date();
	}

	public static String replaceString(String value, String target, String replacement) throws ParseException {
		String replacedValue = value;
		if (!isNullString(value)) {
			replacedValue = value.replace(target, replacement);
		}
		return replacedValue;

	}

	public static String convertJsonToString(Object request) {
		String finalValue = "";
		ObjectMapper Obj = new ObjectMapper();
		try {
			finalValue = Obj.writeValueAsString(request);
		} catch (JsonProcessingException e) {
			finalValue = "";
		}
		return finalValue;
	}

	public static String generateAlphaNumericString() {
		String generatedString = UUID.randomUUID().toString();
		return generatedString;
	}

}
