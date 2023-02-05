package app.mortgage.handler;

import app.mortgage.exceptions.IllegalArrayLengthException;
import app.mortgage.exceptions.ParsingArrayDataException;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Arrays;

public class MortgageFileHandler {
    DataManager manager;

    public MortgageFileHandler(DataManager manager){
        this.manager = manager;
    }

    public void readFile(Path path) throws NoSuchFileException {
        //skip first line in input file
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
                 * \\- matches any line
                 * The ^ inside the square brackets means "not"
                 *
                 * Anything that doesn't meet the above conditions is removed.
                 **/
                line = line.replaceAll("[^\\p{L}\\p{N}\\s\\,\\.\\-]", "");
                String[] parts = line.split(",");

                /*If the split generated an array length shorter than 4 then we just skip it because we know its
                * not parsable. Should be logged!!
                */
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
                        manager.addMortgage(parts);
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

    /**
     * Method will try to parse any array length that is longer then 5. The method is naive in the way that it assumes
     * that the name is put in wrong and adds every string that is not parsable to a number from index 0 of the input array.
     * @param array
     * @return
     * @throws ParsingArrayDataException
     */
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
            //Array was not parsable if we try and to the array past a length of 4. Avoid out of bounds and throw a parse error instead.
            if(parsedArrayIndex > 3){
                throw new ParsingArrayDataException("Failed parsing with given array data: " + Arrays.toString(array));
            }
            parsedArray[parsedArrayIndex++] = array[i];
        }
        return parsedArray;
    }
}
