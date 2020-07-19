package stringMethods;

public class OtherMethods {
	public static void main(String[] args) {
		// Сравнивает StringBuffer с этой строкой
		// public boolean contentEquals(StringBuffer strbuf)
		
		// Проверяет, заканчивается ли строка символами suffix.
		// public boolean endsWith(String suffix)
		
		// Метод для извлечения нескольких символов из строки.
		// public void getChars(int start, int end, char[] buffer, int index)
		
		// Возвращает номер первой встречной позиции с указанным индексом с.
		// public int indexOf(int c)
				
		// Проверяет, является ли строка пустой
		// public boolean isEmpty ()
		
		// Проверяет, соответствует ли строка регулярным выражениям.
		// public boolean matches(String regularExpression)
		
		// Меняет символ или последовательность символов target на replacement
		// public String replace(CharSequence target, CharSequence replacement) и другие перегруженные версии
			
		// Разбивает строку на массив из слов. Например, есть строка Васька Рыжик Мурзик Барсик и мы хотим получить массив имён котов:
		// public String[] split (String regularExpression) и другие перегруженные версии
		
		// Создаёт новую последовательность/строку с символами из данной строки начиная с позиции start до конца строки/заканчивая символом с позиции end. Новая строка содержит символы от start до end - 1, поэтому берём на один символ больше.
		// public String substring(int start) и другие перегруженные версии.
		
		// Копирует символы в этой строке в массив символов. Тот же результат можно получить через метод getChars(). Документация не рекомендует использовать данный метод, предлагая метод charAt().
		// public char[] toCharArray()
		
		//public String toUpperCase()
		
		// Удаляет пробелы в начале и в конце строки.
		// public String trim()
		
		// public void getChars(int start, int end, char[] buffer, int index)
		
		// public void getBytes(int start, int end, byte[] data, int index) и другие перегруженные версии

	}
}
