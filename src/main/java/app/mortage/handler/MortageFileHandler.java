package app.mortage.handler;

import app.mortage.exceptions.IllegalArrayLengthException;
import app.mortage.exceptions.ParsingArrayDataException;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Arrays;

public class MortageFileHandler {
    DataManager manager;

    public MortageFileHandler(DataManager manager){
        this.manager = manager;
    }

    public void readFile(Path path) throws NoSuchFileException {
        boolean firstLineRead = false;
        try (BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset())) {
            String line;
            while ((line = br.readLine()) != null) {

                /*
                 * \\p{L} matches any letter character, including accented characters.
                 * \\p{N} matches any digit character.
                 * \\s matches any white space character
                 * \\, matches any comma
                 * \\. matches any dot
                 * The ^ inside the square brackets means "not"
                 *
                 * Anything that doesn't meet the above conditions is removed.
                 **/
                line = line.replaceAll("[^\\p{L}\\p{N}\\s\\,\\.]", "");
                String[] parts = line.split(",");

                if (firstLineRead && parts.length >= 4) {
                    if (parts.length > 4) {
                        try {
                            parts = parseArray(parts);
                        }
                        catch (ParsingArrayDataException e){
                            System.err.println(e.getMessage());
                        }
                    }
                    try {
                        manager.addMortage(parts);
                    } catch (IllegalArrayLengthException | NumberFormatException e) {
                        System.err.println(e.getMessage());
                    }
                }
                else {
                    firstLineRead = true;
                }
            }
        }
        catch (NoSuchFileException e){
            throw e;
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private String[] parseArray(String[] array) throws ParsingArrayDataException {
        StringBuilder builder = new StringBuilder();
        String[] parsedArray = new String[4];
        int currentIndex = 0, parsedArrayIndex = 1;

        for(String str: array){
            if(!NumberUtils.isParsable(str)){
                builder.append(str);
                builder.append(" ");
                currentIndex++;
            }
            else{
                break;
            }
        }

        parsedArray[0] = builder.toString().trim();
        for(int i = currentIndex; i < array.length; i++){
            if(parsedArrayIndex > 3){
                throw new ParsingArrayDataException("Failed parsing with given array data: " + Arrays.toString(array));
            }
            parsedArray[parsedArrayIndex++] = array[i];
        }
        return parsedArray;
    }
}
