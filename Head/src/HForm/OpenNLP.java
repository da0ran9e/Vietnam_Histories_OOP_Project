package HForm;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.util.Span;

public class OpenNLP {
    public static void main(String[] args) {
        String paragraph = "Albert Einstein was born on March 14, 1879, in Ulm, Germany. He was a theoretical physicist.";

        // Load the tokenizer model
        Tokenizer tokenizer = SimpleTokenizer.INSTANCE;
        String[] tokens = tokenizer.tokenize(paragraph);

        try (InputStream modelIn = new FileInputStream("en-ner-person.bin")) {
            // Load the name finder model
            TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
            NameFinderME nameFinder = new NameFinderME(model);

            // Find proper nouns
            Span[] spans = nameFinder.find(tokens);

            // Extract proper nouns from the paragraph
            for (Span span : spans) {
                StringBuilder properNoun = new StringBuilder();
                for (int i = span.getStart(); i < span.getEnd(); i++) {
                    properNoun.append(tokens[i]).append(" ");
                }
                System.out.println("Proper Noun: " + properNoun.toString().trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
