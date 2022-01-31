package H06;

import java.io.File;

public class ParserMain {
	public static void main(String... args) {
		 ClassLoader cloader = ParserMain.class.getClassLoader();
		 File file = new File(cloader.getResource("H06/as06.txt").getFile());

		 CuteParser cuteParser = new CuteParser(file);
		 NodePrinter nodePrinter = new NodePrinter(cuteParser.parseExpr());
		 nodePrinter.prettyPrint();
		 }

}
