package com.aaron.assertion;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Map;
import java.util.Map.Entry;
import org.assertj.core.internal.Failures;

public class IAssert {

	/**
	 * 断言对象为null
	 * 
	 * @param actual
	 */
	public static void assertIsNull(Object actual) {
		assertThat(actual).isNull();
	}

	/**
	 * 断言actual为true
	 * 
	 * @param actual
	 */
	public static void assertIsTrue(Boolean actual) {
		assertThat(actual).isTrue();
	}

	/**
	 * 断言actual中包含expected
	 * 
	 * @param actual
	 * @param expected
	 */
	public static void assertContainsSubsequence(String actual, String expected) {
		assertThat(actual).contains(expected);
	}

	/**
	 * 断言actual与expected相同
	 * 
	 * @param actual
	 * @param expected
	 */
	public static void assertEqualse(String actual, String expected) {
		assertThat(actual).isEqualTo(expected);
	}

	/**
	 * 断言actual与expected相等
	 * 
	 * @param actual
	 * @param expected
	 */
	public static void assertEqualse(Long actual, Long expected) {
		assertThat(actual).isEqualTo(expected);
	}

	/**
	 * 断言actual与expected布尔值相同
	 * 
	 * @param actual
	 * @param expected
	 */
	public static void assertEqualse(boolean actual, boolean expected) {
		assertThat(actual).isEqualTo(expected);
	}

	/**
	 * 断言actual与expected相等
	 * 
	 * @param actual
	 * @param expected
	 */
	public static void assertEqualse(int actual, int expected) {
		assertThat(actual).isEqualTo(expected);
	}

	/**
	 * 断言actual小于expected
	 * 
	 * @param actual
	 * @param expected
	 */
	public static void assertLess(int actual, int expected) {
		assertThat(actual).isLessThan(expected);
	}

	/**
	 * 断言actual小于等于expected
	 * 
	 * @param actual
	 * @param expected
	 */
	public static void assertLessOrEqualse(int actual, int expected) {
		assertThat(actual).isLessThanOrEqualTo(expected);
	}

	/**
	 * 断言actual大于expected
	 * 
	 * @param actual
	 * @param expected
	 */
	public static void assertGreater(int actual, int expected) {
		assertThat(actual).isGreaterThan(expected);
	}

	/**
	 * 断言actual大于等于expected
	 * 
	 * @param actual
	 * @param expected
	 */
	public static void assertGreaterOrEqualse(int actual, int expected) {
		assertThat(actual).isGreaterThanOrEqualTo(expected);
	}

	/**
	 * 断言actual与expected相同（忽略大小写）
	 * 
	 * @param actual
	 * @param expected
	 */
	public static void assertIgnoringEqualse(String actual, String expected) {
		assertThat(actual).isEqualToIgnoringCase(expected);
	}

	/**
	 * 断言actual与expected相同（忽略空格）
	 * 
	 * @param actual
	 * @param expected
	 */
	public static void assertIgnoringWhitespaceEqualse(String actual, String expected) {
		assertThat(actual).isEqualToIgnoringWhitespace(expected);
	}

	/**
	 * 断言actual Map值与expecte Map值相同
	 * 
	 * @param actual
	 * @param expected
	 */
	public static <T> void assertMapAllEntity(Map<String, T> actual, Map<String, T> expected) {
		assertThat(actual).containsAllEntriesOf(expected);
	}

	/**
	 * 断言actual Map值包含expecte Map值
	 * 
	 * @param actual
	 * @param expected
	 */
	public static <T> void assertMapContainsEntity(Map<String, T> actual, Map<String, T> expected) {
		if (expected.isEmpty()) {
			throw Failures.instance().failure("expected is empty");
		}
		for (Entry<String, T> entry : expected.entrySet()) {
			assertThat(actual).contains(entry);
		}
	}

}
