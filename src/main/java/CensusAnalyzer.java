import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyzer {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyzerException {
    try {
        Reader reader = Files.newBufferredReader(Paths.get(csvFilePath));
        CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
        csvToBeanBuilder.withType(IndiaCensusCSV.class);
        csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
        CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
        Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();
        int namOfEateries = 0;
        while (censusCSVIterator.hasNext()) {
            namOfEateries++;
            IndiaCensusCSV censusData = censusCSVIterator.next();
        }
        Iterable<IndiaCensusCSV>csvIterable = () -> censusCSVIterator;
        int numOfEnteries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return namOfEateries;
    }catch (IOException e){
            throw new CensusAnalyzerException(e.getMessage(),
                                    CensusAnalyzerException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    catch (IllegalStateException e) {
        throw new CensusAnalyzerException(e.getMessage(),
                                    CensusAnalyzerException.ExceptionType.UNABLE_TO_PARSE);
    }
    }

}
