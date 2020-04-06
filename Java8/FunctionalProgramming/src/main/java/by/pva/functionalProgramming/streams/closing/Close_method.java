package by.pva.functionalProgramming.streams.closing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Only Streams whose source are an IO channel like Files.lines(Path, Charset) need to be closed.
 * https://mkyong.com/java/java-8-should-we-close-the-stream-after-use/ 
 */
 
public class Close_method {
	public static void main(String[] args) {
		noNeedToClose();	
        needToClose();
	}
	
	private static void noNeedToClose() {
		Stream<String> stream = Stream.of("A", "B", "C");

        List<String> filter = stream.filter(x -> !x.equalsIgnoreCase("B"))
			.collect(Collectors.toList());

        // no need close the stream.
        //stream.close();

        System.out.println(filter); // [A, C]
	}
	
	private static void needToClose() {
		Path path = Paths.get("src/main/resources/TextFile.txt").toAbsolutePath();
		// auto close
        try (Stream<String> lines = Files.lines(path)) {
        	
        	lines.onClose(()->System.out.println("Closing the stream!"));
        	
            String content = lines.collect(Collectors.joining(System.lineSeparator()));
            System.out.println(content);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
