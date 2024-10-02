package org.translation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for this program.
 * Complete the code according to the "to do" notes.<br/>
 * The system will:<br/>
 * - prompt the user to pick a country name from a list<br/>
 * - prompt the user to pick the language they want it translated to from a list<br/>
 * - output the translation<br/>
 * - at any time, the user can type quit to quit the program<br/>
 */
public class Main {

    /**
     * This is the main entry point of our Translation System!<br/>
     * A class implementing the Translator interface is created and passed into a call to runProgram.
     * @param args not used by the program
     */
    public static void main(String[] args) {
        Translator translator = new InLabByHandTranslator();

        runProgram(translator);
    }

    /**
     * This is the method which we will use to test your overall program, since
     * it allows us to pass in whatever translator object that we want!
     * See the class Javadoc for a summary of what the program will do.
     * @param translator the Translator implementation to use in the program
     */
    public static void runProgram(Translator translator) {
        LanguageCodeConverter languageConverter = new LanguageCodeConverter();
        CountryCodeConverter countryConverter = new CountryCodeConverter();
        while (true) {
            String country = promptForCountry(translator);
            final String quit = "quit";
            if (quit.equals(country)) {
                break;
            }
            String countryCode = countryConverter.fromCountry(country);
            String language = promptForLanguage(translator, countryCode);
            if (language.equals(quit)) {
                break;
            }
            String languageCode = languageConverter.fromLanguage(language);
            System.out.println(country + " in " + language + " is " + translator.translate(country, languageCode));
            System.out.println("Press enter to continue or quit to exit.");
            Scanner scanner = new Scanner(System.in);
            String textTyped = scanner.nextLine();

            if (quit.equals(textTyped)) {
                break;
            }
        }
    }

    // Note: CheckStyle is configured so that we don't need javadoc for private methods
    private static String promptForCountry(Translator translator) {
        List<String> countriesCodes = translator.getCountries();
        CountryCodeConverter converter = new CountryCodeConverter();
        List<String> countryNames = new ArrayList<>();
        for (String country : countriesCodes) {
            String countryName = converter.fromCountryCode(country);
            countryNames.add(countryName);
        }
        Collections.sort(countryNames);
        for (String countryName : countryNames) {
            System.out.println(countryName);
        }
        System.out.println("select a country from above:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();

    }

    // Note: CheckStyle is configured so that we don't need javadoc for private methods
    private static String promptForLanguage(Translator translator, String country) {
        List<String> languagesCodes = translator.getCountryLanguages(country);
        LanguageCodeConverter converter = new LanguageCodeConverter();
        List<String> languageNames = new ArrayList<>();
        for (String language : languagesCodes) {
            String languageName = converter.fromLanguageCode(language);
            languageNames.add(languageName);
        }
        Collections.sort(languageNames);
        for (String languageName : languageNames) {
            System.out.println(languageName);
        }
        System.out.println("select a language from above:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
