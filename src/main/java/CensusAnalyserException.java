public class CensusAnalyserException extends Exception {
    enum ExceptionType {
        CENSUS_FILE_PROBLEM,UNABLE_TO_PARSE;
    }
    ExceptionType type;
    private String message;

    public void CensusAnalyzerException(String message, ExceptionType type){
        super(message);
        this.type = type;
    }
    public void CensusAnalyzerException(String message, ExceptionType type, Throwable cause){
        super(message);
        this.type = type;
    }
}
