package Tutorial.essential.regex;

import util.MvnJarInstaller;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by zhr on 2016/7/22.
 */
public class RegexTestHarness {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String regex = "";//<(\w+)>[\w\.-]+</\1>
        String s = "<dependency>\n" +
                "    <groupId>org.eclipse.core</groupId>\n" +
                "    <artifactId>runtime</artifactId>\n" +
                "    <version>3.10.0-v20140318-2214</version>\n" +
                "</dependency>";
        String search = "";
        boolean reserveRegex = false;
        boolean defaultSearch = false;
        while (true){
            if (!reserveRegex) {
                System.out.println("Enter your regex: ");
                regex = sc.nextLine();
            }
            if (!defaultSearch) {
                System.out.println("whether use default search string? true or false ");
                boolean choice = Boolean.parseBoolean(sc.nextLine());
                if (!choice) {
                    System.out.println("Enter input string to search: ");
                    search = sc.nextLine();
                }else {
                    search = MvnJarInstaller.removeWhiteSpace(s);
                    defaultSearch = true;
                }
            }
            Pattern pattern ;
            Matcher matcher;
            try {
                pattern = Pattern.compile(regex);
                matcher = pattern.matcher(search);
            }catch (PatternSyntaxException pse){
                System.err.format("There is a problem" +
                        " with the regular expression!%n");
                System.err.format("The pattern in question is: %s%n",
                        pse.getPattern());
                System.err.format("The description is: %s%n",
                        pse.getDescription());
                System.err.format("The message is: %s%n",
                        pse.getMessage());
                System.err.format("The index is: %s%n",
                        pse.getIndex());
                reserveRegex = false;
                continue;
            }
            boolean found = false;
            System.out.println("search : "+search);
            while (matcher.find()) {
                System.out.format("I found the text \"%s\" staring at index %d and ending at index %d.%n", matcher.group(), matcher.start(), matcher.end());
                found = true;
            }
            if (!found)
                System.out.println("No match found.%n");
            System.out.println("if continue enter search? true or false ");
            reserveRegex = Boolean.parseBoolean(sc.nextLine());
        }
    }
}
